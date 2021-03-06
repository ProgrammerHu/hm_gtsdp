package com.hemaapp.hm_gtsdp.activity;

import xtom.frame.util.XtomSharedPreferencesUtil;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpActivity;
import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_gtsdp.view.SelectDistrictPicker;

/**
 * 发布路线界面
 * @author Wen
 * @author HuFanglin
 *
 */
public class PublishRouteActivity extends GtsdpActivity implements OnClickListener{
	private TextView txtTitle, txtNext, txtStart, txtEnd, TempTextView;
	private ImageView imageQuitActivity;
	private Button btnConfirm;
	private RelativeLayout layoutEnd, layoutStart;
	private SelectDistrictPicker myDistrictPicker;
	private String StartStationLat, StartStationLog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_publishroute);
		super.onCreate(savedInstanceState);
		if(myDistrictPicker == null)
		{
			myDistrictPicker = new SelectDistrictPicker(mContext);
			myDistrictPicker.setOnClickListener(clickReciver);
		}
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
		txtTitle.setText("发布路线");
		txtNext = (TextView)findViewById(R.id.txtNext);
		txtNext.setVisibility(View.INVISIBLE);
		imageQuitActivity = (ImageView)findViewById(R.id.imageQuitActivity);
		btnConfirm = (Button)findViewById(R.id.btnConfirm);
		layoutEnd = (RelativeLayout)findViewById(R.id.layoutEnd);
		layoutStart = (RelativeLayout)findViewById(R.id.layoutStart);
		txtStart = (TextView)findViewById(R.id.txtStart);
		String Start = XtomSharedPreferencesUtil.get(mContext, "Start");
		if(Start != null && !Start.equals(""))
			txtStart.setText(Start);
		txtEnd = (TextView)findViewById(R.id.txtEnd);
		String End = XtomSharedPreferencesUtil.get(mContext, "End");
		if(End != null && !End.equals(""))
			txtEnd.setText(End);
	}

	@Override
	protected void getExras() {
	}

	@Override
	protected void setListener() {
		imageQuitActivity.setOnClickListener(this);
		btnConfirm.setOnClickListener(this);
		layoutStart.setOnClickListener(this);
		layoutEnd.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
		case R.id.imageQuitActivity:
			setResult(RESULT_CANCELED);
			finish(R.anim.none, R.anim.right_out);
			break;
		case R.id.btnConfirm:
			clickConfirm();
			break;
		case R.id.layoutStart:
			TempTextView = txtStart;
			myDistrictPicker.showAtLocation(PublishRouteActivity.this.findViewById(R.id.father), 
					Gravity.BOTTOM, 0, 0);
			break;
		case R.id.layoutEnd:
			TempTextView = txtEnd;
			myDistrictPicker.showAtLocation(PublishRouteActivity.this.findViewById(R.id.father), 
					Gravity.BOTTOM, 0, 0);
			break;
		}
	}
	@Override
	protected boolean onKeyBack() {
		setResult(RESULT_CANCELED);
		finish(R.anim.none, R.anim.right_out);
		return super.onKeyBack();
	}

	/**
	 * 点击收件人地址
	 */
	private OnClickListener clickReciver = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if(v.getId() == R.id.btnDistrictPickerCancel)
			{
				myDistrictPicker.dismiss();
				return;
			}
			if(v.getId() == R.id.btnDistrictPickerClose)
			{
				String address = myDistrictPicker.getDistrcictString();
				TempTextView.setText(address);
				if(TempTextView.equals(txtStart))
				{
					StartStationLog = myDistrictPicker.getLog();
					StartStationLat = myDistrictPicker.getLat();
				}
			}
			myDistrictPicker.dismiss();
		}
	};
	/**
	 * 点击发布
	 */
	private void clickConfirm()
	{
		String Start = txtStart.getText().toString();
		String End = txtEnd.getText().toString();
		if("".equals(Start))
		{
			showTextDialog("请选择出发地");
			return;
		}
		if("".equals(End))
		{
			showTextDialog("请选择到达地");
			return;
		}
		XtomSharedPreferencesUtil.save(mContext, "Start", Start);
		XtomSharedPreferencesUtil.save(mContext, "End", End);
		XtomSharedPreferencesUtil.save(mContext, "StartStationLog", StartStationLog);
		XtomSharedPreferencesUtil.save(mContext, "StartStationLat", StartStationLat);
		setResult(RESULT_OK);
		finish(R.anim.none, R.anim.right_out);
	}
}
