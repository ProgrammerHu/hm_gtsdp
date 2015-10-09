package com.hemaapp.hm_gtsdp.model;

import org.json.JSONException;
import org.json.JSONObject;

import xtom.frame.XtomObject;
/**
 * 模板列表模型
 * @author Wen
 * @author HuFanglin
 *
 */
public class TemplateItemModel extends XtomObject {
	private String id;
	private String keytype;
	private String name;
	private String address;
	private String telphone;
	private String client_id;
	public boolean isCheck;
	
	public TemplateItemModel(String id, String keytype, String name,
			String address, String telphone, String client_id) {
		super();
		this.id = id;
		this.keytype = keytype;
		this.name = name;
		this.address = address;
		this.telphone = telphone;
		this.client_id = client_id;
		this.isCheck = false;
	}
	public TemplateItemModel(JSONObject json) {
		super();
		try 
		{
			this.id = json.getString("id");
			this.keytype = json.getString("keytype");
			this.name = json.getString("name");
			this.address = json.getString("address");
			this.telphone = json.getString("telphone");
			this.client_id = json.getString("client_id");
			this.isCheck = false;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getId() {
		return id;
	}
	public String getKeytype() {
		return keytype;
	}
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	public String getTelphone() {
		return telphone;
	}
	public String getClient_id() {
		return client_id;
	}
	
}
