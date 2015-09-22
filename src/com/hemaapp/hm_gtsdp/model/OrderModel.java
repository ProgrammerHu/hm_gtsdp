package com.hemaapp.hm_gtsdp.model;

import xtom.frame.XtomObject;

/**
 * 订单列表模型
 * @author Wen
 * @author HuFanglin
 *
 */
public class OrderModel extends XtomObject {
	private String OrderId;
	private String OrderPosition;
	private String OrderDatetime;
	
	public OrderModel(String OrderId, String OrderPosition, String OrderDatetime)
	{
		this.OrderId = OrderId;
		this.OrderDatetime = OrderDatetime;
		this.OrderPosition = OrderPosition;
	}
	
	public String getOrderId()
	{
		return OrderId;
	}
	public String getOrderDatetime()
	{
		return OrderDatetime;
	}
	public String getOrderPosition()
	{
		return OrderPosition;
	}
}
