   package com.hemaapp.hm_gtsdp.activity;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.hemaapp.GtsdpConfig;
import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpActivity;
import com.hemaapp.hm_gtsdp.GtsdpApplication;
import com.hemaapp.hm_gtsdp.dialog.GtsdpTwoButtonDialog;
import com.hemaapp.hm_gtsdp.dialog.GtsdpTwoButtonDialog.OnButtonListener;
import com.hemaapp.hm_gtsdp.model.User;
import com.hemaapp.hm_gtsdp.zxing.camera.CameraManager;
import com.hemaapp.hm_gtsdp.zxing.decoding.CaptureActivityHandler;
import com.hemaapp.hm_gtsdp.zxing.decoding.InactivityTimer;
import com.hemaapp.hm_gtsdp.zxing.decoding.RGBLuminanceSource;
import com.hemaapp.hm_gtsdp.zxing.view.ViewfinderView;

/**
 * ɨ���ά��
 * @author Wen
 * @author HuFanglin
 *
 */
@SuppressLint("HandlerLeak") @SuppressWarnings("deprecation")
public class CodeCaptureActivity extends GtsdpActivity implements Callback, OnClickListener {
	private static final int CAMERA = 100;
	private static final int PARSE_BARCODE_SUC = 300;
	private static final int PARSE_BARCODE_FAIL = 303;
	
	private Intent beforeIntent;
	private int ActivityType;//�������ͣ��ο�GtsdpConfig
	private ImageView imageQuitActivity, imageOpenLight;
	
	private TextView left, title, txtTop, txtBottom, txtTitle; 
	private Button right;
	private LinearLayout layout_empty; //δ��¼����
	
	private RelativeLayout layout1;//�ѵ�¼����
	private CaptureActivityHandler handler;
	private ViewfinderView viewfinderView;
	private boolean hasSurface;
	private Vector<BarcodeFormat> decodeFormats;
	private String characterSet;
	private InactivityTimer inactivityTimer;
	private int sign;

	private ProgressDialog mProgress;
	private String photo_path;
	private Bitmap scanBitmap;
	
	private User user;

	private GtsdpTwoButtonDialog dialog;
	/** Called when the activity is first created. */
	@SuppressLint("CutPasteId") @Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_capture);
		super.onCreate(savedInstanceState);
		
		layout_empty = (LinearLayout) findViewById(R.id.layout);
		
		layout1 = (RelativeLayout) findViewById(R.id.layout1);
		init();
		CameraManager.init(getApplication());
		hasSurface = false;
		inactivityTimer = new InactivityTimer(this);
		viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
	}

	private void init(){
		user = GtsdpApplication.getInstance().getUser();
		if(user == null){
			layout_empty.setVisibility(View.VISIBLE);
			layout1.setVisibility(View.GONE);
		}else 
		{
			layout_empty.setVisibility(View.GONE);
			layout1.setVisibility(View.VISIBLE);
		}
	}
	private Handler mHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

			mProgress.dismiss();
			switch (msg.what) {
			case PARSE_BARCODE_SUC:
				onResultHandler((String)msg.obj, scanBitmap);
				break;
			case PARSE_BARCODE_FAIL:
				Toast.makeText(CodeCaptureActivity.this, (String)msg.obj, Toast.LENGTH_LONG).show();
				break;

			}
		}

	};

	/**
	 * ����ɨ�����������ת����һ��ҳ��
	 * @param resultString
	 * @param bitmap
	 */
	private void onResultHandler(String resultString, Bitmap bitmap){
		if(TextUtils.isEmpty(resultString)){
			Toast.makeText(CodeCaptureActivity.this, "ɨ��ʧ�ܣ�", Toast.LENGTH_SHORT).show();
			return;
		}
//		Intent resultIntent = new Intent(CodeCaptureActivity.this ,CodeIfoActivity.class);
//		Bundle bundle = new Bundle();
//		bundle.putString("result", resultString);
//		bundle.putParcelable("bitmap", bitmap);
//		resultIntent.putExtras(bundle);
//		startActivity(resultIntent);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK){
			switch(requestCode){
			case CAMERA:
				//��ȡѡ��ͼƬ��·��
				Uri uri = data.getData();
				Cursor cursor = getContentResolver().query(data.getData(), null, null, null, null);
				if (cursor.moveToFirst()) {
					photo_path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
				}
				cursor.close();

				mProgress = new ProgressDialog(CodeCaptureActivity.this);
				mProgress.setMessage("����ɨ��...");
				mProgress.setCancelable(false);
				mProgress.show();

				new Thread(new Runnable() {
					@Override
					public void run() {
						Result result = scanningImage(photo_path);
						if (result != null) {
							Message m = mHandler.obtainMessage();
							m.what = PARSE_BARCODE_SUC;
							m.obj = result.getText();
							mHandler.sendMessage(m);
						} else {
							Message m = mHandler.obtainMessage();
							m.what = PARSE_BARCODE_FAIL;
							m.obj = "Scan failed!";
							mHandler.sendMessage(m);
						}
					}
				}).start();

				break;

			}
		}
	}
	/**
	 * ɨ���ά��ͼƬ�ķ���
	 * @param path
	 * @return
	 */
	public Result scanningImage(String path) {
		if(TextUtils.isEmpty(path)){
			return null;
		}
		Hashtable<DecodeHintType, String> hints = new Hashtable<DecodeHintType, String>();
		hints.put(DecodeHintType.CHARACTER_SET, "UTF8"); //���ö�ά�����ݵı���

		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true; // �Ȼ�ȡԭ��С
		scanBitmap = BitmapFactory.decodeFile(path, options);
		options.inJustDecodeBounds = false; // ��ȡ�µĴ�С
		int sampleSize = (int) (options.outHeight / (float) 200);
		if (sampleSize <= 0)
			sampleSize = 1;
		options.inSampleSize = sampleSize;
		scanBitmap = BitmapFactory.decodeFile(path, options);
		RGBLuminanceSource source = new RGBLuminanceSource(scanBitmap);
		BinaryBitmap bitmap1 = new BinaryBitmap(new HybridBinarizer(source));
		QRCodeReader reader = new QRCodeReader();
		try {
			return reader.decode(bitmap1, hints);

		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (ChecksumException e) {
			e.printStackTrace();
		} catch (FormatException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	protected void onResume() {
		super.onResume();
		SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
		SurfaceHolder surfaceHolder = surfaceView.getHolder();
		if (hasSurface) {
			initCamera(surfaceHolder);
		} else {
			surfaceHolder.addCallback(this);
			surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		}
		decodeFormats = null;
		characterSet = null;

		AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
		if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
		}

	}

	@Override
	protected void onPause() {
		super.onPause();
		if (handler != null) {
			handler.quitSynchronously();
			handler = null;
		}
		CameraManager.get().closeDriver();
	}

	@Override
	protected void onDestroy() {
		inactivityTimer.shutdown();
		super.onDestroy();
	}

	/**
	 * ����ɨ����
	 * @param result
	 * @param barcode
	 */
	public void handleDecode(Result result, Bitmap barcode) {
		inactivityTimer.onActivity();
		String resultString = result.getText();
		if (resultString.equals("")) 
		{
			showTextDialog("ɨ��ʧ��");
		}else {
//			Intent resultIntent = new Intent(CodeCaptureActivity.this ,CodeIfoActivity.class);
//			Bundle bundle = new Bundle();
//			bundle.putString("result", resultString);
//			bundle.putParcelable("bitmap", barcode);
//			resultIntent.putExtras(bundle);
//			startActivity(resultIntent);
			dialog.show();
		}
	}

	private void initCamera(SurfaceHolder surfaceHolder) {
		try {
			CameraManager.get().openDriver(surfaceHolder);
		} catch (IOException ioe) {
			return;
		} catch (RuntimeException e) {
			return;
		}
		if (handler == null) {
			handler = new CaptureActivityHandler(this, decodeFormats,
					characterSet);
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (!hasSurface) {
			hasSurface = true;
			initCamera(holder);
		}

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		hasSurface = false;

	}

	public ViewfinderView getViewfinderView() {
		return viewfinderView;
	}

	public Handler getHandler() {
		return handler;
	}

	public void drawViewfinder() {
		viewfinderView.drawViewfinder();

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
		imageOpenLight = (ImageView)findViewById(R.id.imageOpenLight);
		txtTop = (TextView)findViewById(R.id.txtTop);
		txtBottom = (TextView)findViewById(R.id.txtBottom);
		txtTitle = (TextView)findViewById(R.id.txtTitle);

		dialog = new GtsdpTwoButtonDialog(mContext);
		dialog.setButtonListener(buttonListener);
		dialog.setRightButtonTextColor(GtsdpConfig.Main_Blue);
		dialog.cancel();
		switch(ActivityType)
		{
		case -1:
			showTextDialog("�����������");
			break;
		case GtsdpConfig.CODE_GET:
			txtTitle.setText("�ջ�");
			txtTop.setText("ɨ���ά��ȷ�Ͻӻ�/�ջ�");
			txtBottom.setText("");
			dialog.setText("ȷ���ջ���");
			break;
		case GtsdpConfig.CODE_SITE:
			txtTitle.setText("����");
			txtTop.setText("ɨ���ά��ӵ�");
			txtBottom.setText("");
			dialog.setText("ȷ�Ͻӵ���");
			break;
		case GtsdpConfig.CODE_SEND:
			dialog.setText("ȷ����д��ϸ��Ϣ��");
			break;
		}
	}

	@Override
	protected void getExras() {
		beforeIntent = getIntent();
		ActivityType = beforeIntent.getIntExtra("ActivityType", -1);
	}

	@Override
	protected void setListener() {
		imageQuitActivity.setOnClickListener(this);
		imageOpenLight.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) 
	{
		switch(v.getId())
		{
		case R.id.imageQuitActivity:
			finish();
			break;
		case R.id.imageOpenLight:
			if (sign % 2 == 0) {
				CameraManager.get().openF();
			} else {
				CameraManager.get().stopF();
			}
			sign++;
			break;
		}
	}
	/**
	 * �������
	 */
	private OnButtonListener buttonListener = new OnButtonListener() {
		
		@Override
		public void onRightButtonClick(GtsdpTwoButtonDialog dialog) {
			dialog.cancel();
			clickRightButton();
		}
		
		@Override
		public void onLeftButtonClick(GtsdpTwoButtonDialog dialog) {
			dialog.cancel();
		}
	};
	
	protected boolean onKeyBack() {
		finish();
		return super.onKeyBack();
	};
	
	/**
	 * ɨ���ά��֮�����������ȷ��
	 * ��Ҫ������������֣�ִ�в�ͬ�Ĳ���
	 */
	private void clickRightButton()
	{
		//���¿�ʼɨ��
		if (handler != null) 
		{
			handler.quitSynchronously();
			handler = null;
		}
		CameraManager.get().closeDriver();
		onResume();
		Intent intent;
		switch(ActivityType)
		{
		case GtsdpConfig.CODE_SEND:
			intent = new Intent(this, SendDetailActivty.class);
			startActivity(intent);
			overridePendingTransition(R.anim.right_in, R.anim.none);
			finish();
			break;
		case GtsdpConfig.CODE_SITE:
			showTextDialog("�ӻ��ɹ�");
			break;
			
		case GtsdpConfig.CODE_CURSOR:
			showTextDialog("�ӻ��ɹ�");
			break;
		}
	}

}