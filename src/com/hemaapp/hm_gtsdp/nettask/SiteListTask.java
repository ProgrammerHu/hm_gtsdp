package com.hemaapp.hm_gtsdp.nettask;

import java.util.HashMap;

import org.json.JSONObject;

import xtom.frame.exception.DataParseException;

import com.hemaapp.hm_gtsdp.GtsdpHttpInformation;
import com.hemaapp.hm_gtsdp.GtsdpNetTask;
import com.hemaapp.hm_gtsdp.result.SiteListResult;
/**
 * ªÒ»°’æµ„
 * @author Wen
 * @author HuFanglin
 *
 */
public class SiteListTask extends GtsdpNetTask {

	public SiteListTask(GtsdpHttpInformation information,
			HashMap<String, String> params) {
		super(information, params);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object parse(JSONObject jsonObject) throws DataParseException {
		return new SiteListResult(jsonObject);
	}

}
