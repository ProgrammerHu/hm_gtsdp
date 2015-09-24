package com.hemaapp.hm_gtsdp.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpActivity;
import com.hemaapp.hm_gtsdp.GtsdpUtil;
import com.hemaapp.hm_gtsdp.R;

public class AboutUsActivity extends GtsdpActivity {
	private View imageQuitActivity;
	private ImageView imageMain;
	private TextView txtTitle, txtNext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_aboutus);
		super.onCreate(savedInstanceState);
		imageMain.setImageBitmap(GtsdpUtil.getImageFromAssetsFile("about_us.png", mContext));
		
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
		imageQuitActivity = findViewById(R.id.imageQuitActivity);
		txtTitle = (TextView)findViewById(R.id.txtTitle);
		txtNext = (TextView)findViewById(R.id.txtNext);
		txtTitle.setText("关于我们");
		txtNext.setVisibility(View.INVISIBLE);
		imageMain = (ImageView)findViewById(R.id.imageMain);
	}

	@Override
	protected void getExras() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setListener() {
		imageQuitActivity.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish(R.anim.none, R.anim.right_out);
			}
		});
		
	}

}
