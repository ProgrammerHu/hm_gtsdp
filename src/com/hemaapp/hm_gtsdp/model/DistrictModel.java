package com.hemaapp.hm_gtsdp.model;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import xtom.frame.XtomObject;
import xtom.frame.exception.DataParseException;

/**
 * 县区级模型
 * @author Wen
 * @author HuFanglin
 *
 */
public class DistrictModel extends XtomObject {
	private String id;
	private String name;
	private String zipcode;
	private List<DistrictModel> childDistrict;
	/**
	 * 行政区域的层级， 1：省级；2：地市级；3：县区级
	 */
	private int level;
	
	public DistrictModel() {
		super();
	}

	public DistrictModel(String name, String zipcode) {
		super();
		this.name = name;
		this.zipcode = zipcode;
	}
	
	public DistrictModel(String id, String name, List<DistrictModel> childDistrict, String zipcode, int level) {
		super();
		this.id = id;
		this.name = name;
		this.zipcode = zipcode;
		this.level = level;
		this.childDistrict = childDistrict;
	}
	
	public DistrictModel(JSONObject jsonObject) throws DataParseException 
	{
		if(jsonObject != null)
		{
			try
			{
				id = get(jsonObject, "id");
				name = get(jsonObject, "name");
//				zipcode = get(jsonObject, "id");
				level = Integer.parseInt(get(jsonObject, "level"));
			}
			catch(JSONException e)
			{
				throw new DataParseException(e);
			}
		}
	}

	public String getName() {
		return name;
	}

	public String getId()
	{
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getZipcode() {
		return zipcode;
	}

	public int getLevel()
	{
		return level;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	@Override
	public String toString() {
		return "DistrictModel [name=" + name + ", zipcode=" + zipcode + "]";
	}
	public List<DistrictModel> getChildList() {
		return childDistrict;
	}
	public void setChildList(List<DistrictModel> childDistrict)
	{
		this.childDistrict = childDistrict;
	}

}
