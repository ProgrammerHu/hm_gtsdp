package com.hemaapp.hm_gtsdp.dialog;

import com.hemaapp.hm_gtsdp.R;

import xtom.frame.XtomObject;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class GtsdpPasswordDialog extends XtomObject {
	private Dialog mDialog;
	private ViewGroup mContent;
	private EditText mTextView;
	private Button leftButton;
	private Button rightButton;
	private OnButtonListener buttonListener;

	public GtsdpPasswordDialog(Context context) {
		mDialog = new Dialog(context, R.style.dialog);
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.dialog_with_password, null);
		mContent = (ViewGroup) view.findViewById(R.id.content);
		mTextView = (EditText) view.findViewById(R.id.textview);
		leftButton = (Button) view.findViewById(R.id.left);
		leftButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (buttonListener != null)
					buttonListener.onLeftButtonClick(GtsdpPasswordDialog.this);
			}
		});
		rightButton = (Button) view.findViewById(R.id.right);
		rightButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (buttonListener != null)
					buttonListener.onRightButtonClick(GtsdpPasswordDialog.this);
			}
		});
		mDialog.setCancelable(false);
		mDialog.setContentView(view);
	}
	/**
	 * 设置是否可以取消
	 * @param cancelable
	 */
	public void setCancelable(boolean cancelable)
	{
		mDialog.setCancelable(cancelable);
	}
	/**
	 * 给弹框添加自定义View
	 * 
	 * @param v
	 *            自定义View
	 */
	public void setView(View v) {
		mContent.removeAllViews();
		mContent.addView(v);
	}
	public void setLeftButtonText(String text) {
		leftButton.setText(text);
	}

	public void setLeftButtonText(int textID) {
		leftButton.setText(textID);
	}

	public void setRightButtonText(String text) {
		rightButton.setText(text);
	}

	public void setRightButtonText(int textID) {
		rightButton.setText(textID);
	}

	public void setRightButtonTextColor(int color) {
		rightButton.setTextColor(color);
	}

	public void show() {
		mDialog.show();
	}

	public void cancel() {
		mDialog.cancel();
	}

	public OnButtonListener getButtonListener() {
		return buttonListener;
	}

	public void setButtonListener(OnButtonListener buttonListener) {
		this.buttonListener = buttonListener;
	}
	public String getPassword()
	{
		return mTextView.getEditableText().toString();
	}
	public interface OnButtonListener {
		public void onLeftButtonClick(GtsdpPasswordDialog dialog);

		public void onRightButtonClick(GtsdpPasswordDialog dialog);
	}

}
