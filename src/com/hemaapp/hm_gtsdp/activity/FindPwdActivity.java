package com.hemaapp.hm_gtsdp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
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
import com.hemaapp.hm_gtsdp.GtsdpUtil;
import com.hemaapp.hm_gtsdp.R;
/**
 * 找回支付密码界面
 * @author Wen
 * @author HuFanglin
 *
 */
public class FindPwdActivity extends GtsdpActivity implements OnClickListener {
	private final String actionStr = "验证码已经发送到 ";
	
	private ImageView imageQuitActivity,imageClear;
	private TextView txtTitle, txtNext, txtRemainingTime, txtActionStr;
	private Button btnSendIdentifyCode;
	private LinearLayout linearRemainingTime;
	private EditText editRegisterPhone, editCode;

	private GtsdpCountDownTimer myCount;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_findpwd);
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
		txtTitle = (TextView)findViewById(R.id.txtTitle);
		txtNext = (TextView)findViewById(R.id.txtNext);
		txtRemainingTime = (TextView)findViewById(R.id.txtRemainingTime);
		txtActionStr = (TextView)findViewById(R.id.txtActionStr);
		imageQuitActivity = (ImageView)findViewById(R.id.imageQuitActivity);
		imageClear = (ImageView)findViewById(R.id.imageClear);
		btnSendIdentifyCode = (Button)findViewById(R.id.btnSendIdentifyCode);
		linearRemainingTime = (LinearLayout)findViewById(R.id.linearRemainingTime);
		editRegisterPhone = (EditText)findViewById(R.id.editRegisterPhone);
		editCode = (EditText)findViewById(R.id.editCode);
		
	}

	@Override
	protected void getExras() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setListener() {
		txtNext.setOnClickListener(this);
		imageQuitActivity.setOnClickListener(this);
		btnSendIdentifyCode.setOnClickListener(this);
		imageClear.setOnClickListener(this);
		editRegisterPhone.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {}
			@Override
			public void afterTextChanged(Editable s) {
				if(s.length() > 0)
				{
					imageClear.setVisibility(View.VISIBLE);
				}
				else
				{
					imageClear.setVisibility(View.GONE);
				}
			}
		});
		
	}

	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
		case R.id.txtNext:
			clickNext();
			break;
		case R.id.imageQuitActivity:
			finish(R.anim.none, R.anim.right_out);
			break;
		case R.id.btnSendIdentifyCode:
			SendIdentifyCode();
			break;
		case R.id.imageClear:
			editRegisterPhone.setText("");
			break;
		}
		
		
	}
	
	/**
	 * 点击发送验证码
	 */
	private void SendIdentifyCode()
	{//发送验证码时的链接应该要根据不同的界面而不同
		String phoneNumber = editRegisterPhone.getEditableText().toString();
		if(!GtsdpUtil.checkPhoneNumber(phoneNumber))
		{
			showTextDialog("手机号格式不正确！");
			return;
		}
		phoneNumber = phoneNumber.substring(0, 3) + "****" + phoneNumber.substring(phoneNumber.length() - 4, phoneNumber.length());
		txtActionStr.setText(actionStr + phoneNumber);
		linearRemainingTime.setVisibility(View.VISIBLE);
		btnSendIdentifyCode.setVisibility(View.GONE);
		//修改提示信息，应该修改到收到服务器结果时
//		txtActionStr.setText("验证码已发送到" + phoneNumber.substring(0,3)+ "****" + phoneNumber.substring(phoneNumber.length() - 4, phoneNumber.length()));
		myCount = new GtsdpCountDownTimer(61000, 1000, txtRemainingTime, btnSendIdentifyCode, linearRemainingTime, txtActionStr);
		myCount.start();
	}
	
	/**
	 * 点击下一步，要区分类型
	 */
	private void clickNext()
	{
		Intent intent = new Intent(FindPwdActivity.this, FixDataActivity.class);
		startActivity(intent);
		overridePendingTransition(R.anim.right_in, R.anim.none);
	}
	
}
