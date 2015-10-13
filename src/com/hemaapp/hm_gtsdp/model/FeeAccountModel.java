package com.hemaapp.hm_gtsdp.model;

import org.json.JSONException;
import org.json.JSONObject;

import xtom.frame.XtomObject;
/**
 * 账户明细
 * @author Wen
 * @author HuFanglin
 *
 */
public class FeeAccountModel extends XtomObject {
	private String id;
	private String keytype;//	1:充值交易 2:支付订单交易【需跳转到订单详情】 
	private String keyid;//当keytype=1时，keyid=0; 当keytype=2时，keyid=订单id
	private String name;
	private String total_fee;
	private String trade_no;
	private String paytype;
	private String client_id;
	private String regdate;

	public FeeAccountModel(String id, String keytype, String keyid,
			String name, String total_fee, String trade_no, String paytype,
			String client_id, String regdate) {
		super();
		this.id = id;
		this.keytype = keytype;
		this.keyid = keyid;
		this.name = name;
		this.total_fee = total_fee;
		this.trade_no = trade_no;
		this.paytype = paytype;
		this.client_id = client_id;
		this.regdate = regdate;
	}

	public FeeAccountModel(JSONObject json)
	{
		try {
			this.id=json.getString("id");
			this.keytype=json.getString("keytype");
			this.keyid=json.getString("keyid");
			this.name=json.getString("name");
			this.total_fee=json.getString("total_fee");
			this.trade_no=json.getString("trade_no");
			this.paytype=json.getString("paytype");
			this.client_id=json.getString("client_id");
			this.regdate=json.getString("regdate");
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}
	public String getId() {
		return id;
	}

	public String getKeytype() {
		return keytype;
	}

	public String getKeyid() {
		return keyid;
	}

	public String getName() {
		return name;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public String getTrade_no() {
		return trade_no;
	}

	public String getPaytype() {
		return paytype;
	}

	public String getClient_id() {
		return client_id;
	}

	public String getRegdate() {
		return regdate;
	}
	
}
