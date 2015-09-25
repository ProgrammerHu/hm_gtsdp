package com.hemaapp.hm_gtsdp.fragment;

import java.net.MalformedURLException;
import java.net.URL;

import xtom.frame.image.load.XtomImageTask;
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

public class ImageViewerFragment extends GtsdpFragment {
	private String[] paths = {
			"http://7tszkm.com1.z0.glb.clouddn.com/bag0.png",
			"http://7tszkm.com1.z0.glb.clouddn.com/bag1.png",
			"http://7tszkm.com1.z0.glb.clouddn.com/bag2.png",
			"http://7tszkm.com1.z0.glb.clouddn.com/bag3.png"
	};
	private RoundedImageView innerImage;
	private int PageNumber;
	private GtsdpFragmentActivity activity;
	public ImageViewerFragment(int PageNumber, GtsdpFragmentActivity activity)
	{
		super();
		this.PageNumber = PageNumber;
		this.activity = activity;
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
			XtomImageTask task = new XtomImageTask(innerImage, new URL(paths[PageNumber%4]), getActivity());
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
				showTextDialog("ตฺ" + PageNumber % 4 + "าณ");
			}
		});
		
	}

}
