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
        
      //设置SelectPicPopupWindow的View  
        this.setContentView(mMenuView);  
        //设置SelectPicPopupWindow弹出窗体的宽  
        this.setWidth(LayoutParams.MATCH_PARENT);  
        //设置SelectPicPopupWindow弹出窗体的高  
        this.setHeight(LayoutParams.WRAP_CONTENT);  
        //设置SelectPicPopupWindow弹出窗体可点击  
        this.setFocusable(true);  
        //设置SelectPicPopupWindow弹出窗体动画效果  
        this.setAnimationStyle(R.style.AnimationBottomDialog);  
        //实例化一个ColorDrawable颜色为半透明  
        ColorDrawable dw = new ColorDrawable(0x90000000);  
        //设置SelectPicPopupWindow弹出窗体的背景  
        this.setBackgroundDrawable(dw);  
	}

	
	@Override
	public void dismiss() {
		super.dismiss();
	}
}
