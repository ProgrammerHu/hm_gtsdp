package com.hemaapp.hm_gtsdp.activity;

import java.util.ArrayList;
import java.util.List;

import xtom.frame.view.XtomRefreshLoadmoreLayout;
import xtom.frame.view.XtomRefreshLoadmoreLayout.OnStartListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;

import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpActivity;
import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_gtsdp.adapter.MessageListAdapter;
import com.hemaapp.hm_gtsdp.model.MessageItem;
import com.hemaapp.hm_gtsdp.view.GtsdpRefreshLoadmoreLayout;
import com.hemaapp.hm_gtsdp.view.ListViewCompat;
import com.hemaapp.hm_gtsdp.view.SelectPopupWindow;
import com.hemaapp.hm_gtsdp.view.SlideView;
import com.hemaapp.hm_gtsdp.view.SlideView.OnSlideListener;

public class MessagesActivity extends GtsdpActivity implements OnClickListener, OnSlideListener,
OnItemClickListener, OnStartListener{
	private boolean ActivityType;//标记退出时是否需要启动MainPageActivity，true则需要
	private ListViewCompat listViewCompat;
	private ImageView imageQuitActivity, imageSetting;
	private SlideView mLastSlideViewWithStatusOn;
	private SelectPopupWindow selectPopupWindow;
	private List<MessageItem> listData;
	private MessageListAdapter adapter;
	private GtsdpRefreshLoadmoreLayout refreshLoadmoreLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_messages);
		super.onCreate(savedInstanceState);

		listData = new ArrayList<MessageItem>();
		listData.add(new MessageItem("系统消息", "恭喜您成功注册高铁捎带", "6分钟前", true));
		listData.add(new MessageItem("系统消息", "您否货物已经在济南市历下区网点接收，敬请等待", "7分钟前", true));
		listData.add(new MessageItem("系统消息", "恭喜您成功注册高铁捎带", "8分钟前", false));
		listData.add(new MessageItem("系统消息", "恭喜您成功注册高铁捎带", "6分钟前", true));
		listData.add(new MessageItem("系统消息", "您否货物已经在济南市历下区网点接收，敬请等待", "7分钟前", true));
		listData.add(new MessageItem("系统消息", "恭喜您成功注册高铁捎带", "8分钟前", false));
		listData.add(new MessageItem("系统消息", "恭喜您成功注册高铁捎带", "6分钟前", true));
		listData.add(new MessageItem("系统消息", "您否货物已经在济南市历下区网点接收，敬请等待", "7分钟前", true));
		listData.add(new MessageItem("系统消息", "恭喜您成功注册高铁捎带", "8分钟前", false));
		listData.add(new MessageItem("系统消息", "恭喜您成功注册高铁捎带", "6分钟前", true));
		listData.add(new MessageItem("系统消息", "您否货物已经在济南市历下区网点接收，敬请等待", "7分钟前", true));
		listData.add(new MessageItem("系统消息", "恭喜您成功注册高铁捎带", "8分钟前", false));
		listData.add(new MessageItem("系统消息", "恭喜您成功注册高铁捎带", "6分钟前", true));
		listData.add(new MessageItem("系统消息", "您否货物已经在济南市历下区网点接收，敬请等待", "7分钟前", true));
		listData.add(new MessageItem("系统消息", "恭喜您成功注册高铁捎带", "8分钟前", false));
		adapter = new MessageListAdapter(mContext, this, listData);
		listViewCompat.setAdapter(adapter);
		selectPopupWindow = new SelectPopupWindow(mContext, this, "清空", "全部设为已读");
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
		listViewCompat = (ListViewCompat)findViewById(R.id.listViewCompat);
		imageSetting = (ImageView)findViewById(R.id.imageSetting);
		imageQuitActivity = (ImageView)findViewById(R.id.imageQuitActivity);
		refreshLoadmoreLayout = (GtsdpRefreshLoadmoreLayout)findViewById(R.id.refreshLoadmoreLayout);
		
	}

	@Override
	protected void getExras() {
		ActivityType = getIntent().getBooleanExtra("ActivityType", false);
	}

	@Override
	protected void setListener() {
		imageQuitActivity.setOnClickListener(this);
		imageSetting.setOnClickListener(this);
		listViewCompat.setOnItemClickListener(this);
		refreshLoadmoreLayout.setOnStartListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
		case R.id.imageSetting:
			selectPopupWindow.showAtLocation(findViewById(R.id.father), Gravity.BOTTOM, 0, 0);
			break;
		case R.id.imageQuitActivity:
			Intent intent = new Intent(this, MainPageActivity.class);
			startActivity(intent);
			finish(R.anim.none, R.anim.right_out);
			break;
		case R.id.btnTop:
			selectPopupWindow.dismiss();
			break;
		case R.id.btnMiddle:
			selectPopupWindow.dismiss();
			for(MessageItem item : listData)
			{
				item.IsNew = false;
			}
			adapter.notifyDataSetChanged();
			break;
		}
	}

	@Override
	public void onSlide(View view, int status) {
		 if (mLastSlideViewWithStatusOn != null && mLastSlideViewWithStatusOn != view) {
	            mLastSlideViewWithStatusOn.shrink();
		 }
		 if (status == SLIDE_STATUS_ON) {
	            mLastSlideViewWithStatusOn = (SlideView) view;
		 }
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		listData.get(position).IsNew = false;
		adapter.notifyDataSetChanged();
		
	}

	@Override
	public void onStartLoadmore(XtomRefreshLoadmoreLayout arg0) {
		// TODO 加载更多

		listData.add(new MessageItem("系统消息", "恭喜您成功注册高铁捎带", "6分钟前", true));
		listData.add(new MessageItem("系统消息", "您否货物已经在济南市历下区网点接收，敬请等待", "7分钟前", true));
		listData.add(new MessageItem("系统消息", "恭喜您成功注册高铁捎带", "8分钟前", false));
		listData.add(new MessageItem("系统消息", "恭喜您成功注册高铁捎带", "6分钟前", true));
		listData.add(new MessageItem("系统消息", "您否货物已经在济南市历下区网点接收，敬请等待", "7分钟前", true));
		listData.add(new MessageItem("系统消息", "恭喜您成功注册高铁捎带", "8分钟前", false));
		listData.add(new MessageItem("系统消息", "恭喜您成功注册高铁捎带", "6分钟前", true));
		listData.add(new MessageItem("系统消息", "您否货物已经在济南市历下区网点接收，敬请等待", "7分钟前", true));
		listData.add(new MessageItem("系统消息", "恭喜您成功注册高铁捎带", "8分钟前", false));
		listData.add(new MessageItem("系统消息", "恭喜您成功注册高铁捎带", "6分钟前", true));
		listData.add(new MessageItem("系统消息", "您否货物已经在济南市历下区网点接收，敬请等待", "7分钟前", true));
		listData.add(new MessageItem("系统消息", "恭喜您成功注册高铁捎带", "8分钟前", false));
		listData.add(new MessageItem("系统消息", "恭喜您成功注册高铁捎带", "6分钟前", true));
		listData.add(new MessageItem("系统消息", "您否货物已经在济南市历下区网点接收，敬请等待", "7分钟前", true));
		listData.add(new MessageItem("系统消息", "恭喜您成功注册高铁捎带", "8分钟前", false));
		adapter.changeData(listData);
		adapter.notifyDataSetChanged();
		refreshLoadmoreLayout.loadmoreSuccess();
	}

	@Override
	public void onStartRefresh(XtomRefreshLoadmoreLayout arg0) {
		// TODO 刷新


		listData = new ArrayList<MessageItem>();
		listData.add(new MessageItem("系统消息", "恭喜您成功注册高铁捎带", "6分钟前", true));
		listData.add(new MessageItem("系统消息", "您否货物已经在济南市历下区网点接收，敬请等待", "7分钟前", true));
		listData.add(new MessageItem("系统消息", "恭喜您成功注册高铁捎带", "8分钟前", false));
		listData.add(new MessageItem("系统消息", "恭喜您成功注册高铁捎带", "6分钟前", true));
		listData.add(new MessageItem("系统消息", "您否货物已经在济南市历下区网点接收，敬请等待", "7分钟前", true));
		listData.add(new MessageItem("系统消息", "恭喜您成功注册高铁捎带", "8分钟前", false));
		listData.add(new MessageItem("系统消息", "恭喜您成功注册高铁捎带", "6分钟前", true));
		listData.add(new MessageItem("系统消息", "您否货物已经在济南市历下区网点接收，敬请等待", "7分钟前", true));
		listData.add(new MessageItem("系统消息", "恭喜您成功注册高铁捎带", "8分钟前", false));
		listData.add(new MessageItem("系统消息", "恭喜您成功注册高铁捎带", "6分钟前", true));
		listData.add(new MessageItem("系统消息", "您否货物已经在济南市历下区网点接收，敬请等待", "7分钟前", true));
		listData.add(new MessageItem("系统消息", "恭喜您成功注册高铁捎带", "8分钟前", false));
		listData.add(new MessageItem("系统消息", "恭喜您成功注册高铁捎带", "6分钟前", true));
		listData.add(new MessageItem("系统消息", "您否货物已经在济南市历下区网点接收，敬请等待", "7分钟前", true));
		listData.add(new MessageItem("系统消息", "恭喜您成功注册高铁捎带", "8分钟前", false));
		adapter.changeData(listData);
		adapter.notifyDataSetChanged();
		refreshLoadmoreLayout.refreshSuccess();
	}

}
