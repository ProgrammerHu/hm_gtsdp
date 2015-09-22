package com.hemaapp.hm_gtsdp.model;

import org.json.JSONException;
import org.json.JSONObject;

import xtom.frame.exception.DataParseException;

import com.hemaapp.hm_FrameWork.HemaUser;

/**
 * �û���Ϣ(ע��User��Ϣ����̳�HemaUser,����User�в����ٰ���token�ֶ�)
 */
public class User extends HemaUser {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8412756093515557415L;
	private String id;// �û�����
	private String username;// ��¼��
	private String email;// �û�����
	private String nickname;// �û��ǳ�
	private String worker;// �û�ְλ
	private String password;// ��½���� �������˴洢����32λ��MD5���ܴ�
	private String charindex;// �û��ǳƵĺ���ƴ������ĸ����
	private String sex;// �û��Ա� �������˴洢����32λ��MD5���ܴ�
	private String mobile;// �û��ֻ�
	private String avatar;// ������ҳͷ��ͼƬ��С�� ���Ϊ������ʾϵͳĬ��ͷ��С��
	private String avatarbig;// ������ҳͷ��ͼƬ���� ���Ϊ������ʾϵͳĬ��ͷ�񣨴�
	private String backimg;// ������ҳ����ͼƬ ���Ϊ������ʾϵͳĬ�ϱ���
	private String district_name;// �û����� 0��1��
	private String onlineflag;// �û����߱�� 0��1��
	private String validflag;// �û�״̬��� 0����1��Ч
	private String devicetype;// �û��ͻ������� 1��ƻ�� 2����׿
	private String deviceid;// �û��ͻ���Ӳ����ʶ��
	private String lastlogintime;// ���һ�ε�¼��ʱ��
	private String lastloginversion;// ���һ�ε�¼�İ汾
	private String regdate;// �û�ע��ʱ��
	private String team_id;// ���Ŷ�����id
	private String team_name;// ���Ŷ�����
	private String workscore;// ִ����ָ��
	private String hardscore;// ����ָ��
	private String cardavatar;// ��Ƭͷ��
	private String cardavatarbig;// ��Ƭͷ���ͼ
	private String cardname;// ��Ƭ����
	private String cardbusiness;// ��Ƭ��ҵ
	private String cardbusinessid;// ��Ƭ��ҵ����
	private String cardworker;// ��Ƭְλ
	private String cardcompany;// ��Ƭ��˾
	private String cardmobile;// ��Ƭ�绰
	private String cardemail;// ��Ƭ����
	private String cardweburl;// ��Ƭ��ַ
	private String cardopenflag;// ��Ƭ�Ƿ񹫿� ��Ƭ����0��1�� Ĭ�Ϲ���
	private String team_validflag;// �Ŷ��Ƿ���Ч
									// ��team_validflag=0ʱ����¼����Ҫ��ת������Ȩ�ޣ�����ϵ�ͷ�ҳ�桱

	public User(JSONObject jsonObject) throws DataParseException {
		super(jsonObject);
		if (jsonObject != null) {
			try {
				id = get(jsonObject, "id");
				username = get(jsonObject, "username");
				email = get(jsonObject, "email");
				nickname = get(jsonObject, "nickname");
				worker = get(jsonObject, "worker");
				password = get(jsonObject, "password");
				charindex = get(jsonObject, "charindex");
				sex = get(jsonObject, "sex");
				mobile = get(jsonObject, "mobile");
				avatar = get(jsonObject, "avatar");
				avatarbig = get(jsonObject, "avatarbig");
				backimg = get(jsonObject, "backimg");
				district_name = get(jsonObject, "district_name");
				onlineflag = get(jsonObject, "onlineflag");
				validflag = get(jsonObject, "validflag");
				devicetype = get(jsonObject, "devicetype");
				deviceid = get(jsonObject, "deviceid");
				lastlogintime = get(jsonObject, "lastlogintime");
				lastloginversion = get(jsonObject, "lastloginversion");
				regdate = get(jsonObject, "regdate");
				team_id = get(jsonObject, "team_id");
				team_name = get(jsonObject, "team_name");
				workscore = get(jsonObject, "workscore");
				hardscore = get(jsonObject, "hardscore");
				cardavatar = get(jsonObject, "cardavatar");
				cardavatarbig = get(jsonObject, "cardavatarbig");
				cardname = get(jsonObject, "cardname");
				cardbusiness = get(jsonObject, "cardbusiness");
				cardbusinessid = get(jsonObject, "cardbusinessid");
				cardworker = get(jsonObject, "cardworker");
				cardcompany = get(jsonObject, "cardcompany");
				cardmobile = get(jsonObject, "cardmobile");
				cardemail = get(jsonObject, "cardemail");
				cardweburl = get(jsonObject, "cardweburl");
				cardopenflag = get(jsonObject, "cardopenflag");
				team_validflag = get(jsonObject, "team_validflag");

				log_i(toString());
			} catch (JSONException e) {
				throw new DataParseException(e);
			}
		}
	}

	public User(String token, String id, String username, String email,
			String nickname, String worker, String password, String charindex,
			String sex, String mobile, String avatar, String avatarbig,
			String backimg, String district_name, String onlineflag,
			String validflag, String devicetype, String deviceid,
			String lastlogintime, String lastloginversion, String regdate,
			String team_id, String team_name, String workscore,
			String hardscore, String cardavatar, String cardname,
			String cardbusiness, String cardbusinessid, String cardworker,
			String cardcompany, String cardmobile, String cardemail,
			String cardweburl) {
		super(token);
		this.id = id;
		this.username = username;
		this.email = email;
		this.nickname = nickname;
		this.worker = worker;
		this.password = password;
		this.charindex = charindex;
		this.sex = sex;
		this.mobile = mobile;
		this.avatar = avatar;
		this.avatarbig = avatarbig;
		this.backimg = backimg;
		this.district_name = district_name;
		this.onlineflag = onlineflag;
		this.validflag = validflag;
		this.devicetype = devicetype;
		this.deviceid = deviceid;
		this.lastlogintime = lastlogintime;
		this.lastloginversion = lastloginversion;
		this.regdate = regdate;
		this.team_id = team_id;
		this.team_name = team_name;
		this.workscore = workscore;
		this.hardscore = hardscore;
		this.cardavatar = cardavatar;
		this.cardname = cardname;
		this.cardbusiness = cardbusiness;
		this.cardbusinessid = cardbusinessid;
		this.cardworker = cardworker;
		this.cardcompany = cardcompany;
		this.cardmobile = cardmobile;
		this.cardemail = cardemail;
		this.cardweburl = cardweburl;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email
				+ ", nickname=" + nickname + ", worker=" + worker
				+ ", password=" + password + ", charindex=" + charindex
				+ ", sex=" + sex + ", mobile=" + mobile + ", avatar=" + avatar
				+ ", avatarbig=" + avatarbig + ", backimg=" + backimg
				+ ", district_name=" + district_name + ", onlineflag="
				+ onlineflag + ", validflag=" + validflag + ", devicetype="
				+ devicetype + ", deviceid=" + deviceid + ", lastlogintime="
				+ lastlogintime + ", lastloginversion=" + lastloginversion
				+ ", regdate=" + regdate + ", team_id=" + team_id
				+ ", team_name=" + team_name + ", workscore=" + workscore
				+ ", hardscore=" + hardscore + ", cardavatar=" + cardavatar
				+ ", cardavatarbig=" + cardavatarbig + ", cardname=" + cardname
				+ ", cardbusiness=" + cardbusiness + ", cardbusinessid="
				+ cardbusinessid + ", cardworker=" + cardworker
				+ ", cardcompany=" + cardcompany + ", cardmobile=" + cardmobile
				+ ", cardemail=" + cardemail + ", cardweburl=" + cardweburl
				+ ", cardopenflag=" + cardopenflag + ", team_validflag="
				+ team_validflag + "]";
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @return the worker
	 */
	public String getWorker() {
		return worker;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the charindex
	 */
	public String getCharindex() {
		return charindex;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @return the avatar
	 */
	public String getAvatar() {
		return avatar;
	}

	/**
	 * @return the avatarbig
	 */
	public String getAvatarbig() {
		return avatarbig;
	}

	/**
	 * @return the backimg
	 */
	public String getBackimg() {
		return backimg;
	}

	/**
	 * @return the district_name
	 */
	public String getDistrict_name() {
		return district_name;
	}

	/**
	 * @return the onlineflag
	 */
	public String getOnlineflag() {
		return onlineflag;
	}

	/**
	 * @return the validflag
	 */
	public String getValidflag() {
		return validflag;
	}

	/**
	 * @return the devicetype
	 */
	public String getDevicetype() {
		return devicetype;
	}

	/**
	 * @return the deviceid
	 */
	public String getDeviceid() {
		return deviceid;
	}

	/**
	 * @return the lastlogintime
	 */
	public String getLastlogintime() {
		return lastlogintime;
	}

	/**
	 * @return the lastloginversion
	 */
	public String getLastloginversion() {
		return lastloginversion;
	}

	/**
	 * @return the regdate
	 */
	public String getRegdate() {
		return regdate;
	}

	/**
	 * @return the team_id
	 */
	public String getTeam_id() {
		return team_id;
	}

	/**
	 * @return the team_name
	 */
	public String getTeam_name() {
		return team_name;
	}

	/**
	 * @return the workscore
	 */
	public String getWorkscore() {
		return workscore;
	}

	/**
	 * @return the hardscore
	 */
	public String getHardscore() {
		return hardscore;
	}

	/**
	 * @return the cardavatar
	 */
	public String getCardavatar() {
		return cardavatar;
	}

	/**
	 * @return the cardavatarbig
	 */
	public String getCardavatarbig() {
		return cardavatarbig;
	}

	/**
	 * @return the cardname
	 */
	public String getCardname() {
		return cardname;
	}

	/**
	 * @return the cardbusiness
	 */
	public String getCardbusiness() {
		return cardbusiness;
	}

	/**
	 * @return the cardbusinessid
	 */
	public String getCardbusinessid() {
		return cardbusinessid;
	}

	/**
	 * @return the cardworker
	 */
	public String getCardworker() {
		return cardworker;
	}

	/**
	 * @return the cardcompany
	 */
	public String getCardcompany() {
		return cardcompany;
	}

	/**
	 * @return the cardmobile
	 */
	public String getCardmobile() {
		return cardmobile;
	}

	/**
	 * @return the cardemail
	 */
	public String getCardemail() {
		return cardemail;
	}

	/**
	 * @return the cardweburl
	 */
	public String getCardweburl() {
		return cardweburl;
	}

	/**
	 * @return the cardopenflag
	 */
	public String getCardopenflag() {
		return cardopenflag;
	}

	/**
	 * @return the team_validflag
	 */
	public String getTeam_validflag() {
		return team_validflag;
	}

	/**
	 * @param team_id
	 *            the team_id to set
	 */
	public void setTeam_id(String team_id) {
		this.team_id = team_id;
	}

	/**
	 * @param cardopenflag
	 *            the cardopenflag to set
	 */
	public void setCardopenflag(String cardopenflag) {
		this.cardopenflag = cardopenflag;
	}

}
