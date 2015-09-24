package com.hemaapp.hm_gtsdp.nettask;

import java.util.HashMap;

import org.json.JSONObject;

import xtom.frame.exception.DataParseException;

import com.hemaapp.hm_FrameWork.result.HemaArrayResult;
import com.hemaapp.hm_gtsdp.GtsdpHttpInformation;
import com.hemaapp.hm_gtsdp.GtsdpNetTask;
import com.hemaapp.hm_gtsdp.model.UnionTrade;

/**
 * 获取银联交易签名串
 */
public class UnionTradeTask extends GtsdpNetTask {

	public UnionTradeTask(GtsdpHttpInformation information,
			HashMap<String, String> params) {
		super(information, params);
	}

	public UnionTradeTask(GtsdpHttpInformation information,
			HashMap<String, String> params, HashMap<String, String> files) {
		super(information, params, files);
	}

	@Override
	public Object parse(JSONObject jsonObject) throws DataParseException {
		return new Result(jsonObject);
	}

	private class Result extends HemaArrayResult<UnionTrade> {

		public Result(JSONObject jsonObject) throws DataParseException {
			super(jsonObject);
		}

		@Override
		public UnionTrade parse(JSONObject jsonObject)
				throws DataParseException {
			return new UnionTrade(jsonObject);
		}

	}
}
