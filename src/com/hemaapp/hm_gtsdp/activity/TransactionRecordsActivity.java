package com.hemaapp.hm_gtsdp.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpActivity;
import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_gtsdp.adapter.TransactionListAdapter;
import com.hemaapp.hm_gtsdp.model.TransactionModel;
import com.hemaapp.hm_gtsdp.view.GtsdpListView;
import com.hemaapp.hm_gtsdp.view.GtsdpRefreshLoadmoreLayout;

/**
 * 交易记录列表界面
 * @author Wen
 * @author HuFanglin
 *
 */
public class TransactionRecordsActivity extends GtsdpActivity {
	private List<TransactionModel> listDatas;
	private TransactionListAdapter adapter;
	private ImageView imageQuitActivity;
	private GtsdpRefreshLoadmoreLayout refreshLoadmoreLayout;
	private GtsdpListView showListView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_transaction_records);
		super.onCreate(savedInstanceState);
		listDatas = new ArrayList<TransactionModel>();
		listDatas.add(new TransactionModel("发货", "2015.9.22 13:27:54", 250, false));
		listDatas.add(new TransactionModel("银联充值", "2015.9.22 13:27:54", 250, true));
		listDatas.add(new TransactionModel("支付宝充值", "2015.9.22 13:27:54", 250, true));
		listDatas.add(new TransactionModel("配送", "2015.9.22 13:27:54", 250, true));
		adapter = new TransactionListAdapter(mContext, listDatas);
		showListView.setAdapter(adapter);
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
		imageQuitActivity = (ImageView)findViewById(R.id.imageQuitActivity);
		((TextView)findViewById(R.id.txtTitle)).setText("交易记录");
		findViewById(R.id.txtNext).setVisibility(View.INVISIBLE);
		showListView = (GtsdpListView)findViewById(R.id.showListView);
		refreshLoadmoreLayout = (GtsdpRefreshLoadmoreLayout)findViewById(R.id.refreshLoadmoreLayout);
	}

	@Override
	protected void getExras() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setListener() {
		imageQuitActivity.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish(R.anim.none, R.anim.right_out);
			}
		});
	}
	@Override
	protected boolean onKeyBack() {
		finish(R.anim.none, R.anim.right_out);
		return super.onKeyBack();
	}

}
