package com.hemaapp.hm_gtsdp.nettask;

import java.util.HashMap;

import org.json.JSONObject;

import xtom.frame.exception.DataParseException;

import com.hemaapp.hm_gtsdp.GtsdpArrayResult;
import com.hemaapp.hm_gtsdp.GtsdpHttpInformation;
import com.hemaapp.hm_gtsdp.GtsdpNetTask;
import com.hemaapp.hm_gtsdp.model.AdvertiseModel;
/**
 * 广告列表
 * @author Wen
 * @author HuFanglin
 *
 */
public class AdListTask extends GtsdpNetTask
{

	public AdListTask(GtsdpHttpInformation information,
			HashMap<String, String> params) {
		super(information, params);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object parse(JSONObject arg0) throws DataParseException {
		return new Result(arg0);
	}
	
	private class Result extends GtsdpArrayResult<AdvertiseModel>
	{

		public Result(JSONObject jsonObject) throws DataParseException {
			super(jsonObject);
		}

		@Override
		public AdvertiseModel parse(JSONObject jsonObject)
				throws DataParseException {
			return new AdvertiseModel(jsonObject);
		}
		
	}

}
