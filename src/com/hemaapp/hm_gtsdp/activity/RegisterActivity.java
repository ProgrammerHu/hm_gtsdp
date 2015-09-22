package com.hemaapp.hm_gtsdp.activity;

import u.aly.ch;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
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
 * 注册界面，找回密码的界面也使用这个界面
 * @author Wen
 * @author HuFanglin
 *
 */
public class RegisterActivity extends GtsdpActivity implements OnClickListener{
	private final String actionStr = "验证码已经发送到 ";
	private int activityType;//标记是找回密码还是注册，0：注册；1：找回密码；其他都不对
	
	private Intent beforeIntent;
	
	private ImageView imageQuitActivity,imageClear;
	private TextView txtTitle, txtNext, txtRemainingTime, txtDeclare, txtActionStr;
	private Button btnSendIdentifyCode;
	private LinearLayout linearRemainingTime;
	private EditText editRegisterPhone, editCode;
	private CheckBox checkAgree;
	
	private GtsdpCountDownTimer myCount;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_register);
		super.onCreate(savedInstanceState);
		if(activityType != 0 && activityType != 1)
		{
			showTextDialog("界面使用错误");
		}
	}
	

	@Override
	protected void findView() {
		beforeIntent = getIntent();
		activityType = beforeIntent.getIntExtra("activityType", -1);
		txtTitle = (TextView)findViewById(R.id.txtTitle);
		txtNext = (TextView)findViewById(R.id.txtNext);
		txtRemainingTime = (TextView)findViewById(R.id.txtRemainingTime);
		txtDeclare = (TextView)findViewById(R.id.txtDeclare);
		txtDeclare.setText(Html.fromHtml("<u>注册声明</u>"));
		txtActionStr = (TextView)findViewById(R.id.txtActionStr);
		imageQuitActivity = (ImageView)findViewById(R.id.imageQuitActivity);
		imageClear = (ImageView)findViewById(R.id.imageClear);
		if(activityType == 0)
		{//改界面是注册
			txtTitle.setText("注册");
			txtNext.setText("下一步");
		}
		btnSendIdentifyCode = (Button)findViewById(R.id.btnSendIdentifyCode);
		linearRemainingTime = (LinearLayout)findViewById(R.id.linearRemainingTime);
		editRegisterPhone = (EditText)findViewById(R.id.editRegisterPhone);
		editCode = (EditText)findViewById(R.id.editCode);
		checkAgree = (CheckBox)findViewById(R.id.checkAgree);
	}

	@Override
	protected void getExras() {}

	@Override
	protected void setListener() {
		txtNext.setOnClickListener(this);
		imageQuitActivity.setOnClickListener(this);
		btnSendIdentifyCode.setOnClickListener(this);
		txtDeclare.setOnClickListener(this);
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
		case R.id.txtDeclare:
			showTextDialog("去注册声明");
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
		if(activityType == 0)
		{//注册
			if(!checkAgree.isChecked())
			{
				showTextDialog("请同意注册声明");
				return;
			}
			//TODO 加下来要验证验证码和手机号是否有效
		}
		Intent intent = new Intent(RegisterActivity.this, FixDataActivity.class);
		startActivity(intent);
		overridePendingTransition(R.anim.right_in, R.anim.none);
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
	protected boolean onKeyBack() {
		finish(R.anim.none, R.anim.right_out);
		return super.onKeyBack();
	}

}
