package com.hemaapp.hm_gtsdp.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import xtom.frame.XtomObject;

/**
 * 订单列表模型
 * 
 * @author Wen
 * @author HuFanglin
 * 
 */
public class OrderModel extends XtomObject {
	private String id;
	private String client_id;
	private String to_client_id;
	private String receiver_name;
	private String receiver_address;
	private String receiver_telphone;
	private String sender_name;
	private String sender_address;
	private String sender_telphone;
	private String current_address;
	private String code;
	private double total_fee;
	private String tradetype;
	private String delflag;
	private String regdate;
	private String admindate;
	private String paydate;
	private String paytype;
	private String trade_no;
	private String out_trade_no;
	private String senddate;
	private String receivedate;
	private List<ImageItem> imageItems;
	public OrderModel(JSONObject json) {
		try {
			this.id = json.getString("id");
			this.client_id = json.getString("client_id");
			this.to_client_id = json.getString("to_client_id");
			this.receiver_name = json.getString("receiver_name");
			this.receiver_address = json.getString("receiver_address");
			this.receiver_telphone = json.getString("receiver_telphone");
			this.sender_name = json.getString("sender_name");
			this.sender_address = json.getString("sender_address");
			this.sender_telphone = json.getString("sender_telphone");
			this.current_address = json.getString("current_address");
			this.code = json.getString("code");
			this.total_fee = json.getDouble("total_fee");
			this.tradetype = json.getString("tradetype");
			this.delflag = json.getString("delflag");
			this.regdate = json.getString("regdate");
			this.admindate = json.getString("admindate");
			this.paydate = json.getString("paydate");
			this.paytype = json.getString("paytype");
			this.trade_no = json.getString("trade_no");
			this.out_trade_no = json.getString("out_trade_no");
			this.senddate = json.getString("senddate");
			this.receivedate = json.getString("receivedate");
			if (!json.isNull("imageItems") && !"".equals(json.getString("imageItems"))) {
				imageItems = new ArrayList<ImageItem>();
				JSONArray imageJsonArray = json.getJSONArray("imageItems");
				if(imageJsonArray != null && imageJsonArray.length() > 0)
				{
					for(int i = 0; i < imageJsonArray.length(); i++)
					{
						imageItems.add(new ImageItem(imageJsonArray.getJSONObject(i)));
					}
				}
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public OrderModel(String id, String client_id, String to_client_id,
			String receiver_name, String receiver_address,
			String receiver_telphone, String sender_name,
			String sender_address, String sender_telphone,
			String current_address, String code, int total_fee,
			String tradetype, String delflag, String regdate, String admindate,
			String paydate, String paytype, String trade_no,
			String out_trade_no, String senddate, String receivedate) {
		super();
		this.id = id;
		this.client_id = client_id;
		this.to_client_id = to_client_id;
		this.receiver_name = receiver_name;
		this.receiver_address = receiver_address;
		this.receiver_telphone = receiver_telphone;
		this.sender_name = sender_name;
		this.sender_address = sender_address;
		this.sender_telphone = sender_telphone;
		this.current_address = current_address;
		this.code = code;
		this.total_fee = total_fee;
		this.tradetype = tradetype;
		this.delflag = delflag;
		this.regdate = regdate;
		this.admindate = admindate;
		this.paydate = paydate;
		this.paytype = paytype;
		this.trade_no = trade_no;
		this.out_trade_no = out_trade_no;
		this.senddate = senddate;
		this.receivedate = receivedate;
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

	public String getCurrent_address() {
		int Type = Integer.parseInt(tradetype);
		switch (Type) {
		case 0:
			return "未审核";
		case 1:
			return "未支付";
		case 2:
			return "已支付";
		case 3:
			return current_address;
		case 4:
			return "已收货";
		default:
			break;
		}
//		if(tradetype.equals("4"))
//			return "已收货";
//		else if(tradetype.equals("0"))
//			return "未审核";
//		if(current_address == null || "".equals(current_address))
//			return "配送中";
		
		return current_address;
	}

	public String getCode() {
		return code;
	}

	public double getTotal_fee() {
		return total_fee;
	}

	public String getTradetype() {
		return tradetype;
	}

	public String getDelflag() {
		return delflag;
	}

	public String getRegdate() {
		return regdate;
	}

	public String getAdmindate() {
		return admindate;
	}

	public String getPaydate() {
		return paydate;
	}

	public String getPaytype() {
		return paytype;
	}

	public String getTrade_no() {
		return trade_no;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public String getSenddate() {
		return senddate;
	}

	public String getReceivedate() {
		return receivedate;
	}
	
	public List<ImageItem> getImageItems()
	{
		return imageItems;
	}

}
