package com.hemaapp.hm_gtsdp.model;

import org.json.JSONException;
import org.json.JSONObject;

import xtom.frame.XtomObject;

/**
 * �����б�ģ��
 * @author Wen
 * @author HuFanglin
 *
 */
public class OrderModel extends XtomObject {
	private String id;//�Ӵ�id
	private String client_id;//������id
	private String to_client_id;//�ջ��˵�id
	private int total_fee;//֧�����
	private String tradetype;/*��������0:δ��ˣ�1��ʼ��������۸�2:��֧����3:�ѷ�����4:���ջ�*/
	private String receiver_name;//�ռ��˵�����
	private String receiver_address;//�ռ��˵ĵ�ַ
	private String receiver_telphone;//�ռ��˵ĵ绰
	private String sender_name;//����������
	private String sender_address;//�����˵�ַ
	private String sender_telphone;//�����˵绰
	private String code;//��ά����Ϣ
	private String regdate;//�ύʱ��
	private String trade_no;//������
	public OrderModel(String id, String client_id, String to_client_id,
			int total_fee, String tradetype, String receiver_name,
			String receiver_address, String receiver_telphone,
			String sender_name, String sender_address, String sender_telphone,
			String code, String regdate, String trade_no) {
		super();
		this.id = id;
		this.client_id = client_id;
		this.to_client_id = to_client_id;
		this.total_fee = total_fee;
		this.tradetype = tradetype;
		this.receiver_name = receiver_name;
		this.receiver_address = receiver_address;
		this.receiver_telphone = receiver_telphone;
		this.sender_name = sender_name;
		this.sender_address = sender_address;
		this.sender_telphone = sender_telphone;
		this.code = code;
		this.regdate = regdate;
		this.trade_no = trade_no;
	}
	public OrderModel(JSONObject json)
	{
		try {
			this.id = json.getString("id");
			this.client_id = json.getString("client_id");
			this.to_client_id = json.getString("to_client_id");
			this.total_fee = json.getInt("total_fee");
			this.tradetype = json.getString("tradetype");
			this.receiver_name = json.getString("receiver_name");
			this.receiver_address = json.getString("receiver_address");
			this.receiver_telphone = json.getString("receiver_telphone");
			this.sender_name = json.getString("sender_name");
			this.sender_address = json.getString("sender_address");
			this.sender_telphone = json.getString("sender_telphone");
			this.code = json.getString("code");
			this.regdate = json.getString("regdate");
			this.trade_no = json.getString("trade_no");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	public String getId() {
		return id;
	}
	public String getClient_id() {
		return client_id;
	}
	public String getTo_client_id() {
		return to_client_id;
	}
	public int getTotal_fee() {
		return total_fee;
	}
	/**
	 * ��������
	 * @return ��������0:δ���
	1��ʼ��������۸�
	2:��֧��
	3:�ѷ���
	4:���ջ�
	 */
	public String getTradetype() {
		return tradetype;
	}
	public String getReceiver_name() {
		return receiver_name;
	}
	public String getReceiver_address() {
		return receiver_address;
	}
	public String getReceiver_telphone() {
		return receiver_telphone;
	}
	public String getSender_name() {
		return sender_name;
	}
	public String getSender_address() {
		return sender_address;
	}
	public String getSender_telphone() {
		return sender_telphone;
	}
	public String getCode() {
		return code;
	}
	public String getRegdate() {
		return regdate;
	}
	public String getTrade_no() {
		return trade_no;
	}

	
}
