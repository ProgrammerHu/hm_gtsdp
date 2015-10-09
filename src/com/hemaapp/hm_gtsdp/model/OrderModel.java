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
	
	private String id;//捎带id
	private String client_id;//发货人id
	private String to_client_id;//收货人的id
	private int total_fee;//支付金额
	private String tradetype;//交易类型
	private String receiver_name;//收件人的姓名
	private String receiver_address;//收件人的地址
	private String receiver_telphone;//收件人的电话
	private String sender_name;//发件人姓名
	private String sender_address;//发件人地址
	private String sender_telphone;//发件人电话
	private String code;//二维码信息
	private String regdate;//提交时间
	private String trade_no;//订单号

	
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
