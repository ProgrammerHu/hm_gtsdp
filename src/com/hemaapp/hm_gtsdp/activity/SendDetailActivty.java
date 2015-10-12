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
 * 发货详情填写界面
 * 
 * @author Wen
 * @author HuFanglin
 * 
 */
public class SendDetailActivty extends GtsdpActivity implements OnClickListener {
	private final int REQUEST_CODE_PICK_IMAGE = 1;// 相册获取
	private final int REQUEST_CODE_CAPTURE_CAMEIA = 2;// 相机获取
	private final int SCAN_SQCODE = 3;//重新扫描二维码
	private final int SENDER = 100;//发件人
	private final int RECIVER = 200;//收件人

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

	private String SQCode;//二维码信息
	private String token, receiver_name, receiver_address, receiver_telphone, sender_name, sender_address, sender_telphone;
	private boolean CouldCommit = false;//true:可以提交数据，false:需要验证手机号
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
				// 注意：若不重写该方法则使用系统相册选取(对应的onActivityResult中的处理方法也应不同)
				Intent it = new Intent(mContext, AlbumActivity.class);
				it.putExtra("limitCount", 8 - images.size());// 图片选择张数限制
				startActivityForResult(it, albumRequestCode);
			}
		};
		

		popupWindow = new SelectPopupWindow(this, 
				clickFace, "拍照", "从相册选择");
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
				showTextDialog("发件人手机号没有注册");
			}
			else
			{
				showTextDialog("收件人手机号没有注册");
			}
			break;
		case TRANS_ADD:
			int errorCode = baseResult.getError_code();
			if(errorCode == 703)
			{//无效二维码
				GtsdpTwoButtonDialog dialog = new GtsdpTwoButtonDialog(mContext);
				dialog.setCancelable(true);
				dialog.setText("无效二维码，请重新扫描");
				dialog.setLeftButtonText("取消");
				dialog.setRightButtonText("确定");
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
		//TODO 验证收件人电话->验证发件人电话->提交订单数据->上传物品物品 
		GtsdpHttpInformation information = (GtsdpHttpInformation)netTask.getHttpInformation();
		switch (information) {
		case CLIENT_VERIFY:
			if(CouldCommit)
			{
				getNetWorker().transAdd(token, receiver_name, receiver_address, receiver_telphone, sender_name, sender_address, sender_telphone, SQCode);
			}
			else
			{//继续验证发件人手机号
				getNetWorker().clientVerify(sender_telphone);
				CouldCommit = true;//否则会死循环的
			}
			break;
		case TRANS_ADD:
			//订单提交成功，上传图片
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
				clickConfirm();//重新提交申请
				break;
			}
		}
	}
	/**
	 * 调用相机的后续处理
	 */
	private void camera() {
		String imagepath = imageWay.getCameraImage();
		new CompressPicTask().execute(imagepath);
	}

	/**
	 * 自定义相册选择时处理方法
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
		txtTitle.setText("发货");
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
		imm.hideSoftInputFromWindow(editReciverName.getWindowToken(), 0); //强制隐藏键盘  
		
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
			intent.putExtra("Title", "发货协议");
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
	 * 点击收件人地址
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
	 * 点击发件人地址
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
	 * 点击选择图片来源
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
		imm.hideSoftInputFromWindow(editReciverName.getWindowToken(), 0); //强制隐藏键盘  
		popupWindow.showAtLocation(detialMainLinear, Gravity.BOTTOM, 0, 0);
	}
	/**
	 * 压缩图片片
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
			showProgressDialog("正在压缩图片");
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
				showTextDialog("图片压缩失败");
				break;
			}
		}
	}
	/**
	 * 点击确定
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
		CouldCommit = false;//一定要
		if("".equals(receiver_name))
		{
			showTextDialog("请填写收件人姓名");
			return;
		}
		if("".equals(receiver_address))
		{
			showTextDialog("请填写收件人地址");
			return;
		}
		if("".equals(receiver_telphone))
		{
			showTextDialog("请填写收件人电话");
			return;
		}
		else if(!GtsdpUtil.checkPhoneNumber(receiver_telphone))
		{
			showTextDialog("收件人电话格式不正确");
			return;
		}
		if("".equals(sender_name))
		{
			showTextDialog("请填写发件人姓名");
			return;
		}
		if("".equals(sender_address))
		{
			showTextDialog("请填写发件人地址");
			return;
		}
		if("".equals(sender_telphone))
		{
			showTextDialog("请填写发件人电话");
			return;
		}
		else if(!GtsdpUtil.checkPhoneNumber(sender_telphone))
		{
			showTextDialog("发件人电话格式不正确");
			return;
		}
		if(!isAgree.isChecked())
		{
			showTextDialog("请阅读并同意《发货协议》");
			return;
		}
		// TODO
		getNetWorker().clientVerify(receiver_telphone);
		showProgressDialog("提交中");
	}
	
}
