package com.hemaapp.hm_gtsdp.nettask;

import java.util.HashMap;

import org.json.JSONObject;

import xtom.frame.exception.DataParseException;

import com.hemaapp.hm_FrameWork.result.HemaArrayResult;
import com.hemaapp.hm_gtsdp.GtsdpHttpInformation;
import com.hemaapp.hm_gtsdp.GtsdpNetTask;
import com.hemaapp.hm_gtsdp.model.AlipayTrade;

/**
 * 获取支付宝交易签名
 */
public class AlipayTradeTask extends GtsdpNetTask {

	public AlipayTradeTask(GtsdpHttpInformation information,
			HashMap<String, String> params) {
		super(information, params);
	}

	public AlipayTradeTask(GtsdpHttpInformation information,
			HashMap<String, String> params, HashMap<String, String> files) {
		super(information, params, files);
	}

	@Override
	public Object parse(JSONObject jsonObject) throws DataParseException {
		return new Result(jsonObject);
	}

	private class Result extends HemaArrayResult<AlipayTrade> {

		public Result(JSONObject jsonObject) throws DataParseException {
			super(jsonObject);
		}

		@Override
		public AlipayTrade parse(JSONObject jsonObject)
				throws DataParseException {
			return new AlipayTrade(jsonObject);
		}

	}
}
