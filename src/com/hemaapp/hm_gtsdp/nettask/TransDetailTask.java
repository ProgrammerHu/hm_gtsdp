package com.hemaapp.hm_gtsdp.nettask;

import java.util.HashMap;

import org.json.JSONObject;

import xtom.frame.exception.DataParseException;

import com.hemaapp.hm_gtsdp.GtsdpHttpInformation;
import com.hemaapp.hm_gtsdp.GtsdpNetTask;
import com.hemaapp.hm_gtsdp.result.TransDetailResult;
/**
 * ∑¢ªıœÍ«È
 * @author Wen
 * @author HuFanglin
 *
 */
public class TransDetailTask extends GtsdpNetTask {

	public TransDetailTask(GtsdpHttpInformation information,
			HashMap<String, String> params) {
		super(information, params);
	}

	@Override
	public Object parse(JSONObject jsonObject) throws DataParseException {
		return new TransDetailResult(jsonObject);
	}

}
