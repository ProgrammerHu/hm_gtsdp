package com.hemaapp.hm_gtsdp.activity;

import java.util.ArrayList;
import java.util.List;

import u.aly.da;
import xtom.frame.view.XtomRefreshLoadmoreLayout;
import xtom.frame.view.XtomRefreshLoadmoreLayout.OnStartListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.mapcore.ba;
import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpActivity;
import com.hemaapp.hm_gtsdp.GtsdpArrayResult;
import com.hemaapp.hm_gtsdp.GtsdpHttpInformation;
import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_gtsdp.adapter.TemplateAddressAdpater;
import com.hemaapp.hm_gtsdp.model.TemplateItemModel;
import com.hemaapp.hm_gtsdp.view.GtsdpRefreshLoadmoreLayout;

/**
 * 模板列表界面
 * @author Wen
 * @author HuFanglin
 *
 */
public class TemplateListActivty extends GtsdpActivity implements OnClickListener {
	private int SENDER = 100;//发件人
	private int RECIVER = 200;//收件人
	private Intent beforeIntent;
	private int ActivityType;
	private LinearLayout layoutAddTemplate;
	private GtsdpRefreshLoadmoreLayout refreshLoadmoreLayout;
	private ListView templateList;
	private TemplateAddressAdpater addressAdapter;
	private TextView txtTitle, txtNext, txtAdd;
	private ImageView imageQuitActivity;
	private int SelectPosition = -1;
	private List<TemplateItemModel> dataList;
	private int page;//第几页 从0开始
	private String token;// 登录令牌
	private String keytype;// 业务类型 1：收件人模板； 2：发件人模板； 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_template_list);
		super.onCreate(savedInstanceState);
		page = 0;
		token = getApplicationContext().getUser().getToken();
		getNetWorker().getTemplateList(token, keytype, page);
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
	protected void callBackForServerFailed(HemaNetTask netTask, HemaBaseResult arg1) {
		GtsdpHttpInformation infomation = (GtsdpHttpInformation)netTask.getHttpInformation();
		switch (infomation) {
		case TEMPLATE_LIST:
			if(page == 0)
				refreshLoadmoreLayout.refreshFailed();
			else
				refreshLoadmoreLayout.loadmoreFailed();
			break;

		}
	}

	@Override
	protected void callBackForServerSuccess(HemaNetTask netTask,
			HemaBaseResult baseResult) {
		cancelProgressDialog();
		GtsdpHttpInformation infomation = (GtsdpHttpInformation)netTask.getHttpInformation();
		switch (infomation) {
		case TEMPLATE_LIST:
			setListViewData(baseResult);
			if(page == 0)
				refreshLoadmoreLayout.refreshSuccess();
			else
				refreshLoadmoreLayout.loadmoreSuccess();
				
			break;

		}
		
	}

	@Override
	protected void callBeforeDataBack(HemaNetTask netTask) {
	
		
	}

	@Override
	protected void findView() {
		layoutAddTemplate = (LinearLayout)findViewById(R.id.layoutAddTemplate);
		templateList = (ListView)findViewById(R.id.templateList);
		refreshLoadmoreLayout = (GtsdpRefreshLoadmoreLayout)findViewById(R.id.refreshLoadmoreLayout);
		txtTitle = (TextView)findViewById(R.id.txtTitle);
		txtTitle.setText("模板");
		txtNext = (TextView)findViewById(R.id.txtNext);
		txtNext.setVisibility(View.INVISIBLE);
		imageQuitActivity = (ImageView)findViewById(R.id.imageQuitActivity);
		txtAdd = (TextView)findViewById(R.id.txtAdd);
		if(ActivityType == SENDER)
		{
			txtAdd.setText("添加发件人模板");
			keytype = "1";
		}
		else if(ActivityType == RECIVER)
		{
			txtAdd.setText("添加收件人模板");
			keytype = "2";
		}
	}

	@Override
	protected void getExras() {
		beforeIntent = getIntent();
		ActivityType = beforeIntent.getIntExtra("ActivityType", 300);
	}

	@Override
	protected void setListener() {
		layoutAddTemplate.setOnClickListener(this);
		imageQuitActivity.setOnClickListener(this);
		templateList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) { 
				SelectPosition = position;
				addressAdapter.selectItem(position);
				addressAdapter.notifyDataSetChanged();
			}
		});
		refreshLoadmoreLayout.setOnStartListener(new OnStartListener() {
			
			@Override
			public void onStartRefresh(XtomRefreshLoadmoreLayout arg0) {
				page = 0;
				token = getApplicationContext().getUser().getToken();
				getNetWorker().getTemplateList(token, keytype, page);
			}
			
			@Override
			public void onStartLoadmore(XtomRefreshLoadmoreLayout arg0) {
				page++;
				token = getApplicationContext().getUser().getToken();
				getNetWorker().getTemplateList(token, keytype, page);
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
		case R.id.imageQuitActivity:
			if(SelectPosition != -1)
			{//已经选择
				Intent result = new Intent();
				result.putExtra("Name", dataList.get(SelectPosition).getName());
				result.putExtra("Address", dataList.get(SelectPosition).getAddress());
				result.putExtra("Phone", dataList.get(SelectPosition).getTelphone());
				setResult(RESULT_OK, result);
			}
			finish(R.anim.none, R.anim.right_out);
			break;
		case R.id.layoutAddTemplate:
			Intent intent = new Intent(TemplateListActivty.this, TemplateEditActivty.class);
			intent.putExtra("ActivityType", ActivityType);
			startActivityForResult(intent, 0);
			overridePendingTransition(R.anim.right_in, R.anim.none);
			break;
		}
		
	}
	/**
	 * 设置ListView数据
	 */
	private void setListViewData(HemaBaseResult baseResult)
	{
		GtsdpArrayResult<TemplateItemModel> result = (GtsdpArrayResult<TemplateItemModel>)baseResult;
		if(page == 0)
		{
			dataList = result.getObjects();
			addressAdapter = new TemplateAddressAdpater(TemplateListActivty.this, dataList, ActivityType);
			templateList.setAdapter(addressAdapter);
		}
		else
		{
			if(dataList == null)
			{
				dataList = new ArrayList<TemplateItemModel>();
			}
			for(TemplateItemModel model : result.getObjects())
			{
				dataList.add(model);
			}
			addressAdapter.changeDataList(dataList);
			addressAdapter.notifyDataSetChanged();
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK)
		{
			showProgressDialog("更新中");
			page = 0;
			token = getApplicationContext().getUser().getToken();
			getNetWorker().getTemplateList(token, keytype, page);
		}
	}
	
	@Override
	protected boolean onKeyBack() {
		finish(R.anim.none, R.anim.right_out);
		return super.onKeyBack();
	}
}
