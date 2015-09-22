package com.hemaapp.hm_gtsdp.adapter;

import java.util.List;

import u.aly.da;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hemaapp.hm_gtsdp.GtsdpAdapter;
import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_gtsdp.model.ContactsTemplateModel;

public class TemplateAddressAdpater extends GtsdpAdapter{
	private int SENDER = 100;//发件人
	private int RECIVER = 200;//收件人
	private List<ContactsTemplateModel> dataList;
	private int beforePosition = 0;//记录上次选择的位置
	private int ActivityType;

	public TemplateAddressAdpater(Context mContext, List<ContactsTemplateModel> dataList, int activtyType) {
		super(mContext);
		this.dataList = dataList;
		this.ActivityType = activtyType;
	}

	@Override
	public int getCount() {
		if(dataList == null)
			return 0;
		else
			return dataList.size();
	}

	@Override
	public Object getItem(int position) {
		return dataList.get(position);
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
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item_template, null);
		}
		convertView.setClickable(false);
		ViewHolder holder = new ViewHolder();
		holder.imageCheck = (ImageView)convertView.findViewById(R.id.imageCheck);
		holder.txtTitle = (TextView)convertView.findViewById(R.id.txtTitle);
		holder.txtName = (TextView)convertView.findViewById(R.id.txtName);
		holder.txtAddress = (TextView)convertView.findViewById(R.id.txtAddress);
		holder.txtPhone = (TextView)convertView.findViewById(R.id.txtPhone);
		setData(position, holder);
		return convertView;
	}
	
	private class ViewHolder
	{
		public ImageView imageCheck;
		public TextView txtTitle, txtName, txtAddress, txtPhone;
	}
	
	private void setData(int position, ViewHolder holder)
	{
		holder.txtAddress.setText(dataList.get(position).getAddress());
		holder.txtName.setText(dataList.get(position).getName());
		holder.txtPhone.setText(dataList.get(position).getPhoneNumber());
		if(dataList.get(position).isCheck)
		{
			holder.imageCheck.setImageResource(R.drawable.check_blue);
		}
		else
		{
			holder.imageCheck.setImageResource(R.drawable.check_none_blue);
		}
		if(ActivityType == SENDER)
		{
			holder.txtTitle.setText("发件人");
		}
		else if(ActivityType == RECIVER)
		{
			holder.txtTitle.setText("收件人");
		}
	}
	
	/**
	 * 更换点击 
	 * @param position
	 */
	public void selectItem(int position)
	{
		dataList.get(beforePosition).isCheck = false;
		dataList.get(position).isCheck  = true;
		beforePosition = position;
	}

}
