package com.hemaapp.hm_gtsdp.activity;

import xtom.frame.util.XtomSharedPreferencesUtil;
import android.app.DownloadManager.Query;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hemaapp.GtsdpConfig;
import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaArrayResult;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpActivity;
import com.hemaapp.hm_gtsdp.GtsdpHttpInformation;
import com.hemaapp.hm_gtsdp.GtsdpUtil;
import com.hemaapp.hm_gtsdp.dialog.GtsdpTwoButtonDialog;
import com.hemaapp.hm_gtsdp.dialog.GtsdpTwoButtonDialog.OnButtonListener;
import com.hemaapp.hm_gtsdp.model.User;
import com.hemaapp.hm_gtsdp.view.SelectPopupWindow;

/**
 * 登录界面
 * @author Wen
 * @author HuFanglin
 *
 */
public class LoginActivity extends GtsdpActivity implements OnClickListener
{
	private EditText editPhone, editPwd;
	private TextView txtRegister, txtForgetPwd;
	private Button btnConfirm;
	private ImageView imageRegister, imageClear;
	private SelectPopupWindow selectFindWay;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_login);
		super.onCreate(savedInstanceState);
		selectFindWay = new SelectPopupWindow(mContext, itemsOnClick, "手机号码找回", "密保找回");
	}

	@Override
	protected void findView() {
		editPhone = (EditText)findViewById(R.id.editPhone);
		editPwd = (EditText)findViewById(R.id.editPwd);
		txtRegister = (TextView)findViewById(R.id.txtRegister);
		txtForgetPwd = (TextView)findViewById(R.id.txtForgetPwd);
		txtForgetPwd.setText(Html.fromHtml("<u>忘记密码</u>？"));
		btnConfirm = (Button)findViewById(R.id.btnConfirm);
		imageRegister = (ImageView)findViewById(R.id.imageRegister);
		imageClear = (ImageView)findViewById(R.id.imageClear);
		
	}

	@Override
	protected void setListener() {
		txtRegister.setOnClickListener(this);
		txtForgetPwd.setOnClickListener(this);
		btnConfirm.setOnClickListener(this);
		imageRegister.setOnClickListener(this);
		imageClear.setOnClickListener(this);
		editPhone.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {}
			@Override
			public void afterTextChanged(Editable s) {
				if(s.toString().length() > 0)
					imageClear.setVisibility(View.VISIBLE);
				else
					imageClear.setVisibility(View.GONE);
			}
		});
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		switch(v.getId())
		{
		case R.id.txtRegister:
		case R.id.imageRegister:
			intent = new Intent(LoginActivity.this, RegisterActivity.class);
			intent.putExtra("ActivityType", 0);//0代表注册
			startActivity(intent);
			overridePendingTransition(R.anim.right_in, R.anim.none);
			break;
		case R.id.txtForgetPwd:
			selectFindWay.showAtLocation(findViewById(R.id.father), Gravity.BOTTOM, 0, 0);
			break;
		case R.id.btnConfirm:
			clickConfirm();
			break;
		case R.id.imageClear:
			editPhone.setText("");//清空手机号
			break;
		}
	}

    /**
     * 点击找回密码
     */
    private OnClickListener itemsOnClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			selectFindWay.dismiss();
			Intent intent;
			switch (v.getId()) {
			case R.id.btnTop:
				intent = new Intent(LoginActivity.this, RegisterActivity.class);
				intent.putExtra("ActivityType", 1);//1代表找回密码
				startActivity(intent);
				overridePendingTransition(R.anim.right_in, R.anim.none);
				break;
			case R.id.btnMiddle:
				intent = new Intent(LoginActivity.this, AnswerQuestionActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.right_in, R.anim.none);
				break;
			}
		}
	};
	
	/**
	 * 点击确定按钮
	 */
	private void clickConfirm()
	{
		String phoneNumber = editPhone.getEditableText().toString();
		String password = editPwd.getEditableText().toString();
		if(!GtsdpUtil.checkPhoneNumber(phoneNumber))
		{//验证手机号是否合法
			showTextDialog("手机号格式不正确！请重输");
			return;
		}
		if(password.length() < 6 || password.length() > 16)
		{
			showTextDialog("请输入6-16位密码");
			return;
		}
		showProgressDialog("登录中，请稍后");
		getNetWorker().clientLogin(phoneNumber, password);
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
	protected void callBackForServerFailed(HemaNetTask netTask, HemaBaseResult result) {
		GtsdpHttpInformation information = (GtsdpHttpInformation)netTask.getHttpInformation();
		switch(information)
		{
		case CLIENT_LOGIN:
			cancelProgressDialog();
			showTextDialog(result.getMsg());
		}
		
	}

	@Override
	protected void callBackForServerSuccess(HemaNetTask netTask,
			HemaBaseResult baseResult) {
		GtsdpHttpInformation information = (GtsdpHttpInformation)netTask.getHttpInformation();
		switch(information)
		{
		case CLIENT_LOGIN:
			HemaArrayResult<User> sUser = (HemaArrayResult<User>)baseResult;
			getApplicationContext().setUser(sUser.getObjects().get(0));
			cancelProgressDialog();
			String username = netTask.getParams().get("username");
			String password = netTask.getParams().get("password");
			XtomSharedPreferencesUtil.save(mContext, "username", username);
			XtomSharedPreferencesUtil.save(mContext, "password", password);
			Intent intent = new Intent(LoginActivity.this, MainPageActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.right_in, R.anim.none);
			this.finish();
			break;
		}
		
	}

	@Override
	protected void callBeforeDataBack(HemaNetTask arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void getExras() {
		// TODO Auto-generated method stub
		
	}

    @Override
    protected boolean onKeyBack() {
		GtsdpTwoButtonDialog dialog = new GtsdpTwoButtonDialog(mContext);
		dialog.setText("确定要退出吗？");
		dialog.setRightButtonTextColor(GtsdpConfig.Main_Blue);
		dialog.setCancelable(true);
		dialog.setButtonListener(new OnButtonListener() {
			@Override
			public void onRightButtonClick(GtsdpTwoButtonDialog dialog) {
				dialog.cancel();
				finish(R.anim.none, R.anim.right_out);
			}

			@Override
			public void onLeftButtonClick(GtsdpTwoButtonDialog dialog) {
				dialog.cancel();
			}
		});
		dialog.show();
		return false;
    }
    
}
