package com.hemaapp.hm_gtsdp.activity;

import android.content.Intent;
import android.os.Bundle;
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
 * 银行卡提现
 * @author Wen
 * @author HuFanglin
 *
 */
public class ToCashActivity extends GtsdpActivity implements OnClickListener{
	private final int SELECT_BANK = 100;
	
	private ImageView imageQuitActivity;
	private View layoutBank, layoutDirection;
	private TextView txtBalance, txtBank;
	private EditText editCount, editPwd;
	private Button btnConfirm;
	private GtsdptOneButtonDialog dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_tocash);
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
	protected void callBackForServerFailed(HemaNetTask netTask, HemaBaseResult baseResult) {
		cancelProgressDialog();
		showTextDialog(baseResult.getMsg());
		
	}

	@Override
	protected void callBackForServerSuccess(HemaNetTask netTask,
			HemaBaseResult baseResult) {
		cancelProgressDialog();
		GtsdpHttpInformation infomation = (GtsdpHttpInformation)netTask.getHttpInformation();
		switch (infomation) {
		case CLIENT_GET:
			User user = ((HemaArrayResult<User>)baseResult).getObjects().get(0);
			new UserDBHelper(mContext).update(user);
			txtBank.setText(user.getBankname());
			break;
		case CASH_ADD:
			
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
		((TextView)findViewById(R.id.txtTitle)).setText("银行卡提现");
		findViewById(R.id.txtNext).setVisibility(View.INVISIBLE);
		layoutBank = findViewById(R.id.layoutBank);
		layoutDirection = findViewById(R.id.layoutDirection);
		txtBalance = (TextView)findViewById(R.id.txtBalance);
		editCount = (EditText)findViewById(R.id.editCount);
		editPwd = (EditText)findViewById(R.id.editPwd);
		btnConfirm = (Button)findViewById(R.id.btnConfirm);
		txtBank = (TextView)findViewById(R.id.txtBank);
		txtBank.setText(getApplicationContext().getUser().getBankname());
	}

	@Override
	protected void getExras() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setListener() {
		layoutBank.setOnClickListener(this);
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
		case R.id.layoutBank:
		{
			Intent intent = new Intent(ToCashActivity.this, SelectBankActivity.class);
			startActivityForResult(intent, SELECT_BANK);
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
		String paypassword = editPwd.getEditableText().toString();
		if("".equals(txtBank.getText()))
		{
			dialog.setText("绑定银行卡");
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
				return;
			}
		}
		catch(Exception e)
		{
			dialog.setText("请输入正确的提现金额");
			dialog.show();
			return;
		}
		if(paypassword == null || "".equals(paypassword))
		{
			dialog.setText("请输入提现密码");
			dialog.show();
			return;
		}
		getNetWorker().cashAdd(getApplicationContext().getUser().getToken(), "2", strCount, paypassword);
		showProgressDialog("提交中");
	}
	
	@Override
	protected boolean onKeyBack() {
		finish(R.anim.none, R.anim.right_out);
		return super.onKeyBack();
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == SELECT_BANK && resultCode == RESULT_OK)
		{
			showProgressDialog("加载中");
			getNetWorker().clientGet(getApplicationContext().getUser().getToken(), 
					getApplicationContext().getUser().getId());//保存成功之后需要更新用户数据
			
		}
	}

}
