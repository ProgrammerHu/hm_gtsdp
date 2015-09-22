package com.hemaapp.hm_gtsdp.activity;

import java.util.ArrayList;
import java.util.List;

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

import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpActivity;
import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_gtsdp.adapter.TemplateAddressAdpater;
import com.hemaapp.hm_gtsdp.model.ContactsTemplateModel;

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
	private ListView templateList;
	private TemplateAddressAdpater addressAdapter;
	private TextView txtTitle, txtNext, txtAdd;
	private ImageView imageQuitActivity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_template_list);
		super.onCreate(savedInstanceState);
		setListViewData();
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
		layoutAddTemplate = (LinearLayout)findViewById(R.id.layoutAddTemplate);
		templateList = (ListView)findViewById(R.id.templateList);
		txtTitle = (TextView)findViewById(R.id.txtTitle);
		txtTitle.setText("模板");
		txtNext = (TextView)findViewById(R.id.txtNext);
		txtNext.setVisibility(View.INVISIBLE);
		imageQuitActivity = (ImageView)findViewById(R.id.imageQuitActivity);
		txtAdd = (TextView)findViewById(R.id.txtAdd);
		if(ActivityType == SENDER)
		{
			txtAdd.setText("添加发件人模板");
		}
		else if(ActivityType == RECIVER)
		{
			txtAdd.setText("添加收件人模板");
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
				addressAdapter.selectItem(position);
				addressAdapter.notifyDataSetChanged();
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
		case R.id.imageQuitActivity:
			finish();
			break;
		case R.id.layoutAddTemplate:
			Intent intent = new Intent(TemplateListActivty.this, TemplateEditActivty.class);
			intent.putExtra("ActivityType", ActivityType);
			startActivity(intent);
			break;
		}
		
	}
	/**
	 * 设置ListView数据
	 */

	private void setListViewData()
	{
		List<ContactsTemplateModel> dataList = new ArrayList<ContactsTemplateModel>();
		ContactsTemplateModel model = new ContactsTemplateModel("陈程程","山东省济南市","18215968686qq");
		dataList.add(model);model = new ContactsTemplateModel("陈程程","山东省济南市","18215968686qq");
		dataList.add(model);model = new ContactsTemplateModel("陈程程","山东省济南市","18215968686qq");
		dataList.add(model);model = new ContactsTemplateModel("陈程程","山东省济南市","18215968686qq");
		dataList.add(model);model = new ContactsTemplateModel("陈程程","山东省济南市","18215968686qq");
		dataList.add(model);
		addressAdapter = new TemplateAddressAdpater(TemplateListActivty.this, dataList, ActivityType);
		templateList.setAdapter(addressAdapter);
		
//		以下是为了让Listview适应scrollView，暂时去掉
//		View listView = addressAdapter.getView(0, null, templateList);
//		int w = View.MeasureSpec.makeMeasureSpec(0,
//                View.MeasureSpec.UNSPECIFIED);
//        int h = View.MeasureSpec.makeMeasureSpec(0,
//                View.MeasureSpec.UNSPECIFIED);
//        listView.measure(w, h);
//        int itemHeight = listView.getMeasuredHeight();
//        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)templateList.getLayoutParams();
//        params.height = itemHeight * addressAdapter.getCount();
//        templateList.setLayoutParams(params);

	}
}
