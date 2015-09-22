package com.hemaapp.hm_gtsdp.nettask;

import java.util.HashMap;

import org.json.JSONObject;







import com.hemaapp.hm_gtsdp.GtsdpArrayResult;
import com.hemaapp.hm_gtsdp.GtsdpHttpInformation;
import com.hemaapp.hm_gtsdp.GtsdpNetTask;
import com.hemaapp.hm_gtsdp.model.SysInitInfo;

import xtom.frame.exception.DataParseException;

/**
 * 系统初始化
 */
public class InitTask extends GtsdpNetTask {

	public InitTask(GtsdpHttpInformation information,
			HashMap<String, String> params) {
		super(information, params);
	}

	public InitTask(GtsdpHttpInformation information,
			HashMap<String, String> params, HashMap<String, String> files) {
		super(information, params, files);
	}

	@Override
	public Object parse(JSONObject jsonObject) throws DataParseException {
		return new Result(jsonObject);
	}

	private class Result extends GtsdpArrayResult<SysInitInfo> {

		public Result(JSONObject jsonObject) throws DataParseException {
			super(jsonObject);
		}

		@Override
		public SysInitInfo parse(JSONObject jsonObject)
				throws DataParseException {
			return new SysInitInfo(jsonObject);
		}

	}
}
