package com.hemaapp.hm_gtsdp.model;

import xtom.frame.XtomObject;

/**
 * ½»Ò×¼ÇÂ¼
 * @author Wen
 * @author HuFanglin
 *
 */
public class TransactionModel extends XtomObject {
	private String Title;
	private String Datetime;
	private int Count;
	private boolean IsIncome;
	
	public TransactionModel(String Title, String Datetime, int Count, boolean IsIncome)
	{
		this.Title = Title;
		this.Datetime = Datetime;
		this.Count = Count;
		this.IsIncome = IsIncome;
	}
	
	public String getTitle()
	{
		return Title;
	}
	
	public String getDatetime()
	{
		return Datetime;
	}
	
	public int getCount()
	{
		return Count;
	}
	
	public boolean getIsIncome()
	{
		return IsIncome;
	}
}
