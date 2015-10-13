package com.hemaapp.hm_gtsdp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alipay.android.app.sdk.AliPay;
import com.hemaapp.GtsdpConfig;
import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaArrayResult;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpActivity;
import com.hemaapp.hm_gtsdp.GtsdpHttpInformation;
import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_gtsdp.alipay.Result;
import com.hemaapp.hm_gtsdp.model.AlipayTrade;
import com.hemaapp.hm_gtsdp.model.UnionTrade;
import com.hemaapp.hm_gtsdp.model.User;
import com.unionpay.UPPayAssistEx;
import com.unionpay.uppay.PayActivity;
/**
 * 充值
 * @author Wen
 * @author HuFanglin
 *
 */
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
	protected void callBackForServerSuccess(HemaNetTask nettask, HemaBaseResult result) {
		cancelProgressDialog();
		GtsdpHttpInformation inforamtion = (GtsdpHttpInformation)nettask.getHttpInformation();
		switch(inforamtion)
		{
		case ALIPAY:
			HemaArrayResult<AlipayTrade> aResult = (HemaArrayResult<AlipayTrade>) result;
			AlipayTrade trade = aResult.getObjects().get(0);
			String orderInfo = trade.getAlipaysign();
			new AlipayThread(orderInfo).start();
			break;
		case UNIONPAY:
			HemaArrayResult<UnionTrade> uResult = (HemaArrayResult<UnionTrade>) result;
			UnionTrade uTrade = uResult.getObjects().get(0);
			String tn = uTrade.getTn();
			UPPayAssistEx.startPayByJAR(this, PayActivity.class, null, null,
					tn, GtsdpConfig.UNIONPAY_TESTMODE);
			break;
		}
		
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
		User user = getApplicationContext().getUser();
		String token = user.getToken();
		double total_fee = Integer.parseInt(txtPayCount.getText().toString().trim(), 0);
		if(total_fee == 0)
		{
			showTextDialog("请输入充值金额");
			return;
		}
		if(GtsdpConfig.IS_DEVELOPMENT)
		{
			switch(selectId)
			{
			case R.id.layoutUnionpay:
				getNetWorker().unionpay(token, "2", "1", "1", "0.01");
				showProgressDialog("跳转中，请稍后");
				break;
			case R.id.layoutAlipay:
				getNetWorker().alipay(token, "1", "1", "0.01");
				showProgressDialog("跳转中，请稍后");
				break;
			case R.id.layoutWechat:
				showTextDialog("微信");
				break;
			}
		}
		else
		{
			switch(selectId)
			{
			case R.id.layoutUnionpay:
				getNetWorker().unionpay(token, "2", "1", "1", String.valueOf(total_fee));
				showProgressDialog("跳转中，请稍后");
				break;
			case R.id.layoutAlipay:
				getNetWorker().alipay(token, "1", "1", String.valueOf(total_fee));
				showProgressDialog("跳转中，请稍后");
				break;
			case R.id.layoutWechat:
				showTextDialog("微信");
				break;
			}
		}
		
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == Change_Count && resultCode == RESULT_OK)
		{
			double strCount = data.getDoubleExtra("Count", 0);
			txtPayCount.setText(String.valueOf(strCount));
		}
	}
	

	private class AlipayThread extends Thread {
		String orderInfo;
		AlipayHandler alipayHandler;

		public AlipayThread(String orderInfo) {
			this.orderInfo = orderInfo;
			alipayHandler = new AlipayHandler(RechargeActivity.this);
		}

		@Override
		public void run() {
			AliPay alipay = new AliPay(RechargeActivity.this, alipayHandler);

			// 设置为沙箱模式，不设置默认为线上环境
			// alipay.setSandBox(true);
			String result = alipay.pay(orderInfo);
			log_i("result = " + result);
			Message msg = new Message();
			msg.obj = result;
			alipayHandler.sendMessage(msg);
		}
	}

	private static class AlipayHandler extends Handler {
		RechargeActivity activity;

		public AlipayHandler(RechargeActivity activity) {
			this.activity = activity;
		}

		public void handleMessage(android.os.Message msg) {
			Result result = new Result((String) msg.obj);
			int staus = result.getResultStatus();
			switch (staus) {
			case 9000:
				activity.showTextDialog("恭喜\n购买成功");
				postAtTime(new Runnable() {

					@Override
					public void run() {
						activity.finish();
					}
				}, 1500);
				break;
			default:
				activity.showTextDialog(result.getResult());
				break;
			}
		};
	}

	
}
