package com.hemaapp.hm_gtsdp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpActivity;
import com.hemaapp.hm_gtsdp.R;
/**
 * 不是配送员
 * @author Wen
 * @author HuFanglin
 *
 */
public class NotCursorActivity extends GtsdpActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_notcursor);
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
		findViewById(R.id.imageQuitActivity).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish(R.anim.none, R.anim.right_out);
			}
		});
		((TextView)findViewById(R.id.txtTitle)).setText("找货");
		findViewById(R.id.txtNext).setVisibility(View.INVISIBLE);
		findViewById(R.id.btnCommit).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO 去申请认证界面
				Intent intent = new Intent(NotCursorActivity.this, CourierIdentificationActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.right_in, R.anim.none);
				finish();
			}
		});
	}

	@Override
	protected void getExras() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		
	}

}
