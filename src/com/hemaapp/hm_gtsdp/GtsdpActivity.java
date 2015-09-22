package com.hemaapp.hm_gtsdp;

import xtom.frame.XtomActivityManager;
import xtom.frame.net.XtomNetWorker;
import xtom.frame.util.XtomSharedPreferencesUtil;

import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_gtsdp.R.id;
import com.hemaapp.hm_gtsdp.R.layout;
import com.hemaapp.hm_gtsdp.R.menu;
import com.hemaapp.hm_FrameWork.HemaActivity;
import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.HemaNetWorker;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.model.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * ��дactivity
 * @author Wen
 * @author HuFanglin
 *
 */
public abstract class GtsdpActivity extends HemaActivity {

	@Override
	protected HemaNetWorker initNetWorker() {
		return new GtsdpNetWorker(mContext);
	}

	@Override
	public GtsdpNetWorker getNetWorker() {
		return (GtsdpNetWorker)super.getNetWorker();
	}


	@Override
	public GtsdpApplication getApplicationContext() {
		return (GtsdpApplication) super.getApplicationContext();
	}
	
	@Override
	public boolean onAutoLoginFailed(HemaNetWorker netWorker,
			HemaNetTask netTask, int failedType, HemaBaseResult baseResult) {
		switch (failedType) {
		case 0:// ����������ʧ��
			int error_code = baseResult.getError_code();
			switch (error_code) {
			case 102:// �������
				XtomActivityManager.finishAll();
				//TODO ��¼������ת
//				Intent it = new Intent(mContext, LoginActivity.class);
//				startActivity(it);
				return true;
			default:
				break;
			}
		case XtomNetWorker.FAILED_HTTP:// �����쳣
		case XtomNetWorker.FAILED_DATAPARSE:// �����쳣
		case XtomNetWorker.FAILED_NONETWORK:// ������
			break;
		}
		return false;
	}

	// ------------------------���������Ŀ�Զ��巽��---------------------------
	/**
	 * ��ȡϵͳ���
	 * 
	 * @return
	 */
	public String getSystype() {
		GtsdpApplication application = GtsdpApplication.getInstance();
		User user = application.getUser();
		if (user != null) {
			String key = "systype_" + user.getId();
			return XtomSharedPreferencesUtil.get(mContext, key);
		}
		return null;
	}

	/**
	 * ����ϵͳ���
	 * 
	 * @param systype
	 */
	public void saveSystype(String systype) {
		GtsdpApplication application = GtsdpApplication.getInstance();
		User user = application.getUser();
		if (user != null) {
			String key = "systype_" + user.getId();
			XtomSharedPreferencesUtil.save(mContext, key, systype);
		}
	}
	
}
