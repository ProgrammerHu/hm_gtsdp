package com.hemaapp.hm_gtsdp.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hemaapp.hm_gtsdp.GtsdpAdapter;
import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_gtsdp.model.TransactionModel;

public class TransactionListAdapter extends GtsdpAdapter {
	private final int Green = Color.rgb(75, 211, 191);
	private final int Orange = Color.rgb(244, 212, 77);
	private List<TransactionModel> listData;
	
	public TransactionListAdapter(Context mContext, List<TransactionModel> listData) {
		super(mContext);
		this.listData = listData;
	}

	@Override
	public int getCount() {
		if(listData == null)
			return 0;
		else
			return listData.size();
	}

	@Override
	public Object getItem(int position) {

		if(listData == null)
			return null;
		else
			return listData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null)
		{
			convertView = LayoutInflater.from(mContext).inflate(R.layout.listitem_transaction, null);
		}
		ViewHolder holder = new ViewHolder();
		holder.txtCount = (TextView)convertView.findViewById(R.id.txtCount);
		holder.txtTitle = (TextView)convertView.findViewById(R.id.txtTitle);
		holder.txtDatetime = (TextView)convertView.findViewById(R.id.txtDatetime);
		setData(position, holder);
		return convertView;
	}
	

	private class ViewHolder
	{
		public TextView txtTitle;
		public TextView txtCount;
		public TextView txtDatetime;
	}
	
	private void setData(int position, ViewHolder holder)
	{
		holder.txtTitle.setText(listData.get(position).getTitle());
		holder.txtDatetime.setText(listData.get(position).getDatetime());
		if(listData.get(position).getIsIncome())
		{//收入项
			holder.txtCount.setTextColor(Orange);
			holder.txtCount.setText("+￥" + listData.get(position).getCount());
		}
		else
		{//支出项
			holder.txtCount.setTextColor(Green);
			holder.txtCount.setText("-￥" + listData.get(position).getCount());
		}
	}

}
