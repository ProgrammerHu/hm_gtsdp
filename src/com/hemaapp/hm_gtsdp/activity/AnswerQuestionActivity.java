package com.hemaapp.hm_gtsdp.activity;

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

/**
 * 回答问题界面
 * @author Wen
 * @author HuFanglin
 *
 */
public class AnswerQuestionActivity extends GtsdpActivity implements OnClickListener, OnItemSelectedListener {

    private static final String[] question={"请选择", "你最喜欢的菜是什么？","你最喜欢的颜色？",
    	"你爸爸叫什么名字？","你妈妈叫什么名字？","你的家乡在哪里？", "你的大学叫什么？"};
	private TextView txtTitle, txtNext;
	private ImageView imageQuitActivity;
	private Spinner spinner1, spinner2, spinner3;
    private ArrayAdapter<QuestionModel> adapter, adapter1, adapter2, adapter3;
    private QuestionModel tempModel1, tempModel2, tempModel3;
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
					String.valueOf(spinner1.getSelectedItemPosition()), 
					editText1.getEditableText().toString().trim(), 
					String.valueOf(spinner2.getSelectedItemPosition()), 
					editText2.getEditableText().toString().trim(), 
					String.valueOf(spinner3.getSelectedItemPosition()), 
					editText3.getEditableText().toString().trim());
			break;
		case PASSWORD_ASK_CHECK:
			Toast.makeText(this, "验证成功", Toast.LENGTH_SHORT).show();
			break;
		}
		
	}

	@Override
	protected void callBeforeDataBack(HemaNetTask arg0) {
		// TODO Auto-generated method stub
		
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
        adapter = new ArrayAdapter<QuestionModel>(this,android.R.layout.simple_spinner_item);
        adapter1 = new ArrayAdapter<QuestionModel>(this,android.R.layout.simple_spinner_item);
        adapter2 = new ArrayAdapter<QuestionModel>(this,android.R.layout.simple_spinner_item);
        adapter3 = new ArrayAdapter<QuestionModel>(this,android.R.layout.simple_spinner_item);
        adapter.add(new QuestionModel("0", "请选择"));
        adapter.add(new QuestionModel("1", "你最喜欢的菜是什么？"));
        adapter.add(new QuestionModel("2", "你最喜欢的颜色？"));
        adapter.add(new QuestionModel("3", "你爸爸叫什么名字？"));
        adapter.add(new QuestionModel("4", "你妈妈叫什么名字？"));
        adapter.add(new QuestionModel("5", "你的家乡在哪里？"));
        adapter.add(new QuestionModel("6", "你的大学叫什么？"));

        adapter1.add(new QuestionModel("0", "请选择"));
        adapter1.add(new QuestionModel("1", "你最喜欢的菜是什么？"));
        adapter1.add(new QuestionModel("2", "你最喜欢的颜色？"));
        adapter1.add(new QuestionModel("3", "你爸爸叫什么名字？"));
        adapter1.add(new QuestionModel("4", "你妈妈叫什么名字？"));
        adapter1.add(new QuestionModel("5", "你的家乡在哪里？"));
        adapter1.add(new QuestionModel("6", "你的大学叫什么？"));

        adapter2.add(new QuestionModel("0", "请选择"));
        adapter2.add(new QuestionModel("1", "你最喜欢的菜是什么？"));
        adapter2.add(new QuestionModel("2", "你最喜欢的颜色？"));
        adapter2.add(new QuestionModel("3", "你爸爸叫什么名字？"));
        adapter2.add(new QuestionModel("4", "你妈妈叫什么名字？"));
        adapter2.add(new QuestionModel("5", "你的家乡在哪里？"));
        adapter2.add(new QuestionModel("6", "你的大学叫什么？"));

        adapter3.add(new QuestionModel("0", "请选择"));
        adapter3.add(new QuestionModel("1", "你最喜欢的菜是什么？"));
        adapter3.add(new QuestionModel("2", "你最喜欢的颜色？"));
        adapter3.add(new QuestionModel("3", "你爸爸叫什么名字？"));
        adapter3.add(new QuestionModel("4", "你妈妈叫什么名字？"));
        adapter3.add(new QuestionModel("5", "你的家乡在哪里？"));
        adapter3.add(new QuestionModel("6", "你的大学叫什么？"));
        //设置下拉列表的风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner3.setAdapter(adapter);
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
//			Intent intent = new Intent(this, SetPwdActivity.class);
//			intent.putExtra("ActivityType", 1);
//			startActivity(intent);
//			overridePendingTransition(R.anim.right_in, R.anim.none);
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
			return;
		}
		switch (parent.getId()) {
		case R.id.spinner1:
			tempModel1 = adapter1.getItem(position);
			adapter2.remove(tempModel1);
			break;
		case R.id.spinner2:
			tempModel2 = adapter2.getItem(position);
			break;
		case R.id.spinner3:
			tempModel3 = adapter3.getItem(position);
			break;
		}
		showTextDialog(""+adapter.getItem(position).getId());
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
	}

	private class QuestionModel
	{
		public QuestionModel(String id, String name)
		{
			this.id = id;
			this.name = name;
		}
		public String id;
		public String name;
		@Override
		public String toString() {
			return  name;
		}
		public int getId()
		{
			return Integer.parseInt(id);
		}
	}
}
