package com.hemaapp.hm_gtsdp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpActivity;
import com.hemaapp.hm_gtsdp.R;

/**
 * 选择银行账号
 * @author Wen
 * @author HuFanglin
 *
 */
public class SelectBankActivity extends GtsdpActivity implements OnClickListener {
	private final int SELECT_BANK = 100;
	private final int INPUT_USERNAME = 200;
	private final int INPUT_CARD_ID = 300;
	private final int INPUT_BANK_DETAIL = 400;
	
	private ImageView imageQuitActivity;
	private TextView txtTitle, txtNext;
	private View layoutBank, layoutUserName, layoutCardId, layoutBankDetail;
	private TextView txtBank, txtUserName, txtCardId, txtBankDetail;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_select_bank);
		super.onCreate(savedInstanceState);
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
		imageQuitActivity = (ImageView)findViewById(R.id.imageQuitActivity);
		txtTitle = (TextView)findViewById(R.id.txtTitle);
		txtTitle.setText("银行卡账号");
		txtNext = (TextView)findViewById(R.id.txtNext);
		txtNext.setText("确定");
		layoutBank = findViewById(R.id.layoutBank);
		layoutUserName = findViewById(R.id.layoutUserName);
		layoutCardId = findViewById(R.id.layoutCardId);
		layoutBankDetail = findViewById(R.id.layoutBankDetail);
		txtBank = (TextView)findViewById(R.id.txtBank);
		txtUserName = (TextView)findViewById(R.id.txtUserName);
		txtCardId = (TextView)findViewById(R.id.txtCardId);
		txtBankDetail = (TextView)findViewById(R.id.txtBankDetail);
	}

	@Override
	protected void getExras() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setListener() {
		imageQuitActivity.setOnClickListener(this);
		txtNext.setOnClickListener(this);
		layoutBank.setOnClickListener(this);
		layoutUserName.setOnClickListener(this);
		layoutCardId.setOnClickListener(this);
		layoutBankDetail.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		switch(v.getId())
		{
		case R.id.imageQuitActivity:
			finish(R.anim.none, R.anim.right_out);
			break;
		case R.id.layoutUserName:
			intent = new Intent(SelectBankActivity.this, InputActivity.class);
			intent.putExtra("Title", "户主姓名");
			intent.putExtra("Next", "确定");
			intent.putExtra("InputType", InputType.TYPE_CLASS_TEXT);
			intent.putExtra("InputHint", "输入姓名");
			startActivityForResult(intent, INPUT_USERNAME);
			overridePendingTransition(R.anim.right_in, R.anim.none);
			break;
		case R.id.layoutCardId:
			intent = new Intent(SelectBankActivity.this, InputActivity.class);
			intent.putExtra("Title", "银行账号");
			intent.putExtra("Next", "确定");
			intent.putExtra("InputType", InputType.TYPE_CLASS_NUMBER);
			intent.putExtra("InputHint", "银行账号");
			startActivityForResult(intent, INPUT_CARD_ID);
			overridePendingTransition(R.anim.right_in, R.anim.none);
			break;
		case R.id.layoutBankDetail:
			intent = new Intent(SelectBankActivity.this, InputActivity.class);
			intent.putExtra("Title", "开户行名称");
			intent.putExtra("Next", "确定");
			intent.putExtra("InputType", InputType.TYPE_CLASS_TEXT);
			intent.putExtra("InputHint", "开户行名称");
			startActivityForResult(intent, INPUT_BANK_DETAIL);
			overridePendingTransition(R.anim.right_in, R.anim.none);
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
		if(resultCode == RESULT_OK)
		{
			String result = data.getStringExtra("Result");
			switch(requestCode)
			{
			case INPUT_USERNAME:
				txtUserName.setText(result);
				break;
			case INPUT_CARD_ID:
				txtCardId.setText(result);
				break;
			case INPUT_BANK_DETAIL:
				txtBankDetail.setText(result);
				break;
			}
		}
	}
}
