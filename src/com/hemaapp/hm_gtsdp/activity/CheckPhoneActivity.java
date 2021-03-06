package com.hemaapp.hm_gtsdp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpActivity;
import com.hemaapp.hm_gtsdp.GtsdpCountDownTimer;
import com.hemaapp.hm_gtsdp.GtsdpHttpInformation;
import com.hemaapp.hm_gtsdp.GtsdpUtil;
import com.hemaapp.hm_gtsdp.R;

public class CheckPhoneActivity extends GtsdpActivity implements OnClickListener{
	
	private ImageView imageQuitActivity;
	private TextView txtTel, txtRemainingTime, editPhone;
	private Button btnSendIdentifyCode, btnConfirm;
	private LinearLayout linearRemainingTime;
	private EditText editCode;
	
	private GtsdpCountDownTimer myCount;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_check_phone);
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
	protected void callBackForServerFailed(HemaNetTask netTask, HemaBaseResult baseResult) {
		cancelProgressDialog();
		showTextDialog(baseResult.getMsg());
	}

	@Override
	protected void callBackForServerSuccess(HemaNetTask netTask,
			HemaBaseResult baseResult) {
		cancelProgressDialog();
		GtsdpHttpInformation information = (GtsdpHttpInformation)netTask.getHttpInformation();
		switch (information) {
		case CODE_GET:
			myCount = new GtsdpCountDownTimer(61000, 1000, txtRemainingTime, btnSendIdentifyCode, linearRemainingTime);
			myCount.start();
			break;
		case CODE_VERIFY:
			Intent intent = new Intent(this, SetQuestionActivty.class);
			startActivity(intent);
			overridePendingTransition(R.anim.right_in, R.anim.none);
			this.finish();
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
		txtTel = (TextView)findViewById(R.id.txtTel);	
		txtRemainingTime = (TextView)findViewById(R.id.txtRemainingTime);
		btnSendIdentifyCode = (Button)findViewById(R.id.btnSendIdentifyCode);
		btnConfirm = (Button)findViewById(R.id.btnConfirm);
		linearRemainingTime = (LinearLayout)findViewById(R.id.linearRemainingTime);
		editPhone = (TextView)findViewById(R.id.editPhone);
		String username = getApplicationContext().getUser().getUsername();
		int length = username.length();
		username = username.substring(0, 3)+"****"+username.substring(7, length);
		
		editPhone.setText(username);
		editCode = (EditText)findViewById(R.id.editCode);
	}

	@Override
	protected void getExras() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setListener() {
		imageQuitActivity.setOnClickListener(this);
		txtTel.setOnClickListener(this);
		btnSendIdentifyCode.setOnClickListener(this);
		btnConfirm.setOnClickListener(this);
	}
	
	@Override
	protected boolean onKeyBack() {
		finish(R.anim.none, R.anim.right_out);
		return super.onKeyBack();
	}

	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
		case R.id.imageQuitActivity:
			finish(R.anim.none, R.anim.right_out);
			break;
		case R.id.txtTel:
		{
			  Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:05384456325"));
              startActivity(intent);
		}
			break;
		case R.id.btnSendIdentifyCode:
			SendIdentifyCode();
			break;
		case R.id.btnConfirm:
		{
			String code = editCode.getEditableText().toString().trim();
			if(code.equals(""))
			{
				showTextDialog("请输入验证码");
				return;
			}
			getNetWorker().CodeVerify(getApplicationContext().getUser().getUsername(), code);
			showProgressDialog("验证中");
		}
			break;
		}
		
	}

	/**
	 * 点击发送验证码
	 */
	private void SendIdentifyCode()
	{//发送验证码时的链接应该要根据不同的界面而不同
		linearRemainingTime.setVisibility(View.VISIBLE);
		btnSendIdentifyCode.setVisibility(View.GONE);
		getNetWorker().codeGet(getApplicationContext().getUser().getUsername());
		showProgressDialog("验证码发送中");
	}
	

}
