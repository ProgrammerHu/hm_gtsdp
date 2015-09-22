package com.hemaapp.hm_gtsdp.activity;

import java.io.IOException;
import java.util.ArrayList;

import xtom.frame.image.load.XtomImageTask;
import xtom.frame.image.load.XtomImageTask.Size;
import xtom.frame.util.XtomFileUtil;
import xtom.frame.util.XtomImageUtil;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.c;
import com.amap.api.location.core.d;
import com.hemaapp.GtsdpConfig;
import com.hemaapp.hm_FrameWork.HemaImageWay;
import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_FrameWork.view.ShowLargeImageView;
import com.hemaapp.hm_gtsdp.GtsdpActivity;
import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_gtsdp.view.SelectPopupWindow;

/**
 * ����Ա��֤��Ϣ
 * @author Wen
 * @author HuFanglin
 *
 */
public class CourierIdentificationActivity extends GtsdpActivity implements OnClickListener{
	private final int REQUEST_CODE_PICK_IMAGE = 1;// ����ȡ
	private final int REQUEST_CODE_CAPTURE_CAMEIA = 2;// �����ȡ
	private boolean setFace = false;//����Ƿ�ѡ������
	private boolean setBack = false;//����Ƿ�ѡ����
	private boolean setPerson = false;//����Ƿ�ѡ���ֳ����֤
	
	private TextView txtTitle, txtNext;
	private ImageView imageQuitActivity, imageFace, imageBack, imagePerson;
	private ImageButton deleteFace, deleteBack, deletePerson;
//	private RelativeLayout layoutFace, layoutBack, layoutPerson;
	private int TempClickId;
	private SelectPopupWindow popupWindow;
	private HemaImageWay imageWay;

	private ShowLargeImageView mView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_findgoods_notcourier_identification);
		super.onCreate(savedInstanceState);
		popupWindow = new SelectPopupWindow(CourierIdentificationActivity.this, 
				clickFace, "����", "�����ѡ��");

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
		txtTitle.setText("��д��Ϣ");
		txtNext = (TextView)findViewById(R.id.txtNext);
		txtNext.setText("�ύ");
		imageFace = (ImageView)findViewById(R.id.imageFace);
		imageBack = (ImageView)findViewById(R.id.imageBack);
		imagePerson = (ImageView)findViewById(R.id.imagePerson);
		deleteFace = (ImageButton)findViewById(R.id.deleteFace);
		deleteBack = (ImageButton)findViewById(R.id.deleteBack);
		deletePerson = (ImageButton)findViewById(R.id.deletePerson);
	}

	@Override
	protected void getExras() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setListener() {
		imageQuitActivity.setOnClickListener(this);
		txtNext.setOnClickListener(this);
		imageFace.setOnClickListener(this);
		imageBack.setOnClickListener(this);
		imagePerson.setOnClickListener(this);
		deleteBack.setOnClickListener(this);
		deleteFace.setOnClickListener(this);
		deletePerson.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
		case R.id.txtNext:
			break;
		case R.id.imageQuitActivity:
			finish();
			break;
		case R.id.imageFace:
			clickImageView(v, setFace);break;
		case R.id.imageBack:
			clickImageView(v, setBack);break;
		case R.id.imagePerson:
			clickImageView(v, setPerson);break;
		case R.id.deleteFace:
			clearImageView(imageFace, deleteFace);
			setFace = false;
			break;
		case R.id.deleteBack:
			clearImageView(imageBack, deleteBack);
			setBack = false;
			break;
		case R.id.deletePerson:
			clearImageView(imagePerson, deletePerson);
			setPerson = false;
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK)
		{
			switch(requestCode)
			{
			case REQUEST_CODE_PICK_IMAGE:
				album(data);
				break;
			case REQUEST_CODE_CAPTURE_CAMEIA:
				camera();
				break;
			}
		}
	}
	private void clickImageView(View v, boolean param)
	{
		if(param)
		{//չʾͼƬ
			mView = new ShowLargeImageView(this, findViewById(R.id.father));
			mView.show();
			mView.setImagePath((String)v.getTag(R.id.TAG));
		}
		else
		{
			TempClickId = v.getId();	
			popupWindow.showAtLocation(findViewById(R.id.father), Gravity.BOTTOM, 0, 0);
		}
	}
	
	/**
	 * �����ѡ���ͼƬ
	 * @param imageView
	 * @param button
	 * @param param
	 */
	private void clearImageView(ImageView imageView, ImageButton button)
	{
		imageView.setImageResource(R.drawable.camera_big);
		button.setVisibility(View.GONE);
	}
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
				loadImage(compressPath);
				break;
			case 1:
				showTextDialog("ͼƬѹ��ʧ��");
				break;
			}
		}
	}
	/**
	 * ѹ��ͼƬ֮�����ͼƬ
	 */
	private void loadImage(String compressPath)
	{
		XtomImageTask imageTask ;
		switch (TempClickId) {
		case R.id.imageFace:
			imageTask =new XtomImageTask(imageFace, compressPath, mContext);
			deleteFace.setVisibility(View.VISIBLE);
			imageWorker.loadImage(imageTask);
			imageFace.setTag(R.id.TAG, compressPath);
			setFace = true;
			break;
		case R.id.imageBack:
			imageTask =new XtomImageTask(imageBack, compressPath, mContext);
			deleteBack.setVisibility(View.VISIBLE);
			imageWorker.loadImage(imageTask);
			imageBack.setTag(R.id.TAG, compressPath);
			setBack = true;
			break;
		case R.id.imagePerson:
			imageTask =new XtomImageTask(imagePerson, compressPath, mContext);
			deletePerson.setVisibility(View.VISIBLE);
			imageWorker.loadImage(imageTask);
			imagePerson.setTag(R.id.TAG, compressPath);
			setPerson = true;
			break;
		}
	}

}
