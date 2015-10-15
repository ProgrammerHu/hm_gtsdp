package com.hemaapp.hm_gtsdp.activity;

import xtom.frame.util.XtomSharedPreferencesUtil;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.hemaapp.hm_gtsdp.GtsdpHttpInformation;
import com.hemaapp.hm_gtsdp.GtsdpNetWorker;
import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaArrayResult;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpActivity;
import com.hemaapp.hm_gtsdp.model.SysInitInfo;
import com.hemaapp.hm_gtsdp.model.User;
import com.hemaapp.hm_gtsdp.push.PushUtils;
import com.hemaapp.hm_gtsdp.result.SiteListResult;

/**
 * 初始页
 * @author Wen
 * @author HuFanglin
 *
 */
public class StartActivity extends GtsdpActivity
{
	private SysInitInfo sysInitInfo;
	private User user;
	public TextView txtWelcome;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_start);
		super.onCreate(savedInstanceState);
		sysInitInfo = getApplicationContext().getSysInitInfo();
		user = getApplicationContext().getUser();
		txtWelcome = (TextView)findViewById(R.id.txtWelcome);
		init();
	}
	private void init()
	{
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.logo);
		animation.setAnimationListener(new StartAnimationListener());
		txtWelcome.startAnimation(animation);
	}
	
	private class StartAnimationListener implements AnimationListener {

		@Override
		public void onAnimationStart(Animation animation) {
		}

		@Override
		public void onAnimationEnd(Animation animation) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						Thread.sleep(0);
					} 
					catch (InterruptedException e) {
						e.printStackTrace();
					}
					GtsdpNetWorker netWorker = getNetWorker();
					netWorker.init();
				}
			}).start();
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
		}
	}

	@Override
	protected void callAfterDataBack(HemaNetTask arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void callBackForGetDataFailed(HemaNetTask arg0, int arg1) {
		showTextDialog("系统初始化失败，请确定网络参数");
	}

	@Override
	protected void callBackForServerFailed(HemaNetTask netTask, HemaBaseResult result) {
//		showTextDialog("系统初始化失败，请确定网络参数");
		GtsdpHttpInformation infomation = (GtsdpHttpInformation)netTask.getHttpInformation();
		switch(infomation)
		{
		case CLIENT_LOGIN:
			if(result.getError_code() == 102)
			{
				Toast.makeText(StartActivity.this, result.getMsg() + "请重新登录", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(StartActivity.this, LoginActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.right_in, R.anim.none);
				this.finish();
			}
			
		}
			
	}

	@Override
	protected void callBackForServerSuccess(HemaNetTask netTask,
			HemaBaseResult baseResult) {
		GtsdpHttpInformation infomation = (GtsdpHttpInformation)netTask.getHttpInformation();
		switch(infomation)
		{
		case INIT:
			HemaArrayResult<SysInitInfo> sResult = (HemaArrayResult<SysInitInfo>)baseResult;
			sysInitInfo = sResult.getObjects().get(0);
			getApplicationContext().setSysInitInfo(sysInitInfo);
			getNetWorker().getSiteList();
			break;
		case CLIENT_LOGIN:
			HemaArrayResult<User> sUser = (HemaArrayResult<User>)baseResult;
			this.user = sUser.getObjects().get(0);
			getApplicationContext().setUser(user);
			Intent intent = new Intent(StartActivity.this, MainPageActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.right_in, R.anim.none);
			this.finish();
			break;
		case SITE_LIST:
			SiteListResult result = (SiteListResult)baseResult;
			XtomSharedPreferencesUtil.save(mContext, "provinces", result.getProvinces());
			XtomSharedPreferencesUtil.save(mContext, "sites", result.getSites());
			checkLogin();
			break;
		}
	}
	/**
	 * 跳转到登录界面
	 */
	private void toLogin() {
		Intent it = new Intent(this, LoginActivity.class);
		startActivity(it);
		finish();
	}
	/**
	 * 判断是否保存了登录信息
	 */
	private void checkLogin() {
		if (isAutoLogin()) {
			String username = XtomSharedPreferencesUtil.get(this, "username");
			String password = XtomSharedPreferencesUtil.get(this, "password");
			if (!isNull(username) && !isNull(password)) {
				GtsdpNetWorker netWorker = getNetWorker();
				netWorker.clientLogin(username, password);
				
			} else {
				toLogin();
			}
		} else {
			toLogin();
		}
	}

	/**
	 * 检查是否自动登录
	 * @return
	 */
	private boolean isAutoLogin() {
		String autoLogin = XtomSharedPreferencesUtil.get(mContext, "autoLogin");
		boolean no = "no".equals(autoLogin);
		return !no;
	}

	@Override
	protected void callBeforeDataBack(HemaNetTask arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void findView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void getExras() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		
	}

	
}
