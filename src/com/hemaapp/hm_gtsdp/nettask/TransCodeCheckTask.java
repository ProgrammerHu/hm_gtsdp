package com.hemaapp.hm_gtsdp.nettask;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import xtom.frame.exception.DataParseException;

import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpArrayResult;
import com.hemaapp.hm_gtsdp.GtsdpHttpInformation;
import com.hemaapp.hm_gtsdp.GtsdpNetTask;
import com.hemaapp.hm_gtsdp.result.ValidflagResult;
/**
 * 验证二维码是否有效
 * @author Wen
 * @author HuFanglin
 *
 */
public class TransCodeCheckTask extends GtsdpNetTask {

	public TransCodeCheckTask(GtsdpHttpInformation information,
			HashMap<String, String> params) {
		super(information, params);
	}

	@Override
	public Object parse(JSONObject jsonObject) throws DataParseException {
		return new ValidflagResult(jsonObject);
	}

}
