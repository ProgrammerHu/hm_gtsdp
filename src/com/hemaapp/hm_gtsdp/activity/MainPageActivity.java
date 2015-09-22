package com.hemaapp.hm_gtsdp.activity;

import java.lang.reflect.Field;

import android.content.Context;
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
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpFragmentActivity;
import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_gtsdp.adapter.ImageViewPagerAdapter;

public class MainPageActivity extends GtsdpFragmentActivity implements OnClickListener {
	private TextView txtTitle, txtNext;
	private ImageView imageQuitActivity, pointsImage, imageSend, imageGet, imageFind;
	private ViewPager mainViewPager;
	private int PageId = 8;
	private LinearLayout UserCenter, SystemMessage;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_mainpage);
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
		mainViewPager.setAdapter(new ImageViewPagerAdapter(getSupportFragmentManager()));
		RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)mainViewPager.getLayoutParams();
		params.height = mScreenWidth * 4 / 7;
		mainViewPager.setLayoutParams(params);
		pointsImage = (ImageView)findViewById(R.id.pointsImage);
		setScrollSpeed();
	}

	@Override
	protected void getExras() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setListener() {
		mainViewPager.addOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {
				PageId = arg0;
				int imageResource = 0;
				switch(arg0 % 4)
				{
				case 0:
					imageResource = R.drawable.point0;
					break;
				case 1:
					imageResource = R.drawable.point1;
					break;
				case 2:
					imageResource = R.drawable.point2;
					break;
				case 3:
					imageResource = R.drawable.point3;
					break;
				}
				if(imageResource != 0)
				{
					pointsImage.setImageResource(imageResource);
				}
			}
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {}
			@Override
			public void onPageScrollStateChanged(int arg0) {}
		});
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
		case R.id.imageSend:
		case R.id.imageGet:
		case R.id.imageFind:
			break;
		case R.id.UserCenter:
		case R.id.SystemMessage:
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
	         scroller.setmDuration(500);
		}
		catch(Exception e)
		{
			showTextDialog(e.getMessage());
		}
	}
	
	@Override
    protected void onResume() {
        super.onResume();
        switchTask.run();
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
        private int mDuration = 500;
     
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
    protected void onStop() {
    	super.onStop();
    	try {
			switchTask.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
}
