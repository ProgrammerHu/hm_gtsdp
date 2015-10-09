package com.hemaapp.hm_gtsdp.model;

import xtom.frame.XtomObject;

/**
 * �����б�ģ��
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
	
	private String id;//�Ӵ�id
	private String client_id;//������id
	private String to_client_id;//�ջ��˵�id
	private int total_fee;//֧�����
	private String tradetype;//��������
	private String receiver_name;//�ռ��˵�����
	private String receiver_address;//�ռ��˵ĵ�ַ
	private String receiver_telphone;//�ռ��˵ĵ绰
	private String sender_name;//����������
	private String sender_address;//�����˵�ַ
	private String sender_telphone;//�����˵绰
	private String code;//��ά����Ϣ
	private String regdate;//�ύʱ��
	private String trade_no;//������

	
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
