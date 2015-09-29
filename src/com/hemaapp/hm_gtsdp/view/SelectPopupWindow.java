package com.hemaapp.hm_gtsdp.view;


import com.hemaapp.hm_gtsdp.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;

public class SelectPopupWindow extends PopupWindow {
	private Button btnTop, btnMiddle, btnCancel;
	private View mMenuView;
	
	/**
	 * 初始化菜单
	 * @param context Activity 名字
	 * @param itemsOnClick 点击按钮的补充事件
	 */
	public SelectPopupWindow(Activity context,OnClickListener itemsOnClick)
	{
		super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
        mMenuView = inflater.inflate(R.layout.popupwindow_three, null);  
        btnTop = (Button)mMenuView.findViewById(R.id.btnTop);
        btnMiddle = (Button)mMenuView.findViewById(R.id.btnMiddle);
        btnCancel = (Button)mMenuView.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
        btnTop.setOnClickListener(itemsOnClick);
        btnMiddle.setOnClickListener(itemsOnClick);
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
	/**
	 * 初始化菜单（底部按钮显示“取消”不可变）
	 * @param context Activity 名字
	 * @param itemsOnClick 点击按钮的补充事件
	 * @param txtTopButton 顶部按钮显示字符串
	 * @param txtMiddleButton 中间按钮显示字符串
	 */
	public SelectPopupWindow(Activity context,OnClickListener itemsOnClick, 
			String txtTopButton, String txtMiddleButton)
	{
		this(context, itemsOnClick);
		btnTop.setText(txtTopButton);
		btnMiddle.setText(txtMiddleButton);
	}
	@Override
	public void dismiss() {
		super.dismiss();
	}

}
