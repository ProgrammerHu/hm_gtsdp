package com.hemaapp.hm_gtsdp.result;

import org.json.JSONException;
import org.json.JSONObject;

import xtom.frame.exception.DataParseException;

import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.model.OrderModel;
/**
 * ∑¢ªıœÍ«È
 * @author Wen
 * @author HuFanglin
 *
 */
public class TransDetailResult extends HemaBaseResult {
	private OrderModel info;
	public TransDetailResult(JSONObject jsonObject) throws DataParseException {
		super(jsonObject);
		try 
		{
			if (!jsonObject.isNull("infor")) 
			{
				JSONObject infoJson = jsonObject.getJSONArray("infor").getJSONObject(0);
				info = new OrderModel(infoJson);
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public OrderModel getInfo()
	{
		return info;
	}
}
