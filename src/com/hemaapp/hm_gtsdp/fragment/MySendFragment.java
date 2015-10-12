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
	private int keytype;//业务类型 1:发货订单; 2:收货订单; 
	private int keyid;/*主键id	
	当keytype=1时，keyid=1:运输中,keyid=2:已送达; 
	当keytype=2时，keyid=1:待接收,keyid=2:已收货; */
	
	private int pages = 0;//记录listview的页数
	private GtsdpRefreshLoadmoreLayout refreshLoadmoreLayout;
	private GtsdpListView showListView;
	private RadioGroup radioGroup;
	private RadioButton rbtnLeft;
	private List<OrderModel> listDataOnPassage;//在途
	private List<OrderModel> listDataServed;//已送达
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
			if(pages == 0)
				refreshLoadmoreLayout.refreshSuccess();
			else
				refreshLoadmoreLayout.loadmoreSuccess();
			//到不了啊到不了
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
		rbtnLeft.setChecked(true);
	}

	@Override
	protected void setListener() {
		listDataOnPassage = new ArrayList<OrderModel>();
		listDataOnPassage.add(new OrderModel("E156456115645641756","", "", 11, "1", "收件人的姓名", "收件人的地址", "收件人的电话", "发件人姓名",
				"发件人地址", "发件人电话", "二维码信息", "二维码信息", "订单号"));
		listDataOnPassage.add(new OrderModel("E156456115645641756","", "", 12, "1", "收件人的姓名", "收件人的地址", "收件人的电话", "发件人姓名",
				"发件人地址", "发件人电话", "二维码信息", "二维码信息", "订单号"));
		listDataOnPassage.add(new OrderModel("E156456115645641756","", "", 13, "1", "收件人的姓名", "收件人的地址", "收件人的电话", "发件人姓名",
				"发件人地址", "发件人电话", "二维码信息", "二维码信息", "订单号"));
		
		listDataServed = new ArrayList<OrderModel>();
		listDataServed.add(new OrderModel("E156456115645641756","", "", 10, "1", "收件人的姓名", "收件人的地址", "收件人的电话", "发件人姓名",
				"发件人地址", "发件人电话", "二维码信息", "二维码信息", "订单号"));
		listDataServed.add(new OrderModel("E156456115645641756","", "", 10, "1", "收件人的姓名", "收件人的地址", "收件人的电话", "发件人姓名",
				"发件人地址", "发件人电话", "二维码信息", "二维码信息", "订单号"));
		listDataServed.add(new OrderModel("E156456115645641756","", "", 10, "1", "收件人的姓名", "收件人的地址", "收件人的电话", "发件人姓名",
				"发件人地址", "发件人电话", "二维码信息", "二维码信息", "订单号"));
		listDataServed.add(new OrderModel("E156456115645641756","", "", 10, "1", "收件人的姓名", "收件人的地址", "收件人的电话", "发件人姓名",
				"发件人地址", "发件人电话", "二维码信息", "二维码信息", "订单号"));
		listDataServed.add(new OrderModel("E156456115645641756","", "", 10, "1", "收件人的姓名", "收件人的地址", "收件人的电话", "发件人姓名",
				"发件人地址", "发件人电话", "二维码信息", "二维码信息", "订单号"));
		listDataServed.add(new OrderModel("E156456115645641756","", "", 10, "1", "收件人的姓名", "收件人的地址", "收件人的电话", "发件人姓名",
				"发件人地址", "发件人电话", "二维码信息", "二维码信息", "订单号"));
		listViewAdapter = new OrdersListAdapter(getActivity(), listDataOnPassage);
		showListView.setAdapter(listViewAdapter);
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
			listViewAdapter.changeListData(listDataOnPassage);
			listViewAdapter.notifyDataSetChanged();
			break;
		case R.id.rbtnRight:
			keyid = 2;
			doRequset();
			listViewAdapter.changeListData(listDataServed);
			listViewAdapter.notifyDataSetChanged();
			break;
		}
		
	}
	
	/**
	 * 上拉刷新和下拉加载的监听
	 * @author Wen
	 * @author HuFanglin
	 *
	 */
	private class StartListener implements OnStartListener {

		@Override
		public void onStartRefresh(XtomRefreshLoadmoreLayout v) {// 刷新
			pages = 0;
			doRequset();
		}

		@Override
		public void onStartLoadmore(XtomRefreshLoadmoreLayout v) {// 加载更多
			pages++;
			doRequset();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
		if (rbtnLeft.isSelected()) {
			intent.putExtra("keyid", listDataOnPassage.get(position).getTrade_no());
		} else {
			intent.putExtra("keyid", listDataServed.get(position).getTrade_no());
		}
		intent.putExtra("keytype", "1");
		startActivity(intent);
		getActivity().overridePendingTransition(R.anim.right_in, R.anim.none);

	}
	/**
	 * 发起请求，在onResume时发起
	 */
	private void doRequset()
	{
		showProgressDialog("数据获取中");
		getNetWorker().getTransList(Activity.getApplicationContext().getUser().getToken(), 
				String.valueOf(keytype), String.valueOf(keyid), String.valueOf(pages));
	}

}
