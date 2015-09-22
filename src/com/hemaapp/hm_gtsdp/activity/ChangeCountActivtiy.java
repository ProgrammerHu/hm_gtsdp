package com.hemaapp.hm_gtsdp.activity;

import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpActivity;
import com.hemaapp.hm_gtsdp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ChangeCountActivtiy extends GtsdpActivity {
	EditText editCount;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_changecount);
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
		((TextView)findViewById(R.id.txtTitle)).setText("½ð¶î");
		((TextView)findViewById(R.id.txtNext)).setText("È·¶¨");
		editCount = (EditText)findViewById(R.id.editCount);
	}

	@Override
	protected void getExras() {
	}

	@Override
	protected void setListener() {
		((ImageView)findViewById(R.id.imageQuitActivity)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish(R.anim.none, R.anim.right_out);
			}
		});
		
		((TextView)findViewById(R.id.txtNext)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(!"".equals(editCount.getEditableText().toString()))
				{
					Intent result = new Intent();
					final double count = Double.parseDouble(editCount.getEditableText().toString());
					result.putExtra("Count", count);
					setResult(RESULT_OK, result);
				}
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
