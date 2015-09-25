package com.hemaapp.hm_gtsdp.nettask;

import java.util.HashMap;

import org.json.JSONObject;

import xtom.frame.exception.DataParseException;

import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpHttpInformation;
import com.hemaapp.hm_gtsdp.GtsdpNetTask;
/**
 * ÐÞ¸ÄÃÜÂë½Ó¿Ú
 * @author Wen
 * @author HuFanglin
 *
 */
public class ChangePwdTask extends GtsdpNetTask {

	public ChangePwdTask(GtsdpHttpInformation information,
			HashMap<String, String> params) {
		super(information, params);
	}

	@Override
	public Object parse(JSONObject jsonObject) throws DataParseException {
		return new HemaBaseResult(jsonObject);
	}
	
}
