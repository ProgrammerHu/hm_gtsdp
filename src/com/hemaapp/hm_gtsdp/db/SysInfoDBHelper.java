package com.hemaapp.hm_gtsdp.db;

import com.hemaapp.hm_gtsdp.model.SysInitInfo;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


/**
 * ϵͳ��ʼ����Ϣ���ݿ������
 */
public class SysInfoDBHelper extends GtsdpDBHelper {
	String tableName = SYSINITINFO;

	String columns = "sys_web_service,sys_plugins,android_must_update,android_last_version,iphone_must_update,"
			+ "iphone_last_version,sys_chat_ip,sys_chat_port,sys_pagesize,sys_service_phone,android_update_url,"
			+ "iphone_update_url,apad_update_url,ipad_update_url,iphone_comment_url,msg_invite";

	String updateColumns = "sys_web_service=?,sys_plugins=?,android_must_update=?,android_last_version=?,iphone_must_update=?,"
			+ "iphone_last_version=?,sys_chat_ip=?,sys_chat_port=?,sys_pagesize=?,sys_service_phone=?,android_update_url=?,"
			+ "iphone_update_url=?,apad_update_url=?,ipad_update_url=?,iphone_comment_url=?,msg_invite=?";

	/**
	 * ʵ����ϵͳ��ʼ����Ϣ���ݿ������
	 * 
	 * @param context
	 */
	public SysInfoDBHelper(Context context) {
		super(context);
	}

	public boolean insertOrUpdate(SysInitInfo info) {
		if (isExist()) {
			return update(info);
		} else {
			return insert(info);
		}
	}

	/**
	 * ����һ����¼
	 * 
	 * @return �Ƿ�ɹ�
	 */
	public boolean insert(SysInitInfo info) {
		String sql = "insert into " + tableName + " (" + columns
				+ ") values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		Object[] bindArgs = new Object[] { info.getSys_web_service(),
				info.getSys_plugins(), info.getAndroid_must_update(),
				info.getAndroid_last_version(), info.getIphone_must_update(),
				info.getIphone_last_version(), info.getSys_chat_ip(),
				info.getSys_chat_port(), info.getSys_pagesize(),
				info.getSys_service_phone(), info.getAndroid_update_url(),
				info.getIphone_update_url(), info.getApad_update_url(),
				info.getIpad_update_url(), info.getIphone_comment_url(),
				info.getMsg_invite() };
		SQLiteDatabase db = getWritableDatabase();
		boolean success = true;
		try {
			db.execSQL(sql, bindArgs);
		} catch (SQLException e) {
			success = false;
		}
		db.close();
		return success;
	}

	/**
	 * ����
	 * 
	 * @return �Ƿ�ɹ�
	 */
	public boolean update(SysInitInfo info) {
		int id = 1;
		String conditions = " where id=" + id;
		String sql = "update " + tableName + " set " + updateColumns
				+ conditions;
		Object[] bindArgs = new Object[] { info.getSys_web_service(),
				info.getSys_plugins(), info.getAndroid_must_update(),
				info.getAndroid_last_version(), info.getIphone_must_update(),
				info.getIphone_last_version(), info.getSys_chat_ip(),
				info.getSys_chat_port(), info.getSys_pagesize(),
				info.getSys_service_phone(), info.getAndroid_update_url(),
				info.getIphone_update_url(), info.getApad_update_url(),
				info.getIpad_update_url(), info.getIphone_comment_url(),
				info.getMsg_invite() };
		SQLiteDatabase db = getWritableDatabase();
		boolean success = true;
		try {
			db.execSQL(sql, bindArgs);
		} catch (SQLException e) {
			success = false;
		}
		db.close();
		return success;
	}

	public boolean isExist() {
		int id = 1;
		String sql = ("select * from " + tableName + " where id=" + id);
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.rawQuery(sql, null);
		boolean exist = cursor.getCount() > 0;
		cursor.close();
		db.close();
		return exist;
	}

	/**
	 * ���
	 */
	public void clear() {
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL("delete from " + tableName);
		db.close();
	}

	/**
	 * �жϱ��Ƿ�Ϊ��
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from " + tableName, null);
		boolean empty = 0 == cursor.getCount();
		cursor.close();
		db.close();
		return empty;
	}

	/**
	 * @return ϵͳ��ʼ����Ϣ
	 */
	public SysInitInfo select() {
		int id = 1;
		String conditions = " where id=" + id;
		String sql = "select " + columns + " from " + tableName + conditions;

		SQLiteDatabase db = getWritableDatabase();
		SysInitInfo info = null;
		Cursor cursor = db.rawQuery(sql, null);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			info = new SysInitInfo(cursor.getString(0), cursor.getString(1),
					cursor.getString(2), cursor.getString(3),
					cursor.getString(4), cursor.getString(5),
					cursor.getString(6), cursor.getString(7), cursor.getInt(8),
					cursor.getString(9), cursor.getString(10),
					cursor.getString(11), cursor.getString(12),
					cursor.getString(13), cursor.getString(14),
					cursor.getString(15));
		}
		cursor.close();
		db.close();
		return info;
	}
}
