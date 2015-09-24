package com.hemaapp.hm_gtsdp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpActivity;

/**
 * 设置密码和重设密码的界面，要注意区分
 * @author Wen
 * @author HuFanglin
 *
 */
public class SetPwdActivity extends GtsdpActivity implements OnClickListener{

	private Intent beforeIntent;
	private int ActivityType;//0: 设置密码; 1: 重设密码
	
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
		beforeIntent = getIntent();
		ActivityType = beforeIntent.getIntExtra("ActivityType", -1);
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
		// TODO Auto-generated method stub
		
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
			showTextDialog("恭喜您！注册成功");
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
