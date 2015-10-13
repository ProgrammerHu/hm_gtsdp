package com.hemaapp.hm_gtsdp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaArrayResult;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpActivity;
import com.hemaapp.hm_gtsdp.GtsdpHttpInformation;
import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_gtsdp.db.UserDBHelper;
import com.hemaapp.hm_gtsdp.dialog.GtsdptOneButtonDialog;
import com.hemaapp.hm_gtsdp.dialog.GtsdptOneButtonDialog.OnButtonListener;
import com.hemaapp.hm_gtsdp.model.User;
/**
 * 支付宝提现
 * @author Wen
 * @author HuFanglin
 *
 */
public class AlipayIncashActivity extends GtsdpActivity implements OnClickListener{
	private final int INPUT_ALIPAY_ACCOUNT = 100;
	private ImageView imageQuitActivity;
	private View layoutAlipay, layoutDirection;
	private TextView txtBalance, txtAlipayAccount;
	private EditText editCount, editPwd;
	private Button btnConfirm;
	private GtsdptOneButtonDialog dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_alipay_incash);
		super.onCreate(savedInstanceState);
		dialog = new GtsdptOneButtonDialog(mContext);
		dialog.setRightButtonText("确定");
		dialog.setButtonListener(new OnButtonListener() {
			@Override
			public void onRightButtonClick(GtsdptOneButtonDialog gtsdptOneButtonDialog) {
				dialog.cancel();
			}
		});
		dialog.cancel();
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
		cancelProgressDialog();
		showTextDialog(arg1.getMsg());
	}

	@Override
	protected void callBackForServerSuccess(HemaNetTask netTask,
			HemaBaseResult baseResult) {
		GtsdpHttpInformation information = (GtsdpHttpInformation)netTask.getHttpInformation();
		switch (information) {
		case ALI_SAVE:
			getNetWorker().clientGet(getApplicationContext().getUser().getToken(), getApplicationContext().getUser().getId());
			break;
		case CLIENT_GET:
			cancelProgressDialog();
			User user = ((HemaArrayResult<User>)baseResult).getObjects().get(0);
			new UserDBHelper(mContext).update(user);
			txtAlipayAccount.setText(user.getAliuser());
			break;
		}
		
		
	}

	@Override
	protected void callBeforeDataBack(HemaNetTask arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void findView() {
		imageQuitActivity = (ImageView)findViewById(R.id.imageQuitActivity);
		((TextView)findViewById(R.id.txtTitle)).setText("支付宝提现");
		findViewById(R.id.txtNext).setVisibility(View.INVISIBLE);
		layoutAlipay = findViewById(R.id.layoutAlipay);
		layoutDirection = findViewById(R.id.layoutDirection);
		txtBalance = (TextView)findViewById(R.id.txtBalance);
		txtAlipayAccount = (TextView)findViewById(R.id.txtAlipayAccount);
		txtAlipayAccount.setText(getApplicationContext().getUser().getAliuser());
		editCount = (EditText)findViewById(R.id.editCount);
		editPwd = (EditText)findViewById(R.id.editPwd);
		btnConfirm = (Button)findViewById(R.id.btnConfirm);
		
	}

	@Override
	protected void getExras() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setListener() {
		layoutAlipay.setOnClickListener(this);
		imageQuitActivity.setOnClickListener(this);
		btnConfirm.setOnClickListener(this);
		layoutDirection.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imageQuitActivity:
			finish(R.anim.none, R.anim.right_out);
			break;
		case R.id.layoutAlipay:
		{
			Intent intent = new Intent(AlipayIncashActivity.this, InputActivity.class);
			intent.putExtra("Title", "支付宝账号");
			intent.putExtra("Next", "确定");
			intent.putExtra("InputType", InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);//邮箱类型
			intent.putExtra("InputHint", "输入支付宝账号");
			intent.putExtra("Text", txtAlipayAccount.getText().toString().trim());
			startActivityForResult(intent, INPUT_ALIPAY_ACCOUNT);
			overridePendingTransition(R.anim.right_in, R.anim.none);
		}
			break;
		case R.id.btnConfirm:
			clickConfirm();
			break;
		case R.id.layoutDirection:
		{
			Intent intent = new Intent(this, WebViewActivity.class);
			intent.putExtra("Title", "钱包使用说明");
			startActivity(intent);
			overridePendingTransition(R.anim.right_in, R.anim.none);
		}
			break;
		}
	}

	/**
	 * 点击提交申请
	 */
	private void clickConfirm()
	{
		if("".equals(txtAlipayAccount.getText()))
		{
			dialog.setText("请输入支付宝账户");
			dialog.show();
			return;
		}
		String strCount = editCount.getEditableText().toString();
		if("".equals(strCount))
		{
			dialog.setText("请输入提现金额");
			dialog.show();
			return;
		}
		try
		{
			int count = Integer.parseInt(strCount);
			if(count % 100 != 0)
			{
				dialog.setText("提现金额需为100的整数倍");
				dialog.show();
			}
		}
		catch(Exception e)
		{
			dialog.setText("请输入正确的提现金额");
			dialog.show();
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
		if(resultCode == RESULT_OK && requestCode == INPUT_ALIPAY_ACCOUNT)
		{
			String Account = data.getStringExtra("Result");
			if(!"".equals(Account))
			{
				showProgressDialog("加载中");
				getNetWorker().aliSave(getApplicationContext().getUser().getToken(), Account);
			}
		}
	}
}
