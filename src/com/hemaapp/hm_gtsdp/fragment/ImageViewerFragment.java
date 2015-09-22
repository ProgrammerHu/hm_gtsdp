package com.hemaapp.hm_gtsdp.fragment;

import android.os.Bundle;
import android.widget.ImageView;

import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpFragment;
import com.hemaapp.hm_gtsdp.R;

public class ImageViewerFragment extends GtsdpFragment {
	private ImageView innerImage;
	private int PageNumber;
	
	public ImageViewerFragment(int PageNumber)
	{
		super();
		this.PageNumber = PageNumber;
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
		innerImage = (ImageView)findViewById(R.id.innerImage);
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		
	}

}
