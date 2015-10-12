package com.hemaapp.hm_gtsdp.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import xtom.frame.exception.DataParseException;
import android.content.Intent;
import android.os.Bundle;
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
import android.widget.Toast;

import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpActivity;
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
public class AnswerQuestionActivity extends GtsdpActivity implements OnClickListener, OnItemSelectedListener {

	private TextView txtTitle, txtNext;
	private ImageView imageQuitActivity;
	private Spinner spinner1, spinner2, spinner3;
    private ArrayAdapter<QuestionModel> adapter1, adapter2, adapter3;
    private QuestionModel tempModel1, tempModel2, tempModel3;
    private List<QuestionModel> list1, list2, list3, listTemp;
    private Button btnConfirm;
    private EditText editPhone, editText1, editText2, editText3;
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
	protected void callBackForGetDataFailed(HemaNetTask arg0, int arg1) {
		// TODO Auto-generated method stub
		
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
		}
		
	}

	@Override
	protected void callBackForServerSuccess(HemaNetTask netTask,
			HemaBaseResult baseResult) {
		GtsdpHttpInformation information = (GtsdpHttpInformation)netTask.getHttpInformation();
		switch (information) {
		case CLIENT_VERIFY:
			getNetWorker().checkAsk(editPhone.getEditableText().toString(), 
					String.valueOf(((QuestionModel)spinner1.getSelectedItem()).getId()), 
					editText1.getEditableText().toString().trim(), 
					String.valueOf(((QuestionModel)spinner2.getSelectedItem()).getId()),   
					editText2.getEditableText().toString().trim(), 
					String.valueOf(((QuestionModel)spinner3.getSelectedItem()).getId()), 
					editText3.getEditableText().toString().trim());
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
		}
		
	}

	@Override
	protected void callBeforeDataBack(HemaNetTask netTask) {

		GtsdpHttpInformation information = (GtsdpHttpInformation)netTask.getHttpInformation();
		switch (information) 
		{
		case CLIENT_VERIFY:
			showProgressDialog("验证中，请稍后");
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
		spinner1 = (Spinner)findViewById(R.id.spinner1); 
		spinner2 = (Spinner)findViewById(R.id.spinner2); 
		spinner3 = (Spinner)findViewById(R.id.spinner3); 
		//将可选内容与ArrayAdapter连接起来
        adapter1 = new ArrayAdapter<QuestionModel>(this,android.R.layout.simple_spinner_item);
        adapter2 = new ArrayAdapter<QuestionModel>(this,android.R.layout.simple_spinner_item);
        adapter3 = new ArrayAdapter<QuestionModel>(this,android.R.layout.simple_spinner_item);
        list1 = new ArrayList<QuestionModel>();
        list1.add(new QuestionModel("0", "请选择"));
        list1.add(new QuestionModel("1", "你最喜欢的菜是什么？"));
        list1.add(new QuestionModel("2", "你最喜欢的颜色？"));
        list1.add(new QuestionModel("3", "你爸爸叫什么名字？"));
        list1.add(new QuestionModel("4", "你妈妈叫什么名字？"));
        list1.add(new QuestionModel("5", "你的家乡在哪里？"));
        list1.add(new QuestionModel("6", "你的大学叫什么？"));
        list2 = new ArrayList<QuestionModel>();
        list3 = new ArrayList<QuestionModel>();
        listTemp = new ArrayList<QuestionModel>();
        for(QuestionModel model : list1)
        {
        	adapter1.add(model);
        	adapter2.add(model);
        	adapter3.add(model);
        	list2.add(model);
        	list3.add(model);
        	listTemp.add(model);
        }

        //设置下拉列表的风格
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);
        spinner3.setAdapter(adapter3);
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
		spinner1.setOnItemSelectedListener(this);
		spinner2.setOnItemSelectedListener(this);
		spinner3.setOnItemSelectedListener(this);
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
			getNetWorker().clientVerify(editPhone.getEditableText().toString());
		}
		else
		{
			showTextDialog("手机号输入不合法");
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		if(position == 0)
		{
			if(tempModel1 == null && tempModel2 == null && tempModel3 == null)
				return;
			switch (parent.getId()) 
			{
			case R.id.spinner1:
				ResetAdapter(adapter2, list2, tempModel3);
				ResetAdapter(adapter3, list3, tempModel2);
				break;
			case R.id.spinner2:
				ResetAdapter(adapter1, list1, tempModel3);
				ResetAdapter(adapter3, list3, tempModel1);
				break;
			case R.id.spinner3:
				ResetAdapter(adapter1, list1, tempModel2);
				ResetAdapter(adapter2, list2, tempModel1);
				break;
			}
		}
		else
		{
			switch (parent.getId()) {
			case R.id.spinner1:
				tempModel1 = adapter1.getItem(position);
				ResetAdapter(adapter2, list2, tempModel3);
				ResetAdapter(adapter3, list3, tempModel2);
				list2.remove(tempModel1);
				list3.remove(tempModel1);
				setAdapter(adapter2, list2);
				setAdapter(adapter3, list3);
				break;
			case R.id.spinner2:
				tempModel2 = adapter2.getItem(position);
				ResetAdapter(adapter1, list1, tempModel3);
				ResetAdapter(adapter3, list3, tempModel1);
				list1.remove(tempModel2);
				list3.remove(tempModel2);
				setAdapter(adapter1, list1);
				setAdapter(adapter3, list3);
				break;
			case R.id.spinner3:
				tempModel3 = adapter3.getItem(position);
				ResetAdapter(adapter1, list1, tempModel2);
				ResetAdapter(adapter2, list2, tempModel1);
				list1.remove(tempModel3);
				list2.remove(tempModel3);
				setAdapter(adapter1, list1);
				setAdapter(adapter2, list2);
				break;
			}
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
	}
	/**
	 * 点击请选择重置适配器
	 * @param adapter
	 * @param list
	 */
	private void ResetAdapter(ArrayAdapter<QuestionModel> adapter, List<QuestionModel> list, QuestionModel tempModel)
	{
		list.clear();
		for(QuestionModel model : listTemp)
		{
			list.add(model);
		}
		if(tempModel != null)
		{
			list.remove(tempModel);
		}
		setAdapter(adapter, list);
		tempModel = null;
	}
	/**
	 * 选择时设置适配器
	 * @param adapter
	 * @param list
	 */
	private void setAdapter(ArrayAdapter<QuestionModel> adapter, List<QuestionModel> list)
	{
		adapter.clear();
		for(QuestionModel model : list)
		{
			adapter.add(model);
		}
	}
}
