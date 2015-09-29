package com.hemaapp.hm_gtsdp.push;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import xtom.frame.util.XtomBaseUtil;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;

import com.baidu.android.pushservice.PushMessageReceiver;
import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_gtsdp.activity.MessagesActivity;

/**
 * Push��Ϣ����receiver�����д����Ҫ�Ļص������� һ����˵�� onBind�Ǳ���ģ���������startWork����ֵ��
 * onMessage��������͸����Ϣ�� onSetTags��onDelTags��onListTags��tag��ز����Ļص���
 * onNotificationClicked��֪ͨ�����ʱ�ص��� onUnbind��stopWork�ӿڵķ���ֵ�ص�
 * 
 * ����ֵ�е�errorCode���������£� 0 - Success 10001 - Network Problem 30600 - Internal
 * Server Error 30601 - Method Not Allowed 30602 - Request Params Not Valid
 * 30603 - Authentication Failed 30604 - Quota Use Up Payment Required 30605 -
 * Data Required Not Found 30606 - Request Time Expires Timeout 30607 - Channel
 * Token Timeout 30608 - Bind Relation Not Found 30609 - Bind Number Too Many
 * 
 * �����������Ϸ��ش���ʱ��������Ͳ����������⣬����ͬһ����ķ���ֵrequestId��errorCode��ϵ����׷�����⡣
 * 
 */
public class MyPushMessageReceiver extends PushMessageReceiver {
	/** TAG to Log */
	public static final String TAG = MyPushMessageReceiver.class
			.getSimpleName();

	private NotificationManager notificationManager = null;

	/**
	 * ����PushManager.startWork��sdk����push
	 * server�������������������첽�ġ�������Ľ��ͨ��onBind���ء� �������Ҫ�õ������ͣ���Ҫ�������ȡ��channel
	 * id��user id�ϴ���Ӧ��server�У��ٵ���server�ӿ���channel id��user id�������ֻ������û����͡�
	 * 
	 * @param context
	 *            BroadcastReceiver��ִ��Context
	 * @param errorCode
	 *            �󶨽ӿڷ���ֵ��0 - �ɹ�
	 * @param appid
	 *            Ӧ��id��errorCode��0ʱΪnull
	 * @param userId
	 *            Ӧ��user id��errorCode��0ʱΪnull
	 * @param channelId
	 *            Ӧ��channel id��errorCode��0ʱΪnull
	 * @param requestId
	 *            �����˷��������id����׷������ʱ���ã�
	 * @return none
	 */
	@Override
	public void onBind(Context context, int errorCode, String appid,
			String userId, String channelId, String requestId) {
		String responseString = "onBind errorCode=" + errorCode + " appid="
				+ appid + " userId=" + userId + " channelId=" + channelId
				+ " requestId=" + requestId;

		Log.e("mlp", responseString);

		// �󶨳ɹ��������Ѱ�flag��������Ч�ļ��ٲ���Ҫ�İ�����
		if (errorCode == 0) {
			PushUtils.setBind(context, true);
		}
		// Demo���½���չʾ���룬Ӧ��������������Լ��Ĵ����߼�
		updateContent(context, responseString);

		PushUtils.saveUserId(context, userId);
		PushUtils.saveChannelId(context, channelId);

		Intent intent = new Intent();
		intent.setAction("com.hemaapp.push.connect");
		// ���� һ������㲥
		context.sendBroadcast(intent);
	}

	/**
	 * ����͸����Ϣ�ĺ�����
	 * 
	 * @param context
	 *            ������
	 * @param message
	 *            ���͵���Ϣ
	 * @param customContentString
	 *            �Զ�������,Ϊ�ջ���json�ַ���
	 */
	@Override
	public void onMessage(Context context, String message,
			String customContentString) {
		mynotify(context, message, "2", "3");
		String messageString = "͸����Ϣ message=\"" + message
				+ "\" customContentString=" + customContentString;
		Log.e(TAG, messageString);

		if (notificationManager == null)
			notificationManager = (NotificationManager) context
					.getSystemService(Context.NOTIFICATION_SERVICE);

		String msg_title = "";
		String msg_description = "";
		String msg_keytype = "";
		try {
			JSONObject msgJson = new JSONObject(message);
			msg_title = msgJson.getString("title");
			msg_description = msgJson.getString("description");
			JSONObject customJson = msgJson.getJSONObject("custom_content");
			msg_keytype = customJson.getString("keyType");
		} catch (JSONException e) {
			return;
		}

		if (msg_title == null || TextUtils.isEmpty(msg_title))
			msg_title = "���";

		// �Զ������ݻ�ȡ��ʽ��mykey��myvalue��Ӧ͸����Ϣ����ʱ�Զ������������õļ���ֵ
		if (!TextUtils.isEmpty(customContentString)) {
			JSONObject customJson = null;
			try {
				customJson = new JSONObject(customContentString);
				String myvalue = null;
				if (customJson.isNull("mykey")) {
					myvalue = customJson.getString("mykey");
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		mynotify(context, msg_description, msg_title, msg_keytype);
		PushUtils.savemsgreadflag(context, true, msg_keytype);

		Intent intent = new Intent();
		intent.setAction("com.hemaapp.push.msg");
		// ���� һ������㲥
		context.sendBroadcast(intent);
	}

	/**
	 * ����֪ͨ����ĺ�����ע������֪ͨ���û����ǰ��Ӧ���޷�ͨ���ӿڻ�ȡ֪ͨ�����ݡ�
	 * 
	 * @param context
	 *            ������
	 * @param title
	 *            ���͵�֪ͨ�ı���
	 * @param description
	 *            ���͵�֪ͨ������
	 * @param customContentString
	 *            �Զ������ݣ�Ϊ�ջ���json�ַ���
	 */
	@Override
	public void onNotificationClicked(Context context, String title,
			String description, String customContentString) {
		String notifyString = "֪ͨ��� title=\"" + title + "\" description=\""
				+ description + "\" customContent=" + customContentString;
		Log.e(TAG, notifyString);

		// �Զ������ݻ�ȡ��ʽ��mykey��myvalue��Ӧ֪ͨ����ʱ�Զ������������õļ���ֵ
		if (!TextUtils.isEmpty(customContentString)) {
			JSONObject customJson = null;
			try {
				customJson = new JSONObject(customContentString);
				String myvalue = null;
				if (customJson.isNull("mykey")) {
					myvalue = customJson.getString("mykey");
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Demo���½���չʾ���룬Ӧ��������������Լ��Ĵ����߼�
//		 updateContent(context, notifyString);
		
		Intent intent = new Intent();
		intent.setClass(context.getApplicationContext(), MessagesActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.getApplicationContext().startActivity(intent);

	}

	/**
	 * ����֪ͨ����ĺ�����
	 * 
	 * @param context
	 *            ������
	 * @param title
	 *            ���͵�֪ͨ�ı���
	 * @param description
	 *            ���͵�֪ͨ������
	 * @param customContentString
	 *            �Զ������ݣ�Ϊ�ջ���json�ַ���
	 */

	@Override
	public void onNotificationArrived(Context context, String title,
			String description, String customContentString) {

		String notifyString = "onNotificationArrived  title=\"" + title
				+ "\" description=\"" + description + "\" customContent="
				+ customContentString;
		Log.e(TAG, notifyString);

		// �Զ������ݻ�ȡ��ʽ��mykey��myvalue��Ӧ֪ͨ����ʱ�Զ������������õļ���ֵ
		if (!TextUtils.isEmpty(customContentString)) {
			JSONObject customJson = null;
			try {
				customJson = new JSONObject(customContentString);
				String myvalue = null;
				if (!customJson.isNull("mykey")) {
					myvalue = customJson.getString("mykey");
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// Demo���½���չʾ���룬Ӧ��������������Լ��Ĵ����߼�
		// ����ԅ��� onNotificationClicked�е���ʾ���Զ������ݻ�ȡ����ֵ
		updateContent(context, notifyString);
	}

	/**
	 * setTags() �Ļص�������
	 * 
	 * @param context
	 *            ������
	 * @param errorCode
	 *            �����롣0��ʾĳЩtag�Ѿ����óɹ�����0��ʾ����tag�����þ�ʧ�ܡ�
	 * @param successTags
	 *            ���óɹ���tag
	 * @param failTags
	 *            ����ʧ�ܵ�tag
	 * @param requestId
	 *            ������������͵������id
	 */
	@Override
	public void onSetTags(Context context, int errorCode,
			List<String> sucessTags, List<String> failTags, String requestId) {
		String responseString = "onSetTags errorCode=" + errorCode
				+ " sucessTags=" + sucessTags + " failTags=" + failTags
				+ " requestId=" + requestId;
		Log.e(TAG, responseString);

		// Demo���½���չʾ���룬Ӧ��������������Լ��Ĵ����߼�
		updateContent(context, responseString);
	}

	/**
	 * delTags() �Ļص�������
	 * 
	 * @param context
	 *            ������
	 * @param errorCode
	 *            �����롣0��ʾĳЩtag�Ѿ�ɾ���ɹ�����0��ʾ����tag��ɾ��ʧ�ܡ�
	 * @param successTags
	 *            �ɹ�ɾ����tag
	 * @param failTags
	 *            ɾ��ʧ�ܵ�tag
	 * @param requestId
	 *            ������������͵������id
	 */
	@Override
	public void onDelTags(Context context, int errorCode,
			List<String> sucessTags, List<String> failTags, String requestId) {
		String responseString = "onDelTags errorCode=" + errorCode
				+ " sucessTags=" + sucessTags + " failTags=" + failTags
				+ " requestId=" + requestId;
		Log.e(TAG, responseString);

		// Demo���½���չʾ���룬Ӧ��������������Լ��Ĵ����߼�
		updateContent(context, responseString);
	}

	/**
	 * listTags() �Ļص�������
	 * 
	 * @param context
	 *            ������
	 * @param errorCode
	 *            �����롣0��ʾ�о�tag�ɹ�����0��ʾʧ�ܡ�
	 * @param tags
	 *            ��ǰӦ�����õ�����tag��
	 * @param requestId
	 *            ������������͵������id
	 */
	@Override
	public void onListTags(Context context, int errorCode, List<String> tags,
			String requestId) {
		String responseString = "onListTags errorCode=" + errorCode + " tags="
				+ tags;
		Log.e(TAG, responseString);

		// Demo���½���չʾ���룬Ӧ��������������Լ��Ĵ����߼�
		updateContent(context, responseString);
	}

	/**
	 * PushManager.stopWork() �Ļص�������
	 * 
	 * @param context
	 *            ������
	 * @param errorCode
	 *            �����롣0��ʾ�������ͽ�󶨳ɹ�����0��ʾʧ�ܡ�
	 * @param requestId
	 *            ������������͵������id
	 */
	@Override
	public void onUnbind(Context context, int errorCode, String requestId) {
		String responseString = "onUnbind errorCode=" + errorCode
				+ " requestId = " + requestId;
		Log.e(TAG, responseString);

		// ��󶨳ɹ�������δ��flag��
		if (errorCode == 0) {
			PushUtils.setBind(context, false);
		}
		// Demo���½���չʾ���룬Ӧ��������������Լ��Ĵ����߼�
		updateContent(context, responseString);
	}

	private void updateContent(Context context, String content) {
		
//		  Log.e(TAG, "updateContent"); String logText = "" +
//		  Utils.logStringCache;
//		  
//		  if (!logText.equals("")) { logText += "\n"; }
//		  
//		 SimpleDateFormat sDateFormat = new SimpleDateFormat("HH-mm-ss");
//		 logText += sDateFormat.format(new Date()) + ": "; logText += content;
//		  
//		  Utils.logStringCache = logText;
//		  
		 
	}

	@SuppressWarnings("deprecation")
	public void mynotify(Context context, String content, String title,
			String keytype) {
		Log.e(TAG, "notify()...");
		Log.e(TAG, "content=" + content);
		Log.e(TAG, "keytype=" + keytype);

		NotificationManager nm = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		NotificationCompat.Builder builder = new NotificationCompat.Builder(
				context);
		Notification notification = null;
		Intent it = null;
		builder.setSmallIcon(R.drawable.ic_launcher);
		builder.setWhen(System.currentTimeMillis());
		builder.setContentTitle(context.getString(R.string.app_name));
		builder.setContentText(content).setTicker(content);
		it = new Intent(context, MessagesActivity.class);
		PendingIntent pi = PendingIntent.getActivity(context, 0, it,
				PendingIntent.FLAG_CANCEL_CURRENT);
		builder.setContentIntent(pi);
		notification = builder.build();
		notification.flags = Notification.FLAG_AUTO_CANCEL;
		notification.defaults = Notification.DEFAULT_LIGHTS;
		if ("0".equals(keytype)) {
			// notification.defaults |= Notification.DEFAULT_SOUND;
		} else {
			notification.defaults |= Notification.DEFAULT_SOUND;
		}

		nm.notify(0, notification);
	}

	
}
