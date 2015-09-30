/*
 * Copyright (C) 2014 The Android Client Of Demo Project
 * 
 *     The BeiJing PingChuanJiaHeng Technology Co., Ltd.
 * 
 * Author:Yang ZiTian
 * You Can Contact QQ:646172820 Or Email:mail_yzt@163.com
 */
package com.hemaapp.hm_gtsdp.nettask;

import java.util.HashMap;

import org.json.JSONObject;

import com.hemaapp.hm_FrameWork.result.HemaArrayResult;
import com.hemaapp.hm_gtsdp.GtsdpHttpInformation;
import com.hemaapp.hm_gtsdp.GtsdpNetTask;
import com.hemaapp.hm_gtsdp.model.User;

import xtom.frame.exception.DataParseException;

/**
 * ��¼
 */
public class ClientLoginTask extends GtsdpNetTask {

	public ClientLoginTask(GtsdpHttpInformation information,
			HashMap<String, String> params) {
		super(information, params);
	}

	public ClientLoginTask(GtsdpHttpInformation information,
			HashMap<String, String> params, HashMap<String, String> files) {
		super(information, params, files);
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
