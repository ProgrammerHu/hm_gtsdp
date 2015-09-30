package com.hemaapp.hm_gtsdp;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import xtom.frame.exception.DataParseException;

import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
/**
 * �õ�ʱ��һ��Ҫǰ��һ��
 * @author Wen
 * @author HuFanglin
 *
 * @param <T>
 */
public abstract class GtsdpArrayResult<T> extends HemaBaseResult {
	private ArrayList<T> objects = new ArrayList<T>();
	private int totalCount;

	public GtsdpArrayResult(JSONObject jsonObject) throws DataParseException {
		super(jsonObject);
		if (jsonObject != null) {
			try {
				if (!jsonObject.isNull("infor")
						&& !isNull(jsonObject.getString("infor"))) {
					JSONObject object = jsonObject.getJSONObject("infor");
					if (!object.isNull("totalCount")) {
						totalCount = object.getInt("totalCount");
					}
					if (!object.isNull("listItems")
							&& !isNull(object.getString("listItems"))) {
						JSONArray jsonList = object.getJSONArray("listItems");
						int size = jsonList.length();
						for (int i = 0; i < size; i++) {
							objects.add(parse(jsonList.getJSONObject(i)));
						}
					}
				}
			} catch (JSONException e) {
				throw new DataParseException(e);
			}
		}
	}

	/**
	 * @return the totalCount ��ʾ���з��ϲ�ѯ�������ܼ�¼�ĸ�����totalCount=0 ��ʾ�������ݣ�
	 */
	public int getTotalCount() {
		return totalCount;
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
