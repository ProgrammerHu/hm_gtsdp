package com.hemaapp.hm_gtsdp.model;

import org.json.JSONException;
import org.json.JSONObject;

import xtom.frame.XtomObject;
import xtom.frame.exception.DataParseException;

/**
 * ϵͳ��ʼ����Ϣ
 */
public class SysInitInfo extends XtomObject {

	private String sys_web_service;// ��̨�����·��(���汾��)
	// ���磺 http://192.168.0.146:8008/group1/wb_qk/index.php/Webservice/V100/
	// ˵����V100�Ƿ�����ת�������İ汾�ţ�V100 ��Ӧ�ͻ��˰汾��1.0.0

	private String sys_plugins;// �����������·�����磺http://58.56.89.218:8008/group1/hm_PHP/plugins/
	private String android_must_update;// ��׿ǿ�Ƹ��±��0����ǿ��1��ǿ�ƣ�������ܹ������˽ϴ�䶯���ͻ��˱���ǿ���û����������°汾��
	private String android_last_version;// ��׿���°汾�� ������Ϣ�밲׿�����汾�űȶԣ��������ȣ���������������
	private String iphone_must_update;// ƻ��ǿ�Ƹ��±��0����ǿ��1��ǿ�ƣ�������ܹ������˽ϴ�䶯���ͻ��˱���ǿ���û����������°汾��
	private String iphone_last_version;// ƻ�����°汾�� ������Ϣ��ƻ�������汾�űȶԣ��������ȣ���������������
	private String sys_chat_ip;// ���������IP��ַ ���磺192.168.0.146
	private String sys_chat_port;// ����������˿ں� ���磺5222��һ��������
	private int sys_pagesize;// ϵͳ�涨��ҳ��¼�� �˲�����ϵͳ�б��ҳʱ��Ҫ�õ���Ĭ�ϣ�20
	private String sys_service_phone;// �ҹ�˾ͳһ�ͷ��绰 ǰ̨�ͷ������ͻ�ר�ã�Ŀǰ��"0531-67804172"
	private String android_update_url;// ��׿������µ�ַ,����http://192.168.0.146:8008/group1/wb_qk/download/qk.apk
	private String iphone_update_url;// ƻ��������µ�ַ,����https://itunes.apple.com/cn/app/biaobiao/id844008952?mt=8
	private String apad_update_url;// ��׿������µ�ַ,����http://192.168.0.146:8008/group1/wb_qk/download/qk_pad.apk
	private String ipad_update_url;// ƻ��������µ�ַ,����https://itunes.apple.com/cn/app/biaobiao/id844008952?mt=8
	private String iphone_comment_url;// ƻ��������۵�ַ ͬ��
	private String msg_invite;// �������ض�������

	public SysInitInfo(JSONObject jsonObject) throws DataParseException {
		if (jsonObject != null) {
			try {
				sys_web_service = get(jsonObject, "sys_web_service");
				sys_plugins = get(jsonObject, "sys_plugins");
				android_must_update = get(jsonObject, "android_must_update");
				android_last_version = get(jsonObject, "android_last_version");
				iphone_must_update = get(jsonObject, "iphone_must_update");
				iphone_last_version = get(jsonObject, "iphone_last_version");
				sys_chat_ip = get(jsonObject, "sys_chat_ip");
				sys_chat_port = get(jsonObject, "sys_chat_port");
				if (!jsonObject.isNull("sys_pagesize"))
					sys_pagesize = jsonObject.getInt("sys_pagesize");
				sys_service_phone = get(jsonObject, "sys_service_phone");
				android_update_url = get(jsonObject, "android_update_url");
				iphone_update_url = get(jsonObject, "iphone_update_url");
				apad_update_url = get(jsonObject, "apad_update_url");
				ipad_update_url = get(jsonObject, "ipad_update_url");
				iphone_comment_url = get(jsonObject, "iphone_comment_url");
				msg_invite = get(jsonObject, "msg_invite");

				log_i(toString());
			} catch (JSONException e) {
				throw new DataParseException(e);
			}
		}
	}

	public SysInitInfo(String sys_web_service, String sys_plugins,
			String android_must_update, String android_last_version,
			String iphone_must_update, String iphone_last_version,
			String sys_chat_ip, String sys_chat_port, int sys_pagesize,
			String sys_service_phone, String android_update_url,
			String iphone_update_url, String apad_update_url,
			String ipad_update_url, String iphone_comment_url, String msg_invite) {
		super();
		this.sys_web_service = sys_web_service;
		this.sys_plugins = sys_plugins;
		this.android_must_update = android_must_update;
		this.android_last_version = android_last_version;
		this.iphone_must_update = iphone_must_update;
		this.iphone_last_version = iphone_last_version;
		this.sys_chat_ip = sys_chat_ip;
		this.sys_chat_port = sys_chat_port;
		this.sys_pagesize = sys_pagesize;
		this.sys_service_phone = sys_service_phone;
		this.android_update_url = android_update_url;
		this.iphone_update_url = iphone_update_url;
		this.apad_update_url = apad_update_url;
		this.ipad_update_url = ipad_update_url;
		this.iphone_comment_url = iphone_comment_url;
		this.msg_invite = msg_invite;
	}

	@Override
	public String toString() {
		return "SysInitInfo [sys_web_service=" + sys_web_service
				+ ", sys_plugins=" + sys_plugins + ", android_must_update="
				+ android_must_update + ", android_last_version="
				+ android_last_version + ", iphone_must_update="
				+ iphone_must_update + ", iphone_last_version="
				+ iphone_last_version + ", sys_chat_ip=" + sys_chat_ip
				+ ", sys_chat_port=" + sys_chat_port + ", sys_pagesize="
				+ sys_pagesize + ", sys_service_phone=" + sys_service_phone
				+ ", android_update_url=" + android_update_url
				+ ", iphone_update_url=" + iphone_update_url
				+ ", apad_update_url=" + apad_update_url + ", ipad_update_url="
				+ ipad_update_url + ", iphone_comment_url="
				+ iphone_comment_url + ", msg_invite=" + msg_invite + "]";
	}

	/**
	 * @return the sys_web_service
	 */
	public String getSys_web_service() {
		return sys_web_service;
	}

	/**
	 * @return the sys_plugins
	 */
	public String getSys_plugins() {
		return sys_plugins;
	}

	/**
	 * @return the android_must_update
	 */
	public String getAndroid_must_update() {
		return android_must_update;
	}

	/**
	 * @return the android_last_version
	 */
	public String getAndroid_last_version() {
		return android_last_version;
	}

	/**
	 * @return the iphone_must_update
	 */
	public String getIphone_must_update() {
		return iphone_must_update;
	}

	/**
	 * @return the iphone_last_version
	 */
	public String getIphone_last_version() {
		return iphone_last_version;
	}

	/**
	 * @return the sys_chat_ip
	 */
	public String getSys_chat_ip() {
		return sys_chat_ip;
	}

	/**
	 * @return the sys_chat_port
	 */
	public String getSys_chat_port() {
		return sys_chat_port;
	}

	/**
	 * @return the sys_pagesize
	 */
	public int getSys_pagesize() {
		return sys_pagesize;
	}

	/**
	 * @return the sys_service_phone
	 */
	public String getSys_service_phone() {
		return sys_service_phone;
	}

	/**
	 * @return the android_update_url
	 */
	public String getAndroid_update_url() {
		return android_update_url;
	}

	/**
	 * @return the iphone_update_url
	 */
	public String getIphone_update_url() {
		return iphone_update_url;
	}

	/**
	 * @return the apad_update_url
	 */
	public String getApad_update_url() {
		return apad_update_url;
	}

	/**
	 * @return the ipad_update_url
	 */
	public String getIpad_update_url() {
		return ipad_update_url;
	}

	/**
	 * @return the iphone_comment_url
	 */
	public String getIphone_comment_url() {
		return iphone_comment_url;
	}

	/**
	 * @return the msg_invite
	 */
	public String getMsg_invite() {
		return msg_invite;
	}

}
