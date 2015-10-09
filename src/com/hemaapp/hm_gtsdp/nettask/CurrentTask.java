package com.hemaapp.hm_gtsdp.nettask;

import java.util.HashMap;

import org.json.JSONObject;

import xtom.frame.exception.DataParseException;

import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpHttpInformation;
import com.hemaapp.hm_gtsdp.GtsdpNetTask;
/**
 * 通用请求线程（不需要返回结果的，只需要获取是否成功）
 * @author Wen
 * @author HuFanglin
 *
 */
public class CurrentTask extends GtsdpNetTask{

	public CurrentTask(GtsdpHttpInformation information,
			HashMap<String, String> params) {
		super(information, params);
	}

	@Override
	public Object parse(JSONObject jsonObject) throws DataParseException {
		return new HemaBaseResult(jsonObject);
	}

}
