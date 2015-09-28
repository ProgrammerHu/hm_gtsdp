package com.hemaapp.hm_gtsdp.activity;

import java.util.ArrayList;

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
import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_gtsdp.adapter.FindGoodsImageAdapter;
import com.hemaapp.hm_gtsdp.dialog.GtsdpTwoButtonDialog;
import com.hemaapp.hm_gtsdp.dialog.GtsdpTwoButtonDialog.OnButtonListener;
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

	private TextView txtTitle, txtNext, txtSiteCount;
	private ImageView imageQuitActivity;
	private Button btnConfirmReceive, btnSiteAgree, btnSiteBAgree, btnCursorAgree, btnCursorChange, TempClickButton;
	private LinearLayout layoutMoney;
	private RelativeLayout layoutReciver, layoutCursor, layoutSite;
	private MyGridView gridview; 
	private ScrollView scrollview;
	private GtsdpTwoButtonDialog checkAgree;
	
	private ArrayList<String> images;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_goods_detail);
		super.onCreate(savedInstanceState);
		images = new ArrayList<String>();
		images.add("http://d.hiphotos.baidu.com/baike/w%3D268/sign=ce66e2ab940a304e5222a7fce9c9a7c3/ac6eddc451da81cb0aa215625166d016082431dc.jpg");
		images.add("http://7tszkm.com1.z0.glb.clouddn.com/keep-calm-and-carry-on_083060.jpg");
		images.add("http://7tszkm.com1.z0.glb.clouddn.com/keep-calm-and-carry-on_143376.jpg");
		images.add("http://7tszkm.com1.z0.glb.clouddn.com/keep-calm-and-carry-on_143411.png");
		images.add("http://7tszkm.com1.z0.glb.clouddn.com/keep-calm-and-carry-on_1423461.jpg");
		images.add("http://7tszkm.com1.z0.glb.clouddn.com/keep-calm-and-carry-on_1423462.jpg");
		images.add("http://7tszkm.com1.z0.glb.clouddn.com/keep-calm-and-carry-on_1423463.jpg");
		images.add("http://d.hiphotos.baidu.com/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=78fb3c9ffbdcd100d991f07313e22c75/0eb30f2442a7d9338ba972e1ae4bd11373f0011a.jpg");
//		images.add("http://d.hiphotos.baidu.com/baike/w%3D268/sign=ce66e2ab940a304e5222a7fce9c9a7c3/ac6eddc451da81cb0aa215625166d016082431dc.jpg");
//		images.add("http://7tszkm.com1.z0.glb.clouddn.com/1.png");
		gridview.setAdapter(new FindGoodsImageAdapter(mContext, findViewById(R.id.father), images, gridview));
		
	}
	@Override
	protected void onResume() {
		super.onResume();
		scrollview.smoothScrollTo(0, 0);//scrollView移动到顶部
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
		switch(ActivityType)
		{
		case GtsdpConfig.USER_IDENTIFY_CURSOR://捎带者
			btnCursorAgree.setVisibility(View.VISIBLE);
			btnCursorChange.setVisibility(View.VISIBLE);
			layoutCursor.setVisibility(View.VISIBLE);
			break;
		case GtsdpConfig.USER_IDENTIFY_RECIVIER://收货人
			btnConfirmReceive.setVisibility(View.VISIBLE);
			layoutReciver.setVisibility(View.VISIBLE);
			break;
		case GtsdpConfig.USER_IDENTIFY_SITE://网点
			btnSiteAgree.setVisibility(View.VISIBLE);
			layoutSite.setVisibility(View.VISIBLE);
			break;
		case GtsdpConfig.USER_IDENTIFY_SITE_B://网点B
			btnSiteBAgree.setVisibility(View.VISIBLE);
			layoutMoney.setVisibility(View.GONE);
			break;
		}
		gridview = (MyGridView)findViewById(R.id.gridview);
		scrollview = (ScrollView)findViewById(R.id.scrollview);
		txtSiteCount = (TextView)findViewById(R.id.txtSiteCount);
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
		if(ActivityType == -1)
			showTextDialog("页面调用参数错误");
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
				showTextDialog("接单成功");
				break;
			}
			
		}
	};

}
