package com.hemaapp.hm_gtsdp.db;

import com.hemaapp.hm_gtsdp.model.User;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


/**
 * �û���Ϣ���ݿ������
 */
public class UserDBHelper extends GtsdpDBHelper {
	String tableName = USER;

	String columns = "token,id,username,email,nickname,worker,password,charindex,sex,mobile,avatar,"
			+ "avatarbig,backimg,district_name,onlineflag,validflag,devicetype,deviceid,"
			+ "lastlogintime,lastloginversion,regdate,team_id,team_name,workscore,hardscore,"
			+ "cardavatar,cardname,cardbusiness,cardbusinessid,cardworker,cardcompany,cardmobile,"
			+ "cardemail,cardweburl";

	String updateColumns = "token=?,id=?,username=?,email=?,nickname=?,worker=?,password=?,charindex=?,sex=?,mobile=?,avatar=?,"
			+ "avatarbig=?,backimg=?,district_name=?,onlineflag=?,validflag=?,devicetype=?,deviceid=?,"
			+ "lastlogintime=?,lastloginversion=?,regdate=?,team_id=?,team_name=?,workscore=?,hardscore=?,"
			+ "cardavatar=?,cardname=?,cardbusiness=?,cardbusinessid=?,cardworker=?,cardcompany=?,cardmobile=?,"
			+ "cardemail=?,cardweburl=?";

	/**
	 * ʵ����ϵͳ��ʼ����Ϣ���ݿ������
	 * 
	 * @param context
	 */
	public UserDBHelper(Context context) {
		super(context);
	}

	public boolean insertOrUpdate(User user) {
		if (isExist(user)) {
			return update(user);
		} else {
			return insert(user);
		}
	}

	/**
	 * ����һ����¼
	 * 
	 * @return �Ƿ�ɹ�
	 */
	public boolean insert(User user) {
		String sql = "insert into "
				+ tableName
				+ " ("
				+ columns
				+ ") values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		Object[] bindArgs = new Object[] { user.getToken(), user.getId(),
				user.getUsername(), user.getEmail(), user.getNickname(),
				user.getWorker(), user.getPassword(), user.getCharindex(),
				user.getSex(), user.getMobile(), user.getAvatar(),
				user.getAvatarbig(), user.getBackimg(),
				user.getDistrict_name(), user.getOnlineflag(),
				user.getValidflag(), user.getDevicetype(), user.getDeviceid(),
				user.getLastlogintime(), user.getLastloginversion(),
				user.getRegdate(), user.getTeam_id(), user.getTeam_name(),
				user.getWorkscore(), user.getHardscore(), user.getCardavatar(),
				user.getCardname(), user.getCardbusiness(),
				user.getCardbusinessid(), user.getCardworker(),
				user.getCardcompany(), user.getCardmobile(),
				user.getCardemail(), user.getCardweburl() };

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
	public boolean update(User user) {
		String conditions = " where id=" + user.getId();
		String sql = "update " + tableName + " set " + updateColumns
				+ conditions;
		Object[] bindArgs = new Object[] { user.getToken(), user.getId(),
				user.getUsername(), user.getEmail(), user.getNickname(),
				user.getWorker(), user.getPassword(), user.getCharindex(),
				user.getSex(), user.getMobile(), user.getAvatar(),
				user.getAvatarbig(), user.getBackimg(),
				user.getDistrict_name(), user.getOnlineflag(),
				user.getValidflag(), user.getDevicetype(), user.getDeviceid(),
				user.getLastlogintime(), user.getLastloginversion(),
				user.getRegdate(), user.getTeam_id(), user.getTeam_name(),
				user.getWorkscore(), user.getHardscore(), user.getCardavatar(),
				user.getCardname(), user.getCardbusiness(),
				user.getCardbusinessid(), user.getCardworker(),
				user.getCardcompany(), user.getCardmobile(),
				user.getCardemail(), user.getCardweburl() };

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

	public boolean isExist(User user) {
		String id = user.getId();
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
	 * @return �û���Ϣ
	 */
	public User select(String username) {
		String conditions = " where username='" + username + "'";
		String sql = "select " + columns + " from " + tableName + conditions;

		SQLiteDatabase db = getWritableDatabase();
		User user = null;
		Cursor cursor = db.rawQuery(sql, null);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			user = new User(cursor.getString(0), cursor.getString(1),
					cursor.getString(2), cursor.getString(3),
					cursor.getString(4), cursor.getString(5),
					cursor.getString(6), cursor.getString(7),
					cursor.getString(8), cursor.getString(9),
					cursor.getString(10), cursor.getString(11),
					cursor.getString(12), cursor.getString(13),
					cursor.getString(14), cursor.getString(15),
					cursor.getString(16), cursor.getString(17),
					cursor.getString(18), cursor.getString(19),
					cursor.getString(20), cursor.getString(21),
					cursor.getString(22), cursor.getString(23),
					cursor.getString(24), cursor.getString(25),
					cursor.getString(26), cursor.getString(27),
					cursor.getString(28), cursor.getString(29),
					cursor.getString(30), cursor.getString(31),
					cursor.getString(32), cursor.getString(33));
		}
		cursor.close();
		db.close();
		return user;
	}
}
