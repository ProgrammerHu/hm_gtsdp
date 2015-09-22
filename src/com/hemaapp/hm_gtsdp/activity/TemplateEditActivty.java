package com.hemaapp.hm_gtsdp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.dialog.HemaButtonDialog;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpActivity;
import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_gtsdp.dialog.GtsdptOneButtonDialog;
import com.hemaapp.hm_gtsdp.dialog.GtsdptOneButtonDialog.OnButtonListener;
import com.hemaapp.hm_gtsdp.view.SelectDistrictPicker;

public class TemplateEditActivty extends GtsdpActivity implements OnClickListener {
	private final int SENDER = 100;//发件人
	private final int RECIVER = 200;//收件人
	private final int SelectAddress = 300;
	
	private Intent beforeIntent;
	private String beforeName, beforeAddress, beforePhone;
	private int ActivityType;
	private TextView txtTitle, txtNext, txtTemplateTitle, txtAddress;
	private ImageView imageQuitActivity;
	private RelativeLayout layoutSelectAddress;
	private SelectDistrictPicker myDistrictPicker;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_template_edit);
		super.onCreate(savedInstanceState);
		if(myDistrictPicker == null)
		{
			myDistrictPicker = new SelectDistrictPicker(mContext);
			myDistrictPicker.setOnClickListener(clickReciver);
		}
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
		txtTitle.setText("模板");
		txtNext = (TextView)findViewById(R.id.txtNext);
		txtNext.setText("确定");
		imageQuitActivity = (ImageView)findViewById(R.id.imageQuitActivity);
		txtTemplateTitle = (TextView)findViewById(R.id.txtTemplateTitle);
		if(ActivityType == SENDER)
		{
			txtTemplateTitle.setText("发件人");
		}
		else if(ActivityType == RECIVER)
		{
			txtTemplateTitle.setText("收件人");
		}
		layoutSelectAddress = (RelativeLayout)findViewById(R.id.layoutSelectAddress);
		txtAddress = (TextView)findViewById(R.id.txtAddress);
	}

	@Override
	protected void getExras() {
		beforeIntent = getIntent();
		beforeName = beforeIntent.getStringExtra("Name");
		beforeAddress = beforeIntent.getStringExtra("Address");
		beforePhone = beforeIntent.getStringExtra("Phone");
		ActivityType = beforeIntent.getIntExtra("ActivityType", 300);
	}

	@Override
	protected void setListener() {
		imageQuitActivity.setOnClickListener(this);
		txtNext.setOnClickListener(this);
		layoutSelectAddress.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
		case R.id.imageQuitActivity:
			finish();
			break;
		case R.id.txtNext:
			clickSubmit();
			break;
		case R.id.layoutSelectAddress:
//			Intent intent = new Intent(TemplateEditActivty.this, SelectAddressActivity.class);
//			startActivityForResult(intent, SelectAddress);

			myDistrictPicker.showAtLocation(TemplateEditActivty.this.findViewById(R.id.detialMainLinear), 
					Gravity.BOTTOM, 0, 0);
			break;
		}
	}
	
	/**
	 * 点击提交按钮
	 */
	private void clickSubmit()
	{
		GtsdptOneButtonDialog dialog = new GtsdptOneButtonDialog(this);
		dialog.setText("对不起，手机号尚未注册！");
		dialog.setRightButtonTextColor(Color.rgb(0, 161, 216));
//		dialog.show();
		dialog.setButtonListener(new OnButtonListener() {
			@Override
			public void onRightButtonClick(
					GtsdptOneButtonDialog gtsdptOneButtonDialog) {
				gtsdptOneButtonDialog.cancel();
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
//		if(data == null)
//			return;
//		switch(requestCode)
//		{
//		case SelectAddress:
//			String address = data.getStringExtra("Address");
//			if(address != null && !"".equals(address))
//			{
//				txtAddress.setText(address);
//			}
//			break;
//		}
	}
	/**
	 * 点击收件人地址
	 */
	private OnClickListener clickReciver = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if(v.getId() == R.id.btnDistrictPickerCancel)
			{
				myDistrictPicker.dismiss();
				return;
			}
			if(v.getId() == R.id.btnDistrictPickerClose)
			{
				String address = myDistrictPicker.getDistrcictString();
				txtAddress.setText(address);
			}
			myDistrictPicker.dismiss();
		}
	};
	

}
