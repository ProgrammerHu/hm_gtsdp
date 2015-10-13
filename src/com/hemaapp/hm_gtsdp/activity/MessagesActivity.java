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
import com.hemaapp.hm_gtsdp.GtsdpArrayResult;
import com.hemaapp.hm_gtsdp.GtsdpHttpInformation;
import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_gtsdp.adapter.MessageListAdapter;
import com.hemaapp.hm_gtsdp.model.NoticeModel;
import com.hemaapp.hm_gtsdp.view.GtsdpRefreshLoadmoreLayout;
import com.hemaapp.hm_gtsdp.view.ListViewCompat;
import com.hemaapp.hm_gtsdp.view.SelectPopupWindow;
import com.hemaapp.hm_gtsdp.view.SlideView;
import com.hemaapp.hm_gtsdp.view.SlideView.OnSlideListener;
/**
 * ��Ϣ�б����
 * @author Wen
 * @author HuFanglin
 *
 */
public class MessagesActivity extends GtsdpActivity implements OnClickListener, OnSlideListener,
OnItemClickListener, OnStartListener{
	private String token;
	private boolean ActivityType;//����˳�ʱ�Ƿ���Ҫ����MainPageActivity��true����Ҫ
	private ListViewCompat listViewCompat;
	private ImageView imageQuitActivity, imageSetting;
	private SlideView mLastSlideViewWithStatusOn;
	private SelectPopupWindow selectPopupWindow;
	private List<NoticeModel> listNotices;
	private MessageListAdapter adapter;
	private GtsdpRefreshLoadmoreLayout refreshLoadmoreLayout;
	private int page;
	
	private int Position;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_messages);
		super.onCreate(savedInstanceState);
		token = getApplicationContext().getUser().getToken();
		selectPopupWindow = new SelectPopupWindow(mContext, this, "���", "ȫ����Ϊ�Ѷ�");
		getNetWorker().getNoticeList(token, "3", String.valueOf(page));
		refreshLoadmoreLayout.refreshDrawableState();
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
	protected void callBackForServerFailed(HemaNetTask nettask, HemaBaseResult result) {
		cancelProgressDialog();
		showTextDialog(result.getMsg());
		
	}

	@Override
	protected void callBackForServerSuccess(HemaNetTask nettask,
			HemaBaseResult result) {
		cancelProgressDialog();
		GtsdpHttpInformation information = (GtsdpHttpInformation)nettask.getHttpInformation();
		switch (information) {
		case NOTICE_LIST:
			GtsdpArrayResult<NoticeModel> mResult = (GtsdpArrayResult<NoticeModel>)result;
			if(page == 0)
			{
				listNotices = mResult.getObjects();
				adapter = new MessageListAdapter(mContext, this, listNotices);
				listViewCompat.setAdapter(adapter);
				refreshLoadmoreLayout.refreshSuccess();
			}
			else
			{
				for(NoticeModel notice : mResult.getObjects())
				{
					listNotices.add(notice);
				}
				refreshLoadmoreLayout.loadmoreSuccess();
			}
			break;
		case NOTICE_SAVEOPERATE:
			String a = nettask.getParams().get("operatetype");
			int operatetype = Integer.parseInt(a, 5);
			switch (operatetype) {
			case 1:
				listNotices.get(Position).setLooktype("2");
				adapter.notifyDataSetChanged();
				break;
			case 2:
				for(NoticeModel notice : listNotices)
				{
					notice.setLooktype("2");
				}
				adapter.notifyDataSetChanged();
				break;
			case 3:
				listNotices.remove(Position);
				adapter.notifyDataSetChanged();
				break;
			case 4:
				listNotices.clear();
				adapter.notifyDataSetChanged();
				break;
			default:
				showTextDialog("�����������");
				break;
			}
			break;
		}
		
		
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
			if(listNotices != null && listNotices.size() > 0)
			{
				showProgressDialog("�ύ��");
				getNetWorker().noticeSaveOperate(getApplicationContext().getUser().getToken(), 
						listNotices.get(0).getId(), 
						listNotices.get(0).getKeytype(), 
						listNotices.get(0).getKeyid(), "4");
			}
			break;
		case R.id.btnMiddle:
			selectPopupWindow.dismiss();
			if(listNotices != null && listNotices.size() > 0)
			{
				showProgressDialog("�ύ��");
				getNetWorker().noticeSaveOperate(getApplicationContext().getUser().getToken(), 
						listNotices.get(0).getId(), 
						listNotices.get(0).getKeytype(), 
						listNotices.get(0).getKeyid(), "2");
			}
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
		Position = position;
		getNetWorker().noticeSaveOperate(getApplicationContext().getUser().getToken(), 
				listNotices.get(position).getId(), 
				listNotices.get(position).getKeytype(), 
				listNotices.get(position).getKeyid(), "1");
	}

	@Override
	public void onStartLoadmore(XtomRefreshLoadmoreLayout arg0) {
		// TODO ���ظ���
		page++;
		getNetWorker().getNoticeList(token, "1", String.valueOf(page));
		
	}

	@Override
	public void onStartRefresh(XtomRefreshLoadmoreLayout arg0) {
		// TODO ˢ��
		page = 0;
		getNetWorker().getNoticeList(token, "1", String.valueOf(page));
		
	}
	
	@Override
	protected boolean onKeyBack() {
		finish(R.anim.none, R.anim.right_out);
		return super.onKeyBack();
	}
}
