package com.hemaapp.hm_gtsdp.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpActivity;
import com.hemaapp.hm_gtsdp.R;
/**
 * 意见反馈
 * @author Wen
 * @author HuFanglin
 *
 */
public class SuggestionActivity extends GtsdpActivity {
	
	private ImageView imageQuitActivity;
	private TextView txtTitle, txtNext;
	private EditText editSuggestion;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_suggestion);
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
		cancelProgressDialog();
		showTextDialog(arg1.getMsg());
	}

	@Override
	protected void callBackForServerSuccess(HemaNetTask arg0,
			HemaBaseResult arg1) {
		cancelProgressDialog();
		Toast.makeText(mContext, "意见反馈成功", Toast.LENGTH_SHORT).show();
		finish(R.anim.none, R.anim.right_out);
		
	}

	@Override
	protected void callBeforeDataBack(HemaNetTask arg0) {
		showProgressDialog("提交中");
	}

	@Override
	protected void findView() {
		imageQuitActivity = (ImageView)findViewById(R.id.imageQuitActivity);
		txtTitle = (TextView)findViewById(R.id.txtTitle);
		txtTitle.setText("意见反馈");
		txtNext = (TextView)findViewById(R.id.txtNext);
		txtNext.setText("确定");
		editSuggestion = (EditText)findViewById(R.id.editSuggestion);
	}

	@Override
	protected void getExras() {
		
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
				String content = editSuggestion.getEditableText().toString().trim();
				if("".equals(content))
				{
					showTextDialog("请输入您的意见");
				}
				getNetWorker().AddAdvice(getApplicationContext().getUser().getToken(), content);
				showProgressDialog("提交中");
			}
		});
	}
	@Override
	protected boolean onKeyBack() {
		finish(R.anim.none, R.anim.right_out);
		return super.onKeyBack();
	}
}
