/*
 * Copyright (C) 2014 The Android Client Of Demo Project
 * 
 *     The BeiJing PingChuanJiaHeng Technology Co., Ltd.
 * 
 * Author:Yang ZiTian
 * You Can Contact QQ:646172820 Or Email:mail_yzt@163.com
 */
package com.hemaapp.hm_gtsdp;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hemaapp.hm_FrameWork.result.HemaArrayResult;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;

import xtom.frame.exception.DataParseException;

/**
 * ��BaseResult����չ�����÷�������������������
 */
public abstract class GtsdpArrayResult<T> extends HemaArrayResult {
	private ArrayList<T> objects = new ArrayList<T>();

	public GtsdpArrayResult(JSONObject jsonObject) throws DataParseException {
		super(jsonObject);
		if (jsonObject != null) {
			try {
				if (!jsonObject.isNull("infor")
						&& !isNull(jsonObject.getString("infor"))) {
					JSONArray jsonList = jsonObject.getJSONArray("infor");
					int size = jsonList.length();
					for (int i = 0; i < size; i++) {
						objects.add(parse(jsonList.getJSONObject(i)));
					}
				}
			} catch (JSONException e) {
				throw new DataParseException(e);
			}
		}
	}

	/**
	 * ��ȡ���������ص�ʵ������
	 * 
	 * @return ���������ص�ʵ������
	 */
	public ArrayList<T> getObjects() {
		return objects;
	}

	/**
	 * �÷�����JSONObject����Ϊ���������ʵ��
	 */
	public abstract T parse(JSONObject jsonObject) throws DataParseException;
}
