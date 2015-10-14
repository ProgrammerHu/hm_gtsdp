package com.hemaapp.hm_gtsdp.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpActivity;
import com.hemaapp.hm_gtsdp.GtsdpFragmentActivity;
import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_gtsdp.adapter.SendFragmentAdapter;
import com.hemaapp.hm_gtsdp.view.MyViewPager;
import com.hemaapp.hm_gtsdp.view.ZoomOutPageTransformer;

/**
 * 全部订单界面，内含两个fragment
 * @author Wen
 * @author HuFanglin
 *
 */
public class AllOrdersActivity extends GtsdpFragmentActivity{
	private MyViewPager myViewPager;
	private RadioGroup radioGroup;
	private RadioButton rbtnLeft, rbtnRight;
	private ImageView imageQuitActivity;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_all_orders);
		super.onCreate(savedInstanceState);
		myViewPager.setAdapter(new SendFragmentAdapter(getSupportFragmentManager(), this));
		myViewPager.setPageTransformer(true, new ZoomOutPageTransformer());//设置滑动效果
		
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
		myViewPager = (MyViewPager)findViewById(R.id.myViewPager);
		radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
		rbtnLeft = (RadioButton)findViewById(R.id.rbtnLeft);
		rbtnRight = (RadioButton)findViewById(R.id.rbtnRight);
		rbtnLeft.setChecked(true);
		imageQuitActivity = (ImageView)findViewById(R.id.imageQuitActivity);
	}

	@Override
	protected void getExras() {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void setListener() {
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId == R.id.rbtnLeft)
					myViewPager.setCurrentItem(0);
				else
					myViewPager.setCurrentItem(1);
			}
		});
		
		myViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				if(position == 0)
				{
					radioGroup.check(R.id.rbtnLeft);
				}
				else
				{
					radioGroup.check(R.id.rbtnRight);
				}
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
		imageQuitActivity.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
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
