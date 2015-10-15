package com.hemaapp.hm_gtsdp.nettask;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import xtom.frame.exception.DataParseException;

import com.hemaapp.hm_gtsdp.GtsdpArrayResult;
import com.hemaapp.hm_gtsdp.GtsdpHttpInformation;
import com.hemaapp.hm_gtsdp.GtsdpNetTask;
import com.hemaapp.hm_gtsdp.result.DeliveryAddResult;
/**
 * …Í«Î≈‰ÀÕ‘±
 * @author Wen
 * @author HuFanglin
 *
 */
public class DeliveryAddTask extends GtsdpNetTask {

	public DeliveryAddTask(GtsdpHttpInformation information,
			HashMap<String, String> params) {
		super(information, params);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object parse(JSONObject jsonObject) throws DataParseException {
		return new  DeliveryAddResult(jsonObject);
	}
	

}
