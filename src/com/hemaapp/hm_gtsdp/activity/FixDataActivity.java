package com.hemaapp.hm_gtsdp.activity;

import java.io.File;
import java.io.IOException;

import xtom.frame.image.load.XtomImageTask;
import xtom.frame.image.load.XtomImageTask.Size;
import xtom.frame.util.XtomBaseUtil;
import xtom.frame.util.XtomFileUtil;
import xtom.frame.util.XtomImageUtil;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hemaapp.GtsdpConfig;
import com.hemaapp.hm_gtsdp.GtsdpHttpInformation;
import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_FrameWork.HemaImageWay;
import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaArrayResult;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_FrameWork.view.RoundedImageView;
import com.hemaapp.hm_gtsdp.GtsdpActivity;
import com.hemaapp.hm_gtsdp.view.SelectPopupWindow;

import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.content.Loader.OnLoadCompleteListener;
import android.text.InputType;


public class FixDataActivity extends GtsdpActivity implements OnClickListener{
	private final int REQUEST_CODE_PICK_IMAGE = 1;//相册获取
	private final int REQUEST_CODE_CAPTURE_CAMEIA = 2;//相机获取
	private  final int EDIT_IMAGE = 3;//剪裁图片
	
	private final int INPUT_SICK_NAME = 4;//输入昵称
	private final int SELECT_ADDRESS = 5;//选择地址
	
	private TextView txtTitle, txtNext, txtSex, txtSickName, txtAddress;
	private ImageView imageQuitActivity;
	private RelativeLayout layoutAddress, layoutSex, layoutHead, layoutSickName;
	
	private SelectPopupWindow selectPop;
	
	private String tempPath;//保存图片路径
	
	private String imagePathCamera;//相机拍照路径
	private RoundedImageView image_avatar;
	private HemaImageWay imageWay;
	
	private String username;//用户名（手机号）
	private String temp_token;//临时令牌
	private String password;//密码
	
	private String token;//正式令牌
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_fixdata);
		super.onCreate(savedInstanceState);
		imageWay = new HemaImageWay(mContext, 1, 2) {
			@Override
			public void album() {
				// 注意：若不重写该方法则使用系统相册选取(对应的onActivityResult中的处理方法也应不同)
				Intent it = new Intent(mContext, AlbumActivity.class);
				it.putExtra("limitCount", 1);// 图片选择张数限制
				startActivityForResult(it, albumRequestCode);
			}
		};
	}

	@Override
	protected void findView() {
		txtTitle = (TextView)findViewById(R.id.txtTitle);
		txtTitle.setText("完善资料");
		txtNext = (TextView)findViewById(R.id.txtNext);
		txtNext.setText("提交");
		imageQuitActivity = (ImageView)findViewById(R.id.imageQuitActivity);
		txtSex = (TextView)findViewById(R.id.txtSex);
		layoutAddress = (RelativeLayout)findViewById(R.id.layoutAddress);
		layoutSex = (RelativeLayout)findViewById(R.id.layoutSex);
		layoutHead = (RelativeLayout)findViewById(R.id.layoutHead);
		layoutSickName = (RelativeLayout)findViewById(R.id.layoutSickName);
		image_avatar = (RoundedImageView)findViewById(R.id.imageHead);
		txtSickName = (TextView)findViewById(R.id.txtSickName);
		txtAddress = (TextView)findViewById(R.id.txtAddress);
	}

	@Override
	protected void getExras() 
	{
		username = getIntent().getStringExtra("username");
		temp_token = getIntent().getStringExtra("temp_token");
		password = getIntent().getStringExtra("password");
	}

	@Override
	protected void setListener() {
		layoutAddress.setOnClickListener(this);
		layoutSex.setOnClickListener(this);
		layoutHead.setOnClickListener(this);
		layoutSickName.setOnClickListener(this);
		imageQuitActivity.setOnClickListener(this);
		txtNext.setOnClickListener(this);
	}

	/**
	 * 当前界面的点击事件
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.layoutSex:
			selectPop = new SelectPopupWindow(FixDataActivity.this, clickSexItem, "男", "女");
			selectPop.showAtLocation(FixDataActivity.this.findViewById(R.id.FixedMainLayout), 
					Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
			break;
		case R.id.layoutHead:
			selectPop = new SelectPopupWindow(FixDataActivity.this, clickPhotoItem, "拍照", "从相册选择");
			selectPop.showAtLocation(FixDataActivity.this.findViewById(R.id.FixedMainLayout), 
					Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
			break;
		case R.id.layoutAddress:
		{
			Intent intent = new Intent(this, SelectAddressActivity.class);
			startActivityForResult(intent, SELECT_ADDRESS);
			overridePendingTransition(R.anim.right_in, R.anim.none);
		}
			break;
		case R.id.imageQuitActivity:
			finish(R.anim.none, R.anim.right_out);
			break;
		case R.id.layoutSickName:
		{
			Intent intent = new Intent(this, InputActivity.class);
			intent.putExtra("Title", "昵称");
			intent.putExtra("Next", "确定");
			intent.putExtra("InputType", InputType.TYPE_CLASS_TEXT);
			intent.putExtra("InputHint", "昵称");
			if(!txtSickName.getText().equals("请填写"))
				intent.putExtra("Text", txtSickName.getText());
			startActivityForResult(intent, INPUT_SICK_NAME);
			overridePendingTransition(R.anim.right_in, R.anim.none);
		}
			break;
		case R.id.txtNext:
			clickConfirm();
			break;
		}

	}
	/**
	 * 点击选择性别的事件
	 */
	private OnClickListener clickSexItem = new OnClickListener() {
		@Override
		public void onClick(View v) {
			selectPop.dismiss();
			if(v.getId() == R.id.btnTop)
			{
				txtSex.setText("男");
			}
			else if(v.getId() == R.id.btnMiddle)
			{
				txtSex.setText("女");
			}
		}
	};
	
	
	private OnClickListener clickPhotoItem = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if(v.getId() == R.id.btnTop)
			{
				imageWay.camera();
			}
			else if(v.getId() == R.id.btnMiddle)
			{
				getImageFromAlbum();
			}
		}
	};
	
	/**
	 * 从相册获取图片
	 */
	protected void getImageFromAlbum() 
	{  
	       Intent intent = new Intent(Intent.ACTION_PICK);  
	       intent.setType("image/*");//相片类型  
	       startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);  
	}  
	
	/**
	 * 通过相册获取图片的后续处理
	 * @param data
	 */
	private void album(Intent data) {
		if (data == null)
			return;
		Uri selectedImageUri = data.getData();
		// 获取图片路径
		String[] proj = { MediaStore.Images.Media.DATA };
		final CursorLoader loader = new CursorLoader(mContext);
		loader.setUri(selectedImageUri);
		loader.setProjection(proj);
		loader.registerListener(0, new OnLoadCompleteListener<Cursor>() {

			@Override
			public void onLoadComplete(Loader<Cursor> arg0, Cursor cursor) {
				int columnIndex = cursor
						.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
				cursor.moveToFirst();
				String imagepath = cursor.getString(columnIndex);
				editImage(imagepath, EDIT_IMAGE);
				loader.stopLoading();
				cursor.close();
			}
		});
		loader.startLoading();
	}
	private void editImage(String path, int requestCode) {
		File file = new File(path);
		startPhotoZoom(Uri.fromFile(file), requestCode);
	}
	private void startPhotoZoom(Uri uri, int requestCode) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.putExtra(MediaStore.EXTRA_OUTPUT, getTempUri());
		intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
		intent.putExtra("noFaceDetection", false);
		intent.setDataAndType(uri, "image/*");
		// 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
		intent.putExtra("crop", "true");
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", GtsdpConfig.IMAGE_WIDTH);
		intent.putExtra("aspectY", GtsdpConfig.IMAGE_WIDTH);
		// outputX outputY 是裁剪图片宽高
		intent.putExtra("outputX", GtsdpConfig.IMAGE_WIDTH);
		intent.putExtra("outputY", GtsdpConfig.IMAGE_WIDTH);
		intent.putExtra("return-data", false);
		startActivityForResult(intent, requestCode);
	}
	private Uri getTempUri() {
		return Uri.fromFile(getTempFile());
	}
	private File getTempFile() {
		String savedir = XtomFileUtil.getTempFileDir(mContext);
		File dir = new File(savedir);
		if (!dir.exists())
			dir.mkdirs();
		// 保存入sdCard
		tempPath = savedir + XtomBaseUtil.getFileName() + ".jpg";// 保存路径
		File file = new File(tempPath);
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		return file;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode != RESULT_OK)
			return;
		switch(requestCode)
		{
		case REQUEST_CODE_CAPTURE_CAMEIA:
			String imagepath = imageWay.getCameraImage();
			editImage(imagepath, EDIT_IMAGE);
			break;
		case REQUEST_CODE_PICK_IMAGE:
			album(data);
			break;
		case EDIT_IMAGE:
			imageWorker.loadImage(new ImageTask(image_avatar, tempPath,
					mContext, new Size(180, 180)));
			if(selectPop != null)
				selectPop.dismiss();
			break;
		case INPUT_SICK_NAME:
			String SickName = data.getStringExtra("Result");
			txtSickName.setText(SickName);
			break;
		case SELECT_ADDRESS:
			String address = data.getStringExtra("Address");
			txtAddress.setText(address);
			break;
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
	protected void callBackForServerFailed(HemaNetTask netTask, HemaBaseResult baseResult) {
		GtsdpHttpInformation information = (GtsdpHttpInformation)netTask.getHttpInformation();
		switch (information) {
		case CLIENT_ADD:
			HemaArrayResult<String> result = (HemaArrayResult<String>)baseResult;
			
			showTextDialog(result.getObjects().get(0));
			break;

		}
		
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
	
	
	private class ImageTask extends XtomImageTask {

		public ImageTask(ImageView imageView, String path, Object context,
				Size size) {
			super(imageView, path, context, size);
		}
		@Override
		public void setBitmap(Bitmap bitmap) {
			bitmap = XtomImageUtil.getRoundedCornerBitmap(bitmap, 100);
			super.setBitmap(bitmap);
		}

	}

	@Override
	protected boolean onKeyBack() {
		finish(R.anim.none, R.anim.right_out);
		return super.onKeyBack();
	}
	/**
	 * 点击提交
	 */
	private void clickConfirm()
	{
		String sex = txtSex.getText().toString().trim();
		String nickname = txtSickName.getText().toString().trim();
		String address = txtAddress.getText().toString().trim();
		if(tempPath == null || "".equals(tempPath))
		{
			showTextDialog("请选择头像");
			return;
		}
		if("".equals(address))
		{
			showTextDialog("请选择详细地址");
			return;
		}
		getNetWorker().clientAdd(address, sex, address, nickname, address, sex, "117.6", "36.3");
	}
}
