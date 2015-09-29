package com.hemaapp.hm_gtsdp.activity;

import org.xbill.DNS.RPRecord;

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
import com.hemaapp.hm_gtsdp.GtsdpApplication;
import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_gtsdp.model.SysInitInfo;

public class WebViewActivity extends GtsdpActivity{
	private final String MIDDLE = "webview/parm/";
	private Intent beforeIntent;
	private String Title;
	
	private String Baidu="https://www.baidu.com/";
	private WebView webview;
	private ImageView imageQuitActivity;
	private TextView txtTitle;
	
	private String AboutUsUrl;//关于我们
	private String ProtocalUrl;//注册协议
	
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
		imageQuitActivity = (ImageView)findViewById(R.id.imageQuitActivity);
		txtTitle = (TextView)findViewById(R.id.txtTitle);
		txtTitle.setText(Title);
		if("关于我们".equals(Title))
		{
			webview.loadUrl(AboutUsUrl);
		}
		else if("注册声明".equals(Title))
		{
			webview.loadUrl(ProtocalUrl);
		}
		else
		{
			webview.loadUrl(Baidu);
		}
	}

	@Override
	protected void getExras() {
		beforeIntent = getIntent();
		Title = beforeIntent.getStringExtra("Title");
		
		GtsdpApplication application = GtsdpApplication.getInstance();
		SysInitInfo info = application.getSysInitInfo();
		AboutUsUrl = info.getSys_web_service() + MIDDLE + "aboutus";
		ProtocalUrl = info.getSys_web_service() + MIDDLE + "protocal";
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
