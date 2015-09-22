package com.hemaapp.hm_gtsdp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 
 */
public class GtsdpDBHelper extends SQLiteOpenHelper {
	private static final String DBNAME = "demo.db";
	/**
	 * 系统初始化信息
	 */
	protected static final String SYSINITINFO = "sysinfo";
	/**
	 * 当前登录用户信息
	 */
	protected static final String USER = "user";

	public GtsdpDBHelper(Context context) {
		super(context, DBNAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sys = "sys_web_service text,sys_plugins text,android_must_update text,android_last_version text,iphone_must_update text,"
				+ "iphone_last_version text,sys_chat_ip text,sys_chat_port text,sys_pagesize text,sys_service_phone text,android_update_url text,"
				+ "iphone_update_url text,apad_update_url text,ipad_update_url text,iphone_comment_url text,msg_invite text";
		String sysSQL = "create table " + SYSINITINFO
				+ " (id integer primary key," + sys + ")";
		// 创建系统初始化信息缓存表
		db.execSQL(sysSQL);
		// 创建当前登录用户信息缓存表
		String user = "token text,id text,username text,email text,nickname text,worker text,password text,charindex text,sex text,mobile text,avatar text,"
				+ "avatarbig text,backimg text,district_name text,onlineflag text,validflag text,devicetype text,deviceid text,"
				+ "lastlogintime text,lastloginversion text,regdate text,team_id text,team_name text,workscore text,hardscore text,"
				+ "cardavatar text,cardname text,cardbusiness text,cardbusinessid text,cardworker text,cardcompany text,cardmobile text,"
				+ "cardemail text,cardweburl text";
		String userSQL = "create table " + USER + " (" + user + ")";
		db.execSQL(userSQL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j) {

	}

}
