package com.hemaapp.hm_gtsdp.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import xtom.frame.XtomObject;

/**
 * 用户详细信息模型
 * @author Wen
 * @author HuFanglin
 *
 */
public class UserInfo extends XtomObject {
	private String id;
	private String username;
	private String email;
	private String password;
	private String paypassword;
	private String nickname;
	private String charindex;
	private String sex;
	private String birthday;
	private String mobile;
	private String selfsign;
	private String avatar;
	private String avatarbig;
	private String backimg;
	private String district_name;
	private String onlineflag;
	private String validflag;
	private String vestflag;
	private String score;
	private String total_score;
	private String feeaccount;
	private String little_fee;
	private String lng;
	private String lat;
	private String deviceid;
	private String devicetype;
	private String channelid;
	private String lastloginversion;
	private String lastlogintime;
	private String lastsigndate;
	private String content;
	private String delflag;
	private String codeurl;
	private String job;
	private String regdate;
	private String invitecode;
	private String shop_id;
	private String shop_role;
	private String checkflag;
	private String rrno;
	private String aliuser;
	private String weixinuser;
	private String bankuser;
	private String bankname;
	private String bankcard;
	private String bankaddress;
	private String tag;
	private String invite_id;
	private String blog_count;
	private String love_goods_count;
	private String love_shop_count;
	private String signflag;
	private List<SubjectItems> subjectItems;
	private List<ImageItem> imageItems;
	private String blogcount;
	private String total_fee;
	private String shop_score;
	private String friendflag;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPaypassword() {
		return paypassword;
	}

	public void setPaypassword(String paypassword) {
		this.paypassword = paypassword;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getCharindex() {
		return charindex;
	}

	public void setCharindex(String charindex) {
		this.charindex = charindex;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSelfsign() {
		return selfsign;
	}

	public void setSelfsign(String selfsign) {
		this.selfsign = selfsign;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getAvatarbig() {
		return avatarbig;
	}

	public void setAvatarbig(String avatarbig) {
		this.avatarbig = avatarbig;
	}

	public String getBackimg() {
		return backimg;
	}

	public void setBackimg(String backimg) {
		this.backimg = backimg;
	}

	public String getDistrict_name() {
		return district_name;
	}

	public void setDistrict_name(String district_name) {
		this.district_name = district_name;
	}

	public String getOnlineflag() {
		return onlineflag;
	}

	public void setOnlineflag(String onlineflag) {
		this.onlineflag = onlineflag;
	}

	public String getValidflag() {
		return validflag;
	}

	public void setValidflag(String validflag) {
		this.validflag = validflag;
	}

	public String getVestflag() {
		return vestflag;
	}

	public void setVestflag(String vestflag) {
		this.vestflag = vestflag;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getTotal_score() {
		return total_score;
	}

	public void setTotal_score(String total_score) {
		this.total_score = total_score;
	}

	public String getFeeaccount() {
		return feeaccount;
	}

	public void setFeeaccount(String feeaccount) {
		this.feeaccount = feeaccount;
	}

	public String getLittle_fee() {
		return little_fee;
	}

	public void setLittle_fee(String little_fee) {
		this.little_fee = little_fee;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	public String getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}

	public String getChannelid() {
		return channelid;
	}

	public void setChannelid(String channelid) {
		this.channelid = channelid;
	}

	public String getLastloginversion() {
		return lastloginversion;
	}

	public void setLastloginversion(String lastloginversion) {
		this.lastloginversion = lastloginversion;
	}

	public String getLastlogintime() {
		return lastlogintime;
	}

	public void setLastlogintime(String lastlogintime) {
		this.lastlogintime = lastlogintime;
	}

	public String getLastsigndate() {
		return lastsigndate;
	}

	public void setLastsigndate(String lastsigndate) {
		this.lastsigndate = lastsigndate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

	public String getCodeurl() {
		return codeurl;
	}

	public void setCodeurl(String codeurl) {
		this.codeurl = codeurl;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getInvitecode() {
		return invitecode;
	}

	public void setInvitecode(String invitecode) {
		this.invitecode = invitecode;
	}

	public String getShop_id() {
		return shop_id;
	}

	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}

	public String getShop_role() {
		return shop_role;
	}

	public void setShop_role(String shop_role) {
		this.shop_role = shop_role;
	}

	public String getCheckflag() {
		return checkflag;
	}

	public void setCheckflag(String checkflag) {
		this.checkflag = checkflag;
	}

	public String getRrno() {
		return rrno;
	}

	public void setRrno(String rrno) {
		this.rrno = rrno;
	}

	public String getAliuser() {
		return aliuser;
	}

	public void setAliuser(String aliuser) {
		this.aliuser = aliuser;
	}

	public String getWeixinuser() {
		return weixinuser;
	}

	public void setWeixinuser(String weixinuser) {
		this.weixinuser = weixinuser;
	}

	public String getBankuser() {
		return bankuser;
	}

	public void setBankuser(String bankuser) {
		this.bankuser = bankuser;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getBankcard() {
		return bankcard;
	}

	public void setBankcard(String bankcard) {
		this.bankcard = bankcard;
	}

	public String getBankaddress() {
		return bankaddress;
	}

	public void setBankaddress(String bankaddress) {
		this.bankaddress = bankaddress;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getInvite_id() {
		return invite_id;
	}

	public void setInvite_id(String invite_id) {
		this.invite_id = invite_id;
	}

	public String getBlog_count() {
		return blog_count;
	}

	public void setBlog_count(String blog_count) {
		this.blog_count = blog_count;
	}

	public String getLove_goods_count() {
		return love_goods_count;
	}

	public void setLove_goods_count(String love_goods_count) {
		this.love_goods_count = love_goods_count;
	}

	public String getLove_shop_count() {
		return love_shop_count;
	}

	public void setLove_shop_count(String love_shop_count) {
		this.love_shop_count = love_shop_count;
	}

	public String getSignflag() {
		return signflag;
	}

	public void setSignflag(String signflag) {
		this.signflag = signflag;
	}

	public List<SubjectItems> getSubjectItems() {
		return subjectItems;
	}


	public List<ImageItem> getImageItems() {
		return imageItems;
	}

	public String getBlogcount() {
		return blogcount;
	}

	public void setBlogcount(String blogcount) {
		this.blogcount = blogcount;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getShop_score() {
		return shop_score;
	}

	public void setShop_score(String shop_score) {
		this.shop_score = shop_score;
	}

	public String getFriendflag() {
		return friendflag;
	}

	public void setFriendflag(String friendflag) {
		this.friendflag = friendflag;
	}

	public UserInfo(String id, String username, String email, String password,
			String paypassword, String nickname, String charindex, String sex,
			String birthday, String mobile, String selfsign, String avatar,
			String avatarbig, String backimg, String district_name,
			String onlineflag, String validflag, String vestflag, String score,
			String total_score, String feeaccount, String little_fee,
			String lng, String lat, String deviceid, String devicetype,
			String channelid, String lastloginversion, String lastlogintime,
			String lastsigndate, String content, String delflag,
			String codeurl, String job, String regdate, String invitecode,
			String shop_id, String shop_role, String checkflag, String rrno,
			String aliuser, String weixinuser, String bankuser,
			String bankname, String bankcard, String bankaddress, String tag,
			String invite_id, String blog_count, String love_goods_count,
			String love_shop_count, String signflag,
			 String blogcount, String total_fee,
			String shop_score, String friendflag) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.paypassword = paypassword;
		this.nickname = nickname;
		this.charindex = charindex;
		this.sex = sex;
		this.birthday = birthday;
		this.mobile = mobile;
		this.selfsign = selfsign;
		this.avatar = avatar;
		this.avatarbig = avatarbig;
		this.backimg = backimg;
		this.district_name = district_name;
		this.onlineflag = onlineflag;
		this.validflag = validflag;
		this.vestflag = vestflag;
		this.score = score;
		this.total_score = total_score;
		this.feeaccount = feeaccount;
		this.little_fee = little_fee;
		this.lng = lng;
		this.lat = lat;
		this.deviceid = deviceid;
		this.devicetype = devicetype;
		this.channelid = channelid;
		this.lastloginversion = lastloginversion;
		this.lastlogintime = lastlogintime;
		this.lastsigndate = lastsigndate;
		this.content = content;
		this.delflag = delflag;
		this.codeurl = codeurl;
		this.job = job;
		this.regdate = regdate;
		this.invitecode = invitecode;
		this.shop_id = shop_id;
		this.shop_role = shop_role;
		this.checkflag = checkflag;
		this.rrno = rrno;
		this.aliuser = aliuser;
		this.weixinuser = weixinuser;
		this.bankuser = bankuser;
		this.bankname = bankname;
		this.bankcard = bankcard;
		this.bankaddress = bankaddress;
		this.tag = tag;
		this.invite_id = invite_id;
		this.blog_count = blog_count;
		this.love_goods_count = love_goods_count;
		this.love_shop_count = love_shop_count;
		this.signflag = signflag;
		this.blogcount = blogcount;
		this.total_fee = total_fee;
		this.shop_score = shop_score;
		this.friendflag = friendflag;
	}

	public UserInfo(JSONObject json)
	{
		try {
			this.id = json.getString("id");
			this.username = json.getString("username");
			this.email = json.getString("email");
			this.password = json.getString("password");
			this.paypassword = json.getString("paypassword");
			this.nickname = json.getString("nickname");
			this.charindex = json.getString("charindex");
			this.sex = json.getString("sex");
			this.birthday = json.getString("birthday");
			this.mobile = json.getString("mobile");
			this.selfsign = json.getString("selfsign");
			this.avatar = json.getString("avatar");
			this.avatarbig = json.getString("avatarbig");
			this.backimg = json.getString("backimg");
			this.district_name = json.getString("district_name");
			this.onlineflag = json.getString("onlineflag");
			this.validflag = json.getString("validflag");
			this.vestflag = json.getString("vestflag");
			this.score = json.getString("score");
			this.total_score = json.getString("total_score");
			this.feeaccount = json.getString("feeaccount");
			this.little_fee = json.getString("little_fee");
			this.lng = json.getString("lng");
			this.lat = json.getString("lat");
			this.deviceid = json.getString("deviceid");
			this.devicetype = json.getString("devicetype");
			this.channelid = json.getString("channelid");
			this.lastloginversion = json.getString("lastloginversion");
			this.lastlogintime = json.getString("lastlogintime");
			this.lastsigndate = json.getString("lastsigndate");
			this.content = json.getString("content");
			this.delflag = json.getString("delflag");
			this.codeurl = json.getString("codeurl");
			this.job = json.getString("job");
			this.regdate = json.getString("regdate");
			this.invitecode = json.getString("invitecode");
			this.shop_id = json.getString("shop_id");
			this.shop_role = json.getString("shop_role");
			this.checkflag = json.getString("checkflag");
			this.rrno = json.getString("rrno");
			this.aliuser = json.getString("aliuser");
			this.weixinuser = json.getString("weixinuser");
			this.bankuser = json.getString("bankuser");
			this.bankname = json.getString("bankname");
			this.bankcard = json.getString("bankcard");
			this.bankaddress = json.getString("bankaddress");
			this.tag = json.getString("tag");
			this.invite_id = json.getString("invite_id");
			this.blog_count = json.getString("blog_count");
			this.love_goods_count = json.getString("love_goods_count");
			this.love_shop_count = json.getString("love_shop_count");
			this.signflag = json.getString("signflag");
			
			JSONArray subjects = json.getJSONArray("subjectItems");
			this.subjectItems = new ArrayList<SubjectItems>();
			if(subjects != null && subjects.length() > 0)
			{
				for(int i = 0; i < subjects.length(); i++)
					this.subjectItems.add(new SubjectItems(subjects.getJSONObject(i)));
			}
			
			JSONArray images  =json.getJSONArray("imageItems");
			this.imageItems = new ArrayList<ImageItem>();
			if(images != null && images.length() > 0)
			{
				for(int i = 0; i < images.length(); i++)
					this.imageItems.add(new ImageItem(images.getJSONObject(i)));
			}
			this.blogcount = json.getString("blogcount");
			this.total_fee = json.getString("total_fee");
			this.shop_score = json.getString("shop_score");
			this.friendflag = json.getString("friendflag");

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
