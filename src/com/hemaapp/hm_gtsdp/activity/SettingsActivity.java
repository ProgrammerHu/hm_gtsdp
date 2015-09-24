package com.hemaapp.hm_gtsdp.activity;

import java.util.HashMap;

import xtom.frame.XtomActivityManager;
import xtom.frame.image.cache.XtomImageCache;
import xtom.frame.media.XtomVoicePlayer;
import xtom.frame.util.XtomSharedPreferencesUtil;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qzone.QZone;

import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.HemaUtil;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpActivity;
import com.hemaapp.hm_gtsdp.GtsdpApplication;
import com.hemaapp.hm_gtsdp.GtsdpHttpInformation;
import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_gtsdp.db.UserDBHelper;
import com.hemaapp.hm_gtsdp.dialog.GtsdpTwoButtonDialog;
import com.hemaapp.hm_gtsdp.dialog.GtsdpTwoButtonDialog.OnButtonListener;
import com.hemaapp.hm_gtsdp.model.User;
import com.hemaapp.hm_gtsdp.view.SharePopupWindow;

public class SettingsActivity extends GtsdpActivity implements OnClickListener, PlatformActionListener{
	private ImageView imageQuitActivity;
	private View layoutClean, layoutSuggestion, layoutAboutUs, layoutShare, layoutScore;
	private Button btnQuit;
	private GtsdpTwoButtonDialog logoutDialog;
	private SharePopupWindow shareWindow;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_settings);
		super.onCreate(savedInstanceState);
		shareWindow = new SharePopupWindow(mContext, this);
		ShareSDK.initSDK(this);//һ����Ҫ�������޷�����
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
			HemaBaseResult arg1) {
		GtsdpHttpInformation information = (GtsdpHttpInformation) netTask
				.getHttpInformation();
		switch (information) {
		case CLIENT_LOGINOUT:
			cancellationSuccess();
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
		findViewById(R.id.txtNext).setVisibility(View.INVISIBLE);
		((TextView)findViewById(R.id.txtTitle)).setText("����");
		layoutClean = findViewById(R.id.layoutClean);
		layoutSuggestion = findViewById(R.id.layoutSuggestion);
		layoutAboutUs = findViewById(R.id.layoutAboutUs);
		layoutShare = findViewById(R.id.layoutShare);
		layoutScore = findViewById(R.id.layoutScore);
		btnQuit = (Button)findViewById(R.id.btnQuit);
	}

	@Override
	protected void getExras() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setListener() {
		imageQuitActivity.setOnClickListener(this);
		layoutClean.setOnClickListener(this);
		layoutSuggestion.setOnClickListener(this);
		layoutAboutUs.setOnClickListener(this);
		layoutShare.setOnClickListener(this);
		layoutScore.setOnClickListener(this);
		btnQuit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
		case R.id.imageQuitActivity:
			finish(R.anim.none, R.anim.right_out);
			break;
		case R.id.layoutClean:
			new ClearTask().execute();
			break;
		case R.id.layoutSuggestion:
			Intent intent = new Intent(this, SuggestionActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.right_in, R.anim.none);
			break;
		case R.id.layoutAboutUs:
			
			break;
		case R.id.layoutShare:
			shareWindow.showAtLocation(findViewById(R.id.father), Gravity.BOTTOM, 0, 0);
			break;
		case R.id.layoutScore:
			
			break;
		case R.id.btnQuit:
			logout();
			break;

		case R.id.layoutQQ:// qq����
			ShareParams sp = new ShareParams();
			sp.setTitle("Test");
			sp.setTitleUrl("http://sharesdk.cn"); // ����ĳ�����
			sp.setText("TestDetail");
			// sp.setImageUrl(shareParams.getImageUrl());
			sp.setComment("�ҶԴ˷������ݵ�����");
			sp.setSite("Title");
			sp.setSiteUrl("http://sharesdk.cn");
			Platform qq = ShareSDK.getPlatform(mContext, "QQ");
			// qq.setPlatformActionListener(platformActionListener);
			qq.share(sp);
			break;
		case R.id.layoutWechat://΢�ŷ���
			ShareParams wechatSp = new ShareParams();
			wechatSp.setTitle("Test");
			wechatSp.setTitleUrl("http://sharesdk.cn"); // ����ĳ�����
			wechatSp.setText("TestDetail");
			// sp.setImageUrl(shareParams.getImageUrl());
			wechatSp.setComment("�ҶԴ˷������ݵ�����");
			wechatSp.setSite("Title");
			wechatSp.setSiteUrl("http://sharesdk.cn");
			wechatSp.setShareType(Platform.SHARE_TEXT);
			String wechatName = cn.sharesdk.wechat.friends.Wechat.NAME;
			Platform Wechat = ShareSDK.getPlatform(wechatName);
			Wechat.setPlatformActionListener(this);
			Wechat.share(wechatSp);
			break;
		case R.id.layoutQQZone://�ռ����
			ShareParams spZone = new ShareParams();
			spZone.setTitle("Test");
			spZone.setTitleUrl("http://sharesdk.cn"); // ����ĳ�����
			spZone.setText("TestDetail");
			// sp.setImageUrl(shareParams.getImageUrl());
			spZone.setComment("�ҶԴ˷������ݵ�����");
			spZone.setSite("Title");
			spZone.setSiteUrl("http://sharesdk.cn");
			Platform qqZone = ShareSDK.getPlatform(mContext, QZone.NAME);
			// qq.setPlatformActionListener(platformActionListener);
			qqZone.share(spZone);
			break;
		case R.id.layoutWeibo://΢������
			break;
		}
	}
	
	@Override
	protected boolean onKeyBack() {
		finish(R.anim.none, R.anim.right_out);
		return super.onKeyBack();
	}
	/**
	 * �û��˳�
	 */
	private void logout()
	{
		if (logoutDialog == null) {
			logoutDialog = new GtsdpTwoButtonDialog(mContext);
			logoutDialog.setText("ȷ��Ҫ�˳���¼��");
			logoutDialog.setLeftButtonText("ȡ��");
			logoutDialog.setRightButtonText("�˳�");
			logoutDialog.setButtonListener(new ExitButtonListener());
		}
		logoutDialog.show();
	}
	
	private class ExitButtonListener implements OnButtonListener {

		@Override
		public void onLeftButtonClick(GtsdpTwoButtonDialog dialog) {
			dialog.cancel();
			
		}

		@Override
		public void onRightButtonClick(GtsdpTwoButtonDialog dialog) {
			dialog.cancel();
			GtsdpApplication application = getApplicationContext();
			User user = application.getUser();
			getNetWorker().clientLoginout(user.getToken());
		}
		
	}

	private class ClearTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			// ɾ��ͼƬ����
			XtomImageCache.getInstance(mContext).deleteCache();
			// ɾ����������
			XtomVoicePlayer player = XtomVoicePlayer.getInstance(mContext);
			player.deleteCache();
			player.release();
			return null;
		}

		@Override
		protected void onPreExecute() {
			showProgressDialog("�����������");
		}
		@Override
		protected void onPostExecute(Void result) {
			cancelProgressDialog();
			showTextDialog("������");
		}
	}
	
	private void cancellationSuccess() {
		UserDBHelper dbHelper = new UserDBHelper(mContext);
		dbHelper.clear();
		// ��յ�¼��Ϣ
		GtsdpApplication.getInstance().setUser(null);
		XtomSharedPreferencesUtil.save(mContext, "username", "");// ����û���
		XtomSharedPreferencesUtil.save(mContext, "password", "");// �������
		HemaUtil.setThirdSave(mContext, false);// ����������¼�����Ϊfalse
		XtomActivityManager.finishAll();
		Intent intent = new Intent(mContext, LoginActivity.class);
		startActivity(intent);
		overridePendingTransition(R.anim.right_in, R.anim.none);
		this.finish();
	}

	@Override
	public void onCancel(Platform arg0, int arg1) {
		showTextDialog("cancel");
		
	}

	@Override
	public void onComplete(Platform plat, int action,
			HashMap<String, Object> res) {
		showTextDialog(res.toString());
	}

	@Override
	public void onError(Platform arg0, int arg1, Throwable arg2) {
		showTextDialog("Error");
		
	}


}
