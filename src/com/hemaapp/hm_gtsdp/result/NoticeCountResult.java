package com.hemaapp.hm_gtsdp.result;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import xtom.frame.exception.DataParseException;

import com.hemaapp.hm_FrameWork.result.HemaBaseResult;

public class NoticeCountResult extends HemaBaseResult{
	private String syscount;
	public NoticeCountResult(JSONObject jsonObject) throws DataParseException {
		super(jsonObject);
		if (jsonObject != null) {
			try {
				if (!jsonObject.isNull("infor")
						&& !isNull(jsonObject.getString("infor"))) {
					JSONArray object = jsonObject.getJSONArray("infor");
					syscount = object.getJSONObject(0).getString("syscount");
					
				}
			} catch (JSONException e) {
				throw new DataParseException(e);
			}
		}
	}

	public int getSysCount()
	{
		return Integer.parseInt(syscount);
	}
}
