/*
 * Copyright (C) 2014 The Android Client Of Demo Project
 * 
 *     The BeiJing PingChuanJiaHeng Technology Co., Ltd.
 * 
 * Author:Yang ZiTian
 * You Can Contact QQ:646172820 Or Email:mail_yzt@163.com
 */
package com.hemaapp.hm_gtsdp;

import org.json.JSONException;
import org.json.JSONObject;

import com.hemaapp.hm_FrameWork.result.HemaBaseResult;

import xtom.frame.XtomObject;
import xtom.frame.exception.DataParseException;

/**
 * ������ķ��������ؽ��
 */
public class GtsdpBaseResult extends HemaBaseResult {
	private boolean success;// ����������״̬
	private String msg;// ���������ص�������Ϣ
	private int error_code;// ��status==0ʱ������һ����Ӧ��error_code�������������

	/**
	 * ʵ����һ��������ķ��������ؽ��
	 * 
	 * @param jsonObject
	 *            һ��JSONObjectʵ��
	 * @throws DataParseException
	 *             ���ݽ����쳣
	 */
	public GtsdpBaseResult(JSONObject jsonObject) throws DataParseException {
		super(jsonObject);
		if (jsonObject != null) {
			try {
				success = jsonObject.getBoolean("success");
				msg = get(jsonObject, "msg");
				if (!jsonObject.isNull("error_code")) {
					error_code = jsonObject.getInt("error_code");
				}
			} catch (JSONException e) {
				throw new DataParseException(e);
			}
		}
	}

	/**
	 * @return ������ִ��״̬
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @return ���������ص�������Ϣ
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * ��ȡerror_codeֵ
	 * 
	 * @return һ������(��status==0ʱ������һ����Ӧ��error_code�������������)
	 */
	public int getError_code() {
		return error_code;
	}

}