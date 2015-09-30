package com.hemaapp.hm_gtsdp.model;

import org.json.JSONException;
import org.json.JSONObject;

import xtom.frame.XtomObject;
/**
 * 用户头像模型
 * @author Wen
 * @author HuFanglin
 *
 */
public class ImageItem extends XtomObject {
	private String id ;
	private String client_id ;
	private String content ;
	private String keytype ;
	private String keyid ;
	private String imgurl ;
	private String imgurlbig ;
	private String orderby ;
	private String regdate ;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getKeytype() {
		return keytype;
	}
	public void setKeytype(String keytype) {
		this.keytype = keytype;
	}
	public String getKeyid() {
		return keyid;
	}
	public void setKeyid(String keyid) {
		this.keyid = keyid;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getImgurlbig() {
		return imgurlbig;
	}
	public void setImgurlbig(String imgurlbig) {
		this.imgurlbig = imgurlbig;
	}
	public String getOrderby() {
		return orderby;
	}
	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public ImageItem(String id, String client_id, String content,
			String keytype, String keyid, String imgurl, String imgurlbig,
			String orderby, String regdate) {
		super();
		this.id = id;
		this.client_id = client_id;
		this.content = content;
		this.keytype = keytype;
		this.keyid = keyid;
		this.imgurl = imgurl;
		this.imgurlbig = imgurlbig;
		this.orderby = orderby;
		this.regdate = regdate;
	}
	
	public ImageItem(JSONObject json)
	{
		try {
			this.id = json.getString("id");
			this.client_id = json.getString("client_id");
			this.content = json.getString("content");
			this.keytype = json.getString("keytype");
			this.keyid = json.getString("keyid");
			this.imgurl = json.getString("imgurl");
			this.imgurlbig = json.getString("imgurlbig");
			this.orderby = json.getString("orderby");
			this.regdate = json.getString("regdate");
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}
}
