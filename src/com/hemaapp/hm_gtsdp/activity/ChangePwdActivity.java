package com.hemaapp.hm_gtsdp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.hemaapp.GtsdpConfig;
import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpActivity;
import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_gtsdp.R.id;

public class ChangePwdActivity extends GtsdpActivity implements OnClickListener{
	
	private Intent beforeIntent;
	private int ActivityType;
	private View imageQuitActivity;
	private EditText editOldPwd, editNewPwd, editRepeatPwd;
	private TextView txtTitle, txtNext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_change_pwd);
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
		imageQuitActivity = findViewById(R.id.imageQuitActivity);
		txtTitle = (TextView)findViewById(R.id.txtTitle);
		txtNext = (TextView)findViewById(R.id.txtNext);
		editOldPwd = (EditText)findViewById(R.id.editOldPwd);
		editNewPwd = (EditText)findViewById(R.id.editNewPwd);
		editRepeatPwd = (EditText)findViewById(R.id.editRepeatPwd);
		if(ActivityType == GtsdpConfig.CHANGE_LOGIN_PWD)
		{
			txtTitle.setText("�޸ĵ�¼����");
			txtNext.setText("����");
		}
		else if(ActivityType == GtsdpConfig.CHANGE_PAY_PWD)
		{
			txtTitle.setText("�޸���������");
			txtNext.setText("����");
		}
	}

	@Override
	protected void getExras() {
		beforeIntent = getIntent();
		ActivityType = beforeIntent.getIntExtra("ActivityType", -1);
		if(ActivityType == -1)
			showTextDialog("������ô���");
	}

	@Override
	protected void setListener() {
		imageQuitActivity.setOnClickListener(this);
		txtNext.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.imageQuitActivity)
		{
			finish(R.anim.none, R.anim.right_out);
		}
		else if(v.getId() == R.id.txtNext)
		{
			doSubmit();
		}
	}
	@Override
	protected boolean onKeyBack() {
		finish(R.anim.none, R.anim.right_out);
		return super.onKeyBack();
	}
	
	private void doSubmit()
	{
		/*1��ȷ���������Ƿ�Ϊ��
		 * 2��ȷ���������Ƿ����6-16λ�Ĺ涨
		 * 3��ȷ���¾������Ƿ���ͬ
		 */
		String NewPwd = editNewPwd.getEditableText().toString().trim();
		String RepeatPwd = editRepeatPwd.getEditableText().toString().trim();
		if("".equals(NewPwd))
		{
			showTextDialog("������������");
			return;
		}
		if(NewPwd.length() < 6 || NewPwd.length() > 16)
		{
			showTextDialog("������6-16λ������");
			return;
		}
		if(!NewPwd.equals(RepeatPwd))
		{
			showTextDialog("�������벻һ�£�����������");
			return;
		}
		showTextDialog("OK");
	}
}
