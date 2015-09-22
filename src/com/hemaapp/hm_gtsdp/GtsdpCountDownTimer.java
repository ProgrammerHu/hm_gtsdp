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
	 * ���캯��
	 * @param millisInFuture ����ʱʱ��
	 * @param countDownInterval ��λʱ��
	 * @param textView ����ʱ�ı���
	 * @param beforeButton ���ǰ��ť
	 * @param afterLayout ����󲼾�
	 * @param actionView ��ʾ�ı���
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
