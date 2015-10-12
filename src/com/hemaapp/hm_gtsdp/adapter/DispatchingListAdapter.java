package com.hemaapp.hm_gtsdp.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amap.api.mapcore.util.o;
import com.hemaapp.hm_gtsdp.GtsdpAdapter;
import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_gtsdp.model.OrderModel;

/**
 * 配送订单列表适配器
 * @author Wen
 * @author HuFanglin
 *
 */
public class DispatchingListAdapter extends GtsdpAdapter{
	private List<OrderModel> listDatas;
	public DispatchingListAdapter(Context mContext, List<OrderModel> listDatas) {
		super(mContext);
		this.listDatas = listDatas;
	}

	@Override
	public int getCount() {
		if(listDatas == null)
			return 0;
		else
			return listDatas.size();
	}

	@Override
	public Object getItem(int position) {
		if(listDatas == null)
			return 0;
		else
			return listDatas.get(position);
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
			convertView = LayoutInflater.from(mContext).inflate(R.layout.listitem_dispatching, null);
		}
		ViewHolder holder = new ViewHolder();
		holder.txtOrderNumber = (TextView)convertView.findViewById(R.id.txtOrderNumber);
		holder.txtStart = (TextView)convertView.findViewById(R.id.txtStart);
		holder.txtEnd = (TextView)convertView.findViewById(R.id.txtEnd);
		holder.txtDatetime = (TextView)convertView.findViewById(R.id.txtDatetime);
		holder.txtPrice = (TextView)convertView.findViewById(R.id.txtPrice);
		OrderModel order = listDatas.get(position);
		holder.txtOrderNumber.setText("订单号：" + order.getTrade_no());
		holder.txtStart.setText("出发城市："+order.getSender_address());
		holder.txtEnd.setText("到达城市："+order.getReceiver_address());
		holder.txtDatetime.setText(order.getRegdate());
		holder.txtPrice.setText("￥"+order.getTotal_fee());
		return convertView;
	}
	
	private class ViewHolder
	{
		public TextView txtOrderNumber;
		public TextView txtStart;
		public TextView txtEnd;
		public TextView txtDatetime;
		public TextView txtPrice;
	}
	
	public void ChangeListData(List<OrderModel> listDatas)
	{
		this.listDatas = listDatas;
	}

}
