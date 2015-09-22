package com.hemaapp.hm_gtsdp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hemaapp.GtsdpConfig;
import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpActivity;
import com.hemaapp.hm_gtsdp.R;
import com.unionpay.UPPayAssistEx;
import com.unionpay.uppay.PayActivity;
/**
 * 支付界面
 * @author Wen
 * @author HuFanglin
 *
 */
public class MyPayActivity extends GtsdpActivity implements OnClickListener{
	String unionStr = "201509181555567100698";

	private TextView txtTitle, txtNext;
	private ImageView imageQuitActivity, TempImageView;
	
	private View layoutAccount, layoutAlipay, layoutUnionpay, layoutWechat;
	private Button btnConfirmPay;
	private int selectId;//记录最终选择的
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_pay);
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
		txtTitle = (TextView)findViewById(R.id.txtTitle);
		txtTitle.setText("支付");
		txtNext = (TextView)findViewById(R.id.txtNext);
		txtNext.setVisibility(View.INVISIBLE);
		imageQuitActivity = (ImageView) findViewById(R.id.imageQuitActivity);
		layoutAccount = findViewById(R.id.layoutAccount);
		layoutAlipay = findViewById(R.id.layoutAlipay);
		layoutUnionpay = findViewById(R.id.layoutUnionpay);
		layoutWechat = findViewById(R.id.layoutWechat);
		btnConfirmPay = (Button)findViewById(R.id.btnConfirmPay);
	}

	@Override
	protected void getExras() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setListener() {
		imageQuitActivity.setOnClickListener(this);
		layoutAlipay.setOnClickListener(this);
		layoutAccount.setOnClickListener(this);
		layoutUnionpay.setOnClickListener(this);
		layoutWechat.setOnClickListener(this);
		btnConfirmPay.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
		case R.id.imageQuitActivity:
			finish(R.anim.none, R.anim.right_out);
			break;
		case R.id.layoutAlipay:
			if(TempImageView != null)
				TempImageView.setImageResource(R.drawable.circle_none);
			TempImageView = (ImageView)layoutAlipay.findViewById(R.id.imageview);
			TempImageView.setImageResource(R.drawable.circle_checked);
			selectId = v.getId();
			break;
		case R.id.layoutAccount:
			if(TempImageView != null)
				TempImageView.setImageResource(R.drawable.circle_none);
			TempImageView = (ImageView)layoutAccount.findViewById(R.id.imageview);
			TempImageView.setImageResource(R.drawable.circle_checked);
			selectId = v.getId();
			break;
		case R.id.layoutUnionpay:
			if(TempImageView != null)
				TempImageView.setImageResource(R.drawable.circle_none);
			TempImageView = (ImageView)layoutUnionpay.findViewById(R.id.imageview);
			TempImageView.setImageResource(R.drawable.circle_checked);
			selectId = v.getId();
			break;
		case R.id.layoutWechat:
			if(TempImageView != null)
				TempImageView.setImageResource(R.drawable.circle_none);
			TempImageView = (ImageView)layoutWechat.findViewById(R.id.imageview);
			TempImageView.setImageResource(R.drawable.circle_checked);
			selectId = v.getId();
			break;
		case R.id.btnConfirmPay:
			clickPay();
			break;
		}
	}
	
	@Override
	protected boolean onKeyBack() {
		finish(R.anim.none, R.anim.right_out);
		return super.onKeyBack();
	}
	
	private void clickPay()
	{
		switch(selectId)
		{
		case R.id.layoutUnionpay:
			UPPayAssistEx.startPayByJAR(mContext, PayActivity.class, null, null,
					unionStr, GtsdpConfig.UNIONPAY_TESTMODE);
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}
}
