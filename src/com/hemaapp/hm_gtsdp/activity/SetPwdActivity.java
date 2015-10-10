package com.hemaapp.hm_gtsdp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hemaapp.hm_gtsdp.GtsdpHttpInformation;
import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaArrayResult;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpActivity;

/**
 * 设置密码和重设密码的界面，要注意区分
 * @author Wen
 * @author HuFanglin
 *
 */
public class SetPwdActivity extends GtsdpActivity implements OnClickListener{
	private final int SET_PWD = 0;//设置密码
	private final int RESET_PWD = 1;//重设密码
	private Intent beforeIntent;
	private String username;
	private int ActivityType;//0: 设置密码; 1: 重设密码
	private String temp_token;//临时令牌
	
	private TextView txtTitle, txtNext;
	private ImageView imageQuitActivity;
	private EditText editPwd, editCheckPwd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_set_pwd);
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void findView() {
		txtTitle = (TextView)findViewById(R.id.txtTitle);
		editCheckPwd = (EditText)findViewById(R.id.editCheckPwd);
		editPwd = (EditText)findViewById(R.id.editPwd);
		if(ActivityType == 0)
			txtTitle.setText("设置密码");
		else if(ActivityType == 1)
		{
			txtTitle.setText("重设密码");
			editCheckPwd.setHint("新密码");
			editPwd.setHint("确认新密码");
		}
		else
			showTextDialog("界面调用参数错误");
		txtNext = (TextView)findViewById(R.id.txtNext);
		txtNext.setText("提交");
		imageQuitActivity = (ImageView)findViewById(R.id.imageQuitActivity);
		
		
	}

	@Override
	protected void getExras() {
		beforeIntent = getIntent();
		ActivityType = beforeIntent.getIntExtra("ActivityType", -1);
		username = beforeIntent.getStringExtra("username");
		temp_token = beforeIntent.getStringExtra("temp_token");
	}

	@Override
	protected void setListener() {
		imageQuitActivity.setOnClickListener(this);
		txtNext.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
		case R.id.imageQuitActivity:
			finish(R.anim.none, R.anim.right_out);
			break;
		case R.id.txtNext:
			String pwd = editPwd.getEditableText().toString();
			String checkPwd = editCheckPwd.getEditableText().toString();
			if(pwd.length() < 6 || pwd.length() > 16)
			{
				showTextDialog("请输入6-16位密码");
				return;
			}
			if(!pwd.equals(checkPwd))
			{
				showTextDialog("密码输入不一致，请重新输入");
				return;
			}
			if(username == null || username.equals(""))
			{
				showTextDialog("没传手机号，亲");
				return;
			}
			if(ActivityType == RESET_PWD)
			{//这是重设密码
				getNetWorker().resetPwd(temp_token, "1", editPwd.getEditableText().toString());
			}
			else
			{//注册 直接把密码带到详细信息界面
				Intent intent = new Intent(this, FixDataActivity.class);
				intent.putExtra("username", username);
				intent.putExtra("temp_token", temp_token);
				intent.putExtra("password", pwd);
				startActivity(intent);
				overridePendingTransition(R.anim.right_in, R.anim.none);
				finish();
			}
			break;
		}
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
	protected void callBackForServerSuccess(HemaNetTask netTask,
			HemaBaseResult baseResult) {
		GtsdpHttpInformation infomation = (GtsdpHttpInformation)netTask.getHttpInformation();
		switch (infomation) {
		case PASSWORD_RESET:
			cancelProgressDialog();
			if(ActivityType == RESET_PWD)
			{
				Toast.makeText(mContext, "密码重置成功", Toast.LENGTH_SHORT).show();
				finish(R.anim.none, R.anim.right_out);
			}
			break;
		}
		
	}

	@Override
	protected void callBeforeDataBack(HemaNetTask netTask) {
		GtsdpHttpInformation information = (GtsdpHttpInformation)netTask.getHttpInformation();
		switch (information) {
		case CODE_VERIFY:
			showProgressDialog("提交中，请稍后");
			break;
		}
	}
	
	@Override
	protected boolean onKeyBack() {
		finish(R.anim.none, R.anim.right_out);
		return super.onKeyBack();
	}

}
