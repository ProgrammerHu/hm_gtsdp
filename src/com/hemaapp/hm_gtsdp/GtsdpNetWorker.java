package com.hemaapp.hm_gtsdp;

import java.util.HashMap;

import xtom.frame.util.XtomDeviceUuidFactory;
import android.content.Context;

import com.hemaapp.GtsdpConfig;
import com.hemaapp.hm_FrameWork.HemaNetWorker;
import com.hemaapp.hm_gtsdp.nettask.ClientLoginTask;
import com.hemaapp.hm_gtsdp.nettask.ClientLoginoutTask;
import com.hemaapp.hm_gtsdp.nettask.DeviceSaveTask;
import com.hemaapp.hm_gtsdp.nettask.InitTask;

/**
 * �������󹤾���
 * @author Wen
 * @author HuFanglin
 *
 */
public class GtsdpNetWorker extends HemaNetWorker {
	private Context mContext;

	public GtsdpNetWorker(Context mContext) {
		super(mContext);
		this.mContext = mContext;
	}

	@Override
	public void clientLogin() {
		// TODO Auto-generated method stub
		
	}
	

	/**
	 * ��¼
	 */
	public void clientLogin(String username, String password) {
		GtsdpHttpInformation information = GtsdpHttpInformation.CLIENT_LOGIN;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("username", username);// �û���¼�� �ֻ��Ż�����
		params.put("password", password); // ��½���� �������˴洢����32λ��MD5���ܴ�
		params.put("devicetype", "2"); // �û���¼�����ֻ����� 1��ƻ�� 2����׿�������������άͳ�ƣ�
		String version = GtsdpUtil.getAppVersion(mContext);
		params.put("lastloginversion", version);// ��½���õ�ϵͳ�汾��
		params.put("submit", "�ύ");

		GtsdpNetTask task = new ClientLoginTask(information, params);

		executeTask(task);
	}
	

	/**
	 * �˳���¼
	 */
	public void clientLoginout(String token) {
		GtsdpHttpInformation information = GtsdpHttpInformation.CLIENT_LOGINOUT;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("token", token);// ��½����

		GtsdpNetTask task = new ClientLoginoutTask(information, params);
		executeTask(task);
	}

	@Override
	public boolean thirdSave() {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * ϵͳ��ʼ��
	 */
	public void init() {
		GtsdpHttpInformation information = GtsdpHttpInformation.INIT;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("lastloginversion", GtsdpUtil.getAppVersion(mContext));// �汾����(Ĭ�ϣ�1.0.0)
		params.put("devicetype", String.valueOf(GtsdpConfig.DEVICETYPE));// ��½���õ�ϵͳ�汾��
		params.put("device_sn", XtomDeviceUuidFactory.get(mContext));// �ͻ���Ӳ������
		GtsdpNetTask task = new InitTask(information, params);
		executeTask(task);
	}
	
	/**
	 * Ӳ��ע�ᱣ��
	 */
	public void deviceSave(String token, String deviceid, String devicetype,
			String channelid) {
		GtsdpHttpInformation information = GtsdpHttpInformation.DEVICE_SAVE;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("token", token);// ��½����
		params.put("deviceid", deviceid);// ��½�ֻ�Ӳ���� ��Ӧ�ٶ�����userid
		params.put("devicetype", devicetype);// ��½�ֻ����� 1:ƻ�� 2:��׿
		params.put("channelid", channelid);// �ٶ���������id ����ֱ�ӴӰٶȺ�̨�������Ͳ���

		GtsdpNetTask task = new DeviceSaveTask(information, params);
		executeTask(task);
	}
}
