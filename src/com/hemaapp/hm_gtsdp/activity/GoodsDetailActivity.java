package com.hemaapp.hm_gtsdp.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.hemaapp.GtsdpConfig;
import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpActivity;
import com.hemaapp.hm_gtsdp.GtsdpArrayResult;
import com.hemaapp.hm_gtsdp.GtsdpHttpInformation;
import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_gtsdp.adapter.FindGoodsImageAdapter;
import com.hemaapp.hm_gtsdp.dialog.GtsdpTwoButtonDialog;
import com.hemaapp.hm_gtsdp.dialog.GtsdpTwoButtonDialog.OnButtonListener;
import com.hemaapp.hm_gtsdp.model.ImageItem;
import com.hemaapp.hm_gtsdp.model.OrderModel;
import com.hemaapp.hm_gtsdp.result.TransDetailResult;
import com.hemaapp.hm_gtsdp.view.MyGridView;
/**
 * 货物详情
 * @author Wen
 * @author HuFanglin
 *
 */
public class GoodsDetailActivity extends GtsdpActivity implements OnClickListener {
	private final int CHANGE_COUNT = 100;
	private Intent beforeIntent;
	private int ActivityType;
	private String keytype;/* 业务类型 1:从订单列表中进入详情; 2:网点用户扫描后进入详情; 3:收货人扫描后进入详情;  */
	private String keyid;//主键id 当keytype=1时，keyid=捎带id; 当keytype=2、3时，keyid=二维码信息; 
 
	private TextView txtTitle, txtNext, txtSiteCount, txtReciverCount, txtCursorCount, txtOrderNumber, txtStart, txtEnd, 
	txtDatetime, txtReciverName, txtReciverAddress, txtReciverPhone, txtSenderName, txtSenderAddress, txtSenderPhone;
	private ImageView imageQuitActivity;
	private Button btnConfirmReceive, btnSiteAgree, btnSiteBAgree, btnCursorAgree, btnCursorChange, TempClickButton;
	private LinearLayout layoutMoney;
	private RelativeLayout layoutReciver, layoutCursor, layoutSite;
	private MyGridView gridview; 
	private ScrollView scrollview;
	private GtsdpTwoButtonDialog checkAgree;
	
	private ArrayList<String> images;
	private OrderModel ORDER;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_goods_detail);
		super.onCreate(savedInstanceState);
		images = new ArrayList<String>();

		if(ActivityType == -1 || keyid == null || "".equals(keyid))
			showTextDialog("页面调用参数错误");
		else
		{
			getNetWorker().getTransDetail(getApplicationContext().getUser().getToken(), keytype, keyid);
			showProgressDialog("加载中");
		}
	}
	@Override
	protected void onResume() {
		super.onResume();
		scrollview.smoothScrollTo(0, 0);//scrollView移动到顶部
	}
	/**
	 * 调整界面数据
	 * @param receiver_address 收货人地址
	 * @param sender_address 发货人地址
	 * @param total_fee 支付金额
	 * @param receiver_name  收件人的姓名
	 * @param sender_name 发件人姓名
	 * @param receiver_telphone 收件人的电话
	 * @param sender_telphone 发件人电话
	 * @param regdate 提交时间
	 */
	private void setData(String id, String receiver_address, String sender_address, double total_fee, 
			String receiver_name, String sender_name, String receiver_telphone, String sender_telphone,
			String regdate)
	{
		txtOrderNumber.setText("订单号:"+id);
		txtStart.setText("出发地："+receiver_address);
		txtEnd.setText("目的地："+sender_address);
		txtDatetime.setText(regdate);
		txtReciverName.setText(receiver_name);
		txtReciverAddress.setText(receiver_address);
		txtReciverPhone.setText(receiver_telphone);
		txtSenderName.setText(sender_name);
		txtSenderAddress.setText(sender_address);
		txtSenderPhone.setText(sender_telphone);
		switch(ActivityType)
		{
		case GtsdpConfig.USER_IDENTIFY_CURSOR://捎带者
			btnCursorAgree.setVisibility(View.VISIBLE);
			btnCursorChange.setVisibility(View.VISIBLE);
			layoutCursor.setVisibility(View.VISIBLE);
			txtCursorCount.setText(String.valueOf(total_fee));
			break;
		case GtsdpConfig.USER_IDENTIFY_RECIVIER://收货人
			btnConfirmReceive.setVisibility(View.VISIBLE);
			layoutReciver.setVisibility(View.VISIBLE);
			txtReciverCount.setText(String.valueOf(total_fee));
			break;
		case GtsdpConfig.USER_IDENTIFY_SITE://网点
			btnSiteAgree.setVisibility(View.VISIBLE);
			layoutSite.setVisibility(View.VISIBLE);
			txtSiteCount.setText(String.valueOf(total_fee));
			break;
		case GtsdpConfig.USER_IDENTIFY_SITE_B://网点B
			btnSiteBAgree.setVisibility(View.VISIBLE);
			layoutMoney.setVisibility(View.GONE);
			break;
		}
	}
	
	@Override
	protected void callAfterDataBack(HemaNetTask arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void callBackForGetDataFailed(HemaNetTask arg0, int arg1) {
		
	}

	@Override
	protected void callBackForServerFailed(HemaNetTask netTask, HemaBaseResult baseResult) {
		// TODO Auto-generated method stub
		cancelProgressDialog();
		GtsdpHttpInformation information = (GtsdpHttpInformation)netTask.getHttpInformation();
		switch (information) {
		case TRANS_GET:
			showTextDialog(baseResult.getMsg());
			break;
		case TRANS_PRICE_SAVE:
		case NETWORK_RECEIVE:
			showTextDialog(baseResult.getMsg());
			break;
		}
		
	}

	@Override
	protected void callBackForServerSuccess(HemaNetTask netTask,
			HemaBaseResult baseResult) {
		// TODO Auto-generated method stub
		cancelProgressDialog();
		GtsdpHttpInformation information = (GtsdpHttpInformation)netTask.getHttpInformation();
		switch (information) {
		case TRANS_GET:
			TransDetailResult result = (TransDetailResult)baseResult;
			ORDER = result.getInfo();
			setData(ORDER.getTrade_no(), ORDER.getReceiver_address(), ORDER.getSender_address(), 
					ORDER.getTotal_fee(), ORDER.getReceiver_name(), ORDER.getSender_name(), 
					ORDER.getReceiver_telphone(), ORDER.getSender_telphone(), ORDER.getRegdate());
			int tradetype = Integer.parseInt(ORDER.getTradetype());
			if(tradetype >= 3)
			{
				findViewById(R.id.image1).setVisibility(View.INVISIBLE);
				findViewById(R.id.txtSet).setVisibility(View.INVISIBLE);
			}
			List<ImageItem>imageList = ORDER.getImageItems();
			if(imageList != null && imageList.size() > 0)
			{
				images = new ArrayList<String>();
				for(ImageItem item : imageList)
				{
					images.add(item.getImgurlbig());
				}
				gridview.setAdapter(new FindGoodsImageAdapter(mContext, findViewById(R.id.father), imageList, gridview));
			}
			break;
		case TRANS_PRICE_SAVE:
			showTextDialog("金额保存成功");
			break;
		case NETWORK_RECEIVE:
			showTextDialog("接单成功");
			break;
			
		}
	}

	@Override
	protected void callBeforeDataBack(HemaNetTask arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void findView() {
		txtTitle = (TextView)findViewById(R.id.txtTitle);
		txtTitle.setText("详情");
		txtNext = (TextView)findViewById(R.id.txtNext);
		txtNext.setVisibility(View.INVISIBLE);
		imageQuitActivity = (ImageView)findViewById(R.id.imageQuitActivity);
		btnConfirmReceive = (Button)findViewById(R.id.btnConfirmReceive);
		btnSiteAgree = (Button) findViewById(R.id.btnSiteAgree);
		btnCursorAgree = (Button) findViewById(R.id.btnCursorAgree);
		btnSiteBAgree = (Button)findViewById(R.id.btnSiteBAgree);
		btnCursorChange = (Button) findViewById(R.id.btnCursorChange);
		layoutMoney = (LinearLayout)findViewById(R.id.layoutMoney);
		layoutReciver = (RelativeLayout)findViewById(R.id.layoutReciver);
		layoutCursor = (RelativeLayout)findViewById(R.id.layoutCursor);
		layoutSite = (RelativeLayout)findViewById(R.id.layoutSite);
		
		gridview = (MyGridView)findViewById(R.id.gridview);
		scrollview = (ScrollView)findViewById(R.id.scrollview);
		txtSiteCount = (TextView)findViewById(R.id.txtSiteCount);
		txtReciverCount = (TextView)findViewById(R.id.txtReciverCount);
		txtCursorCount = (TextView)findViewById(R.id.txtCursorCount);
		txtOrderNumber= (TextView)findViewById(R.id.txtOrderNumber);
		txtStart = (TextView) findViewById(R.id.txtStart);
		txtEnd = (TextView) findViewById(R.id.txtEnd);
		txtDatetime = (TextView) findViewById(R.id.txtDatetime);
		txtReciverName = (TextView) findViewById(R.id.txtReciverName);
		txtReciverAddress = (TextView) findViewById(R.id.txtReciverAddress);
		txtReciverPhone = (TextView) findViewById(R.id.txtReciverPhone);
		txtSenderName = (TextView) findViewById(R.id.txtSenderName);
		txtSenderAddress = (TextView) findViewById(R.id.txtSenderAddress);
		txtSenderPhone = (TextView) findViewById(R.id.txtSenderPhone);

		btnConfirmReceive = (Button)findViewById(R.id.btnConfirmReceive);
		btnSiteAgree = (Button)findViewById(R.id.btnSiteAgree);
		btnSiteBAgree = (Button)findViewById(R.id.btnSiteBAgree);
		btnCursorAgree = (Button)findViewById(R.id.btnCursorAgree);
		btnCursorChange = (Button)findViewById(R.id.btnCursorChange);
	}

	@Override
	protected void getExras() {
		beforeIntent = getIntent();
		ActivityType = beforeIntent.getIntExtra("ActivityType", -1);
		keyid = getIntent().getStringExtra("keyid");
		keytype = getIntent().getStringExtra("keytype");
	}

	@Override
	protected void setListener() {
		imageQuitActivity.setOnClickListener(this);
		layoutSite.setOnClickListener(this);
		btnConfirmReceive.setOnClickListener(this);
		btnSiteAgree.setOnClickListener(this);
		btnSiteBAgree.setOnClickListener(this);
		btnCursorAgree.setOnClickListener(this);
		btnCursorChange.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		switch(v.getId())
		{
		case R.id.imageQuitActivity:
			finish(R.anim.none, R.anim.right_out);
			break;
		case R.id.layoutSite:
			Intent intent = new Intent(GoodsDetailActivity.this, ChangeCountActivtiy.class);
			startActivityForResult(intent, CHANGE_COUNT);
			overridePendingTransition(R.anim.right_in, R.anim.none);
			break;
		case R.id.btnSiteAgree://网点接单
			checkAgree = new GtsdpTwoButtonDialog(GoodsDetailActivity.this);
			checkAgree.setText("确定是否接单");
			checkAgree.setRightButtonText("接单");
			checkAgree.setLeftButtonText("取消");
			checkAgree.setRightButtonTextColor(GtsdpConfig.Main_Blue);
			checkAgree.setButtonListener(clickDialogButton);
			TempClickButton = btnSiteAgree;
			checkAgree.show();
			break;
		case R.id.btnSiteBAgree://网点B接单
			
			break;
		}
	}
	
	@Override
	protected boolean onKeyBack() {
		finish(R.anim.none, R.anim.right_out);
		return super.onKeyBack();
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(data != null && resultCode == RESULT_OK)
		{
			switch(requestCode)
			{
			case CHANGE_COUNT:
				double Count = data.getDoubleExtra("Count", 0);
				txtSiteCount.setText(String.valueOf(Count));
				showProgressDialog("金额保存中");
				String id = ORDER.getId();
				String count =  txtSiteCount.getText().toString().trim();
				getNetWorker().transPriceSave(getApplicationContext().getUser().getToken(), id, count);
				break;
			}
		}
	}
	
	private OnButtonListener clickDialogButton = new OnButtonListener() {

		@Override
		public void onLeftButtonClick(GtsdpTwoButtonDialog arg0) {
			checkAgree.cancel();
		}

		@Override
		public void onRightButtonClick(GtsdpTwoButtonDialog arg0) {
			checkAgree.cancel();
			switch(ActivityType)
			{
			case GtsdpConfig.USER_IDENTIFY_SITE:
				getNetWorker().NetworkReceive(getApplicationContext().getUser().getToken(), ORDER.getId());
				break;
			}
			
		}
	};

}
