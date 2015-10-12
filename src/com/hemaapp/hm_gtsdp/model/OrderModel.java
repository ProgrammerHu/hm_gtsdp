package com.hemaapp.hm_gtsdp.model;

import org.json.JSONException;
import org.json.JSONObject;

import xtom.frame.XtomObject;

/**
 * 订单列表模型
 * @author Wen
 * @author HuFanglin
 *
 */
public class OrderModel extends XtomObject {
	private String id;//捎带id
	private String client_id;//发货人id
	private String to_client_id;//收货人的id
	private int total_fee;//支付金额
	private String tradetype;/*交易类型0:未审核；1起始网点已填价格；2:已支付；3:已发货；4:已收货*/
	private String receiver_name;//收件人的姓名
	private String receiver_address;//收件人的地址
	private String receiver_telphone;//收件人的电话
	private String sender_name;//发件人姓名
	private String sender_address;//发件人地址
	private String sender_telphone;//发件人电话
	private String code;//二维码信息
	private String regdate;//提交时间
	private String trade_no;//订单号
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
	 * 交易类型
	 * @return 交易类型0:未审核
	1起始网点已填价格
	2:已支付
	3:已发货
	4:已收货
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
