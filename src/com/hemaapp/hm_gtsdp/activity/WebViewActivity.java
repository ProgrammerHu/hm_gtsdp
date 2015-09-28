package com.hemaapp.hm_gtsdp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpActivity;
import com.hemaapp.hm_gtsdp.R;

public class WebViewActivity extends GtsdpActivity{
	private Intent beforeIntent;
	private String Title;
	
	private String Baidu="https://www.baidu.com/";
	private WebView webview;
	private ImageView imageQuitActivity;
	private TextView txtTitle;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_webview);
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
	protected void onResume() {
		webview.onResume();
		super.onResume();
	}

	@Override
	protected void onPause() {
		webview.onPause();
		super.onPause();
	}
	@Override
	protected void findView() {
		webview = (WebView)findViewById(R.id.webview);
		webview.loadUrl(Baidu);
		imageQuitActivity = (ImageView)findViewById(R.id.imageQuitActivity);
		txtTitle = (TextView)findViewById(R.id.txtTitle);
		txtTitle.setText(Title);
	}

	@Override
	protected void getExras() {
		beforeIntent = getIntent();
		Title = beforeIntent.getStringExtra("Title");
		
	}

	@Override
	protected void setListener() {
		imageQuitActivity.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				webview.goBack();
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
