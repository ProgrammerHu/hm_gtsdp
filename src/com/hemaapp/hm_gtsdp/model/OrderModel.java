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
	private String OrderStart;
	private String OrderEnd;
	private int Price;
	
	public OrderModel(String OrderId, String OrderPosition, String OrderDatetime)
	{
		this.OrderId = OrderId;
		this.OrderDatetime = OrderDatetime;
		this.OrderPosition = OrderPosition;
	}
	
	public OrderModel(String OrderId, String OrderPosition, String OrderDatetime, String OrderStart, String OrderEnd, int Price)
	{
		this(OrderId, OrderPosition, OrderDatetime);
		this.OrderStart = OrderStart;
		this.OrderEnd = OrderEnd;
		this.Price = Price;
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
	public String getOrderStart()
	{
		return OrderStart;
	}
	public String getOrderEnd()
	{
		return OrderEnd;
	}
	public int getPrice()
	{
		return Price;
	}
}
