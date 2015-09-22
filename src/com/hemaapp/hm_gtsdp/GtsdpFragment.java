package com.hemaapp.hm_gtsdp;

import xtom.frame.XtomActivityManager;
import xtom.frame.net.XtomNetWorker;
import xtom.frame.util.XtomSharedPreferencesUtil;
import android.content.Intent;

import com.hemaapp.hm_FrameWork.HemaFragment;
import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.HemaNetWorker;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.model.User;

public abstract class GtsdpFragment extends HemaFragment {

	@Override
	protected HemaNetWorker initNetWorker() {
		return new GtsdpNetWorker(getActivity());
	}
	@Override
	public GtsdpNetWorker getNetWorker() {
		return (GtsdpNetWorker)super.getNetWorker();
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
//				Intent it = new Intent(getActivity(), LoginActivity.class);
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
			return XtomSharedPreferencesUtil.get(getActivity(), key);
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
			XtomSharedPreferencesUtil.save(getActivity(), key, systype);
		}
	}
}
