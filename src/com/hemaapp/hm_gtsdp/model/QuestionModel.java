package com.hemaapp.hm_gtsdp.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 问题模型
 * @author Wen
 * @author HuFanglin
 *
 */
public class QuestionModel 
{
	public QuestionModel(JSONObject json)
	{
		try {
			id = json.getString("id");
			name = json.getString("name");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public QuestionModel(String id, String name)
	{
		this.id = id;
		this.name = name;
	}
	public String id;
	public String name;
	@Override
	public String toString() {
		return  name;
	}
	public int getId()
	{
		return Integer.parseInt(id);
	}
}
