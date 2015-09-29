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
	 * ��ʼ���˵�
	 * @param context Activity ����
	 * @param itemsOnClick �����ť�Ĳ����¼�
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
	/**
	 * ��ʼ���˵����ײ���ť��ʾ��ȡ�������ɱ䣩
	 * @param context Activity ����
	 * @param itemsOnClick �����ť�Ĳ����¼�
	 * @param txtTopButton ������ť��ʾ�ַ���
	 * @param txtMiddleButton �м䰴ť��ʾ�ַ���
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
