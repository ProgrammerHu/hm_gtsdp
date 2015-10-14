package com.hemaapp.hm_gtsdp.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpActivity;
import com.hemaapp.hm_gtsdp.GtsdpHttpInformation;
import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_gtsdp.adapter.FindGoodsImageAdapter;
import com.hemaapp.hm_gtsdp.model.ImageItem;
import com.hemaapp.hm_gtsdp.model.OrderModel;
import com.hemaapp.hm_gtsdp.result.TransDetailResult;
import com.hemaapp.hm_gtsdp.view.MyGridView;
/**
 * �����������
 * @author Wen
 * @author HuFanglin
 *
 */
public class OrderDetailActivity extends GtsdpActivity{
	private String keytype;/* ҵ������ 1:�Ӷ����б��н�������; 2:�����û�ɨ����������; 3:�ջ���ɨ����������;  */
	private String keyid;//����id ��keytype=1ʱ��keyid=�Ӵ�id; ��keytype=2��3ʱ��keyid=��ά����Ϣ; 
	private TextView txtTitle, txtNext, txtOrderNumber, txtPosition, txtDatetime, txtCount, txtReciverName, txtReciverAddress, txtReciverPhone,
	txtSenderName, txtSenderAddress, txtSenderPhone;
	private ImageView imageQuitActivity;
	private MyGridView gridview; 

	private ArrayList<String> images;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_order_detail);
		super.onCreate(savedInstanceState);
		if(keytype == null || keytype.equals(""))
		{
			showTextDialog("�����������");
		}
		else
		{
			getNetWorker().getTransDetail(getApplicationContext().getUser().getToken(), keytype, keyid);
			showProgressDialog("������");
		}
		
	}

	@Override
	protected void callAfterDataBack(HemaNetTask arg0) {
	}

	@Override
	protected void callBackForGetDataFailed(HemaNetTask arg0, int arg1) {
	}

	@Override
	protected void callBackForServerFailed(HemaNetTask netTask, HemaBaseResult baseResult) {
		// TODO Auto-generated method stub
		cancelProgressDialog();
		showTextDialog(baseResult.getMsg());
	}

	@Override
	protected void callBackForServerSuccess(HemaNetTask netTask,
			HemaBaseResult baseResult) {
		GtsdpHttpInformation information = (GtsdpHttpInformation)netTask.getHttpInformation();
		switch (information) {
		case TRANS_GET:
			cancelProgressDialog();
			TransDetailResult result = (TransDetailResult) baseResult;
			OrderModel order = result.getInfo();
			txtOrderNumber.setText("������:" + order.getTrade_no());
			txtPosition.setText("��ǰλ��:" + order.getCurrent_address());
			txtDatetime.setText(order.getRegdate());
			txtCount.setText(order.getTotal_fee() + "Ԫ");
			txtReciverName.setText(order.getReceiver_name());
			txtReciverAddress.setText(order.getReceiver_address());
			txtReciverPhone.setText(order.getReceiver_telphone());
			txtSenderName.setText(order.getSender_name());
			txtSenderAddress.setText(order.getSender_address());
			txtSenderPhone.setText(order.getSender_telphone());
			
			List<ImageItem>imageList = order.getImageItems();
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
		default:
			break;
		}
	}

	@Override
	protected void callBeforeDataBack(HemaNetTask arg0) {
	}

	@Override
	protected void findView() {
		txtTitle = (TextView)findViewById(R.id.txtTitle);
		txtTitle.setText("����");
		txtNext = (TextView) findViewById(R.id.txtNext);
		txtNext.setVisibility(View.GONE);
		txtOrderNumber = (TextView) findViewById(R.id.txtOrderNumber);
		txtPosition = (TextView) findViewById(R.id.txtPosition);
		txtDatetime = (TextView) findViewById(R.id.txtDatetime);
		txtCount = (TextView) findViewById(R.id.txtCount);
		txtReciverName = (TextView) findViewById(R.id.txtReciverName);
		txtReciverAddress = (TextView) findViewById(R.id.txtReciverAddress);
		txtReciverPhone = (TextView) findViewById(R.id.txtReciverPhone);
		txtSenderName = (TextView) findViewById(R.id.txtSenderName);
		txtSenderAddress = (TextView) findViewById(R.id.txtSenderAddress);
		txtSenderPhone = (TextView) findViewById(R.id.txtSenderPhone);
		
		imageQuitActivity = (ImageView)findViewById(R.id.imageQuitActivity);
		gridview = (MyGridView)findViewById(R.id.gridview);
	}

	@Override
	protected void getExras() {
		Intent before = getIntent();
		keytype = before.getStringExtra("keytype");
		keyid = before.getStringExtra("keyid");
	}

	@Override
	protected void setListener() {
		imageQuitActivity.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish(R.anim.none, R.anim.right_out);
			}
		});
	}
	
	@Override
	protected boolean onKeyBack() {
		finish(R.anim.none, R.anim.right_out);
		return super.onKeyBack();
	}

}
