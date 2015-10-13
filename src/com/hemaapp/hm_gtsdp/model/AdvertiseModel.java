package com.hemaapp.hm_gtsdp.model;

import org.json.JSONException;
import org.json.JSONObject;

import xtom.frame.XtomObject;
/**
 * ¹ã¸æ
 * @author Wen
 * @author HuFanglin
 *
 */
public class AdvertiseModel extends XtomObject {
	private String id;
	private String keytype;
	private String keyid;
	private String clicktype;
	private String clickid;
	private String imgurl;
	private String imgurlbig;
	private String content;
	private String regdate;
	private String delflag;
	private String name;
	private String orderby;
	public AdvertiseModel(String id, String keytype, String keyid,
			String clicktype, String clickid, String imgurl, String imgurlbig,
			String content, String regdate, String delflag, String name,
			String orderby) {
		super();
		this.id = id;
		this.keytype = keytype;
		this.keyid = keyid;
		this.clicktype = clicktype;
		this.clickid = clickid;
		this.imgurl = imgurl;
		this.imgurlbig = imgurlbig;
		this.content = content;
		this.regdate = regdate;
		this.delflag = delflag;
		this.name = name;
		this.orderby = orderby;
	}
	
	public AdvertiseModel(JSONObject json)
	{
		try {
			this.id=json.getString("id");
			this.keytype=json.getString("keytype");
			this.keyid=json.getString("keyid");
			this.clicktype=json.getString("clicktype");
			this.clickid=json.getString("clickid");
			this.imgurl=json.getString("imgurl");
			this.imgurlbig=json.getString("imgurlbig");
			this.content=json.getString("content");
			this.regdate=json.getString("regdate");
			this.delflag=json.getString("delflag");
			this.name=json.getString("name");
			this.orderby=json.getString("orderby");
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}
	
	public String getId() {
		return id;
	}
	public String getKeytype() {
		return keytype;
	}
	public String getKeyid() {
		return keyid;
	}
	public String getClicktype() {
		return clicktype;
	}
	public String getClickid() {
		return clickid;
	}
	public String getImgurl() {
		return imgurl;
	}
	public String getImgurlbig() {
		return imgurlbig;
	}
	public String getContent() {
		return content;
	}
	public String getRegdate() {
		return regdate;
	}
	public String getDelflag() {
		return delflag;
	}
	public String getName() {
		return name;
	}
	public String getOrderby() {
		return orderby;
	}
	

}
