package com.hemaapp.hm_gtsdp.adapter;

import java.util.List;
import java.util.zip.Inflater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hemaapp.hm_gtsdp.GtsdpAdapter;
import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_gtsdp.model.OrderModel;

public class OrdersListAdapter extends GtsdpAdapter {
	private List<OrderModel> listData;

	public OrdersListAdapter(Context mContext, List<OrderModel> listData) {
		super(mContext);
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
		if(listData == null)
			return null;
		return listData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void changeListData(List<OrderModel> listData)
	{
		this.listData = listData;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null)
		{
			convertView = LayoutInflater.from(mContext).inflate(R.layout.listitem_orders, null);
		}
		ViewHolder holder = new ViewHolder();
		holder.txtId = (TextView)convertView.findViewById(R.id.txtId);
		holder.txtPosition = (TextView)convertView.findViewById(R.id.txtPosition);
		holder.txtDatetime = (TextView)convertView.findViewById(R.id.txtDatetime);
		SetData(position, holder);
		return convertView;
	}

	private class ViewHolder
	{
		public TextView txtId;
		public TextView txtPosition;
		public TextView txtDatetime;
	}
	private void SetData(int position, ViewHolder holder)
	{
		holder.txtId.setText("订单号："+listData.get(position).getOrderId());
		holder.txtPosition.setText("当前位置："+listData.get(position).getOrderPosition());
		holder.txtDatetime.setText(listData.get(position).getOrderDatetime());
		
	}
}
