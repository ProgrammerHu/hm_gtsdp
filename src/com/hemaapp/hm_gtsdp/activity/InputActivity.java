package com.hemaapp.hm_gtsdp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpActivity;
import com.hemaapp.hm_gtsdp.R;
/**
 * 输入界面
 * @author Wen
 * @author HuFanglin
 *参数：
 *Title 标题
 *Next 确定
 *InputType 不为-1
 *InputHint 提示
 */
public class InputActivity extends GtsdpActivity {
	private Intent beforeIntent;
	private String title = "";
	private String next = "";
	private String InputHint = "";
	private String Text = "";
	private int InputType = -1;
	private ImageView imageQuitActivity;
	private TextView txtTitle, txtNext;
	private EditText editEmail;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_input_alipay);
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
		imageQuitActivity = (ImageView)findViewById(R.id.imageQuitActivity);
		txtTitle = (TextView)findViewById(R.id.txtTitle);
		txtTitle.setText(title);
		txtNext = (TextView)findViewById(R.id.txtNext);
		txtNext.setText(next);
		editEmail = (EditText)findViewById(R.id.editEmail);
		if(InputType != -1)
		{
			editEmail.setInputType(InputType);
		}
		editEmail.setHint(InputHint);
		editEmail.setText(Text);
	}

	@Override
	protected void getExras() {
		beforeIntent = getIntent();
		if(beforeIntent == null)
			return;
		title = beforeIntent.getStringExtra("Title");
		next = beforeIntent.getStringExtra("Next");
		InputType = beforeIntent.getIntExtra("InputType", -1);
		InputHint = beforeIntent.getStringExtra("InputHint");
		Text = beforeIntent.getStringExtra("Text");
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
				String Account = editEmail.getEditableText().toString().trim();
				Intent result = new Intent();
				result.putExtra("Result", Account);
				setResult(RESULT_OK, result);
				finish(R.anim.none, R.anim.right_out);
			}
		});
	}
	
	@Override
	protected boolean onKeyBack() {
		finish(R.anim.none, R.anim.right_out);
		return super.onKeyBack();
	}

}
