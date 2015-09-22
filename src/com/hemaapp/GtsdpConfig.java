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
 * ����Ŀ������Ϣ
 */
public class GtsdpConfig {

	/**
	 * �豸����
	 *  1:ƻ����2:��׿�� 
	 *  �ӿ�·��ʹ�ã����봫�˲���  
	 */
	public static final int DEVICETYPE = 2;
	/**
	 * �Ƿ��ӡ��Ϣ����
	 */
	public static final boolean DEBUG = true;
	/**
	 * �Ƿ���������ͳ��
	 */
	public static final boolean UMENG_ENABLE = false;
	/**
	 * �����������ӳ�ʱʱ��(��λ:����)
	 */
	public static final int TIMEOUT_HTTP = 30000;
	/**
	 * ���������Դ���
	 */
	public static final int TRYTIMES_HTTP = 1;
	/**
	 * ͼƬѹ���������
	 */
	public static final int IMAGE_WIDTH = 320;
	/**
	 * ͼƬѹ�������߶�
	 */
	public static final int IMAGE_HEIGHT = 3000;
	/**
	 * ͼƬѹ����ʧ����
	 */
	public static final int IMAGE_QUALITY = 100;
	/**
	 * ����֧������--"00"��������,"01"���Ի���
	 */
	public static final String UNIONPAY_TESTMODE = "00";
	/**
	 * ��̨����ӿڸ�·��
	 */
	// public static final String SYS_ROOT =
	// "http://192.168.2.146:8008/group1/hm_5m/";
	// public static final String SYS_ROOT =
	// "http://124.128.23.74:8008/group1/hm_5m/";
	public static final String SYS_ROOT = "http://124.128.23.74:8008/group4/hm_rrg/";
	
	/**
	 * ����Ա���Ӵ��ߣ�
	 */
	public static final int USER_IDENTIFY_CURSOR = 0;
	/**
	 * �ջ���
	 */
	public static final int USER_IDENTIFY_RECIVIER = 1;
	/**
	 * ����
	 */
	public static final int USER_IDENTIFY_SITE = 2;
	/**
	 * ����B
	 */
	public static final int USER_IDENTIFY_SITE_B = 3;
	/**
	 * ������
	 */
	public static final int USER_IDENTIFY_SENDER = 4;
	/**
	 * ��ɫ��
	 */
	public static final int Main_Blue = Color.rgb(0, 161, 216);
}
