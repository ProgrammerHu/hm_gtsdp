package com.hemaapp.hm_gtsdp.nettask;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import xtom.frame.exception.DataParseException;

import com.hemaapp.hm_FrameWork.result.HemaArrayResult;
import com.hemaapp.hm_gtsdp.GtsdpHttpInformation;
import com.hemaapp.hm_gtsdp.GtsdpNetTask;

/**
 * 新增发货线程
 * @author Wen
 * @author HuFanglin
 *
 */
public class TransAddTask extends GtsdpNetTask {

	public TransAddTask(GtsdpHttpInformation information,
			HashMap<String, String> params) {
		super(information, params);
	}

	@Override
	public Object parse(JSONObject jsonObject) throws DataParseException {
		return new Result(jsonObject);
	}
	
	private class Result extends HemaArrayResult<String>
	{

		public Result(JSONObject jsonObject) throws DataParseException {
			super(jsonObject);
		}

		@Override
		public String parse(JSONObject jsonObject) throws DataParseException {
			try {
				return get(jsonObject, "pubid");
			} catch (JSONException e) {
				throw new DataParseException(e);
			}
		}
		
	}

}
