package com.hemaapp.hm_gtsdp.view;

import com.hemaapp.hm_gtsdp.R;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;

public class SharePopupWindow extends PopupWindow {
	private Button btnCancel;
	private View layoutQQ, layoutWechat, layoutQQZone, layoutWeibo;
	private View mMenuView;
	
	public SharePopupWindow(Context context, OnClickListener onClickListener)
	{
		super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
        mMenuView = inflater.inflate(R.layout.popupwindow_share, null);  
        layoutQQ = mMenuView.findViewById(R.id.layoutQQ);
        layoutWechat = mMenuView.findViewById(R.id.layoutWechat);
        layoutQQZone = mMenuView.findViewById(R.id.layoutQQZone);
        layoutWeibo = mMenuView.findViewById(R.id.layoutWeibo);
        btnCancel = (Button)mMenuView.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
        layoutQQ.setOnClickListener(onClickListener);
        layoutWechat.setOnClickListener(onClickListener);
        layoutQQZone.setOnClickListener(onClickListener);
        layoutWeibo.setOnClickListener(onClickListener);
        
      //����SelectPicPopupWindow��View  
        this.setContentView(mMenuView);  
        //����SelectPicPopupWindow��������Ŀ�  
        this.setWidth(LayoutParams.MATCH_PARENT);  
        //����SelectPicPopupWindow��������ĸ�  
        this.setHeight(LayoutParams.WRAP_CONTENT);  
        //����SelectPicPopupWindow��������ɵ��  
        this.setFocusable(true);  
        //����SelectPicPopupWindow�������嶯��Ч��  
        this.setAnimationStyle(R.style.AnimationBottomDialog);  
        //ʵ����һ��ColorDrawable��ɫΪ��͸��  
        ColorDrawable dw = new ColorDrawable(0x90000000);  
        //����SelectPicPopupWindow��������ı���  
        this.setBackgroundDrawable(dw);  
	}

	
	@Override
	public void dismiss() {
		super.dismiss();
	}
}
