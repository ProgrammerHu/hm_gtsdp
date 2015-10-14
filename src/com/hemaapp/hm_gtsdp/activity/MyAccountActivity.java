package com.hemaapp.hm_gtsdp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaArrayResult;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpActivity;
import com.hemaapp.hm_gtsdp.GtsdpHttpInformation;
import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_gtsdp.db.UserDBHelper;
import com.hemaapp.hm_gtsdp.model.User;
import com.hemaapp.hm_gtsdp.view.SelectPopupWindow;

/**
 * 我的账户界面
 * @author Wen
 * @author HuFanglin
 *
 */
public class MyAccountActivity extends GtsdpActivity implements OnClickListener{
	
	private ImageView imageQuitActivity;
	private TextView txtTitle, txtCount;
	private View layoutRecords;
	private LinearLayout layoutIncash, layoutRecharge;
	
	private SelectPopupWindow popupWindow;
	
	private double FeeAccount;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_my_account);
		super.onCreate(savedInstanceState);
		popupWindow = new SelectPopupWindow(mContext, this, "支付宝提现", "银行卡提现");
	}
	@Override
	protected void onRestart() {
		super.onRestart();
		getNetWorker().clientGet(getApplicationContext().getUser().getToken(), getApplicationContext().getUser().getId());
		showProgressDialog("更新中");
		FeeAccount = Double.parseDouble(getApplicationContext().getUser().getFeeaccount());
		txtCount.setText("￥"+String.valueOf(FeeAccount));
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
		cancelProgressDialog();
		
	}

	@Override
	protected void callBackForServerSuccess(HemaNetTask netTask,
			HemaBaseResult baseResult) {
		cancelProgressDialog();
		GtsdpHttpInformation information = (GtsdpHttpInformation)netTask.getHttpInformation();
		switch (information) {
		case CLIENT_GET:
			HemaArrayResult<User> successResult = (HemaArrayResult<User>)baseResult;
			User user = successResult.getObjects().get(0);
			new UserDBHelper(mContext).update(user);//更新用户数据
			FeeAccount = Double.parseDouble(user.getFeeaccount());
			txtCount.setText("￥"+String.valueOf(FeeAccount));
			break;

		}
		
	}

	@Override
	protected void callBeforeDataBack(HemaNetTask arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void findView() {
		imageQuitActivity = (ImageView)findViewById(R.id.imageQuitActivity);
		txtTitle = (TextView)findViewById(R.id.txtTitle);
		txtTitle.setText("我的账户");
		findViewById(R.id.txtNext).setVisibility(View.INVISIBLE);
		layoutRecords = findViewById(R.id.layoutRecords);
		layoutIncash = (LinearLayout)findViewById(R.id.layoutIncash);
		layoutRecharge = (LinearLayout)findViewById(R.id.layoutRecharge);
		txtCount = (TextView)findViewById(R.id.txtCount);
		FeeAccount = Double.parseDouble(getApplicationContext().getUser().getFeeaccount());
		txtCount.setText("￥"+String.valueOf(FeeAccount));
	}

	@Override
	protected void getExras() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setListener() {
		imageQuitActivity.setOnClickListener(this);
		layoutRecords.setOnClickListener(this);
		layoutRecharge.setOnClickListener(this);
		layoutIncash.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		switch(v.getId())
		{
		case R.id.imageQuitActivity:
			finish(R.anim.none, R.anim.right_out);
			break;
		case R.id.layoutRecords:
			intent = new Intent(MyAccountActivity.this, TransactionRecordsActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.right_in, R.anim.none);
			break;
		case R.id.layoutIncash://提现
			popupWindow.showAtLocation(findViewById(R.id.father), Gravity.BOTTOM, 0, 0);
			break;
		case R.id.layoutRecharge://充值
			intent = new Intent(MyAccountActivity.this, RechargeActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.right_in, R.anim.none);
			break;
		case R.id.btnTop://支付宝提现
			popupWindow.dismiss();
			intent = new Intent(MyAccountActivity.this, AlipayIncashActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.right_in, R.anim.none);
			break;
		case R.id.btnMiddle://银行卡提现
			popupWindow.dismiss();
			intent = new Intent(MyAccountActivity.this, ToCashActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.right_in, R.anim.none);
			break;
		}
		
	}

}
