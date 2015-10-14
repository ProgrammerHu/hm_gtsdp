package com.hemaapp.hm_gtsdp.nettask;

import java.util.HashMap;

import org.json.JSONObject;

import xtom.frame.exception.DataParseException;

import com.hemaapp.hm_gtsdp.GtsdpArrayResult;
import com.hemaapp.hm_gtsdp.GtsdpHttpInformation;
import com.hemaapp.hm_gtsdp.GtsdpNetTask;
import com.hemaapp.hm_gtsdp.model.QuestionModel;

public class QuestionListTask extends GtsdpNetTask {

	public QuestionListTask(GtsdpHttpInformation information,
			HashMap<String, String> params) {
		super(information, params);
	}

	@Override
	public Object parse(JSONObject jsonObject) throws DataParseException {
		return new Result(jsonObject);
	}
	
	private class Result extends GtsdpArrayResult<QuestionModel>
	{

		public Result(JSONObject jsonObject) throws DataParseException {
			super(jsonObject);
		}

		@Override
		public QuestionModel parse(JSONObject jsonObject)
				throws DataParseException {
			return new QuestionModel(jsonObject);
		}
		
	}

}
