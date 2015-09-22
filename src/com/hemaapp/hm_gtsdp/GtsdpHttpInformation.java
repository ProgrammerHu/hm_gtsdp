package com.hemaapp.hm_gtsdp;

import com.hemaapp.GtsdpConfig;
import com.hemaapp.HemaConfig;
import com.hemaapp.hm_FrameWork.HemaHttpInfomation;
import com.hemaapp.hm_gtsdp.model.SysInitInfo;

/**
 * ����������Ϣö����
 */
public enum GtsdpHttpInformation implements HemaHttpInfomation {
	/**
	 * ��¼
	 */
	CLIENT_LOGIN(HemaConfig.ID_LOGIN, "client_login", "��¼", false),
	// ע���¼�ӿ�id����ΪHemaConfig.ID_LOGIN
	/**
	 * ��̨����ӿڸ�·��
	 */
	SYS_ROOT(0, GtsdpConfig.SYS_ROOT, "��̨����ӿڸ�·��", true),
	/**
	 * ϵͳ��ʼ��
	 */
	INIT(1, "index.php/webservice/index/init", "ϵͳ��ʼ��", false),
	/**
	 * ��֤�û����Ƿ�Ϸ�
	 */
	CLIENT_VERIFY(2, "client_verify", "��֤�û����Ƿ�Ϸ�", false),
	/**
	 * ���������֤��
	 */
	CODE_GET(3, "code_get", "���������֤��", false),
	/**
	 * ��֤�����
	 */
	CODE_VERIFY(4, "code_verify", "��֤�����", false),
	/**
	 * �û�ע��
	 */
	CLIENT_ADD(5, "client_add", "�û�ע��", false),
	/**
	 * �ϴ��ļ���ͼƬ����Ƶ����Ƶ��
	 */
	FILE_UPLOAD(6, "file_upload", "�ϴ��ļ���ͼƬ����Ƶ����Ƶ��", false),
	/**
	 * ��������
	 */
	PASSWORD_RESET(7, "password_reset", "��������", false),
	/**
	 * �˳���¼
	 */
	CLIENT_LOGINOUT(8, "client_loginout", "�˳���¼", false),
	/**
	 * �������
	 */
	BLOG_ADD(9, "blog_add", "�������", false),
	/**
	 * ��ȡ�����б�
	 */
	BLOG_LIST(10, "blog_list", "��ȡ�����б�", false),
	/**
	 * ��ȡ����������Ϣ
	 */
	BLOG_GET(11, "blog_get", "��ȡ����������Ϣ", false),
	/**
	 * ��ȡ��ҵ���
	 */
	TYPE_LIST(12, "type_list", "��ȡ��ҵ���", false),
	/**
	 * ��ȡ��ҵ����Ƿ��ע״̬
	 */
	TYPELOVE_GET(13, "typelove_get", "��ȡ��ҵ����Ƿ��ע״̬", false),
	/**
	 * ����ղأ���ע��
	 */
	LOVE_ADD(14, "love_add", "����ղأ���ע��", false),
	/**
	 * ����ɾ������
	 */
	DATA_REMOVE(15, "data_remove", "����ɾ������", false),
	/**
	 * ��ȡ���ӻظ��б�
	 */
	REPLY_LIST(16, "reply_list", "��ȡ���ӻظ��б�", false),
	/**
	 * �������
	 */
	REPLY_ADD(17, "reply_add", "�������", false),
	/**
	 * ��ȡ��Ƭ����
	 */
	CARD_GET(18, "card_get", "��ȡ��Ƭ����", false),
	/**
	 * ��ȡ��Ƭ�б�
	 */
	CARD_LIST(19, "card_list", "��ȡ��Ƭ�б�", false),
	/**
	 * �ղ���Ƭ�ӿڣ����ݸ��ķ��飩
	 */
	CARD_SAVE(20, "card_save", "�ղ���Ƭ�ӿڣ����ݸ��ķ��飩", false),
	/**
	 * ������Ƭ
	 */
	CLIENTCARD_SAVE(21, "clientcard_save", "������Ƭ", false),
	/**
	 * ����¼����
	 */
	NEAR_ADD(22, "near_add", "����¼����", false),
	/**
	 * ��ȡ��Ƭ�ղ����
	 */
	CARDTYPE_LIST(23, "cardtype_list", "��ȡ��Ƭ�ղ����", false),
	/**
	 * ����(�޸�)��Ƭ���
	 */
	CARDTYPE_SAVE(24, "cardtype_save", "����(�޸�)��Ƭ���", false),
	/**
	 * ��Ƭ��ʼ����
	 */
	SWAP_START(25, "swap_start", "��Ƭ��ʼ����", false),
	/**
	 * ��Ƭֹͣ����
	 */
	SWAP_STOP(26, "swap_stop", "��Ƭֹͣ����", false),
	/**
	 * ��ȡ�û���������
	 */
	CLIENT_GET(27, "client_get", "��ȡ�û���������", false),
	/**
	 * �����û�����
	 */
	CLIENT_SAVE(28, "client_save", "�����û�����", false),
	/**
	 * ��ȡ�û�֪ͨ�б�
	 */
	NOTICE_LIST(29, "notice_list", "��ȡ�û�֪ͨ�б�", false),
	/**
	 * �����û�֪ͨ����
	 */
	NOTICE_SAVEOPERATE(30, "notice_saveoperate", "�����û�֪ͨ����", false),
	/**
	 * �������
	 */
	ADVICE_ADD(31, "advice_add", "�������", false),
	/**
	 * �޸Ĳ���������
	 */
	PASSWORD_SAVE(32, "password_save", "�޸Ĳ���������", false),
	/**
	 * �Ŷӱ���(�������޸�)
	 */
	TEAM_SAVE(33, "team_save", "�Ŷӱ���(�������޸�)", false),
	/**
	 * ��ȡ�Ŷ��б�
	 */
	TEAM_LIST(34, "team_list", "��ȡ�Ŷ��б�", false),
	/**
	 * ��ȡ�Ŷ���������
	 */
	TEAM_GET(35, "team_get", "��ȡ�Ŷ���������", false),
	/**
	 * �Ŷӽ�ɢ���˳�
	 */
	TEAM_REMOVE(36, "team_remove", "�Ŷӽ�ɢ���˳�", false),
	/**
	 * ��ȡ��Ա�б�
	 */
	CLIENT_LIST(37, "client_list", "��ȡ��Ա�б�", false),
	/**
	 * �Ŷӳ�Ա����
	 */
	GROUP_CLIENT_SAVE(38, "group_client_save", "�Ŷӳ�Ա����", false),
	/**
	 * ��ȡ�����б�
	 */
	GROUP_DEPT_LIST(39, "group_dept_list", "��ȡ�����б�", false),
	/**
	 * ����(�޸�)����
	 */
	GROUP_DEPT_SAVE(40, "group_dept_save", "����(�޸�)����", false),
	/**
	 * �������Ӳ���
	 */
	BLOG_SAVEOPERATE(41, "blog_saveoperate", "�������Ӳ���", false),
	/**
	 * ��ȡϵͳ����б�
	 */
	SYSTYPE_GET(42, "systype_get", "��ȡϵͳ����б�", false),
	/**
	 * ����ϵͳ���
	 */
	SYSTYPE_SAVE(43, "systype_save", "����ϵͳ���", false),
	/**
	 * ����(�޸�)�ͻ�
	 */
	VIP_SAVE(44, "vip_save", "����(�޸�)�ͻ�", false),
	/**
	 * ��ȡ�ͻ�������Ϣ
	 */
	VIP_GET(45, "vip_get", "��ȡ�ͻ�������Ϣ", false),
	/**
	 * ��ȡ�ͻ��б���Ϣ
	 */
	VIP_LIST(46, "vip_list", "��ȡ�ͻ��б���Ϣ", false),
	/**
	 * ����(�޸�)�ͻ����
	 */
	VIPTYPE_SAVE(47, "viptype_save", "����(�޸�)�ͻ����", false),
	/**
	 * ��ȡ�ͻ�����б�
	 */
	VIPTYPE_LIST(48, "viptype_list", "��ȡ�ͻ�����б�", false),
	/**
	 * ��ӹ���
	 */
	WORK_ADD(49, "work_add", "��ӹ���", false),
	/**
	 * ��ȡ�����б�
	 */
	WORK_LIST(50, "work_list", "��ȡ�����б�", false),
	/**
	 * ��ȡ��������
	 */
	WORK_GET(51, "work_get", "��ȡ��������", false),
	/**
	 * ���湤������
	 */
	WORK_SAVEOPERATE(52, "work_saveoperate", "���湤������", false),
	/**
	 * ��Ӽ��˻�ҵ��
	 */
	ACCOUNT_ADD(53, "account_add", "��Ӽ��˻�ҵ��", false),
	/**
	 * ��ȡ���˻�ҵ���б�
	 */
	ACCOUNT_LIST(54, "account_list", "��ȡ���˻�ҵ���б�", false),
	/**
	 * ��ӿ���
	 */
	CHECK_ADD(55, "check_add", "��ӿ���", false),
	/**
	 * ҵ��ͳ��
	 */
	ACCOUNT_SUM(56, "account_sum", "ҵ��ͳ��", false),
	/**
	 * ���浱ǰ�û�����
	 */
	POSITION_SAVE(57, "position_save", "���浱ǰ�û�����", false),
	/**
	 * ���ջ�ȡ�����б�
	 */
	CHECK_LIST_BYDATE(58, "check_list_bydate", "���ջ�ȡ�����б�", false),
	/**
	 * ���»�ȡ�����б�
	 */
	CHECK_LIST_BYMONTH(59, "check_list_bymonth", "���»�ȡ�����б�", false),
	/**
	 * �����ֻ�����
	 */
	MOBILE_SAVE(60, "mobile_save", "�����ֻ�����", false),
	/**
	 * Ӳ��ע�ᱣ��
	 */
	DEVICE_SAVE(61, "device_save", "Ӳ��ע�ᱣ��", false),
	/**
	 * ��ȡ�켣�б�
	 */
	POSITION_LIST(62, "position_list", "��ȡ�켣�б�", false),
	/**
	 * ��ȡ����״̬
	 */
	CHECK_GET(63, "check_get", "��ȡ����״̬", false),
	/**
	 * ����ص�
	 */
	SHARE_ADD(64, "share_add", "����ص�", false),
	/**
	 * �����༭����
	 */
	WORK_SAVE(65, "work_save", "�����༭����", false),
	/**
	 * ת���Ź���
	 */
	WORK_CHANGESAVE(66, "work_changesave", "ת���Ź���", false),
	/**
	 * ��������Ϣ����
	 */
	CHATPUSH_ADD(67, "chatpush_add", "��������Ϣ����", false),

	;

	private int id;// ��ӦNetTask��id
	private String urlPath;// �����ַ
	private String description;// ��������
	private boolean isRootPath;// �Ƿ��Ǹ�·��

	private GtsdpHttpInformation(int id, String urlPath, String description,
			boolean isRootPath) {
		this.id = id;
		this.urlPath = urlPath;
		this.description = description;
		this.isRootPath = isRootPath;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getUrlPath() {
		if (isRootPath)
			return urlPath;

		String path = SYS_ROOT.urlPath + urlPath;

		if (this.equals(INIT))
			return path;

		GtsdpApplication application = GtsdpApplication.getInstance();
		SysInitInfo info = application.getSysInitInfo();
		path = info.getSys_web_service() + urlPath;

		// if (this.equals(ALIPAY))
		// path = info.getSys_plugins() + urlPath;
		//
		// if (this.equals(UNIONPAY))
		// path = info.getSys_plugins() + urlPath;

		return path;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public boolean isRootPath() {
		return isRootPath;
	}

}
