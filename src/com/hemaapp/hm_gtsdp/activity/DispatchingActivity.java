package com.hemaapp.hm_gtsdp.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpActivity;
import com.hemaapp.hm_gtsdp.GtsdpArrayResult;
import com.hemaapp.hm_gtsdp.GtsdpHttpInformation;
import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_gtsdp.adapter.DispatchingListAdapter;
import com.hemaapp.hm_gtsdp.model.OrderModel;
import com.hemaapp.hm_gtsdp.view.GtsdpListView;
import com.hemaapp.hm_gtsdp.view.GtsdpRefreshLoadmoreLayout;

/**
 * 配送订单列表界面
 * @author Wen
 * @author HuFanglin
 *
 */
public class DispatchingActivity extends GtsdpActivity implements OnCheckedChangeListener{
	private final int LEFT = 100;
	private final int RIGHT=200;
	
	private ImageView imageQuitActivity;
	private GtsdpRefreshLoadmoreLayout refreshLoadmoreLayout;
	private GtsdpListView showListView;
	private RadioGroup radioGroup;
	private RadioButton rbtnLeft;
	private DispatchingListAdapter adapter;
	private List<OrderModel> listDatas;
	
	private String keytype;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_dispatching);
		super.onCreate(savedInstanceState);
		keytype = "1";
		getNetWorker().getDeliveryOrderList(getApplicationContext().getUser().getToken(), keytype);
		showProgressDialog("数据获取中");
		listDatas = new ArrayList<OrderModel>();
		
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
		cancelProgressDialog();
		showTextDialog(baseResult.getMsg());
	}

	@Override
	protected void callBackForServerSuccess(HemaNetTask netTask,
			HemaBaseResult baseResult) {
		GtsdpHttpInformation information = (GtsdpHttpInformation)netTask.getHttpInformation();
		switch (information) {
		case DELIVERY_ORDER_LIST:
			cancelProgressDialog();
			GtsdpArrayResult<OrderModel> result = (GtsdpArrayResult<OrderModel>)baseResult;
			listDatas = result.getObjects();
			adapter = new DispatchingListAdapter(mContext, listDatas);
			showListView.setAdapter(adapter);
			break;

		default:
			break;
		}
	}

	@Override
	protected void callBeforeDataBack(HemaNetTask arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void findView() {
		imageQuitActivity = (ImageView)findViewById(R.id.imageQuitActivity);
		((TextView)findViewById(R.id.txtTitle)).setText("配送订单");
		findViewById(R.id.txtNext).setVisibility(View.INVISIBLE);
		radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
		rbtnLeft = (RadioButton)findViewById(R.id.rbtnLeft);
		rbtnLeft.setChecked(true);
		showListView = (GtsdpListView)findViewById(R.id.showListView);
	}

	@Override
	protected void getExras() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setListener() {
		radioGroup.setOnCheckedChangeListener(this);
		imageQuitActivity.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish(R.anim.none, R.anim.right_out);
			}
		});
		showListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(DispatchingActivity.this, OrderDetailActivity.class);
				intent.putExtra("keytype", "1");
				intent.putExtra("keyid", listDatas.get(position).getId());
				startActivity(intent);
				overridePendingTransition(R.anim.right_in, R.anim.none);

			}
		});
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		if(checkedId == R.id.rbtnLeft)
		{
			keytype = "1";
			getNetWorker().getDeliveryOrderList(getApplicationContext().getUser().getToken(), keytype);
			showProgressDialog("数据获取中");
		}
		else
		{
			keytype = "2";
			getNetWorker().getDeliveryOrderList(getApplicationContext().getUser().getToken(), keytype);
			showProgressDialog("数据获取中");
		}
	}
	
	@Override
	protected boolean onKeyBack() {
		finish(R.anim.none, R.anim.right_out);
		return super.onKeyBack();
	}

}
