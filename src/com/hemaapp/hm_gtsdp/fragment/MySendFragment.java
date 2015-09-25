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
import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_gtsdp.activity.OrderDetailActivity;
import com.hemaapp.hm_gtsdp.adapter.OrdersListAdapter;
import com.hemaapp.hm_gtsdp.model.OrderModel;
import com.hemaapp.hm_gtsdp.view.GtsdpListView;
import com.hemaapp.hm_gtsdp.view.GtsdpRefreshLoadmoreLayout;

public class MySendFragment extends GtsdpFragment implements OnCheckedChangeListener,
OnItemClickListener{
	private int pageNumber;//记录当前fragment的页码
	
	private int pages = 0;//记录listview的页数
	private GtsdpRefreshLoadmoreLayout refreshLoadmoreLayout;
	private GtsdpListView showListView;
	private RadioGroup radioGroup;
	private RadioButton rbtnLeft;
	private List<OrderModel> listDataOnPassage;//在途
	private List<OrderModel> listDataServed;//已送达
	private OrdersListAdapter listViewAdapter;
	public MySendFragment(int pageNumber)
	{
		super();
		this.pageNumber = pageNumber;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.fragment_send);
		super.onCreate(savedInstanceState);
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
	protected void callBackForServerFailed(HemaNetTask arg0, HemaBaseResult arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void callBackForServerSuccess(HemaNetTask arg0,
			HemaBaseResult arg1) {
		// TODO Auto-generated method stub
		
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
		listDataOnPassage.add(new OrderModel("E156456115645641756", "山东济南营业网点", "2015-8-21 10:13"));
		listDataOnPassage.add(new OrderModel("E123542353453423426", "运输中", "2015-9-20 10:13"));
		listDataOnPassage.add(new OrderModel("E855625851111111116", "运输中", "2015-9-21 10:13"));
		
		listDataServed = new ArrayList<OrderModel>();
		listDataServed.add(new OrderModel("E156456115645641756", "已接收", "2015-8-21 10:13"));
		listDataServed.add(new OrderModel("E123542353453423426", "已接收", "2015-9-20 10:13"));
		listDataServed.add(new OrderModel("E855625851111111116", "已接收", "2015-9-21 10:13"));
		listDataServed.add(new OrderModel("E855625851111111116", "已接收", "2015-9-21 10:13"));
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
			listViewAdapter.changeListData(listDataOnPassage);
			listViewAdapter.notifyDataSetChanged();
			break;
		case R.id.rbtnRight:
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
			showTextDialog("上拉");
		}

		@Override
		public void onStartLoadmore(XtomRefreshLoadmoreLayout v) {// 加载更多
			showTextDialog("下拉");
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
		if (rbtnLeft.isSelected()) {
			intent.putExtra("Id", listDataOnPassage.get(position).getOrderId());
		} else {
			intent.putExtra("Id", listDataServed.get(position).getOrderId());
		}
		startActivity(intent);
		getActivity().overridePendingTransition(R.anim.right_in, R.anim.none);

	}

}
