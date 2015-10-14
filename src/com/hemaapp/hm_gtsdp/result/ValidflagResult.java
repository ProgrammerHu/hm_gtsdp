package com.hemaapp.hm_gtsdp.result;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import xtom.frame.exception.DataParseException;

import com.hemaapp.hm_FrameWork.result.HemaBaseResult;

public class ValidflagResult extends HemaBaseResult
{
	private String validflag;
	public ValidflagResult(JSONObject jsonObject) throws DataParseException {
		super(jsonObject);
		if (!jsonObject.isNull("infor")) {
			try {
				JSONArray infor = jsonObject.getJSONArray("infor");
				validflag = infor.getJSONObject(0).getString("validflag");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}
	public boolean getValidflag()
	{
		if(validflag == null || "0".equals(validflag))
		{
			return false;
		}
		return true;
	}
}