package com.hemaapp.hm_gtsdp.fragment;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import xtom.frame.image.load.XtomImageTask;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_FrameWork.view.RoundedImageView;
import com.hemaapp.hm_gtsdp.GtsdpActivity;
import com.hemaapp.hm_gtsdp.GtsdpFragment;
import com.hemaapp.hm_gtsdp.GtsdpFragmentActivity;
import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_gtsdp.activity.WebViewActivity;
import com.hemaapp.hm_gtsdp.model.AdvertiseModel;

public class ImageViewerFragment extends GtsdpFragment {
	private RoundedImageView innerImage;
	private int PageNumber;
	private GtsdpFragmentActivity activity;
	private List<AdvertiseModel> listData;
	public ImageViewerFragment(int PageNumber, GtsdpFragmentActivity activity, List<AdvertiseModel> listData)
	{
		super();
		this.PageNumber = PageNumber;
		this.activity = activity;
		this.listData = listData;
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.fragment_imageviewer);
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void callAfterDataBack(HemaNetTask arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void callBackForGetDataFailed(HemaNetTask arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void callBackForServerFailed(HemaNetTask arg0, HemaBaseResult arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void callBackForServerSuccess(HemaNetTask arg0,
			HemaBaseResult arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void callBeforeDataBack(HemaNetTask arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void findView() {
		innerImage = (RoundedImageView)findViewById(R.id.innerImage);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		try {
			XtomImageTask task = new XtomImageTask(innerImage, 
					new URL(listData.get(PageNumber%3).getImgurlbig()), getActivity());
			activity.imageWorker.loadImage(task);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	protected void setListener() {
		innerImage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(activity, WebViewActivity.class);
				intent.putExtra("Title", "อฦนใ");
				intent.putExtra("BeforeUrl", listData.get(PageNumber%3).getClickid());
				startActivity(intent);
				activity.overridePendingTransition(R.anim.right_in, R.anim.none);
			}
		});
		
	}

}
