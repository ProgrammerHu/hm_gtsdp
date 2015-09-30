package com.hemaapp.hm_gtsdp.nettask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import xtom.frame.exception.DataParseException;

import com.hemaapp.hm_FrameWork.result.HemaArrayResult;
import com.hemaapp.hm_gtsdp.GtsdpArrayResult;
import com.hemaapp.hm_gtsdp.GtsdpHttpInformation;
import com.hemaapp.hm_gtsdp.GtsdpNetTask;
import com.hemaapp.hm_gtsdp.model.NoticeModel;

public class NoticeListTask extends GtsdpNetTask {
 
	public NoticeListTask(GtsdpHttpInformation information,
			HashMap<String, String> params) {
		super(information, params);
	}

	@Override
	public Object parse(JSONObject jsonObject) throws DataParseException {
		return new Result(jsonObject);
	}
	
	private class Result extends GtsdpArrayResult<NoticeModel> {

		public Result(JSONObject jsonObject) throws DataParseException {
			super(jsonObject);
		}  

		@Override
		public NoticeModel parse(JSONObject jsonObject) throws DataParseException {
			return new NoticeModel(jsonObject);
		}
	}
	

}
