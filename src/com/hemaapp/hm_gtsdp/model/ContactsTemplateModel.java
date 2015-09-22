package com.hemaapp.hm_gtsdp.model;

import xtom.frame.XtomObject;

/**
 * 联系人模板模型
 * @author Wen
 * @author HuFanglin
 *
 */
public class ContactsTemplateModel extends XtomObject {
	
	private String Name;
	private String Address;
	private String PhoneNumber;
	public boolean isCheck;
	
	public ContactsTemplateModel(String Name, String Address, String PhoneNumber)
	{
		this.Name = Name;
		this.Address = Address;
		this.PhoneNumber = PhoneNumber;
		isCheck = false;
	}
	
	public String getName()
	{
		return Name;
	}
	public String getAddress()
	{
		return Address;
	}
	public String getPhoneNumber()
	{
		return PhoneNumber;
	}

}
