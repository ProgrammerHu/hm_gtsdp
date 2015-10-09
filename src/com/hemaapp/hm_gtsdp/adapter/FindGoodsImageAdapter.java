package com.hemaapp.hm_gtsdp.adapter;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import xtom.frame.image.load.XtomImageTask;
import xtom.frame.image.load.XtomImageTask.Size;
import xtom.frame.util.XtomImageUtil;
import xtom.frame.view.XtomListView;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.hemaapp.hm_FrameWork.model.Image;
import com.hemaapp.hm_FrameWork.showlargepic.ShowLargePicActivity;
import com.hemaapp.hm_FrameWork.view.HemaGridView;
import com.hemaapp.hm_FrameWork.view.RoundedImageView;
import com.hemaapp.hm_FrameWork.view.ShowLargeImageView;
import com.hemaapp.hm_gtsdp.GtsdpAdapter;
import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_gtsdp.activity.MyShowLargePicActivity;
import com.hemaapp.hm_gtsdp.view.MyGridView;

public class FindGoodsImageAdapter extends GtsdpAdapter implements OnClickListener {
	private View rootView;
	private ArrayList<String> images;
	private MyGridView gridview;
	private ShowLargeImageView mView;
	

	public FindGoodsImageAdapter(Context mContext, View rootView,
			ArrayList<String> images, MyGridView gridview) {
		super(mContext);
		this.rootView = rootView;
		this.images = images;
		this.gridview = gridview;
	}

	@Override
	public int getCount() {
		if(images == null)
			return 0;
		else 
			return images.size();
	}

	@Override
	public boolean isEmpty() {
		int size = images == null ? 0 : images.size();
		return size == 0;
	}

	@Override
	public int getViewTypeCount() {
		return 2;
	}
	
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
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
			convertView = LayoutInflater.from(mContext).inflate(R.layout.griditem_show_image, null);
		}
		ViewHolder holder = new ViewHolder();
		holder.imageView = (ImageView)convertView.findViewById(R.id.imageview);
		String path = images.get(position);
		holder.imageView.setTag(R.id.TAG, position);
		try
		{
			URL url = new URL(path);
			XtomImageTask task = new AvatarImageTask(holder.imageView, url,
					mContext, null);
			gridview.addTask(position, 0, task);
			holder.imageView.setOnClickListener(this);
		}
		catch (MalformedURLException e) 
		{
			e.printStackTrace();
		}
		return convertView;
	}

	@Override
	public void onClick(View v) {
		int position = Integer.parseInt(v.getTag(R.id.TAG).toString());
		Intent sIt = new Intent(mContext, MyShowLargePicActivity.class);
		sIt.putExtra("position", position);
		sIt.putExtra("images", getImages());
		mContext.startActivity(sIt);
		
	}
	
	private class ViewHolder
	{
		public ImageView imageView; 
	}


	private class AvatarImageTask extends XtomImageTask {

		public AvatarImageTask(ImageView imageView, URL url, Object context,
				Size size) {
			super(imageView, url, context, size);
		}

		@Override
		public void setBitmap(Bitmap bitmap) {
			bitmap = XtomImageUtil.getRoundedCornerBitmap(bitmap, 15);
			super.setBitmap(bitmap);
		}
	}
	
	private ArrayList<Image> getImages()
	{
		ArrayList<Image> imageList = new ArrayList<Image>();
		int i=0;
		for(String url : this.images)
		{
			imageList.add(new Image(String.valueOf(i), String.valueOf(i), String.valueOf(i+1), "", url, url, String.valueOf(i)));
			i++;
		}
		
		return imageList;
	}
}
