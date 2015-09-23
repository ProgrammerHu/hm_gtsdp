package com.hemaapp.hm_gtsdp.activity;

import java.util.ArrayList;
import java.util.List;

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
	private List<OrderModel> listDatasLeft;
	private List<OrderModel> listDatasRight;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_dispatching);
		super.onCreate(savedInstanceState);
		listDatasLeft = new ArrayList<OrderModel>();
		listDatasLeft.add(new OrderModel("E28924131313543", "none", "2015.5.3 15:23", "济南市历下区", "北京市朝阳区", 500000));
		listDatasLeft.add(new OrderModel("E28924131313545", "none", "2015.5.3 15:23", "呼和浩特市玉泉区", "北京市朝阳区", 500000));
		listDatasLeft.add(new OrderModel("E28924131313546", "none", "2015.5.6 15:23", "济南市历下区", "北京市朝阳区", 50000));
		listDatasLeft.add(new OrderModel("E28924131313549", "none", "2015.5.30 15:23", "济南市历下区", "北京市朝阳区", 5000));
		listDatasLeft.add(new OrderModel("E28924131313553", "none", "2015.5.30 15:23", "济南市历下区", "北京市朝阳区", 5000));
		adapter = new DispatchingListAdapter(mContext, listDatasLeft);
		showListView.setAdapter(adapter);
		
		listDatasRight = new ArrayList<OrderModel>();
		listDatasRight.add(new OrderModel("E28924131313543", "none", "2015.5.3 15:23", "济南市历下区", "北京市朝阳区", 500000));
		listDatasRight.add(new OrderModel("E28924131313545", "none", "2015.5.3 15:23", "呼和浩特市玉泉区", "北京市朝阳区", 500000));
		listDatasRight.add(new OrderModel("E28924131313546", "none", "2015.5.6 15:23", "济南市历下区", "北京市朝阳区", 50000));
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
				showTextDialog("123");
			}
		});
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		if(checkedId == R.id.rbtnLeft)
		{
			adapter.ChangeListData(listDatasLeft);
			adapter.notifyDataSetChanged();
		}
		else
		{
			adapter.ChangeListData(listDatasRight);
			adapter.notifyDataSetChanged();
		}
	}
	
	@Override
	protected boolean onKeyBack() {
		finish(R.anim.none, R.anim.right_out);
		return super.onKeyBack();
	}

}
