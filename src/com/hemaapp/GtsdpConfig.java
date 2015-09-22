/*
 * Copyright (C) 2014 The Android Client Of Demo Project
 * 
 *     The BeiJing PingChuanJiaHeng Technology Co., Ltd.
 * 
 * Author:Yang ZiTian
 * You Can Contact QQ:646172820 Or Email:mail_yzt@163.com
 */
package com.hemaapp;

import android.graphics.Color;

/**
 * 该项目配置信息
 */
public class GtsdpConfig {

	/**
	 * 设备类型
	 *  1:苹果；2:安卓； 
	 *  接口路由使用，必须传此参数  
	 */
	public static final int DEVICETYPE = 2;
	/**
	 * 是否打印信息开关
	 */
	public static final boolean DEBUG = true;
	/**
	 * 是否启用友盟统计
	 */
	public static final boolean UMENG_ENABLE = false;
	/**
	 * 网络请求连接超时时限(单位:毫秒)
	 */
	public static final int TIMEOUT_HTTP = 30000;
	/**
	 * 网络请求尝试次数
	 */
	public static final int TRYTIMES_HTTP = 1;
	/**
	 * 图片压缩的最大宽度
	 */
	public static final int IMAGE_WIDTH = 320;
	/**
	 * 图片压缩的最大高度
	 */
	public static final int IMAGE_HEIGHT = 3000;
	/**
	 * 图片压缩的失真率
	 */
	public static final int IMAGE_QUALITY = 100;
	/**
	 * 银联支付环境--"00"生产环境,"01"测试环境
	 */
	public static final String UNIONPAY_TESTMODE = "00";
	/**
	 * 后台服务接口根路径
	 */
	// public static final String SYS_ROOT =
	// "http://192.168.2.146:8008/group1/hm_5m/";
	// public static final String SYS_ROOT =
	// "http://124.128.23.74:8008/group1/hm_5m/";
	public static final String SYS_ROOT = "http://124.128.23.74:8008/group4/hm_rrg/";
	
	/**
	 * 配送员（捎带者）
	 */
	public static final int USER_IDENTIFY_CURSOR = 0;
	/**
	 * 收货人
	 */
	public static final int USER_IDENTIFY_RECIVIER = 1;
	/**
	 * 网点
	 */
	public static final int USER_IDENTIFY_SITE = 2;
	/**
	 * 网点B
	 */
	public static final int USER_IDENTIFY_SITE_B = 3;
	/**
	 * 发货人
	 */
	public static final int USER_IDENTIFY_SENDER = 4;
	/**
	 * 主色调
	 */
	public static final int Main_Blue = Color.rgb(0, 161, 216);
}
