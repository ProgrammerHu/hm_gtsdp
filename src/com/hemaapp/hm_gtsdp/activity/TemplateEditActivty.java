package com.hemaapp.hm_gtsdp.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpActivity;
import com.hemaapp.hm_gtsdp.GtsdpHttpInformation;
import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_gtsdp.dialog.GtsdptOneButtonDialog;
import com.hemaapp.hm_gtsdp.dialog.GtsdptOneButtonDialog.OnButtonListener;
import com.hemaapp.hm_gtsdp.view.SelectDistrictPicker;

public class TemplateEditActivty extends GtsdpActivity implements OnClickListener {
	private final int SENDER = 100;//������
	private final int RECIVER = 200;//�ռ���
	private final int SelectAddress = 300;
	
	private Intent beforeIntent;
	private String beforeName, beforeAddress, beforePhone;
	private int ActivityType;
	private TextView txtTitle, txtNext, txtTemplateTitle, txtAddress;
	private ImageView imageQuitActivity;
	private RelativeLayout layoutSelectAddress;
	private SelectDistrictPicker myDistrictPicker;
	private EditText editName, editPhone;
	
	private String token;//��¼����
	private String keytype;// ҵ������ 1��������ģ�壻2���ռ���ģ��
	private String name;//����
	private String address;//����
	private String telphone;//��ϵ�绰
	private String templateId;//ģ��Id
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_template_edit);
		super.onCreate(savedInstanceState);
		if(myDistrictPicker == null)
		{
			myDistrictPicker = new SelectDistrictPicker(mContext);
			myDistrictPicker.setOnClickListener(clickReciver);
		}
		token = getApplicationContext().getUser().getToken();
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
	protected void callBackForServerSuccess(HemaNetTask netTask,
			HemaBaseResult baseResult) {
		GtsdpHttpInformation information = (GtsdpHttpInformation)netTask.getHttpInformation();
		switch (information) {
		case TEMPLATE_ADD:
		case TEMPLATE_SAVE:
			cancelProgressDialog();
			setResult(RESULT_OK);
			finish(R.anim.none, R.anim.right_out);
			break;
		}
	}

	@Override
	protected void callBeforeDataBack(HemaNetTask arg0) {
		showProgressDialog("������");
		
	}

	@Override
	protected void findView() {
		txtTitle = (TextView)findViewById(R.id.txtTitle);
		txtTitle.setText("ģ��");
		txtNext = (TextView)findViewById(R.id.txtNext);
		txtNext.setText("ȷ��");
		imageQuitActivity = (ImageView)findViewById(R.id.imageQuitActivity);
		txtTemplateTitle = (TextView)findViewById(R.id.txtTemplateTitle);
		if(ActivityType == SENDER)
		{
			txtTemplateTitle.setText("������");
			keytype = "1";
		}
		else if(ActivityType == RECIVER)
		{
			txtTemplateTitle.setText("�ռ���");
			keytype = "2";
		}
		layoutSelectAddress = (RelativeLayout)findViewById(R.id.layoutSelectAddress);
		txtAddress = (TextView)findViewById(R.id.txtAddress);
		txtAddress.setText(beforeAddress);
		editName = (EditText)findViewById(R.id.editName);
		editName.setText(beforeName);
		editPhone = (EditText)findViewById(R.id.editPhone);
		editPhone.setText(beforePhone);
	}

	@Override
	protected void getExras() {
		beforeIntent = getIntent();
		beforeName = beforeIntent.getStringExtra("name");
		beforeAddress = beforeIntent.getStringExtra("address");
		beforePhone = beforeIntent.getStringExtra("telphone");
		ActivityType = beforeIntent.getIntExtra("ActivityType", 300);
		templateId = beforeIntent.getStringExtra("id");
	}

	@Override
	protected void setListener() {
		imageQuitActivity.setOnClickListener(this);
		txtNext.setOnClickListener(this);
		layoutSelectAddress.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);  
		imm.hideSoftInputFromWindow(editName.getWindowToken(), 0); //ǿ�����ؼ���
		switch(v.getId())
		{
		case R.id.imageQuitActivity:
			setResult(RESULT_CANCELED);
			finish(R.anim.none, R.anim.right_out);
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
	 * ����ύ��ť
	 */
	private void clickSubmit()
	{
		name = editName.getEditableText().toString();
		address = txtAddress.getText().toString();
		telphone = editPhone.getEditableText().toString();
		if(beforePhone == null || beforePhone.equals(""))
		{//û�д������ݣ���ʾ������
			getNetWorker().addTemplate(token, keytype, name, address, telphone);
		}
		else
		{
			getNetWorker().saveTemplate(token, templateId, keytype, name, address, telphone);
		}
		
		
		GtsdptOneButtonDialog dialog = new GtsdptOneButtonDialog(this);
		dialog.setText("�Բ����ֻ�����δע�ᣡ");
		dialog.setRightButtonTextColor(Color.rgb(0, 161, 216));
		dialog.setButtonListener(new OnButtonListener() {
			@Override
			public void onRightButtonClick(
					GtsdptOneButtonDialog gtsdptOneButtonDialog) {
				gtsdptOneButtonDialog.cancel();
			}
		});
		dialog.cancel();
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
	 * ����ռ��˵�ַ
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
	
	@Override
	protected boolean onKeyBack() {
		setResult(RESULT_CANCELED);
		finish(R.anim.none, R.anim.right_out);
		return super.onKeyBack();
	};

}
