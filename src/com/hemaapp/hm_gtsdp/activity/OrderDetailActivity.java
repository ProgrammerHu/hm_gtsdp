package com.hemaapp.hm_gtsdp.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpActivity;
import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_gtsdp.adapter.FindGoodsImageAdapter;
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
	private TextView txtTitle, txtNext;
	private ImageView imageQuitActivity;
	private MyGridView gridview; 

	private ArrayList<String> images;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_order_detail);
		super.onCreate(savedInstanceState);
		images = new ArrayList<String>();
		images.add("http://d.hiphotos.baidu.com/baike/w%3D268/sign=ce66e2ab940a304e5222a7fce9c9a7c3/ac6eddc451da81cb0aa215625166d016082431dc.jpg");
		images.add("http://d.hiphotos.baidu.com/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=78fb3c9ffbdcd100d991f07313e22c75/0eb30f2442a7d9338ba972e1ae4bd11373f0011a.jpg");
		images.add("http://d.hiphotos.baidu.com/baike/w%3D268/sign=ce66e2ab940a304e5222a7fce9c9a7c3/ac6eddc451da81cb0aa215625166d016082431dc.jpg");
		images.add("http://7tszkm.com1.z0.glb.clouddn.com/1.png");
		images.add("http://d.hiphotos.baidu.com/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=78fb3c9ffbdcd100d991f07313e22c75/0eb30f2442a7d9338ba972e1ae4bd11373f0011a.jpg");
		images.add("http://d.hiphotos.baidu.com/baike/w%3D268/sign=ce66e2ab940a304e5222a7fce9c9a7c3/ac6eddc451da81cb0aa215625166d016082431dc.jpg");
		images.add("http://7tszkm.com1.z0.glb.clouddn.com/1.png");
		images.add("http://d.hiphotos.baidu.com/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=78fb3c9ffbdcd100d991f07313e22c75/0eb30f2442a7d9338ba972e1ae4bd11373f0011a.jpg");
//		images.add("http://d.hiphotos.baidu.com/baike/w%3D268/sign=ce66e2ab940a304e5222a7fce9c9a7c3/ac6eddc451da81cb0aa215625166d016082431dc.jpg");
//		images.add("http://7tszkm.com1.z0.glb.clouddn.com/1.png");
		gridview.setAdapter(new FindGoodsImageAdapter(mContext, findViewById(R.id.father), images, gridview));
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
		//TODO
		cancelProgressDialog();
		showTextDialog(baseResult.getMsg());
		
	}

	@Override
	protected void callBeforeDataBack(HemaNetTask arg0) {
	}

	@Override
	protected void findView() {
		txtTitle = (TextView)findViewById(R.id.txtTitle);
		txtTitle.setText("����");
		txtNext = (TextView)findViewById(R.id.txtNext);
		txtNext.setVisibility(View.GONE);
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
