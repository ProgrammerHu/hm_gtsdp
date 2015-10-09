package com.hemaapp.hm_gtsdp.adapter;

import java.util.List;

import u.aly.da;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hemaapp.hm_gtsdp.GtsdpActivity;
import com.hemaapp.hm_gtsdp.GtsdpAdapter;
import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_gtsdp.activity.StartActivity;
import com.hemaapp.hm_gtsdp.activity.TemplateEditActivty;
import com.hemaapp.hm_gtsdp.model.TemplateItemModel;

public class TemplateAddressAdpater extends GtsdpAdapter{
	private int SENDER = 100;//发件人
	private int RECIVER = 200;//收件人
	private List<TemplateItemModel> dataList;
	private int beforePosition = 0;//记录上次选择的位置
	private int ActivityType;
	private GtsdpActivity Activity;
	
	public TemplateAddressAdpater(Context mContext, List<TemplateItemModel> dataList, int activtyType) {
		super(mContext);
		Activity = (GtsdpActivity)mContext;
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
		holder.layoutTitle = convertView.findViewById(R.id.layoutTitle);
		holder.layoutBody = convertView.findViewById(R.id.layoutBody);
		setData(position, holder);
		return convertView;
	}
	
	private class ViewHolder
	{
		public ImageView imageCheck;
		public TextView txtTitle, txtName, txtAddress, txtPhone;
		public View layoutTitle, layoutBody;
	}
	
	private void setData(final int position, ViewHolder holder)
	{
		holder.txtAddress.setText(dataList.get(position).getAddress());
		holder.txtName.setText(dataList.get(position).getName());
		holder.txtPhone.setText(dataList.get(position).getTelphone());
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
		holder.layoutBody.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext, TemplateEditActivty.class);
				intent.putExtra("ActivityType", ActivityType);
				intent.putExtra("id", dataList.get(position).getId());
				intent.putExtra("name", dataList.get(position).getName());
				intent.putExtra("address", dataList.get(position).getAddress());
				intent.putExtra("telphone", dataList.get(position).getTelphone());
				Activity.startActivityForResult(intent, 0);
				Activity.overridePendingTransition(R.anim.right_in, R.anim.none);
			}
		});
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
	
	public void changeDataList(List<TemplateItemModel> dataList)
	{
		this.dataList = dataList;
	}
}
