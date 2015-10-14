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

	String columns = "id,username,email,password,nickname,charindex,sex,mobile,age,selfsign,avatar,birthday,avatarbig,backimg,address,"
			+ "onlineflag,validflag,vestflag,score,feeaccount,lng,lat,deviceid,devicetype,channelid,lastloginversion,lastlogintime,content,delflag,"
			+ "regdate,ask1_id,answer1,ask2_id,answer2,ask3_id,answer3,aliuser,bankuser,bankname,bankcard,bankaddress,transflag,checkflag,"
			+ "token,role,android_must_update,android_last_version,android_update_url";

	String updateColumns = "id=?,username=?,email=?,password=?,nickname=?,charindex=?,sex=?,mobile=?,age=?,selfsign=?,avatar=?,birthday=?,avatarbig=?,backimg=?,address=?,"
			+ "onlineflag=?,validflag=?,vestflag=?,score=?,feeaccount=?,lng=?,lat=?,deviceid=?,devicetype=?,channelid=?,lastloginversion=?,lastlogintime=?,content=?,delflag=?,"
			+ "regdate=?,ask1_id=?,answer1=?,ask2_id=?,answer2=?,ask3_id=?,answer3=?,aliuser=?,bankuser=?,bankname=?,bankcard=?,bankaddress=?,transflag=?,checkflag=?,"
			+ "token=?,role=?,android_must_update=?,android_last_version=?,android_update_url=?";

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
				+ ") values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";//46

		Object[] bindArgs = new Object[] { user.getId(), user.getUsername(),
				user.getEmail(), user.getPassword(), user.getNickname(),
				user.getCharindex(), user.getSex(), user.getMobile(),
				user.getAge(), user.getSelfsign(), user.getAvatar(),
				user.getBirthday(), user.getAvatarbig(), user.getBackimg(),
				user.getAddress(), user.getOnlineflag(),
				user.getValidflag(), user.getVestflag(), user.getScore(),
				user.getFeeaccount(), user.getLng(), user.getLat(),
				user.getDeviceid(), user.getDevicetype(), user.getChannelid(),
				user.getLastloginversion(), user.getLastlogintime(),
				user.getContent(), user.getDelflag(), user.getRegdate(),
				user.getAsk1_id(), user.getAnswer1(), user.getAsk2_id(),
				user.getAnswer2(), user.getAsk3_id(), user.getAnswer3(),
				user.getAliuser(), user.getBankuser(), user.getBankname(),
				user.getBankcard(), user.getBankaddress(), user.getTransflag(),
				user.getCheckflag(), user.getToken(),user.getRole(),
				user.getAndroid_must_update(), user.getAndroid_last_version(),
				user.getAndroid_update_url() };//48

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
		Object[] bindArgs = new Object[] { user.getId(), user.getUsername(),
				user.getEmail(), user.getPassword(), user.getNickname(),
				user.getCharindex(), user.getSex(), user.getMobile(),
				user.getAge(), user.getSelfsign(), user.getAvatar(),
				user.getBirthday(), user.getAvatarbig(), user.getBackimg(),
				user.getAddress(), user.getOnlineflag(),
				user.getValidflag(), user.getVestflag(), user.getScore(),
				user.getFeeaccount(), user.getLng(), user.getLat(),
				user.getDeviceid(), user.getDevicetype(), user.getChannelid(),
				user.getLastloginversion(), user.getLastlogintime(),
				user.getContent(), user.getDelflag(), user.getRegdate(),
				user.getAsk1_id(), user.getAnswer1(), user.getAsk2_id(),
				user.getAnswer2(), user.getAsk3_id(), user.getAnswer3(),
				user.getAliuser(), user.getBankuser(), user.getBankname(),
				user.getBankcard(), user.getBankaddress(), user.getTransflag(),
				user.getCheckflag(), user.getToken(),user.getRole(), 
				user.getAndroid_must_update(), user.getAndroid_last_version(),
				user.getAndroid_update_url() };

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
					cursor.getString(32), cursor.getString(33),
					cursor.getString(34), cursor.getString(35),
					cursor.getString(36), cursor.getString(37),
					cursor.getString(38), cursor.getString(39),
					cursor.getString(40), cursor.getString(41),
					cursor.getString(42), cursor.getString(43),
					cursor.getString(44));
		}
		cursor.close();
		db.close();
		return user;
	}
}
