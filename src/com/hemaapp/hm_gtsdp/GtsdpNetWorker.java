package com.hemaapp.hm_gtsdp;

import java.util.HashMap;

import xtom.frame.util.XtomDeviceUuidFactory;
import android.content.Context;

import com.hemaapp.GtsdpConfig;
import com.hemaapp.hm_FrameWork.HemaNetWorker;
import com.hemaapp.hm_gtsdp.nettask.AlipayTradeTask;
import com.hemaapp.hm_gtsdp.nettask.ChangePwdTask;
import com.hemaapp.hm_gtsdp.nettask.ClientLoginTask;
import com.hemaapp.hm_gtsdp.nettask.ClientLoginoutTask;
import com.hemaapp.hm_gtsdp.nettask.DeviceSaveTask;
import com.hemaapp.hm_gtsdp.nettask.FileUploadTask;
import com.hemaapp.hm_gtsdp.nettask.InitTask;
import com.hemaapp.hm_gtsdp.nettask.UnionTradeTask;

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
	 * �޸�����
	 * @param token ��¼����
	 * @param keytype ��������	1����½���� 2��֧������
	 * @param old_password ������
	 * @param new_password ������
	 */ 
	public void changePwd(String token, String keytype, String old_password, String new_password)
	{
		GtsdpHttpInformation information = GtsdpHttpInformation.PASSWORD_SAVE;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("token", token);// �汾����(Ĭ�ϣ�1.0.0)
		params.put("keytype", keytype);// ��½���õ�ϵͳ�汾��
		params.put("old_password", old_password);// �ͻ���Ӳ������
		params.put("new_password", new_password);// �ͻ���Ӳ������
		GtsdpNetTask task = new ChangePwdTask(information, params);
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
	

	
	/**
	 * ��ȡ֧��������ǩ����
	 * @param token ��½����
	 * @param keytype ҵ�����ͣ�1���˻�����ֵ��2����Ʒ��������
	 * @param keyid ҵ�����,id��keytype=1ʱ,keyid=0��keytype=2ʱ,keyid=blog_id
	 * @param total_fee ֧�����׽��,��λ��Ԫ(����ʱͳһ����0.01Ԫ)
	 */
	public void alipay(String token, String keytype, String keyid, String total_fee) {
		GtsdpHttpInformation information = GtsdpHttpInformation.ALIPAY;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("token", token);// ��½����
//		params.put("paytype", paytype);// ֧������ �̶���1
		params.put("keytype", keytype);// ҵ������,1���˻�����ֵ2����Ʒ��������
		params.put("keyid", keyid);// ҵ�����,id��keytype=1ʱ,keyid=0��keytype=2ʱ,keyid=blog_id
//		params.put("total_fee", total_fee);// ֧�����׽��,��λ��Ԫ(����ʱͳһ����0.01Ԫ)

		GtsdpNetTask task = new AlipayTradeTask(information, params);
		executeTask(task);
	}
	/**
	 * ��ȡ��������ǩ����
	 * @param token ��½����
	 * @param paytype ֧������ �̶���2
	 * @param keytype ҵ������,1���˻�����ֵ2����Ʒ��������
	 * @param keyid ҵ�����,id��keytype=1ʱ,keyid=0��keytype=2ʱ,keyid=blog_id
	 * @param total_fee  ֧�����׽��,��λ��Ԫ(����ʱͳһ����0.01Ԫ)
	 */
	public void unionpay(String token, String paytype, String keytype,
			String keyid, String total_fee) {
		GtsdpHttpInformation information = GtsdpHttpInformation.UNIONPAY;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("token", token);// ��½����
//		params.put("paytype", paytype);// ֧������ �̶���2
		params.put("keytype", keytype);// ҵ������,1���˻�����ֵ2����Ʒ��������
		params.put("keyid", keyid);// ҵ�����,id��keytype=1ʱ,keyid=0��keytype=2ʱ,keyid=blog_id
//		params.put("total_fee", total_fee);// ֧�����׽��,��λ��Ԫ(����ʱͳһ����0.01Ԫ)

		GtsdpNetTask task = new UnionTradeTask(information, params);
		executeTask(task);
	}
	/**
	 * �ϴ��ļ���ͼƬ����Ƶ����Ƶ��
	 * @param token ��¼����
	 * @param keytype �ϴ��������� 1���û�ͷ��; 
	 * @param keyid ����id ��keytype=1ʱ��keyid=client_id��
	 * @param duration ����ʱ�� �ϴ�ͼƬʱ����ֵ�̶���"0"���� ��λ��S(��)
	 * @param orderby �����ϴ��ัͼƬʱ�������ϴ�����  ��0��ʼ�����ε���
	 * @param content ��������  �е���Ŀ�У�չʾ��ͼƬ��Ҫ����һ������˵����Ϣ��  Ĭ�ϴ�"��"
	 * @param temp_file �ļ� ��ʱ��Ҫ�ϴ����ļ��ؼ�����  ��Ӧ��type="file" �е�nameֵ ������ļ������ڿͻ���ѹ�����ϴ���ѹ���ߴ��ȹ̶�640��
	 */
	public void fileUpload(String token, String keytype, String keyid,
			String duration, String orderby, String content, String temp_file) {
		GtsdpHttpInformation information = GtsdpHttpInformation.FILE_UPLOAD;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("token", token);//
		params.put("keytype", keytype); //
		params.put("keyid", keyid); //
		params.put("duration", duration); //
		params.put("orderby", orderby); //
		params.put("content", content);// �������� �е���Ŀ�У�չʾ��ͼƬ��Ҫ����һ������˵����Ϣ��Ĭ�ϴ�"��"
		HashMap<String, String> files = new HashMap<String, String>();
		files.put("temp_file", temp_file); //

		GtsdpNetTask task = new FileUploadTask(information, params, files);
		executeTask(task);
	}
}
