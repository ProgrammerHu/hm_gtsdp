package com.hemaapp.hm_gtsdp.model;
/**
 * 问题模型
 * @author Wen
 * @author HuFanglin
 *
 */
public class QuestionModel 
{
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
