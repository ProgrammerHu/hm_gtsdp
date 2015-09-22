package com.hemaapp.hm_gtsdp.model;

import com.hemaapp.hm_gtsdp.view.SlideView;

import xtom.frame.XtomObject;

public class MessageItem extends XtomObject {
    public SlideView slideView;
	public String MsgTitle;
	public String MsgDetail;
	public String TimeDiff;
	public boolean IsNew;
	
	/**
	 * 
	 * @param MsgTitle 标题
	 * @param MsgDetail 明细
	 * @param TimeDiff 时间差
	 * @param IsNew 是否未读
	 */
	public MessageItem(String MsgTitle, String MsgDetail, String TimeDiff, boolean IsNew)
	{
		this.MsgTitle = MsgTitle;
		this.MsgDetail = MsgDetail;
		this.TimeDiff = TimeDiff;
		this.IsNew = IsNew;
	}
	
}
