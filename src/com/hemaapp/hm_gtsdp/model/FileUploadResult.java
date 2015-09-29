package com.hemaapp.hm_gtsdp.model;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import xtom.frame.XtomObject;
import xtom.frame.exception.DataParseException;

/**
 * �ļ��ϴ����
 */
public class FileUploadResult extends XtomObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String item1;// ��鿴��׼��api�ĵ�
	private String item2;// ��鿴��׼��api�ĵ�

	public FileUploadResult(JSONObject jsonObject) throws DataParseException {
		if (jsonObject != null) {
			try {
				item1 = get(jsonObject, "item1");
				item2 = get(jsonObject, "item2");

				log_i(toString());
			} catch (JSONException e) {
				throw new DataParseException(e);
			}
		}
	}

	@Override
	public String toString() {
		return "FileUploadResult [item1=" + item1 + ", item2=" + item2 + "]";
	}

	/**
	 * @return the item1
	 */
	public String getItem1() {
		return item1;
	}

	/**
	 * @return the item2
	 */
	public String getItem2() {
		return item2;
	}

}
