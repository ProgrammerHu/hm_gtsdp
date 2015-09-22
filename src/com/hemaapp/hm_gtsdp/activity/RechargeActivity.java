package com.hemaapp.hm_gtsdp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpActivity;
import com.hemaapp.hm_gtsdp.R;

public class RechargeActivity extends GtsdpActivity implements OnClickListener{
	private final int Change_Count = 100;
	private TextView txtTitle, txtNext, txtPayCount;
	private ImageView imageQuitActivity, TempImageView;
	
	private View layoutAlipay, layoutUnionpay, layoutWechat, layoutBalance;
	private Button btnConfirmPay;
	private int selectId;//记录最终选择的
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_recharge);
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
		txtTitle.setText("账户充值");
		txtNext = (TextView)findViewById(R.id.txtNext);
		txtNext.setVisibility(View.INVISIBLE);
		txtPayCount = (TextView)findViewById(R.id.txtPayCount);
		imageQuitActivity = (ImageView) findViewById(R.id.imageQuitActivity);
		layoutAlipay = findViewById(R.id.layoutAlipay);
		layoutUnionpay = findViewById(R.id.layoutUnionpay);
		layoutWechat = findViewById(R.id.layoutWechat);
		layoutBalance = findViewById(R.id.layoutBalance);
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
		layoutUnionpay.setOnClickListener(this);
		layoutWechat.setOnClickListener(this);
		btnConfirmPay.setOnClickListener(this);
		layoutBalance.setOnClickListener(this);
	}

	@Override
	protected boolean onKeyBack() {
		finish(R.anim.none, R.anim.right_out);
		return super.onKeyBack();
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
		case R.id.layoutBalance:
			Intent intent = new Intent(RechargeActivity.this, ChangeCountActivtiy.class);
			startActivityForResult(intent, Change_Count);
			overridePendingTransition(R.anim.right_in, R.anim.none);
			break;
		}
	}	
	
	private void clickPay()
	{
		switch(selectId)
		{
		case R.id.layoutUnionpay:
			showTextDialog("银联支付");
			break;
		case R.id.layoutAlipay:
			showTextDialog("支付宝");
			break;
		case R.id.layoutWechat:
			showTextDialog("微信");
			break;
		}
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == Change_Count && resultCode == RESULT_OK)
		{
			double strCount = data.getDoubleExtra("Count", 0);
			txtPayCount.setText("￥" + strCount);
		}
	}
}
