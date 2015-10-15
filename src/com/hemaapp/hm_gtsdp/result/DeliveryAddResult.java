package com.hemaapp.hm_gtsdp.result;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import xtom.frame.exception.DataParseException;

import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
/**
 * …Í«Î≈‰ÀÕ‘±Ω·π˚
 * @author Wen
 * @author HuFanglin
 *
 */
public class DeliveryAddResult extends HemaBaseResult{
	private String pubid;
	public DeliveryAddResult(JSONObject jsonObject) throws DataParseException {
		super(jsonObject);
		if (jsonObject != null) {
			try {
				if (!jsonObject.isNull("infor")
						&& !isNull(jsonObject.getString("infor"))) {
					JSONArray object = jsonObject.getJSONArray("infor");
					pubid = object.getJSONObject(0).getString("pubid");
					
				}
			} catch (JSONException e) {
				throw new DataParseException(e);
			}
		}
	}
	
	public String getPubid()
	{
		return pubid;
	}
}
