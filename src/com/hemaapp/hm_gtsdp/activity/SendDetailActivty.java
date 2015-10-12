package com.hemaapp.hm_gtsdp.activity;

import java.io.IOException;
import java.util.ArrayList;

import xtom.frame.util.XtomFileUtil;
import xtom.frame.util.XtomImageUtil;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hemaapp.GtsdpConfig;
import com.hemaapp.hm_FrameWork.HemaImageWay;
import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaArrayResult;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpActivity;
import com.hemaapp.hm_gtsdp.GtsdpHttpInformation;
import com.hemaapp.hm_gtsdp.GtsdpUtil;
import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_gtsdp.adapter.SendImageAdapter;
import com.hemaapp.hm_gtsdp.dialog.GtsdpTwoButtonDialog;
import com.hemaapp.hm_gtsdp.dialog.GtsdpTwoButtonDialog.OnButtonListener;
import com.hemaapp.hm_gtsdp.view.SelectDistrictPicker;
import com.hemaapp.hm_gtsdp.view.SelectPopupWindow;
import com.hemaapp.hm_gtsdp.zxing.camera.CameraManager;

/**
 * ����������д����
 * 
 * @author Wen
 * @author HuFanglin
 * 
 */
public class SendDetailActivty extends GtsdpActivity implements OnClickListener {
	private final int REQUEST_CODE_PICK_IMAGE = 1;// ����ȡ
	private final int REQUEST_CODE_CAPTURE_CAMEIA = 2;// �����ȡ
	private final int SCAN_SQCODE = 3;//����ɨ���ά��
	private final int SENDER = 100;//������
	private final int RECIVER = 200;//�ռ���

	private View detialMainLinear;
	
	private TextView txtTitle, txtNext, txtSendProtocol, txtSenderTemplet,
			txtReciverTemplet, txtSenderAddress, txtReciverAddress;
	private ImageView imageQuitActivity;
	private EditText editReciverName, editReciverPhone, editSenderName,
			editSenderPhone;
	private Button btnSend;
	private CheckBox isAgree;
	private LinearLayout layoutReciverAddress, layoutSendAddress;

	private SelectDistrictPicker myDistrictPicker;
	private GridView gridView;

	private ArrayList<String> images = new ArrayList<String>();
	private SendImageAdapter imageAdapter;

	private SelectPopupWindow popupWindow;
	private HemaImageWay imageWay;

	private String SQCode;//��ά����Ϣ
	private String token, receiver_name, receiver_address, receiver_telphone, sender_name, sender_address, sender_telphone;
	private boolean CouldCommit = false;//true:�����ύ���ݣ�false:��Ҫ��֤�ֻ���
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_send_detail);
		super.onCreate(savedInstanceState);
		CameraManager.init(getApplication());
		if (myDistrictPicker == null) {
			myDistrictPicker = new SelectDistrictPicker(mContext);
		}

		imageWay = new HemaImageWay(mContext, 1, 2) {
			@Override
			public void album() {
				// ע�⣺������д�÷�����ʹ��ϵͳ���ѡȡ(��Ӧ��onActivityResult�еĴ�����ҲӦ��ͬ)
				Intent it = new Intent(mContext, AlbumActivity.class);
				it.putExtra("limitCount", 8 - images.size());// ͼƬѡ����������
				startActivityForResult(it, albumRequestCode);
			}
		};
		

		popupWindow = new SelectPopupWindow(this, 
				clickFace, "����", "�����ѡ��");
		imageAdapter = new SendImageAdapter(mContext, detialMainLinear, images);
		gridView.setAdapter(imageAdapter);
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
	protected void callBackForServerFailed(HemaNetTask netTask, HemaBaseResult baseResult) {
		// TODO Auto-generated method stub
		cancelProgressDialog();
		GtsdpHttpInformation information = (GtsdpHttpInformation)netTask.getHttpInformation();
		switch (information) {
		case CLIENT_VERIFY:
			if(CouldCommit)
			{
				showTextDialog("�������ֻ���û��ע��");
			}
			else
			{
				showTextDialog("�ռ����ֻ���û��ע��");
			}
			break;
		case TRANS_ADD:
			int errorCode = baseResult.getError_code();
			if(errorCode == 703)
			{//��Ч��ά��
				GtsdpTwoButtonDialog dialog = new GtsdpTwoButtonDialog(mContext);
				dialog.setCancelable(true);
				dialog.setText("��Ч��ά�룬������ɨ��");
				dialog.setLeftButtonText("ȡ��");
				dialog.setRightButtonText("ȷ��");
				dialog.setRightButtonTextColor(GtsdpConfig.Main_Blue);
				dialog.setButtonListener(new OnButtonListener() {
					@Override
					public void onRightButtonClick(GtsdpTwoButtonDialog dialog) {
						dialog.cancel();
						Intent intent = new Intent(SendDetailActivty.this, CodeCaptureActivity.class);
						intent.putExtra("ActivityType", GtsdpConfig.CODE_SEND);
						intent.putExtra("IsRepeat", true);
						startActivityForResult(intent, SCAN_SQCODE);
						overridePendingTransition(R.anim.right_in, R.anim.none);
					}
					
					@Override
					public void onLeftButtonClick(GtsdpTwoButtonDialog dialog) {
						dialog.cancel();
					}
				});
				dialog.show();
			}
			else
			{
				showTextDialog(baseResult.getMsg());
			}
			break;
		}
	}

	@Override
	protected void callBackForServerSuccess(HemaNetTask netTask,
			HemaBaseResult baseResult) {
		//TODO ��֤�ռ��˵绰->��֤�����˵绰->�ύ��������->�ϴ���Ʒ��Ʒ 
		GtsdpHttpInformation information = (GtsdpHttpInformation)netTask.getHttpInformation();
		switch (information) {
		case CLIENT_VERIFY:
			if(CouldCommit)
			{
				getNetWorker().transAdd(token, receiver_name, receiver_address, receiver_telphone, sender_name, sender_address, sender_telphone, SQCode);
			}
			else
			{//������֤�������ֻ���
				getNetWorker().clientVerify(sender_telphone);
				CouldCommit = true;//�������ѭ����
			}
			break;
		case TRANS_ADD:
			//�����ύ�ɹ����ϴ�ͼƬ
			HemaArrayResult<String> result = (HemaArrayResult<String>)baseResult;
			showTextDialog(result.getObjects().get(0));
			break;
		}
	}

	@Override
	protected void callBeforeDataBack(HemaNetTask arg0) {
	}

	@Override
	protected void getExras() {
		SQCode = getIntent().getStringExtra("SQCode");

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK) {
			switch (requestCode) {
			case REQUEST_CODE_PICK_IMAGE:
				album(data);
				break;
			case REQUEST_CODE_CAPTURE_CAMEIA:
				camera();
				break;
			case SENDER: {
				String Name = data.getStringExtra("Name");
				String Address = data.getStringExtra("Address");
				String Phone = data.getStringExtra("Phone");
				editSenderName.setText(Name);
				txtSenderAddress.setText(Address);
				editSenderPhone.setText(Phone);
			}
				break;
			case RECIVER: {
				String Name = data.getStringExtra("Name");
				String Address = data.getStringExtra("Address");
				String Phone = data.getStringExtra("Phone");
				editReciverName.setText(Name);
				txtReciverAddress.setText(Address);
				editReciverPhone.setText(Phone);
			}
				break;
			case SCAN_SQCODE:
				SQCode = data.getStringExtra("SQCode");
				clickConfirm();//�����ύ����
				break;
			}
		}
	}
	/**
	 * ��������ĺ�������
	 */
	private void camera() {
		String imagepath = imageWay.getCameraImage();
		new CompressPicTask().execute(imagepath);
	}

	/**
	 * �Զ������ѡ��ʱ������
	 * @param data
	 */
	private void album(Intent data) {
		if (data == null)
			return;
		ArrayList<String> imgList = data.getStringArrayListExtra("images");
		if (imgList == null)
			return;
		for (String img : imgList) {
			log_i(img);
			new CompressPicTask().execute(img);
		}
	}
	
	@Override
	protected void setListener() {
		txtNext.setOnClickListener(this);
		imageQuitActivity.setOnClickListener(this);
		txtSendProtocol.setOnClickListener(this);
		txtSenderTemplet.setOnClickListener(this);
		txtReciverTemplet.setOnClickListener(this);
		btnSend.setOnClickListener(this);
		layoutReciverAddress.setOnClickListener(this);
		layoutSendAddress.setOnClickListener(this);
	}

	@Override
	protected void findView() {
		txtTitle = (TextView) findViewById(R.id.txtTitle);
		txtTitle.setText("����");
		txtNext = (TextView) findViewById(R.id.txtNext);
		txtNext.setVisibility(View.GONE);
		imageQuitActivity = (ImageView) findViewById(R.id.imageQuitActivity);
		txtSendProtocol = (TextView) findViewById(R.id.txtSendProtocol);
		txtSenderTemplet = (TextView) findViewById(R.id.txtSenderTemplet);
		txtReciverTemplet = (TextView) findViewById(R.id.txtReciverTemplet);
		editReciverName = (EditText) findViewById(R.id.editReciverName);
		txtSenderAddress = (TextView) findViewById(R.id.txtSenderAddress);
		editReciverPhone = (EditText) findViewById(R.id.editReciverPhone);
		editSenderName = (EditText) findViewById(R.id.editSenderName);
		txtReciverAddress = (TextView) findViewById(R.id.txtReciverAddress);
		editSenderPhone = (EditText) findViewById(R.id.editSenderPhone);
		detialMainLinear = findViewById(R.id.detialMainLinear);
		btnSend = (Button) findViewById(R.id.btnSend);
		isAgree = (CheckBox) findViewById(R.id.isAgree);
		layoutReciverAddress = (LinearLayout) findViewById(R.id.layoutReciverAddress);
		layoutSendAddress = (LinearLayout) findViewById(R.id.layoutSenderAddress);
		gridView = (GridView) findViewById(R.id.gridview);
	}

	@Override
	public void onClick(View v) {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);  
		imm.hideSoftInputFromWindow(editReciverName.getWindowToken(), 0); //ǿ�����ؼ���  
		
		Intent intent;
		switch (v.getId()) {
		case R.id.imageQuitActivity:
			finish(R.anim.none, R.anim.right_out);
			break;
		case R.id.layoutReciverAddress:
			myDistrictPicker.setOnClickListener(clickReciver);
			myDistrictPicker.showAtLocation(
					SendDetailActivty.this.findViewById(R.id.detialMainLinear),
					Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
			break;
		case R.id.layoutSenderAddress:
			myDistrictPicker.setOnClickListener(clickSender);
			myDistrictPicker.showAtLocation(
					SendDetailActivty.this.findViewById(R.id.detialMainLinear),
					Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);

			break;
		case R.id.txtSenderTemplet:
			intent = new Intent(this, TemplateListActivty.class);
			intent.putExtra("ActivityType", SENDER);
			startActivityForResult(intent, SENDER);
			overridePendingTransition(R.anim.right_in, R.anim.none);
			break;
		case R.id.txtReciverTemplet:
			intent = new Intent(this, TemplateListActivty.class);
			intent.putExtra("ActivityType", RECIVER);
			startActivityForResult(intent, RECIVER);
			overridePendingTransition(R.anim.right_in, R.anim.none);
			break;
		case R.id.txtSendProtocol:
			intent = new Intent(this, WebViewActivity.class);
			intent.putExtra("Title", "����Э��");
			startActivity(intent);
			overridePendingTransition(R.anim.right_in, R.anim.none);
			break;
		case R.id.btnSend:
			clickConfirm();
			break;
		}
	}
	
	@Override
	protected boolean onKeyBack() {
		finish(R.anim.none, R.anim.right_out);
		return super.onKeyBack();
	}
	

	/**
	 * ����ռ��˵�ַ
	 */
	private OnClickListener clickReciver = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (v.getId() == R.id.btnDistrictPickerCancel) {
				myDistrictPicker.dismiss();
				return;
			}
			if (v.getId() == R.id.btnDistrictPickerClose) {
				String address = myDistrictPicker.getDistrcictString();
				txtReciverAddress.setText(address);
			}
			myDistrictPicker.dismiss();
		}
	};
	/**
	 * ��������˵�ַ
	 */
	private OnClickListener clickSender = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (v.getId() == R.id.btnDistrictPickerCancel) {
				myDistrictPicker.dismiss();
				return;
			}
			if (v.getId() == R.id.btnDistrictPickerClose) {
				String address = myDistrictPicker.getDistrcictString();
				txtSenderAddress.setText(address);
			}
			myDistrictPicker.dismiss();
		}
	};

	/**
	 * ���ѡ��ͼƬ��Դ
	 * 
	 */
	private OnClickListener clickFace = new OnClickListener() {
		@Override
		public void onClick(View v) {
			popupWindow.dismiss();
			switch(v.getId())
			{
			case R.id.btnTop:
				imageWay.camera();
				break;
			case R.id.btnMiddle:
				imageWay.album();
				break;
			}
		}
	};
	
	public void showImageWay() 
	{
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);  
		imm.hideSoftInputFromWindow(editReciverName.getWindowToken(), 0); //ǿ�����ؼ���  
		popupWindow.showAtLocation(detialMainLinear, Gravity.BOTTOM, 0, 0);
	}
	/**
	 * ѹ��ͼƬƬ
	 */
	private class CompressPicTask extends AsyncTask<String, Void, Integer> {
		String compressPath;

		@Override
		protected Integer doInBackground(String... params) {
			try {
				String path = params[0];
				String savedir = XtomFileUtil.getTempFileDir(mContext);
				compressPath = XtomImageUtil.compressPictureWithSaveDir(path,
						GtsdpConfig.IMAGE_HEIGHT, GtsdpConfig.IMAGE_WIDTH,
						GtsdpConfig.IMAGE_QUALITY, savedir, mContext);
				return 0;
			} catch (IOException e) {
				return 1;
			}
		}

		@Override
		protected void onPreExecute() {
			showProgressDialog("����ѹ��ͼƬ");
		}

		@Override
		protected void onPostExecute(Integer result) {
			cancelProgressDialog();
			switch (result) {
			case 0:
				images.add(compressPath);
				imageAdapter.notifyDataSetChanged();
				break;
			case 1:
				showTextDialog("ͼƬѹ��ʧ��");
				break;
			}
		}
	}
	/**
	 * ���ȷ��
	 */
	private void clickConfirm()
	{
		token = getApplicationContext().getUser().getToken();
		receiver_name = editReciverName.getEditableText().toString().trim();
		receiver_address = txtReciverAddress.getText().toString().trim();
		receiver_telphone = editReciverPhone.getEditableText().toString().trim();
		sender_name = editSenderName.getEditableText().toString().trim();
		sender_address = txtSenderAddress.getText().toString().trim();
		sender_telphone = editSenderPhone.getEditableText().toString().trim();
		CouldCommit = false;//һ��Ҫ
		if("".equals(receiver_name))
		{
			showTextDialog("����д�ռ�������");
			return;
		}
		if("".equals(receiver_address))
		{
			showTextDialog("����д�ռ��˵�ַ");
			return;
		}
		if("".equals(receiver_telphone))
		{
			showTextDialog("����д�ռ��˵绰");
			return;
		}
		else if(!GtsdpUtil.checkPhoneNumber(receiver_telphone))
		{
			showTextDialog("�ռ��˵绰��ʽ����ȷ");
			return;
		}
		if("".equals(sender_name))
		{
			showTextDialog("����д����������");
			return;
		}
		if("".equals(sender_address))
		{
			showTextDialog("����д�����˵�ַ");
			return;
		}
		if("".equals(sender_telphone))
		{
			showTextDialog("����д�����˵绰");
			return;
		}
		else if(!GtsdpUtil.checkPhoneNumber(sender_telphone))
		{
			showTextDialog("�����˵绰��ʽ����ȷ");
			return;
		}
		if(!isAgree.isChecked())
		{
			showTextDialog("���Ķ���ͬ�⡶����Э�顷");
			return;
		}
		// TODO
		getNetWorker().clientVerify(receiver_telphone);
		showProgressDialog("�ύ��");
	}
	
}
