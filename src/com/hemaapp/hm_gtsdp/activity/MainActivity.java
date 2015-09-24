package com.hemaapp.hm_gtsdp.activity;

import java.io.InputStream;

import org.apache.http.util.EncodingUtils;
import org.json.JSONArray;
import org.json.JSONObject;


import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.hemaapp.GtsdpConfig;
import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_gtsdp.R.id;
import com.hemaapp.hm_gtsdp.R.layout;
import com.hemaapp.hm_gtsdp.R.menu;
import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpActivity;
import com.hemaapp.hm_gtsdp.model.User;
import com.hemaapp.hm_gtsdp.push.PushUtils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends GtsdpActivity implements
OnClickListener
{
	private Button btnQRCode, btnLogin, btnSetPwd, btnFixData, btnMap, btnAnswer,
	btnMainPage, btnSendDetail, btnReciverList, btnSenderList, btnTimePicker, 
	btnIdentification,btnSelectPic, btnIndentified, btnNotCursor, btnPublishRoute, btnFindGoods,
	btnGoodsDetail, btnCrusorDetail, btnReciverDetail, btnSiteBDetail, btnSiteDetail, btnPay, btnMessages,
	btnConfirmGet, btnMyAccount, btnBank, btnTransactionRecords, btnRecharge, btnAlipay,
	btnSettings, btnDispatching, btnPwdManage, btnUsercenter, btnSetQuestion, btnAboutUs;
	private TextView txtShow;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_main);
		super.onCreate(savedInstanceState);
		startPush();
	}
	@Override
	protected void findView() {
		txtShow = (TextView)findViewById(R.id.txtShow);
		btnQRCode = (Button)findViewById(R.id.btnQRCode);
		btnLogin = (Button)findViewById(R.id.btnLogin);
		btnSetPwd = (Button)findViewById(R.id.btnSetPwd);
		btnMap = (Button)findViewById(R.id.btnMap);
		btnAnswer = (Button)findViewById(R.id.btnAnswer);
		btnMainPage = (Button)findViewById(R.id.btnMainPage);
		btnSendDetail = (Button)findViewById(R.id.btnSendDetail);
		btnReciverList = (Button)findViewById(R.id.btnReciverList);
		btnSenderList = (Button)findViewById(R.id.btnSenderList);
		btnIndentified = (Button)findViewById(R.id.btnIndentified);
		btnTimePicker = (Button)findViewById(R.id.btnTimePicker);
		btnIdentification = (Button)findViewById(R.id.btnIdentification);
		btnSelectPic = (Button)findViewById(R.id.btnSelectPic);
		btnNotCursor = (Button)findViewById(R.id.btnNotCursor);
		btnPublishRoute = (Button)findViewById(R.id.btnPublishRoute);
		btnFindGoods = (Button)findViewById(R.id.btnFindGoods);
		btnPay = (Button)findViewById(R.id.btnPay);
		btnQRCode.setOnClickListener(this);
		btnLogin.setOnClickListener(this);
		btnSetPwd.setOnClickListener(this);
		btnMap.setOnClickListener(this);
		btnAnswer.setOnClickListener(this);
		btnMainPage.setOnClickListener(this);
		btnSendDetail.setOnClickListener(this);
		btnReciverList.setOnClickListener(this);
		btnSenderList.setOnClickListener(this);
		btnTimePicker.setOnClickListener(this);
		btnIdentification.setOnClickListener(this);
		btnSelectPic.setOnClickListener(this);
		btnIndentified.setOnClickListener(this);
		btnNotCursor.setOnClickListener(this);
		btnPublishRoute.setOnClickListener(this);
		btnFindGoods.setOnClickListener(this);
		init(btnGoodsDetail, R.id.btnGoodsDetail);
		init(btnCrusorDetail, R.id.btnCrusorDetail);
		init(btnReciverDetail, R.id.btnReciverDetail);
		init(btnSiteBDetail, R.id.btnSiteBDetail);
		init(btnSiteDetail, R.id.btnSiteDetail);
		init(btnPay, R.id.btnPay);
		init(btnConfirmGet, R.id.btnAllOrders);
		init(btnMessages, R.id.btnMessages);
		init(btnMyAccount, R.id.btnMyAccount);
		init(btnBank, R.id.btnBank);
		init(btnTransactionRecords, R.id.btnTransactionRecords);
		init(btnRecharge, R.id.btnRecharge);
		init(btnAlipay, R.id.btnAlipay);
		init(btnSettings, R.id.btnSettings);
		init(btnDispatching, R.id.btnDispatching);
		init(btnPwdManage, R.id.btnPwdManage);
		init(btnUsercenter, R.id.btnUsercenter);
		init(btnFixData, R.id.btnFixData);
		init(btnSetQuestion, R.id.btnSetQuestion);
		init(btnAboutUs, R.id.btnAboutUs);
		
	}

	private void init(Button button, int id)
	{
		button = (Button)findViewById(id);
		button.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		Intent intent;
		switch(v.getId())
		{
		case R.id.btnQRCode:
			intent = new Intent(MainActivity.this, CodeCaptureActivity.class);
			startActivity(intent);
			break;
		case R.id.btnLogin:
			intent = new Intent(MainActivity.this, LoginActivity.class);
			startActivity(intent);
			break;
		case R.id.btnSetPwd:
			intent = new Intent(MainActivity.this, SetPwdActivity.class);
			intent.putExtra("ActivityType", 0);
			startActivity(intent);
			break;
		case R.id.btnMap:
			intent = new Intent(MainActivity.this, SelectAddressActivity.class);
			startActivity(intent);
			break;
		case R.id.btnAnswer:
			intent = new Intent(MainActivity.this, AnswerQuestionActivity.class);
			startActivity(intent);
			break;
		case R.id.btnMainPage:
			intent = new Intent(MainActivity.this, MainPageActivity.class);
			startActivity(intent);
			break;
		case R.id.btnSendDetail:
			intent = new Intent(MainActivity.this, SendDetailActivty.class);
			startActivity(intent);
			break;
		case R.id.btnReciverList:
			intent = new Intent(MainActivity.this, TemplateListActivty.class);
			intent.putExtra("ActivityType", 200);
			startActivity(intent);
			break;
		case R.id.btnSenderList:
			intent = new Intent(MainActivity.this, TemplateListActivty.class);
			intent.putExtra("ActivityType", 100);
			startActivity(intent);
			break;
		case R.id.btnTimePicker:
			getJsonAssets();
			break;
		case R.id.btnIdentification:
			intent = new Intent(MainActivity.this, CourierIdentificationActivity.class);
			startActivity(intent);
			break;
		case R.id.btnSelectPic:
			intent = new Intent(MainActivity.this, AlbumActivity.class);
			startActivity(intent);
			break;
		case R.id.btnIndentified:
			intent = new Intent(MainActivity.this, IdentifyingActivity.class);
			startActivity(intent);
			break;
		case R.id.btnNotCursor:
			intent = new Intent(MainActivity.this, NotCursorActivity.class);
			startActivity(intent);
			break;
		case R.id.btnPublishRoute:
			intent = new Intent(MainActivity.this, PublishRouteActivity.class);
			startActivity(intent);
			break;
		case R.id.btnFindGoods:
			intent = new Intent(MainActivity.this, FindGoodsActivity.class);
			startActivity(intent);
			break;
		case R.id.btnGoodsDetail:
			intent = new Intent(MainActivity.this, GoodsDetailActivity.class);
			startActivity(intent);
			break;
		case R.id.btnCrusorDetail:
			intent = new Intent(MainActivity.this, GoodsDetailActivity.class);
			intent.putExtra("ActivityType", GtsdpConfig.USER_IDENTIFY_CURSOR);
			startActivity(intent);
			break;
		case R.id.btnReciverDetail:
			intent = new Intent(MainActivity.this, GoodsDetailActivity.class);
			intent.putExtra("ActivityType", GtsdpConfig.USER_IDENTIFY_RECIVIER);
			startActivity(intent);
			break;
		case R.id.btnSiteBDetail:
			intent = new Intent(MainActivity.this, GoodsDetailActivity.class);
			intent.putExtra("ActivityType", GtsdpConfig.USER_IDENTIFY_SITE_B);
			startActivity(intent);
			break;
		case R.id.btnSiteDetail:
			intent = new Intent(MainActivity.this, GoodsDetailActivity.class);
			intent.putExtra("ActivityType", GtsdpConfig.USER_IDENTIFY_SITE);
			startActivity(intent);
			break;
		case R.id.btnPay:
			intent = new Intent(MainActivity.this, MyPayActivity.class);
			startActivity(intent);
			break;
		case R.id.btnAllOrders:
			intent = new Intent(MainActivity.this, AllOrdersActivity.class);
			startActivity(intent);
			break;
		case R.id.btnMessages:
			intent = new Intent(MainActivity.this, MessagesActivity.class);
			startActivity(intent);
			break;
		case R.id.btnMyAccount:
			intent = new Intent(MainActivity.this, MyAccountActivity.class);
			startActivity(intent);
			break;
		case R.id.btnBank:
			intent = new Intent(MainActivity.this, ToCashActivity.class);
			startActivity(intent);
			break;
		case R.id.btnTransactionRecords:
			intent = new Intent(MainActivity.this, TransactionRecordsActivity.class);
			startActivity(intent);
			break;
		case R.id.btnRecharge:
			intent = new Intent(MainActivity.this, RechargeActivity.class);
			startActivity(intent);
			break;
		case R.id.btnAlipay:
			intent = new Intent(MainActivity.this, AlipayIncashActivity.class);
			startActivity(intent);
			break;
		case R.id.btnSettings:
			intent = new Intent(MainActivity.this, SettingsActivity.class);
			startActivity(intent);
			break;
		case R.id.btnDispatching:
			intent = new Intent(MainActivity.this, DispatchingActivity.class);
			startActivity(intent);
			break;
		case R.id.btnPwdManage:
			intent = new Intent(MainActivity.this, PwdManangeActivity.class);
			startActivity(intent);
			break;
		case R.id.btnUsercenter:
			intent = new Intent(MainActivity.this, UserCenterActivity.class);
			startActivity(intent);
			break;
		case R.id.btnFixData:
			intent = new Intent(MainActivity.this, FixDataActivity.class);
			startActivity(intent);
			break;
		case R.id.btnSetQuestion:
			intent = new Intent(MainActivity.this, SetQuestionActivty.class);
			startActivity(intent);
			break;
		case R.id.btnAboutUs:
			intent = new Intent(MainActivity.this, AboutUsActivity.class);
			startActivity(intent);
			break;
			
			
			
			
		}
		overridePendingTransition(R.anim.right_in, R.anim.none);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onDestroy() {
		// ShareSDK分享相关
//		ShareSDK.stopSDK(this);
		// ShareSDK分享相关end

		// 百度推送相关
//		stopPush();
		// 百度推送相关end
		super.onDestroy();
	}

	@Override
	protected void callAfterDataBack(HemaNetTask arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void callBackForGetDataFailed(HemaNetTask arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void callBackForServerFailed(HemaNetTask arg0, HemaBaseResult arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void callBackForServerSuccess(HemaNetTask arg0,
			HemaBaseResult arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void callBeforeDataBack(HemaNetTask arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected void getExras() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		
	}
	
	@SuppressLint("NewApi")
	private void getJsonAssets()
	{
		try 
		{
			String jsonProvinceStr = getAssetsJsonStr("province.json");
			JSONObject jsonProvince = new JSONObject(jsonProvinceStr);
			JSONArray jsonProvinceArray = jsonProvince.getJSONArray("listItems");
			
			String jsonCityStr = getAssetsJsonStr("city.json");
			JSONObject jsonCity = new JSONObject(jsonCityStr);
			JSONArray jsonCityArray = jsonCity.getJSONArray("listItems");
			
			String text = "";
			for(int i = 0; i < jsonProvinceArray.length(); i++)
			{
				String ProvinceName = jsonProvinceArray.getJSONObject(i).getString("name");
				text+= ProvinceName +i+"\n";
				String provinceId = jsonProvinceArray.getJSONObject(i).getString("id");
				for(int j = 0; j < jsonCityArray.length(); j++)
				{
					String parentId = jsonCityArray.getJSONObject(j).getString("parentid");
					String CityName = jsonCityArray.getJSONObject(j).getString("name");
					if(parentId.equals("0") && CityName.equals(ProvinceName))
					{//这是直辖市
						text += jsonProvinceArray.getJSONObject(i).getString("name");
					}
					if(parentId.equals(provinceId))
					{//这是普通地市
						text+= CityName+",";
					}
				}
				text+="\n\n";
			}
			txtShow.setText(text);
		}
		catch(Exception e)
		{
			txtShow.setText(e.getMessage());
		}
		
	}
	
	private String getAssetsJsonStr(String fileName) throws Exception
	{
		try 
		{
			AssetManager asset = getAssets();
			InputStream input = asset.open(fileName);
			int length = input.available();
			byte[] buffer = new byte[length];
			input.read(buffer);
			String jsonStr = EncodingUtils.getString(buffer, "error");  
			return jsonStr;
		}
		catch(Exception e)
		{
			throw new Exception(e);
		}
	}

	

	/* 推送相关 */
	private PushReceiver pushReceiver;
	
	private void startPush() {
		User user = getApplicationContext().getUser();
		if (user == null) {
			log_i("未登录，无需启动推送服务");
			return;
		}
		if (!PushUtils.hasBind(getApplicationContext())) {
			PushManager.startWork(getApplicationContext(),
					PushConstants.LOGIN_TYPE_API_KEY,
					PushUtils.getMetaValue(this, "api_key"));
			// Push: 如果想基于地理位置推送，可以打开支持地理位置的推送的开关
			// PushManager.enableLbs(getApplicationContext());
		} else {
			saveDevice();
		}
		registerPushReceiver();
	}
	

	private void stopPush() {
		PushManager.stopWork(getApplicationContext());
		PushUtils.setBind(mContext, false);
		unregisterPushReceiver();
	}
	
	private void registerPushReceiver() {
		if (pushReceiver == null) {
			pushReceiver = new PushReceiver();
			IntentFilter mFilter = new IntentFilter("com.hemaapp.push.connect");
			mFilter.addAction("com.hemaapp.push.msg");
			registerReceiver(pushReceiver, mFilter);
		}
	}

	private void unregisterPushReceiver() {
		if (pushReceiver != null)
			unregisterReceiver(pushReceiver);
	}
	
	private class PushReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			handleEvent(intent);
		}

		private void handleEvent(Intent intent) {
			String action = intent.getAction();

			if ("com.hemaapp.push.connect".equals(action)) {
				saveDevice();

			} else if ("com.hemaapp.push.msg".equals(action)) {
				boolean unread = PushUtils.getmsgreadflag(
						getApplicationContext(), "2");
				if (unread) {
					// showNoticePoint();
					log_i("有未读推送");
				} else {
					log_i("无未读推送");
					// hideNoticePoint();
				}
			}
		}
	}
	public void saveDevice() {
		User user = getApplicationContext().getUser();
		getNetWorker().deviceSave(user.getToken(),
				PushUtils.getUserId(mContext), "2",
				PushUtils.getChannelId(mContext));
	}
	
	/*推送相关END*/
}
