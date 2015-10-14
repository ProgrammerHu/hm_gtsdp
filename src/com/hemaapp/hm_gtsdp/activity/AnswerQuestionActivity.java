package com.hemaapp.hm_gtsdp.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import xtom.frame.exception.DataParseException;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpActivity;
import com.hemaapp.hm_gtsdp.GtsdpArrayResult;
import com.hemaapp.hm_gtsdp.GtsdpHttpInformation;
import com.hemaapp.hm_gtsdp.GtsdpUtil;
import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_gtsdp.model.QuestionModel;
import com.hemaapp.hm_gtsdp.model.User;

/**
 * 回答问题界面
 * @author Wen
 * @author HuFanglin
 *
 */
public class AnswerQuestionActivity extends GtsdpActivity implements OnClickListener {

	private TextView txtTitle, txtNext;
	private ImageView imageQuitActivity;
	private TextView spinner1, spinner2, spinner3;
    private ArrayAdapter<QuestionModel> adapter1, adapter2, adapter3;
    private QuestionModel tempModel1, tempModel2, tempModel3;
    private List<QuestionModel> list1, list2, list3, listTemp;
    private Button btnConfirm;
    private EditText editPhone, editText1, editText2, editText3;
    private String id1, id2, id3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_question);
		super.onCreate(savedInstanceState);
		findViewById(R.id.layoutPhone).setVisibility(View.VISIBLE);
	}

	@Override
	protected void callAfterDataBack(HemaNetTask arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void callBackForGetDataFailed(HemaNetTask netTask, int arg1) {
		
		
	}

	@Override
	protected void callBackForServerFailed(HemaNetTask netTask, HemaBaseResult baseResult) {
		cancelProgressDialog();
		GtsdpHttpInformation information = (GtsdpHttpInformation)netTask.getHttpInformation();
		switch (information) {
		case CLIENT_VERIFY:
			showTextDialog(baseResult.getMsg());
			break;
		case PASSWORD_ASK_CHECK:
			showTextDialog("验证失败");
			break;
		case PASSWORD_ASK_LIST:
			showTextDialog(baseResult.getMsg());
			break;
		}
		
	}

	@Override
	protected void callBackForServerSuccess(HemaNetTask netTask,
			HemaBaseResult baseResult) {
		GtsdpHttpInformation information = (GtsdpHttpInformation)netTask.getHttpInformation();
		switch (information) {
		case CLIENT_VERIFY:
			getNetWorker().checkAsk(editPhone.getEditableText().toString(), 
					id1, editText1.getEditableText().toString().trim(), 
					id2, editText2.getEditableText().toString().trim(), 
					id3, editText3.getEditableText().toString().trim());
			break;
		case PASSWORD_ASK_CHECK:
			cancelProgressDialog();
			Toast.makeText(this, "验证成功", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(this, SetPwdActivity.class);
			intent.putExtra("ActivityType", 1);
			intent.putExtra("username", editPhone.getEditableText().toString().trim());
			startActivity(intent);
			overridePendingTransition(R.anim.right_in, R.anim.none);
			finish();
			break;
	case PASSWORD_ASK_LIST:
			cancelProgressDialog();
			GtsdpArrayResult<QuestionModel> result = (GtsdpArrayResult<QuestionModel>)baseResult;
			List<QuestionModel> list = result.getObjects();
			if(list == null || list.size() <= 0)
			{
				showTextDialog("您的账号没有设置密保问题");
				return;
			}
			spinner1.setText(list.get(0).name);
			id1 = list.get(0).id;
			spinner2.setText(list.get(1).name);
			id2 = list.get(1).id;
			spinner3.setText(list.get(2).name);
			id3 = list.get(2).id;
			break;
		}
		
	}

	@Override
	protected void callBeforeDataBack(HemaNetTask netTask) {

		GtsdpHttpInformation information = (GtsdpHttpInformation)netTask.getHttpInformation();
		switch (information) 
		{
		case CLIENT_VERIFY:
			showProgressDialog("验证中");
			break;
		
		}
		
	}

	@Override
	protected void findView() {
		txtTitle = (TextView)findViewById(R.id.txtTitle);
		txtTitle.setText("回答问题");
		txtNext = (TextView)findViewById(R.id.txtNext);
		txtNext.setVisibility(View.INVISIBLE);
		imageQuitActivity = (ImageView)findViewById(R.id.imageQuitActivity);
		spinner1 = (TextView)findViewById(R.id.spinner1); 
		spinner2 = (TextView)findViewById(R.id.spinner2); 
		spinner3 = (TextView)findViewById(R.id.spinner3); 
        btnConfirm = (Button)findViewById(R.id.btnConfirm);
        editText1 = (EditText)findViewById(R.id.editText1);
        editText2 = (EditText)findViewById(R.id.editText2);
        editText3 = (EditText)findViewById(R.id.editText3);
        editPhone = (EditText)findViewById(R.id.editPhone);
	}

	@Override
	protected void getExras() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setListener() {
		imageQuitActivity.setOnClickListener(this);
		txtNext.setOnClickListener(this);
		btnConfirm.setOnClickListener(this);
		editPhone.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				String username = editPhone.getEditableText().toString().trim();
				if(username != null && username.length() == 11)
				{
					showProgressDialog("获取密保问题");
					getNetWorker().getPwdAskList("2", username);
				}
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			@Override
			public void afterTextChanged(Editable s) {
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
		case R.id.imageQuitActivity:
			finish(R.anim.none, R.anim.right_out);
			break;
		case R.id.btnConfirm:
			clickConfirm();
			break;
		}
	}
	
	private void clickConfirm()
	{
		if(GtsdpUtil.checkPhoneNumber(editPhone.getEditableText().toString()))
		{
			if(id1 == null || "".equals(id1))
			{
				showTextDialog("您的账号没有设置密保问题");
				return;
			}
			getNetWorker().clientVerify(editPhone.getEditableText().toString());
		}
		else
		{
			showTextDialog("手机号输入不合法");
		}
	}

}
