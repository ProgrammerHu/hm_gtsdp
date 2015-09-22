package com.hemaapp.hm_gtsdp.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hemaapp.hm_gtsdp.GtsdpAdapter;
import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_gtsdp.model.AddressModel;

/**
 * 详细地址地图下面的列表
 * @author Wen
 * @author HuFanglin
 *
 */
public class SelectAddressAdapter extends GtsdpAdapter {
	private Context mContext;
	private List<AddressModel> listData;
	private int beforePosition = 0;//每次默认选择第0个

	public SelectAddressAdapter(Context mContext, List<AddressModel> listData) {
		super(mContext);
		this.mContext = mContext;
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
		return listData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(mContext == null || convertView == null)
		{
			convertView = LayoutInflater.from(mContext).inflate(R.layout.listitem_address_around, null);
		}
		ViewHolder holder = new ViewHolder();
		holder.txtAddressName = (TextView)convertView.findViewById(R.id.txtAddressName);
		holder.txtAddressDetail = (TextView)convertView.findViewById(R.id.txtAddressDetail);
		holder.imageCheck = (ImageView)convertView.findViewById(R.id.imageCheck);
		holder.imageHere = (ImageView)convertView.findViewById(R.id.imageHere);
		setData(position, convertView, holder);
		return convertView;
	}
	
	protected class ViewHolder
	{
		public TextView txtAddressName;
		public TextView txtAddressDetail;
		public ImageView imageCheck, imageHere;
	}

	protected void setData(int position, View convertView, ViewHolder holder) 
	{
		boolean isChecked = listData.get(position).IsChecked;
		if(position == 0)
		{
			holder.txtAddressName.setText(listData.get(position).getAddressName());
			holder.txtAddressDetail.setText(listData.get(position).getSnippet());
			holder.imageHere.setVisibility(View.VISIBLE);
		}
		else
		{
			holder.txtAddressName.setText(listData.get(position).getAddressName());
			String AddressDetail = listData.get(position).getCityName()
					+listData.get(position).getAdName()
					+listData.get(position).getSnippet()
					+listData.get(position).getAddressName();
			holder.txtAddressDetail.setText(AddressDetail);
			holder.imageHere.setVisibility(View.GONE);
		}
		if(isChecked)
		{
			holder.imageCheck.setVisibility(View.VISIBLE);
		}
		else
		{
			holder.imageCheck.setVisibility(View.INVISIBLE);
		}
	}
	
	/**
	 * 修改选择项
	 * @param position
	 */
	public void setCheckedItem(int position)
	{
		listData.get(beforePosition).IsChecked = false;
		listData.get(position).IsChecked = true;
		beforePosition = position;
	}

}
