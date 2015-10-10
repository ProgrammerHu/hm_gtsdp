package com.hemaapp.hm_gtsdp.activity;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;

import xtom.frame.image.load.XtomImageTask;
import xtom.frame.image.load.XtomImageTask.Size;
import xtom.frame.util.XtomBaseUtil;
import xtom.frame.util.XtomFileUtil;
import xtom.frame.util.XtomImageUtil;
import xtom.frame.util.XtomSharedPreferencesUtil;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.content.Loader.OnLoadCompleteListener;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.hemaapp.GtsdpConfig;
import com.hemaapp.hm_FrameWork.HemaImageWay;
import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaArrayResult;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_FrameWork.view.RoundedImageView;
import com.hemaapp.hm_FrameWork.view.ShowLargeImageView;
import com.hemaapp.hm_gtsdp.GtsdpActivity;
import com.hemaapp.hm_gtsdp.GtsdpHttpInformation;
import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_gtsdp.db.UserDBHelper;
import com.hemaapp.hm_gtsdp.model.User;
import com.hemaapp.hm_gtsdp.view.SelectPopupWindow;

public class UserCenterActivity extends GtsdpActivity implements
		OnClickListener {
	private final int REQUEST_CODE_PICK_IMAGE = 1;// ����ȡ
	private final int REQUEST_CODE_CAPTURE_CAMEIA = 2;// �����ȡ
	private final int EDIT_IMAGE = 3;// ����ͼƬ

	private RelativeLayout insideFather, view1, father;
	private ScrollView scrollview;
	private LinearLayout layoutBottom, layoutHead;
	private View imageQuitActivity, imageSetting, layoutMyAccount,
			layoutMyOrder, layoutChangePwd, layoutPwdSafety, layoutOrders;
	private SelectPopupWindow selectPop;
	private TextView txtUsername;
	private String tempPath;// ����ͼƬ·��
	private String imagePathCamera;// �������·��
	private RoundedImageView image_avatar;
	private boolean IsChangeImage = false;
	private User user;
	private HemaImageWay imageWay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_usercenter);
		super.onCreate(savedInstanceState);
		init();

		imageWay = new HemaImageWay(mContext, 1, 2) {
			@Override
			public void album() {
				// ע�⣺������д�÷�����ʹ��ϵͳ���ѡȡ(��Ӧ��onActivityResult�еĴ�����ҲӦ��ͬ)
				Intent it = new Intent(mContext, AlbumActivity.class);
				it.putExtra("limitCount", 1);// ͼƬѡ����������
				startActivityForResult(it, albumRequestCode);
			}
		};
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
	protected void callBackForServerSuccess(HemaNetTask nettask,
			HemaBaseResult result) {
		GtsdpHttpInformation infomation = (GtsdpHttpInformation) nettask
				.getHttpInformation();

		switch (infomation) {
		case FILE_UPLOAD:
			String userName = XtomSharedPreferencesUtil.get(mContext,
					"username");
			String pwd = XtomSharedPreferencesUtil.get(mContext, "password");
			getNetWorker().clientLogin(userName, pwd);// ���µ�½����ͷ������
			break;
		case CLIENT_LOGIN:
			cancelProgressDialog();
			break;
		case CLIENT_GET:
			HemaArrayResult<User> successResult = (HemaArrayResult<User>) result;
			String Avatar = successResult.getObjects().get(0).getAvatar();
			String AvatarBig = successResult.getObjects().get(0).getAvatarbig();
			LoadImage(Avatar, AvatarBig);
			User user = successResult.getObjects().get(0);
			new UserDBHelper(mContext).update(user);//�����û�����
			break;
		}

	}

	@Override
	protected void callBeforeDataBack(HemaNetTask nettask) {
		GtsdpHttpInformation infomation = (GtsdpHttpInformation) nettask
				.getHttpInformation();
		switch (infomation) {
		case FILE_UPLOAD:
			showProgressDialog("ͼƬ�ϴ���");
			break;
		}

	}

	@Override
	protected void findView() {
		scrollview = (ScrollView) findViewById(R.id.scrollview);
		insideFather = (RelativeLayout) findViewById(R.id.insideFather);
		layoutBottom = (LinearLayout) findViewById(R.id.layoutBottom);
		father = (RelativeLayout) findViewById(R.id.father);
		view1 = (RelativeLayout) findViewById(R.id.view1);
		layoutHead = (LinearLayout) findViewById(R.id.layoutHead);
		imageQuitActivity = findViewById(R.id.imageQuitActivity);
		imageSetting = findViewById(R.id.imageSetting);

		layoutMyAccount = findViewById(R.id.layoutMyAccount);
		layoutMyOrder = findViewById(R.id.layoutMyOrder);
		layoutChangePwd = findViewById(R.id.layoutChangePwd);
		layoutPwdSafety = findViewById(R.id.layoutPwdSafety);
		layoutOrders = findViewById(R.id.layoutOrders);
		image_avatar = (RoundedImageView) findViewById(R.id.imageHead);
		txtUsername = (TextView) findViewById(R.id.txtUsername);
		txtUsername.setText(user.getNickname());
	}

	@Override
	protected void getExras() {
		user = getApplicationContext().getUser();

	}

	@Override
	protected void setListener() {
		imageQuitActivity.setOnClickListener(this);
		imageSetting.setOnClickListener(this);
		layoutMyAccount.setOnClickListener(this);
		layoutMyOrder.setOnClickListener(this);
		layoutChangePwd.setOnClickListener(this);
		layoutPwdSafety.setOnClickListener(this);
		layoutOrders.setOnClickListener(this);
		image_avatar.setOnClickListener(this);
		image_avatar.setOnLongClickListener(new OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				String iPath = (String) v.getTag(R.id.TAG);
				ShowLargeImageView mView = new ShowLargeImageView(mContext,
						findViewById(R.id.father));
				mView.show();
				if (IsChangeImage)
					mView.setImagePath(tempPath);
				else
					mView.setImageURL(iPath);
				return false;
			}
		});
	}

	private void init() {
		int w = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		scrollview.measure(w, h);
		insideFather.measure(w, h);
		view1.measure(w, h);
		layoutHead.measure(w, h);
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int insideHeight = insideFather.getMeasuredHeight();
		if (insideHeight < getScreenHeight() - view1.getMeasuredHeight()
				- layoutHead.getMeasuredHeight()) {
			int maxHight = getScreenHeight() - view1.getMeasuredHeight()
					- layoutHead.getMeasuredHeight();
			FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) insideFather
					.getLayoutParams();
			params.height = maxHight;
			insideFather.setLayoutParams(params);
		}

	}

	/**
	 * ��ȡȥ������������Ļ�߶�
	 * 
	 * @return
	 */
	private int getScreenHeight() {
		int statusBarHeight = getStatusBarHeight();
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int mScreenHeight = dm.heightPixels;
		return mScreenHeight - statusBarHeight;
	}

	/**
	 * ��ȡ�������ĸ߶�
	 * 
	 * @return
	 */
	private int getStatusBarHeight() {
		Class<?> c = null;
		Object obj = null;
		Field field = null;
		int x = 0, sbar = 0;
		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			sbar = getResources().getDimensionPixelSize(x);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return sbar;
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.imageQuitActivity:
			finish(R.anim.none, R.anim.right_out);
			break;
		case R.id.imageSetting:
			intent = new Intent(UserCenterActivity.this, SettingsActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.right_in, R.anim.none);
			break;
		case R.id.layoutMyAccount:
			intent = new Intent(UserCenterActivity.this,
					MyAccountActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.right_in, R.anim.none);
			break;
		case R.id.layoutMyOrder:
			intent = new Intent(UserCenterActivity.this,
					AllOrdersActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.right_in, R.anim.none);
			break;
		case R.id.layoutChangePwd:
			intent = new Intent(UserCenterActivity.this,
					PwdManangeActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.right_in, R.anim.none);
			break;
		case R.id.layoutPwdSafety:// �ܱ�����
			intent = new Intent(UserCenterActivity.this,
					CheckPhoneActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.right_in, R.anim.none);
			break;
		case R.id.layoutOrders:// ���Ͷ���
			intent = new Intent(UserCenterActivity.this,
					DispatchingActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.right_in, R.anim.none);
			break;
		case R.id.imageHead:
			selectPop = new SelectPopupWindow(this, this, "����", "�����ѡ��");
			selectPop.showAtLocation(findViewById(R.id.father), Gravity.CENTER
					| Gravity.CENTER_HORIZONTAL, 0, 0);
			break;
		case R.id.btnTop:
			selectPop.dismiss();
//			getImageFromCamera();
			imageWay.camera();
			break;
		case R.id.btnMiddle:
			selectPop.dismiss();
			getImageFromAlbum();
			break;
		}

	}
//
//	/**
//	 * �������
//	 */
//	protected void getImageFromCamera() {
//		String state = Environment.getExternalStorageState();
//		if (state.equals(Environment.MEDIA_MOUNTED)) {
//			Intent getImageByCamera = new Intent(
//					"android.media.action.IMAGE_CAPTURE");
//			startActivityForResult(getImageByCamera,
//					REQUEST_CODE_CAPTURE_CAMEIA);
//		} else {
//			showTextDialog("��ȷ���Ѿ�����SD��");
//		}
//	}

	/**
	 * ������ȡͼƬ
	 */
	protected void getImageFromAlbum() {
		Intent intent = new Intent(Intent.ACTION_PICK);
		intent.setType("image/*");// ��Ƭ����
		startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
	}

	/**
	 * ��������ĺ�������
	 */
	private void camera() {
		String imagepath = imageWay.getCameraImage();
		editImage(imagepath, EDIT_IMAGE);
	}
	/**
	 * ͨ������ȡͼƬ�ĺ�������
	 * 
	 * @param data
	 */
	private void album(Intent data) {
		if (data == null)
			return;
		Uri selectedImageUri = data.getData();
		// ��ȡͼƬ·��
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
		// �������crop=true�������ڿ�����Intent��������ʾ��VIEW�ɲü�
		intent.putExtra("crop", "true");
		// aspectX aspectY �ǿ�ߵı���
		intent.putExtra("aspectX", GtsdpConfig.IMAGE_WIDTH);
		intent.putExtra("aspectY", GtsdpConfig.IMAGE_WIDTH);
		// outputX outputY �ǲü�ͼƬ���
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
		// ������sdCard
		tempPath = savedir + XtomBaseUtil.getFileName() + ".jpg";// ����·��
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
		if (resultCode != RESULT_OK)
			return;
		switch (requestCode) {
		case REQUEST_CODE_CAPTURE_CAMEIA:
			camera();
			break;
		case REQUEST_CODE_PICK_IMAGE:
			album(data);
			break;
		case EDIT_IMAGE:
			image_avatar.setTag(R.id.TAG, tempPath);
			IsChangeImage = true;
			imageWorker.loadImage(new ImageTask(image_avatar, tempPath,
					mContext, new Size(180, 180)));
			if (selectPop != null)
				selectPop.dismiss();
			getNetWorker().fileUpload(
					getApplicationContext().getUser().getToken(), "1", "1",
					"0", "0", "��", tempPath);
			break;
		}
	}

	private class ImageTask extends XtomImageTask {

		public ImageTask(ImageView imageView, String path, Object context,
				Size size) {
			super(imageView, path, context, size);
		}

		public ImageTask(ImageView imageView, URL url, Object context, Size size) {
			super(imageView, url, context, size);
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

	@Override
	protected void onResume() {
		super.onResume();
		String Token = getApplicationContext().getUser().getToken();
		String ID = getApplicationContext().getUser().getId();
		getNetWorker().clientGet(Token, ID);
	}

	/**
	 * �����û�ͷ��
	 * 
	 * @param Avatar
	 *            Сͷ��
	 * @param AvatarBig
	 *            ��ͷ��
	 */
	private void LoadImage(String Avatar, String AvatarBig) {
		// �����û�ͷ��
		try {
			URL url = new URL(Avatar);
			ImageTask imageTask = new ImageTask(image_avatar, url, mContext,
					new Size(180, 180));
			imageWorker.loadImage(imageTask);
			image_avatar.setTag(R.id.TAG, AvatarBig);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
						GtsdpConfig.IMAGE_WIDTH, GtsdpConfig.IMAGE_WIDTH,
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
				tempPath = compressPath;
//				imageWorker.loadImage(new ImageTask(image_avatar, tempPath,
//						mContext, new Size(180, 180)));
//				IsChangeImage = true;
				break;
			case 1:
				showTextDialog("ͼƬѹ��ʧ��");
				break;
			}
		}
	}
}
