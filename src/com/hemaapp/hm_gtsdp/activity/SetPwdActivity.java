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
 * �����������������Ľ��棬Ҫע������
 * @author Wen
 * @author HuFanglin
 *
 */
public class SetPwdActivity extends GtsdpActivity implements OnClickListener{
	private final int SET_PWD = 0;//��������
	private final int RESET_PWD = 1;//��������
	private Intent beforeIntent;
	private String username;
	private int ActivityType;//0: ��������; 1: ��������
	private String temp_token;//��ʱ����
	
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
			txtTitle.setText("��������");
		else if(ActivityType == 1)
		{
			txtTitle.setText("��������");
			editCheckPwd.setHint("������");
			editPwd.setHint("ȷ��������");
		}
		else
			showTextDialog("������ò�������");
		txtNext = (TextView)findViewById(R.id.txtNext);
		txtNext.setText("�ύ");
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
				showTextDialog("������6-16λ����");
				return;
			}
			if(!pwd.equals(checkPwd))
			{
				showTextDialog("�������벻һ�£�����������");
				return;
			}
			if(username == null || username.equals(""))
			{
				showTextDialog("û���ֻ��ţ���");
				return;
			}
			if(ActivityType == RESET_PWD)
			{//������������
				getNetWorker().resetPwd(temp_token, "1", editPwd.getEditableText().toString());
			}
			else
			{//ע�� ֱ�Ӱ����������ϸ��Ϣ����
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
				Toast.makeText(mContext, "�������óɹ�", Toast.LENGTH_SHORT).show();
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
			showProgressDialog("�ύ�У����Ժ�");
			break;
		}
	}
	
	@Override
	protected boolean onKeyBack() {
		finish(R.anim.none, R.anim.right_out);
		return super.onKeyBack();
	}

}
