package com.hemaapp.hm_gtsdp.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpActivity;
import com.hemaapp.hm_gtsdp.R;

public class ChangePwdActivity2 extends GtsdpActivity {
	private View imageQuitActivity;
	private EditText editNewPwd, editRepeatPwd;
	private TextView txtTitle, txtNext;
	private Button btnConfirm;
	
	private String temp_token;//��ʱ����
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_change_pwd2);
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
		showTextDialog("�������óɹ�");
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
		editNewPwd = (EditText)findViewById(R.id.editNewPwd);
		editRepeatPwd = (EditText)findViewById(R.id.editRepeatPwd);
		txtTitle.setText("�һ�֧��/��������");
		txtNext.setVisibility(View.INVISIBLE);
		btnConfirm = (Button)findViewById(R.id.btnConfirm);
	}

	@Override
	protected void getExras() {
		temp_token = getIntent().getStringExtra("temp_token");
	}

	@Override
	protected void setListener() {
		imageQuitActivity.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish(R.anim.none, R.anim.right_out);
			}
		});
		txtNext.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish(R.anim.none, R.anim.right_out);
			}
		});
		btnConfirm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				doSubmit();
			}
		});
	}
	
	private void doSubmit()
	{
		/*
		 * 1��ȷ���������Ƿ����6-16λ�Ĺ涨
		 * 2��ȷ���¾������Ƿ���ͬ
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
		if(temp_token.equals(""))
		{
			showTextDialog("��ʱ���Ʋ���Ϊ�գ�get out");
			return;
		}
		showProgressDialog("������");
		getNetWorker().resetPwd(temp_token, "2", NewPwd);
	}

}
