package com.hemaapp.hm_gtsdp.fragment;

import java.util.ArrayList;
import java.util.List;

import xtom.frame.view.XtomRefreshLoadmoreLayout;
import xtom.frame.view.XtomRefreshLoadmoreLayout.OnStartListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpArrayResult;
import com.hemaapp.hm_gtsdp.GtsdpFragment;
import com.hemaapp.hm_gtsdp.GtsdpFragmentActivity;
import com.hemaapp.hm_gtsdp.GtsdpHttpInformation;
import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_gtsdp.activity.OrderDetailActivity;
import com.hemaapp.hm_gtsdp.adapter.OrdersListAdapter;
import com.hemaapp.hm_gtsdp.model.OrderModel;
import com.hemaapp.hm_gtsdp.view.GtsdpListView;
import com.hemaapp.hm_gtsdp.view.GtsdpRefreshLoadmoreLayout;

public class MySendFragment extends GtsdpFragment implements OnCheckedChangeListener,
OnItemClickListener{
	private GtsdpFragmentActivity Activity;
	private int keytype;//ҵ������ 1:��������; 2:�ջ�����; 
	private int keyid;/*����id	
	��keytype=1ʱ��keyid=1:������,keyid=2:���ʹ�; 
	��keytype=2ʱ��keyid=1:������,keyid=2:���ջ�; */
	
	private int pages = 0;//��¼listview��ҳ��
	private GtsdpRefreshLoadmoreLayout refreshLoadmoreLayout;
	private GtsdpListView showListView;
	private RadioGroup radioGroup;
	private RadioButton rbtnLeft;
	private RadioButton rbtnRight;
	private List<OrderModel> listData;
	private OrdersListAdapter listViewAdapter;
	public MySendFragment(int pageNumber, GtsdpFragmentActivity activity)
	{
		super();
		this.keytype = pageNumber;
		this.Activity = activity;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.fragment_send);
		super.onCreate(savedInstanceState);
		keyid = 1;
		listData = new ArrayList<OrderModel>();
		listViewAdapter = new OrdersListAdapter(Activity, listData);
		showListView.setAdapter(listViewAdapter);
		doRequset();
	}

	@Override
	protected void callAfterDataBack(HemaNetTask arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void callBackForGetDataFailed(HemaNetTask arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void callBackForServerFailed(HemaNetTask netTask, HemaBaseResult baseResult) {
		GtsdpHttpInformation information = (GtsdpHttpInformation)netTask.getHttpInformation();
		switch(information)
		{
		case TRANS_LIST:
			cancelProgressDialog();
			if(pages == 0)
				refreshLoadmoreLayout.refreshFailed();
			else
				refreshLoadmoreLayout.loadmoreFailed();
			showTextDialog(baseResult.getMsg());
			break;
		}
	}

	@Override
	protected void callBackForServerSuccess(HemaNetTask netTask, 
			HemaBaseResult baseResult) {
		GtsdpHttpInformation information = (GtsdpHttpInformation)netTask.getHttpInformation();
		switch(information)
		{
		case TRANS_LIST:
			cancelProgressDialog();
			GtsdpArrayResult<OrderModel> result = (GtsdpArrayResult<OrderModel>)baseResult;
			List<OrderModel> listResult = result.getObjects();
			if(pages == 0)
			{
				listData = listResult;
				listViewAdapter.setListData(listData);
				listViewAdapter.notifyDataSetChanged();
				refreshLoadmoreLayout.refreshSuccess();
			}
			else
			{
				listData.addAll(listResult);
				listViewAdapter.setListData(listData);
				listViewAdapter.notifyDataSetChanged();
				refreshLoadmoreLayout.loadmoreSuccess();
			}

			if(listResult.size() < 20)
			{
				refreshLoadmoreLayout.setLoadmoreable(false);
			}
			else
				refreshLoadmoreLayout.setLoadmoreable(true);
			break;
		}
		
	}

	@Override
	protected void callBeforeDataBack(HemaNetTask arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void findView() {
		refreshLoadmoreLayout = (GtsdpRefreshLoadmoreLayout)findViewById(R.id.refreshLoadmoreLayout);
		showListView = (GtsdpListView)findViewById(R.id.showListView);
		radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
		rbtnLeft = (RadioButton)findViewById(R.id.rbtnLeft);
		rbtnRight = (RadioButton)findViewById(R.id.rbtnRight);
		if(keytype == 1)
		{
			rbtnLeft.setText("������");
			rbtnRight.setText("���ʹ�");
		}
		else if(keytype == 2)
		{
			rbtnLeft.setText("������");
			rbtnRight.setText("���ջ�");
		}
		rbtnLeft.setChecked(true);
	}

	@Override
	protected void setListener() {
		radioGroup.setOnCheckedChangeListener(this);
		refreshLoadmoreLayout.setOnStartListener(new StartListener());
		showListView.setOnItemClickListener(this);
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch(checkedId)
		{
		case R.id.rbtnLeft:
			keyid = 1;
			doRequset();
			break;
		case R.id.rbtnRight:
			keyid = 2;
			doRequset();
			break;
		}
		
	}
	
	/**
	 * ����ˢ�º��������صļ���
	 * @author Wen
	 * @author HuFanglin
	 *
	 */
	private class StartListener implements OnStartListener {

		@Override
		public void onStartRefresh(XtomRefreshLoadmoreLayout v) {// ˢ��
			pages = 0;
			doRequset();
		}

		@Override
		public void onStartLoadmore(XtomRefreshLoadmoreLayout v) {// ���ظ���
			pages++;
			doRequset();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
		intent.putExtra("keytype", "1");
		intent.putExtra("keyid",listData.get(position).getId());
		startActivity(intent);
		getActivity().overridePendingTransition(R.anim.right_in, R.anim.none);

	}
	/**
	 * ����������onResumeʱ����
	 */
	private void doRequset()
	{
		showProgressDialog("���ݻ�ȡ��");
		getNetWorker().getTransList(Activity.getApplicationContext().getUser().getToken(), 
				String.valueOf(keytype), String.valueOf(keyid), String.valueOf(pages));
	}

}
