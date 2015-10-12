package com.hemaapp.hm_gtsdp.nettask;

import java.util.HashMap;

import org.json.JSONObject;

import xtom.frame.exception.DataParseException;

import com.hemaapp.hm_gtsdp.GtsdpArrayResult;
import com.hemaapp.hm_gtsdp.GtsdpHttpInformation;
import com.hemaapp.hm_gtsdp.GtsdpNetTask;
import com.hemaapp.hm_gtsdp.model.OrderModel;
/**
 * 获取捎带（订单）列表
 * @author Wen
 * @author HuFanglin
 *
 */
public class TransListTask extends GtsdpNetTask {

	public TransListTask(GtsdpHttpInformation information,
			HashMap<String, String> params) {
		super(information, params);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object parse(JSONObject jsonObject) throws DataParseException {
		return new Result(jsonObject);
	}

	private class Result extends GtsdpArrayResult<OrderModel>
	{
		public Result(JSONObject jsonObject) throws DataParseException {
			super(jsonObject);
		}

		@Override
		public OrderModel parse(JSONObject jsonObject)
				throws DataParseException {
			return new OrderModel(jsonObject);
		}
		
	}
}
