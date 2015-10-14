package com.hemaapp.hm_gtsdp.result;

import org.json.JSONException;
import org.json.JSONObject;

import xtom.frame.exception.DataParseException;

import com.hemaapp.hm_FrameWork.result.HemaBaseResult;

public class SiteListResult extends HemaBaseResult {
	
	private String provinces;
	private String sites;
	
	public SiteListResult(JSONObject jsonObject) throws DataParseException {
		super(jsonObject);
		try 
		{
			JSONObject json = jsonObject.getJSONArray("infor").getJSONObject(0);
			provinces = json.getJSONArray("one").toString();
			sites = json.getJSONArray("two").toString();
		} 
		catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getProvinces()
	{
		return  "{listItems:"+provinces +"}";
	}
	public String getSites()
	{
		return "{listItems:"+sites+"}";
	}

}
