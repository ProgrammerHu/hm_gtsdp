package com.hemaapp.hm_gtsdp.model;

import org.json.JSONException;
import org.json.JSONObject;

import xtom.frame.XtomObject;
/**
 * 感兴趣的话题
 * @author Wen
 * @author HuFanglin
 *
 */
public class SubjectItems extends XtomObject{
	private String id;
	private String keytype;
	private String name;
	private String delflag;
	private String regdate;
	private String hotflag;
	public String getId() {
		return id;
	}
	public String getKeytype() {
		return keytype;
	}
	public String getName() {
		return name;
	}
	public String getDelflag() {
		return delflag;
	}
	public String getRegdate() {
		return regdate;
	}
	public String getHotflag() {
		return hotflag;
	}
	public SubjectItems(String id, String keytype, String name, String delflag,
			String regdate, String hotflag) {
		super();
		this.id = id;
		this.keytype = keytype;
		this.name = name;
		this.delflag = delflag;
		this.regdate = regdate;
		this.hotflag = hotflag;
	}
	public SubjectItems(JSONObject json)
	{
		try {
			this.id = json.getString("id");
			this.keytype = json.getString("keytype");
			this.name = json.getString("name");
			this.delflag = json.getString("delflag");
			this.regdate = json.getString("regdate");
			this.hotflag = json.getString("hotflag");
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
