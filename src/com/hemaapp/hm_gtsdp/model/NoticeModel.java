package com.hemaapp.hm_gtsdp.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.hemaapp.hm_gtsdp.view.SlideView;

import xtom.frame.XtomObject;
/**
 * 消息通知模型
 * @author Wen
 * @author HuFanglin
 *
 */
public class NoticeModel extends XtomObject {
    public SlideView slideView;
	private String id;
	private String keytype;
	private String keyid;
	private String content;
	private String client_id;
	private String from_id;
	private String looktype;
	private String regdate;
	private String avatar;
	private String nickname;
	public String getId() {
		return id;
	}
	public String getKeytype() {
		return keytype;
	}
	public String getKeyid() {
		return keyid;
	}
	public String getContent() {
		return content;
	}
	public String getClient_id() {
		return client_id;
	}
	public String getFrom_id() {
		return from_id;
	}
	public String getLooktype() {
		return looktype;
	}
	public void setLooktype(String looktype) {
		this.looktype = looktype;
	}
	public String getRegdate() {
		return regdate;
	}
	public String getAvatar() {
		return avatar;
	}
	public String getNickname() {
		return nickname;
	}
	public NoticeModel(String id, String keytype, String keyid, String content,
			String client_id, String from_id, String looktype, String regdate,
			String avatar, String nickname) {
		super();
		this.id = id;
		this.keytype = keytype;
		this.keyid = keyid;
		this.content = content;
		this.client_id = client_id;
		this.from_id = from_id;
		this.looktype = looktype;
		this.regdate = regdate;
		this.avatar = avatar;
		this.nickname = nickname;
	}
	
	public NoticeModel(JSONObject json)
	{
		try {
			this.id = json.getString("id");
			this.keytype = json.getString("keytype");
			this.keyid = json.getString("keyid");
			this.content = json.getString("content");
			this.client_id = json.getString("client_id");
			this.from_id = json.getString("from_id");
			this.looktype = json.getString("looktype");
			this.regdate = json.getString("regdate");
			this.avatar = json.getString("avatar");
			this.nickname = json.getString("nickname");
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}
	
}
