package com.hemaapp.hm_gtsdp;

import java.util.HashMap;

import xtom.frame.util.XtomDeviceUuidFactory;
import android.content.Context;

import com.hemaapp.GtsdpConfig;
import com.hemaapp.hm_FrameWork.HemaNetWorker;
import com.hemaapp.hm_gtsdp.nettask.AlipayTradeTask;
import com.hemaapp.hm_gtsdp.nettask.ChangePwdTask;
import com.hemaapp.hm_gtsdp.nettask.ClientAddTask;
import com.hemaapp.hm_gtsdp.nettask.ClientGetTask;
import com.hemaapp.hm_gtsdp.nettask.ClientLoginTask;
import com.hemaapp.hm_gtsdp.nettask.ClientLoginoutTask;
import com.hemaapp.hm_gtsdp.nettask.CodeVerifyTask;
import com.hemaapp.hm_gtsdp.nettask.DeviceSaveTask;
import com.hemaapp.hm_gtsdp.nettask.FileUploadTask;
import com.hemaapp.hm_gtsdp.nettask.GetTemplateTask;
import com.hemaapp.hm_gtsdp.nettask.InitTask;
import com.hemaapp.hm_gtsdp.nettask.NoticeListTask;
import com.hemaapp.hm_gtsdp.nettask.CurrentTask;
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
	 * ��ȡ�����
	 * @param username
	 * @param code ���Խ׶ι̶���������ύ��1234��
	 */
	public void getCodeVerify(String username, String code)
	{
		GtsdpHttpInformation information = GtsdpHttpInformation.CODE_VERIFY;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("username", username);// �û���¼�� �ֻ��Ż�����
		params.put("code", code);
		GtsdpNetTask task = new CodeVerifyTask(information, params);
		executeTask(task);
	}
	/**
	 * �û�ע�� 
	 * @param temp_token ��ʱ����
	 * @param username �û�ע����
	 * @param password ��½���� ���Ի���һ����д��123456��
	 * @param nickname �û��ǳ�
	 * @param address ��ϸ��ַ
	 * @param sex �Ա�
	 * @param lng ����
	 * @param lat ά��
	 */
	public void clientAdd(String temp_token, String username, String password, String nickname, String address, String sex,
			String lng, String lat)
	{
		if(GtsdpConfig.IS_DEVELOPMENT)
		{
			password = "123456";
		}
		GtsdpHttpInformation information = GtsdpHttpInformation.CLIENT_ADD;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("temp_token", temp_token);
		params.put("username", username);
		params.put("password", password);
		params.put("nickname", nickname);
		params.put("address", address);
		params.put("sex", sex);
		params.put("lng", lng);
		params.put("lat", lat);
		GtsdpNetTask task = new ClientAddTask(information, params);
		executeTask(task);
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
	public void clientVerify(String username)
	{
		GtsdpHttpInformation information = GtsdpHttpInformation.CLIENT_VERIFY;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("username", username);
		GtsdpNetTask task = new CurrentTask(information, params);
		executeTask(task);
	}
	
	/**
	 * �޸�����
	 * @param token ��¼����
	 * @param keytype �������� 1����½���� 2��֧������
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
	 * ��������
	 * @param temp_token ��ʱ���� 
	 * @param keytype �������� 1����½���� 2��֧������
	 * @param new_password ������
	 */
	public void resetPwd(String temp_token, String keytype, String new_password)
	{
		GtsdpHttpInformation information = GtsdpHttpInformation.PASSWORD_RESET;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("temp_token", temp_token);
		params.put("keytype", keytype);
		params.put("new_password", new_password);
		
		GtsdpNetTask task = new CurrentTask(information, params);
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
	/**
	 * ��ȡ�û���Ϣ
	 * @param token ��¼����
	 * @param id ͨ���������û�������ȡ
	 */
	public void clientGet(String token, String id)
	{
		GtsdpHttpInformation information = GtsdpHttpInformation.CLIENT_GET;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("token", token);
		params.put("id", id); 
		
		GtsdpNetTask task = new ClientGetTask(information, params);
		executeTask(task);
	}
	/**
	 * ��ȡ֪ͨ�б�
	 * @param token ��¼����
	 * @param keytype ҵ������ 1�����ۻظ� 2���������� 3��ϵͳ֪ͨ 
	 * @param page ҳ��
	 */
	public void getNoticeList(String token, String keytype, String page)
	{
		GtsdpHttpInformation information = GtsdpHttpInformation.NOTICE_LIST;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("token", token);
		params.put("keytype", keytype); 
		params.put("page", page); 
		
		GtsdpNetTask task = new NoticeListTask(information, params);
		executeTask(task);
	}
	/**
	 * ���ģ��ӿ�
	 * @param token ��¼����
	 * @param keytype ҵ������ 1��������ģ�壻2���ռ���ģ��
	 * @param name ����
	 * @param address ��ַ
	 * @param telphone ��ϵ�绰
	 */
	public void addTemplate(String token, String keytype, String name, String address, String telphone)
	{
		GtsdpHttpInformation information = GtsdpHttpInformation.TEMPLATE_ADD;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("token", token);// ��½����
		params.put("keytype", keytype);
		params.put("name", name);
		params.put("address", address);
		params.put("telphone", telphone);
		GtsdpNetTask task = new CurrentTask(information, params);
		executeTask(task);
	}
	/**
	 * �޸�ģ��
	 * @param token ��¼����
	 * @param id ID
	 * @param keytype ҵ������ 1��������ģ�壻2���ռ���ģ��
	 * @param name ����
	 * @param address ��ַ
	 * @param telphone ��ϵ�绰
	 */
	public void saveTemplate(String token,String id, String keytype, String name, String address, String telphone)
	{
		GtsdpHttpInformation information = GtsdpHttpInformation.TEMPLATE_SAVE;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("token", token);// ��½����
		params.put("id", id);
		params.put("keytype", keytype);
		params.put("name", name);
		params.put("address", address);
		params.put("telphone", telphone);
		GtsdpNetTask task = new CurrentTask(information, params);
		executeTask(task);
	}
	/**
	 * ��ȡģ���б�
	 * @param token ��¼����
	 * @param keytype ҵ������ 1��������ģ�壻2���ռ���ģ��
	 * @param page �ڼ�ҳ ��0��ʼ
	 */
	public void getTemplateList(String token, String keytype, int page)
	{
		GtsdpHttpInformation information = GtsdpHttpInformation.TEMPLATE_LIST;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("token", token);// ��½����
		params.put("keytype", keytype);
		params.put("page", String.valueOf(page));
		GtsdpNetTask task = new GetTemplateTask(information, params);
		executeTask(task);
	}
	/**
	 * ��֤�ܱ��ӿ�
	 * @param username ���޸�������ֻ���
	 * @param ask1_id �ܱ�1����id
	 * @param answer1 �ܱ�1����Ĵ�
	 * @param ask2_id �ܱ�2����id
	 * @param answer2 �ܱ�2����Ĵ�
	 * @param ask3_id �ܱ�3����id
	 * @param answer3 �ܱ�3����Ĵ�
	 */
	public void checkAsk(String username, String ask1_id, String answer1
			, String ask2_id, String answer2
			, String ask3_id, String answer3)
	{
		GtsdpHttpInformation information = GtsdpHttpInformation.PASSWORD_ASK_CHECK;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("username", username);// ��½����
		params.put("ask1_id", ask1_id);
		params.put("answer1", answer1);
		params.put("ask2_id", ask2_id);
		params.put("answer2", answer2);
		params.put("ask3_id", ask3_id);
		params.put("answer3", answer3);
		GtsdpNetTask task = new CurrentTask(information, params);
		executeTask(task);
	}
	/**
	 * �����ܱ��ӿ�
	 * @param token ��¼����
	 * @param ask1_id �ܱ�1����id
	 * @param answer1 �ܱ�1����Ĵ�
	 * @param ask2_id �ܱ�2����id
	 * @param answer2 �ܱ�2����Ĵ�
	 * @param ask3_id �ܱ�3����id
	 * @param answer3 �ܱ�3����Ĵ�
	 */
	public void saveAsk(String token, String ask1_id, String answer1
			, String ask2_id, String answer2
			, String ask3_id, String answer3)
	{
		GtsdpHttpInformation information = GtsdpHttpInformation.PASSWORD_ASK_SAVE;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("token", token);
		params.put("ask1_id", ask1_id);
		params.put("answer1", answer1);
		params.put("ask2_id", ask2_id);
		params.put("answer2", answer2);
		params.put("ask3_id", ask3_id);
		params.put("answer3", answer3);
		GtsdpNetTask task = new CurrentTask(information, params);
		executeTask(task);
	}
	/**
	 * ��������Ա
	 * @param token ��¼����
	 * @param realname ��ʵ����
	 * @param telphone ��ϵ�绰
	 * @param address ��ͥסַ
	 */
	public void deliveryAdd(String token, String realname, String telphone, String address)
	{
		GtsdpHttpInformation information = GtsdpHttpInformation.DELIVERY_ADD;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("token", token);
		params.put("realname", realname);
		params.put("telphone", telphone);
		params.put("address", address);
		GtsdpNetTask task = new CurrentTask(information, params);
		executeTask(task);
	}
	/**
	 * �������п�
	 * @param token ��¼����
	 * @param bankuser ������
	 * @param bankname ��������
	 * @param bankcard ����
	 * @param bankaddress �����е�ַ
	 */
	public void bankSave(String token, String bankuser, String bankname, String bankcard, String bankaddress)
	{
		GtsdpHttpInformation information = GtsdpHttpInformation.BANK_SAVE;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("token", token);
		params.put("bankuser", bankuser);
		params.put("bankname", bankname);
		params.put("bankcard", bankcard);
		params.put("bankaddress", bankaddress);
		GtsdpNetTask task = new CurrentTask(information, params);
		executeTask(task);
	}
	/**
	 * ֧������Ϣ����
	 * @param token ��¼����
	 * @param aliuser ֧�����˺�
	 */
	public void aliSave(String token, String aliuser)
	{
		GtsdpHttpInformation information = GtsdpHttpInformation.ALI_SAVE;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("token", token);
		params.put("aliuser", aliuser);	
		GtsdpNetTask task = new CurrentTask(information, params);
		executeTask(task);
	}
}
