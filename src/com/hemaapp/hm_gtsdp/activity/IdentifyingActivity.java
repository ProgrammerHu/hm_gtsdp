package com.hemaapp.hm_gtsdp.activity;

import com.hemaapp.hm_gtsdp.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 审核中界面
 * @author Wen
 * @author HuFanglin
 *
 */
public class IdentifyingActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_identified);
		super.onCreate(savedInstanceState);
		findViewById(R.id.imageQuitActivity).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		((TextView)findViewById(R.id.txtTitle)).setText("配送员");
		((TextView)findViewById(R.id.txtNext)).setVisibility(View.INVISIBLE);
	}
}
