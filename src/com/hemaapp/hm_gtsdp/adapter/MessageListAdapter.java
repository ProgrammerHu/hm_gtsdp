package com.hemaapp.hm_gtsdp.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hemaapp.hm_gtsdp.GtsdpActivity;
import com.hemaapp.hm_gtsdp.GtsdpAdapter;
import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_gtsdp.model.NoticeModel;
import com.hemaapp.hm_gtsdp.view.SlideView;
import com.hemaapp.hm_gtsdp.view.SlideView.OnSlideListener;

public class MessageListAdapter extends GtsdpAdapter  {
	private OnSlideListener mOnSlideListener;
	private List<NoticeModel> listData;
	private GtsdpActivity mContext;
	
	public MessageListAdapter(Context mContext, OnSlideListener mOnSlideListener, List<NoticeModel> listData) {
		super(mContext);
		this.mContext = (GtsdpActivity)mContext;
		this.mOnSlideListener = mOnSlideListener;
		this.listData = listData;
	}

	@Override
	public int getCount() {
		if(listData == null)
			return 0;
		return listData.size();
	}

	@Override
	public Object getItem(int position) {
		return listData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		SlideView slideView = (SlideView)convertView;
        ViewHolder holder;
		if(slideView == null)
		{
			View itemView =  LayoutInflater.from(mContext).inflate(R.layout.listitem_message, null);
			slideView = new SlideView(mContext);
            slideView.setContentView(itemView);
            
		}

        holder = new ViewHolder(slideView);
        slideView.setOnSlideListener(mOnSlideListener);
        slideView.setTag(holder);
        listData.get(position).slideView = slideView;
		holder.imageLogo = (ImageView)slideView.findViewById(R.id.imageLogo);
		holder.txtMsgTitle = (TextView)slideView.findViewById(R.id.txtMsgTitle);
		holder.txtMsgDetail = (TextView)slideView.findViewById(R.id.txtMsgDetail);
		holder.txtTimeDiff = (TextView)slideView.findViewById(R.id.txtTimeDiff);
		holder.layoutHolder = (LinearLayout)slideView.findViewById(R.id.layoutHolder);
		holder.father = (LinearLayout)slideView.findViewById(R.id.father);
        setData(position, holder);
		return slideView;
	}

	
	private class ViewHolder
	{
		public ViewHolder(SlideView slideView)
		{
			this.slideView = slideView;
		}
		public TextView txtMsgTitle;
		public TextView txtMsgDetail;
		public TextView txtTimeDiff;
		public ImageView imageLogo;
		public SlideView slideView;
		public LinearLayout layoutHolder;
		public LinearLayout father;
	}
	private void setData(final int position, final ViewHolder holder)
	{
		holder.txtMsgTitle.setText("系统消息");
		holder.txtMsgDetail.setText(listData.get(position).getContent());
		holder.txtTimeDiff.setText("7分钟前");
		if(listData.get(position).getLooktype().equals("1"))
			holder.imageLogo.setImageResource(R.drawable.message_item_logo_red);
		else
			holder.imageLogo.setImageResource(R.drawable.message_item_logo);
		
		holder.layoutHolder.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				listData.remove(position);
				notifyDataSetChanged();
				holder.slideView.scrollTo(0, 0);
			}
		});
	}
	
	public void changeData(List<NoticeModel> listData)
	{
		this.listData = listData;
	}

}
