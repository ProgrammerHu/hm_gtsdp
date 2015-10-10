package com.hemaapp.hm_gtsdp.nettask;

import java.util.HashMap;

import org.json.JSONObject;

import xtom.frame.exception.DataParseException;

import com.hemaapp.hm_FrameWork.result.HemaArrayResult;
import com.hemaapp.hm_gtsdp.GtsdpHttpInformation;
import com.hemaapp.hm_gtsdp.GtsdpNetTask;
import com.hemaapp.hm_gtsdp.model.User;

public class ClientGetTask extends GtsdpNetTask {

	public ClientGetTask(GtsdpHttpInformation information,
			HashMap<String, String> params) {
		super(information, params);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object parse(JSONObject jsonObject) throws DataParseException {
		return new Result(jsonObject);
	}
	
	private class Result extends HemaArrayResult<User> {

		public Result(JSONObject jsonObject) throws DataParseException {
			super(jsonObject);
		} 

		@Override
		public User parse(JSONObject jsonObject) throws DataParseException {
			return new User(jsonObject);
		}
	}
}
