package com.hemaapp.hm_gtsdp.activity;

import java.lang.reflect.Field;
import java.util.List;

import xtom.frame.exception.DataParseException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.amap.api.mapcore.ba;
import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.hemaapp.GtsdpConfig;
import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpArrayResult;
import com.hemaapp.hm_gtsdp.GtsdpFragmentActivity;
import com.hemaapp.hm_gtsdp.GtsdpHttpInformation;
import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_gtsdp.adapter.ImageViewPagerAdapter;
import com.hemaapp.hm_gtsdp.dialog.GtsdpTwoButtonDialog;
import com.hemaapp.hm_gtsdp.dialog.GtsdpTwoButtonDialog.OnButtonListener;
import com.hemaapp.hm_gtsdp.model.AdvertiseModel;
import com.hemaapp.hm_gtsdp.model.User;
import com.hemaapp.hm_gtsdp.push.PushUtils;
import com.hemaapp.hm_gtsdp.result.NoticeCountResult;

public class MainPageActivity extends GtsdpFragmentActivity implements OnClickListener {
	private int transflag;//配送员状态 0：不是配送员；1：是配送员
	private int checkflag;//配送员审核状态 0：未审核；1：已审核
	private int role;//身份	1：普通用户；2：网点；
	private TextView txtTitle, txtNext;
	private ImageView imageQuitActivity, pointsImage, imageSend, imageGet, imageFind, imageBell;
	private ViewPager mainViewPager;
	private int PageId;
	private LinearLayout UserCenter, SystemMessage, layoutPoint;
	private List<AdvertiseModel> listData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_mainpage);
		super.onCreate(savedInstanceState);
		startPush();
		try
		{
			transflag = Integer.parseInt(getApplicationContext().getUser().getTransflag());
			checkflag = Integer.parseInt(getApplicationContext().getUser().getCheckflag());
			role = Integer.parseInt(getApplicationContext().getUser().getRole());
		}
		catch(NumberFormatException e)
		{
			showTextDialog("用户身份异常");
		}
		getNetWorker().getAdList();
		getNetWorker().getNoticeCount(getApplicationContext().getUser().getToken());
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
	protected void callBackForServerSuccess(HemaNetTask netTask,
			HemaBaseResult baseResult) {
		GtsdpHttpInformation information = (GtsdpHttpInformation)netTask.getHttpInformation();
		switch (information) {
		case AD_LIST:
		{
			GtsdpArrayResult<AdvertiseModel> result = (GtsdpArrayResult<AdvertiseModel>)baseResult;
			listData= result.getObjects();
			mainViewPager.setAdapter(new ImageViewPagerAdapter(getSupportFragmentManager(), 
					MainPageActivity.this, listData));
			addImageView(listData);
			PageId = listData.size() * 10;
	    	switchTask.run();
		}
			break;
		case NOTICE_COUNT:
		{
			NoticeCountResult result = (NoticeCountResult)baseResult;
			if(result.getSysCount() > 0)
				imageBell.setImageResource(R.drawable.bell_message);
		}
			break;
		}
		
	}

	@Override
	protected void callBeforeDataBack(HemaNetTask arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void findView() {
		 DisplayMetrics dm = new DisplayMetrics();
	     getWindowManager().getDefaultDisplay().getMetrics(dm);
	     int mScreenWidth = dm.widthPixels;
		txtTitle = (TextView)findViewById(R.id.txtTitle);
		txtTitle.setText("首页");
		txtNext = (TextView)findViewById(R.id.txtNext);
		txtNext.setVisibility(View.INVISIBLE);
		imageQuitActivity = (ImageView)findViewById(R.id.imageQuitActivity);
		imageQuitActivity.setVisibility(View.INVISIBLE);
		mainViewPager = (ViewPager)findViewById(R.id.mainViewPager);
		RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)mainViewPager.getLayoutParams();
		params.height = mScreenWidth * 4 / 7;
		mainViewPager.setLayoutParams(params);
		pointsImage = (ImageView)findViewById(R.id.pointsImage);
//		setScrollSpeed();
		imageSend = (ImageView)findViewById(R.id.imageSend);
		imageGet = (ImageView)findViewById(R.id.imageGet);
		imageFind = (ImageView)findViewById(R.id.imageFind);
		UserCenter = (LinearLayout)findViewById(R.id.UserCenter);
		SystemMessage = (LinearLayout)findViewById(R.id.SystemMessage);
		layoutPoint = (LinearLayout)findViewById(R.id.layoutPoint);
		imageBell = (ImageView)findViewById(R.id.imageBell);
	}

	@Override
	protected void getExras() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setListener() {
		imageSend.setOnClickListener(this);
		imageGet.setOnClickListener(this);
		imageFind.setOnClickListener(this);
		UserCenter.setOnClickListener(this);
		SystemMessage.setOnClickListener(this);
		mainViewPager.addOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {
				PageId = arg0;
				if(listData == null || listData.size() <= 0)
				{
					return;
				}
				int pageSize = listData.size();
				for(int i = 0; i < pageSize; i++)
				{
					((ImageView)findViewById(i)).setImageResource(R.drawable.point_none);
				}
				((ImageView)findViewById(PageId % pageSize)).setImageResource(R.drawable.point);
			}
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {}
			@Override
			public void onPageScrollStateChanged(int arg0) {}
		});
	}
	private void addImageView(List<AdvertiseModel> list)
	{
		if(list == null || list.size() <= 0)
			return;
		int Size = list.size();
		for(int i = 0; i < Size; i++)
		{
			ImageView imageView = new ImageView(mContext);
			LinearLayout.LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			imageView.setLayoutParams(params);
			imageView.setId(i);
			if(i == 0)
				imageView.setImageResource(R.drawable.point);
			else
				imageView.setImageResource(R.drawable.point_none);
			layoutPoint.addView(imageView);
		}
	}
	@Override
	public void onClick(View v) {
		Intent intent;
		switch(v.getId())
		{
		case R.id.imageSend://发货
			intent = new Intent(this, CodeCaptureActivity.class);
			intent.putExtra("ActivityType", GtsdpConfig.CODE_SEND);
			startActivity(intent);
			overridePendingTransition(R.anim.right_in, R.anim.none);
			break;
		case R.id.imageGet://收货
			intent = new Intent(this, CodeCaptureActivity.class);
			if(role == 2)
			{//网点
				intent.putExtra("ActivityType", GtsdpConfig.CODE_SITE);
				startActivity(intent);
				overridePendingTransition(R.anim.right_in, R.anim.none);
			}
			else
			{//普通用户
				intent.putExtra("ActivityType", GtsdpConfig.CODE_GET);
				startActivity(intent);
				overridePendingTransition(R.anim.right_in, R.anim.none);
			}
			break;
		case R.id.imageFind://找货
			intent = new Intent();
//			if(GtsdpConfig.IS_DEVELOPMENT)
//			{
//				intent.setClass(this, FindGoodsActivity.class);
//				startActivity(intent);
//				overridePendingTransition(R.anim.right_in, R.anim.none);
//				return;
//			}
			if(transflag == 1)
			{/* 是配送员->找货界面*/
				if(checkflag == 0)
				{/*审核中*/
					intent.setClass(this, IdentifyingActivity.class);
				}
				else
				{
					intent.setClass(this, FindGoodsActivity.class);
				}
			}
			else if(transflag == 0)
			{/*不是配送员*/
				intent.setClass(this, NotCursorActivity.class);
			}
			if(intent != null)
			{
				startActivity(intent);
				overridePendingTransition(R.anim.right_in, R.anim.none);
			}
			break;
		case R.id.UserCenter:
			intent = new Intent(this,UserCenterActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.right_in, R.anim.none);
			break;
		case R.id.SystemMessage:
			intent = new Intent(this, MessagesActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.right_in, R.anim.none);
			break;
		}
	}
	
	/**
	 * 设置滑动速度
	 */
	private void setScrollSpeed()
	{
		try
		{
			 Field field = ViewPager.class.getDeclaredField("mScroller");
	         field.setAccessible(true);
	         FixedSpeedScroller scroller = new FixedSpeedScroller(MainPageActivity.this,
	                 new AccelerateInterpolator());
	         field.set(mainViewPager, scroller);
	         scroller.setmDuration(200);//还是250比较好，神奇的数字
		}
		catch(Exception e)
		{
			showTextDialog(e.getMessage());
		}
	}
	
    private Runnable switchTask = new Runnable() {
        public void run() {
        	mainViewPager.setCurrentItem(PageId);  
            PageId++;
            mHandler.postDelayed(switchTask, 3000);
        }
        
    };
    Handler mHandler = new Handler();
    
    /**
     * 设置ViewPager的滑动速度
     * @author Wen
     * @author HuFanglin
     *
     */
    private class FixedSpeedScroller extends Scroller {
        private int mDuration = 100;//这个没用
     
        public FixedSpeedScroller(Context context) {
            super(context);
        }
     
        public FixedSpeedScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }
     
        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            // Ignore received duration, use fixed one instead
            super.startScroll(startX, startY, dx, dy, mDuration);
        }
     
        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            // Ignore received duration, use fixed one instead
            super.startScroll(startX, startY, dx, dy, mDuration);
        }
     
        public void setmDuration(int time) {
            mDuration = time;
        }
     
        public int getmDuration() {
            return mDuration;
        }
    }
    
    @Override
    protected boolean onKeyBack() {
		GtsdpTwoButtonDialog dialog = new GtsdpTwoButtonDialog(mContext);
		dialog.setText("确定要退出吗？");
		dialog.setRightButtonTextColor(GtsdpConfig.Main_Blue);
		dialog.setCancelable(true);
		dialog.setButtonListener(new OnButtonListener() {
			@Override
			public void onRightButtonClick(GtsdpTwoButtonDialog dialog) {
				dialog.cancel();
				finish(R.anim.none, R.anim.right_out);
			}

			@Override
			public void onLeftButtonClick(GtsdpTwoButtonDialog dialog) {
				dialog.cancel();
			}
		});
		dialog.show();
		return false;
    }
    @Override
    protected void onPause() {
    	super.onPause();
    	mHandler.removeCallbacks(switchTask);
    }
    @Override
    protected void onRestart() {
    	switchTask.run();
    	super.onRestart();
    }

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterPushReceiver();
	}
    
    
    /* 推送相关 */
	private PushReceiver pushReceiver;
	
	private void startPush() {
		User user = getApplicationContext().getUser();
		if (user == null) {
			log_i("未登录，无需启动推送服务");
			return;
		}
		if (!PushUtils.hasBind(getApplicationContext())) {
			PushManager.startWork(getApplicationContext(),
					PushConstants.LOGIN_TYPE_API_KEY,
					PushUtils.getMetaValue(this, "api_key"));
			// Push: 如果想基于地理位置推送，可以打开支持地理位置的推送的开关
			// PushManager.enableLbs(getApplicationContext());
		} else {
			saveDevice();
		}
		registerPushReceiver();
	}
	

	private void stopPush() {
		PushManager.stopWork(getApplicationContext());
		PushUtils.setBind(mContext, false);
		unregisterPushReceiver();
	}
	
	private void registerPushReceiver() {
		if (pushReceiver == null) {
			pushReceiver = new PushReceiver();
			IntentFilter mFilter = new IntentFilter("com.hemaapp.push.connect");
			mFilter.addAction("com.hemaapp.push.msg");
			registerReceiver(pushReceiver, mFilter);
		}
	}

	private void unregisterPushReceiver() {
		if (pushReceiver != null)
			unregisterReceiver(pushReceiver);
	}
	
	private class PushReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			handleEvent(intent);
		}

		private void handleEvent(Intent intent) {
			String action = intent.getAction();

			if ("com.hemaapp.push.connect".equals(action)) {
				saveDevice();

			} else if ("com.hemaapp.push.msg".equals(action)) {
				boolean unread = PushUtils.getmsgreadflag(
						getApplicationContext(), "2");
				if (unread) {
					// showNoticePoint();
					log_i("有未读推送");
				} else {
					log_i("无未读推送");
					// hideNoticePoint();
				}
			}
		}
	}
	public void saveDevice() {
		User user = getApplicationContext().getUser();
		getNetWorker().deviceSave(user.getToken(),
				PushUtils.getUserId(mContext), "2",
				PushUtils.getChannelId(mContext));
	}
	
	/*推送相关END*/
}
