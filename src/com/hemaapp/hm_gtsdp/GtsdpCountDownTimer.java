package com.hemaapp.hm_gtsdp;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GtsdpCountDownTimer extends CountDownTimer
{
	private TextView textView;
	private Button beforeButton;
	private LinearLayout afterLayout;
	private TextView actionView;
	/**
	 * 构造函数
	 * @param millisInFuture 倒计时时长
	 * @param countDownInterval 单位时间
	 * @param textView 倒计时文本框
	 * @param beforeButton 点击前按钮
	 * @param afterLayout 点击后布局
	 * @param actionView 提示文本框
	 */
	public GtsdpCountDownTimer(long millisInFuture, long countDownInterval, TextView textView, Button beforeButton, LinearLayout afterLayout, TextView actionView)
	{
		super(millisInFuture, countDownInterval);
		this.textView = textView;
		this.beforeButton = beforeButton;
		this.afterLayout = afterLayout;
		this.actionView = actionView;
	}

	@Override
	public void onTick(long millisUntilFinished) {
		Log.e("tick", String.valueOf(millisUntilFinished));
		textView.setText(String.valueOf(millisUntilFinished / 1000));
	}

	@Override
	public void onFinish() {
		afterLayout.setVisibility(View.GONE);
		beforeButton.setVisibility(View.VISIBLE);
		actionView.setText("");
	}
}
