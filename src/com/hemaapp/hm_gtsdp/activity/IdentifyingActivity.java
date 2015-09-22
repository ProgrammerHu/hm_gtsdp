package com.hemaapp.hm_gtsdp.activity;

import com.hemaapp.hm_gtsdp.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * ����н���
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
		((TextView)findViewById(R.id.txtTitle)).setText("����Ա");
		((TextView)findViewById(R.id.txtNext)).setVisibility(View.INVISIBLE);
	}
}
