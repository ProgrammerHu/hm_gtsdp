package com.hemaapp.hm_gtsdp.model;

import org.json.JSONException;
import org.json.JSONObject;

import xtom.frame.exception.DataParseException;

import com.hemaapp.hm_FrameWork.HemaUser;

/**
 * 用户信息(注意User信息必须继承HemaUser,并且User中不用再包含token字段)
 */
public class User extends HemaUser {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8412756093515557415L;
	private String id;// 用户主键
	private String username;// 登录名
	private String email;// 用户邮箱
	private String nickname;// 用户昵称
	private String worker;// 用户职位
	private String password;// 登陆密码 服务器端存储的是32位的MD5加密串
	private String charindex;// 用户昵称的汉语拼音首字母索引
	private String sex;// 用户性别 服务器端存储的是32位的MD5加密串
	private String mobile;// 用户手机
	private String avatar;// 个人主页头像图片（小） 如果为空请显示系统默认头像（小）
	private String avatarbig;// 个人主页头像图片（大） 如果为空请显示系统默认头像（大）
	private String backimg;// 个人主页背景图片 如果为空请显示系统默认背景
	private String district_name;// 用户地区 0否1是
	private String onlineflag;// 用户在线标记 0否1是
	private String validflag;// 用户状态标记 0冻结1有效
	private String devicetype;// 用户客户端类型 1：苹果 2：安卓
	private String deviceid;// 用户客户端硬件标识码
	private String lastlogintime;// 最后一次登录的时间
	private String lastloginversion;// 最后一次登录的版本
	private String regdate;// 用户注册时间
	private String team_id;// 根团队主键id
	private String team_name;// 根团队名称
	private String workscore;// 执行力指数
	private String hardscore;// 勤劳指数
	private String cardavatar;// 名片头像
	private String cardavatarbig;// 名片头像大图
	private String cardname;// 名片姓名
	private String cardbusiness;// 名片行业
	private String cardbusinessid;// 名片行业主键
	private String cardworker;// 名片职位
	private String cardcompany;// 名片公司
	private String cardmobile;// 名片电话
	private String cardemail;// 名片邮箱
	private String cardweburl;// 名片网址
	private String cardopenflag;// 名片是否公开 名片公开0否1是 默认公开
	private String team_validflag;// 团队是否有效
									// 当team_validflag=0时，登录后需要跳转到“无权限，请联系客服页面”

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
