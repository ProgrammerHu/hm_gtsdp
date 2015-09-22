package com.hemaapp.hm_gtsdp.activity;

import xtom.frame.XtomActivityManager;
import xtom.frame.image.cache.XtomImageCache;
import xtom.frame.media.XtomVoicePlayer;
import xtom.frame.util.XtomSharedPreferencesUtil;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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

public class SettingsActivity extends GtsdpActivity implements OnClickListener{
	private ImageView imageQuitActivity;
	private View layoutClean, layoutSuggestion, layoutAboutUs, layoutShare, layoutScore;
	private Button btnQuit;
	private GtsdpTwoButtonDialog logoutDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_settings);
		super.onCreate(savedInstanceState);
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
		((TextView)findViewById(R.id.txtTitle)).setText("设置");
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
			
			break;
		case R.id.layoutAboutUs:
			
			break;
		case R.id.layoutShare:
			
			break;
		case R.id.layoutScore:
			
			break;
		case R.id.btnQuit:
			logout();
			break;
		}
	}
	
	@Override
	protected boolean onKeyBack() {
		finish(R.anim.none, R.anim.right_out);
		return super.onKeyBack();
	}
	
	private void logout()
	{

		if (logoutDialog == null) {
			logoutDialog = new GtsdpTwoButtonDialog(mContext);
			logoutDialog.setText("您确定要退出登录吗？");
			logoutDialog.setLeftButtonText("取消");
			logoutDialog.setRightButtonText("退出");
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
			// 删除图片缓存
			XtomImageCache.getInstance(mContext).deleteCache();
			// 删除语音缓存
			XtomVoicePlayer player = XtomVoicePlayer.getInstance(mContext);
			player.deleteCache();
			player.release();
			return null;
		}

		@Override
		protected void onPreExecute() {
			showProgressDialog("正在清除缓存");
		}
		@Override
		protected void onPostExecute(Void result) {
			cancelProgressDialog();
			showTextDialog("清除完成");
		}
	}
	
	private void cancellationSuccess() {
		UserDBHelper dbHelper = new UserDBHelper(mContext);
		dbHelper.clear();
		// 清空登录信息
		GtsdpApplication.getInstance().setUser(null);
		XtomSharedPreferencesUtil.save(mContext, "username", "");// 清空用户名
		XtomSharedPreferencesUtil.save(mContext, "password", "");// 青空密码
		HemaUtil.setThirdSave(mContext, false);// 将第三方登录标记置为false
		XtomActivityManager.finishAll();
		Intent intent = new Intent(mContext, LoginActivity.class);
		startActivity(intent);
		overridePendingTransition(R.anim.right_in, R.anim.none);
		this.finish();
	}


}
