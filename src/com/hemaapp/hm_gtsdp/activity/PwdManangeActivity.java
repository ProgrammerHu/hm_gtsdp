package com.hemaapp.hm_gtsdp.activity;

import org.w3c.dom.Text;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.hemaapp.GtsdpConfig;
import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpActivity;
import com.hemaapp.hm_gtsdp.R;

/**
 * 密码管理界面
 * @author Wen
 * @author HuFanglin
 *
 */
public class PwdManangeActivity extends GtsdpActivity implements OnClickListener {
	private View imageQuitActivity, layoutTop, layoutMiddle, layoutBottom;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_pwd_manage);
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void findView() {
		((TextView)findViewById(R.id.txtTitle)).setText("密码管理");
		findViewById(R.id.txtNext).setVisibility(View.INVISIBLE);
		imageQuitActivity = findViewById(R.id.imageQuitActivity);
		layoutTop = findViewById(R.id.layoutTop); 
		layoutMiddle = findViewById(R.id.layoutMiddle);
		layoutBottom = findViewById(R.id.layoutBottom);
		
	}

	@Override
	protected void getExras() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setListener() {
		imageQuitActivity.setOnClickListener(this);
		layoutTop.setOnClickListener(this);
		layoutMiddle.setOnClickListener(this);
		layoutBottom.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		switch(v.getId())
		{
		case R.id.imageQuitActivity:
			finish(R.anim.none, R.anim.right_out);
			break;
		case R.id.layoutTop:
			intent = new Intent(PwdManangeActivity.this, ChangePwdActivity.class);
			intent.putExtra("ActivityType", GtsdpConfig.CHANGE_LOGIN_PWD);
			startActivity(intent);
			overridePendingTransition(R.anim.right_in, R.anim.none);
			break;
		case R.id.layoutMiddle:
			intent = new Intent(PwdManangeActivity.this, ChangePwdActivity.class);
			intent.putExtra("ActivityType", GtsdpConfig.CHANGE_PAY_PWD);
			startActivity(intent);
			overridePendingTransition(R.anim.right_in, R.anim.none);
			break;
		case R.id.layoutBottom:
			intent = new Intent(PwdManangeActivity.this, FindPwdActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.right_in, R.anim.none);
			break;
		}
		
	}
	@Override
	protected boolean onKeyBack() {
		finish(R.anim.none, R.anim.right_out);
		return super.onKeyBack();
	}
	@Override
	protected void callAfterDataBack(HemaNetTask arg0) {
	}

	@Override
	protected void callBackForGetDataFailed(HemaNetTask arg0, int arg1) {
	}

	@Override
	protected void callBackForServerFailed(HemaNetTask arg0, HemaBaseResult arg1) {
	}

	@Override
	protected void callBackForServerSuccess(HemaNetTask arg0,
			HemaBaseResult arg1) {
	}

	@Override
	protected void callBeforeDataBack(HemaNetTask arg0) {
	}


}
