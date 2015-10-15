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
	 * ��ȡ֧��������ǩ����
	 */
	ALIPAY(9, "OnlinePay/Alipay/alipaysign_get.php", "��ȡ֧��������ǩ����", false),
	/**
	 * ��ȡ��������ǩ����
	 */
	UNIONPAY(10, "OnlinePay/Unionpay/unionpay_get.php", "��ȡ��������ǩ����", false),
	/**
	 * �û��˻�����
	 */
	CLIENT_ACCOUNTPAY(11, "client_accountpay", "�û��˻�����", false),
	/**
	 * Ӳ��ע�ᱣ��
	 */
	DEVICE_SAVE(12, "device_save", "Ӳ��ע�ᱣ��", false),
	/**
	 * �޸�����
	 */
	PASSWORD_SAVE(13, "password_save", "�޸�����", false),
	/**
	 * ��ȡ�û���Ϣ
	 */
	CLIENT_GET(14, "client_get", "��ȡ�û���Ϣ", false),
	/**
	 * ֪ͨ�б�
	 */
	NOTICE_LIST(15, "notice_list", "֪ͨ�б�", false),
	/**
	 * ���ģ��
	 */
	TEMPLATE_ADD(16, "template_add", "���ģ��", false),
	/**
	 * �޸�ģ��
	 */
	TEMPLATE_SAVE(17, "template_save", "�޸�ģ��", false),
	/**
	 * ���ģ��
	 */
	TEMPLATE_LIST(18, "template_list", "ģ���б�", false),
	/**
	 * ��֤�ܱ�
	 */
	PASSWORD_ASK_CHECK(19, "password_ask_check", "��֤�ܱ�", false),
	/**
	 * �����ܱ�
	 */
	PASSWORD_ASK_SAVE(20, "password_ask_save", "�����ܱ�", false),
	/**
	 * ��������Ա
	 */
	DELIVERY_ADD(21, "delivery_add", "��������Ա", false),
	/**
	 * �������п�
	 */
	BANK_SAVE(22, "bank_save", "�������п�", false),
	/**
	 * ����֧�����˻�
	 */
	ALI_SAVE(23, "ali_save", "����֧�����˻�", false),
	/**
	 * ��������
	 */
	TRANS_ADD(24, "trans_add", "��������", false),
	/**
	 * �Ӵ�(����)�б�
	 */
	TRANS_LIST(25, "trans_list", "��������", false),
	/**
	 * �Ӵ�(����)��ϸ
	 */
	TRANS_GET(26, "trans_get", "��������", false),
	/**
	 * ����ӵ�
	 */
	NETWORK_RECEIVE(27, "network_receive", "����ӵ�", false),
	/**
	 * ��ʼ���㱣��۸�
	 */
	TRANS_PRICE_SAVE(28, "trans_price_save", "��ʼ���㱣��۸�", false),
	/**
	 * ֪ͨ����
	 */
	NOTICE_SAVEOPERATE(29, "notice_saveoperate", "֪ͨ����", false),
	/**
	 * ��������
	 */
	CASH_ADD(30, "cash_add", "��������", false),
	/**
	 * ����б�
	 */
	AD_LIST(31, "ad_list", "����б�", false),
	/**
	 * ͨ��ɾ��
	 */
	REMOVE(32, "remove", "ͨ��ɾ��", false),
	/**
	 * �˻���ϸ
	 */
	FEEACCOUNT_LIST(33, "feeaccount_list", "ͨ��ɾ��", false),
	/**
	 * �������
	 */
	ADVICE_ADD(34, "advice_add", "�������", false),
	/**
	 * ��֤��ά���Ƿ���Ч
	 */
	TRANS_CODE_CHECK(35, "trans_code_check", "��֤��ά���Ƿ���Ч", false),
	/**
	 * �ܱ��б�
	 */
	PASSWORD_ASK_LIST(36, "password_ask_list", "�ܱ��б�", false),
	/**
	 * ��ȡվ���б�
	 */
	SITE_LIST(37, "site_list", "��ȡվ���б�", false),
	/**
	 * ��ȡվ���б�
	 */
	DELIVERY_ORDER_LIST(38, "delivery_order_list", "��ȡվ���б�", false),
	/**
	 * ȷ���ջ�
	 */
	TRANS_RECEIVE(39, "trans_receive", "ȷ���ջ�", false),
	/**
	 * ����Ա�ӵ�
	 */
	DELIVERY_RECEIVE(40, "delivery_receive", "����Ա�ӵ�", false),
	/**
	 * ��ȡϵͳ֪ͨδ����Ϣ��
	 */
	NOTICE_COUNT(41, "notice_count", "��ȡϵͳ֪ͨδ����Ϣ��", false),
	
	
	
	
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

		 if (this.equals(ALIPAY))
		 path = info.getSys_plugins() + urlPath;
		
		 if (this.equals(UNIONPAY))
		 path = info.getSys_plugins() + urlPath;

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
