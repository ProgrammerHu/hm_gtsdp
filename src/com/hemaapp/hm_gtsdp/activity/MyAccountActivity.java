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
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpActivity;
import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_gtsdp.view.SelectPopupWindow;

/**
 * �ҵ��˻�����
 * @author Wen
 * @author HuFanglin
 *
 */
public class MyAccountActivity extends GtsdpActivity implements OnClickListener{
	
	private ImageView imageQuitActivity;
	private TextView txtTitle;
	private View layoutRecords;
	private LinearLayout layoutIncash, layoutRecharge;
	
	private SelectPopupWindow popupWindow;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_my_account);
		super.onCreate(savedInstanceState);
		popupWindow = new SelectPopupWindow(mContext, this, "֧��������", "���п�����");
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
		imageQuitActivity = (ImageView)findViewById(R.id.imageQuitActivity);
		txtTitle = (TextView)findViewById(R.id.txtTitle);
		txtTitle.setText("�ҵ��˻�");
		findViewById(R.id.txtNext).setVisibility(View.INVISIBLE);
		layoutRecords = findViewById(R.id.layoutRecords);
		layoutIncash = (LinearLayout)findViewById(R.id.layoutIncash);
		layoutRecharge = (LinearLayout)findViewById(R.id.layoutRecharge);
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
		case R.id.layoutIncash://����
			popupWindow.showAtLocation(findViewById(R.id.father), Gravity.BOTTOM, 0, 0);
			break;
		case R.id.layoutRecharge://��ֵ
			intent = new Intent(MyAccountActivity.this, RechargeActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.right_in, R.anim.none);
			break;
		case R.id.btnTop://֧��������
			popupWindow.dismiss();
			intent = new Intent(MyAccountActivity.this, AlipayIncashActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.right_in, R.anim.none);
			break;
		case R.id.btnMiddle://���п�����
			popupWindow.dismiss();
			intent = new Intent(MyAccountActivity.this, ToCashActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.right_in, R.anim.none);
			break;
		}
		
	}

}
