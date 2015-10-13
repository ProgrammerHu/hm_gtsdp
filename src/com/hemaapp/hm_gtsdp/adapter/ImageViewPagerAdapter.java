package com.hemaapp.hm_gtsdp.adapter;

import java.util.List;

import com.hemaapp.hm_gtsdp.GtsdpActivity;
import com.hemaapp.hm_gtsdp.GtsdpFragmentActivity;
import com.hemaapp.hm_gtsdp.fragment.ImageViewerFragment;
import com.hemaapp.hm_gtsdp.model.AdvertiseModel;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;


public class ImageViewPagerAdapter extends FragmentPagerAdapter {
	private GtsdpFragmentActivity activity;
	private List<AdvertiseModel> listData;
	public ImageViewPagerAdapter(FragmentManager fm, GtsdpFragmentActivity activity,
			List<AdvertiseModel> listData) {
		super(fm);
		this.activity = activity;
		this.listData = listData;
	}

	@Override
	public Fragment getItem(int PageNumber) {
		return new ImageViewerFragment(PageNumber, activity, listData);
	}

	@Override
	public int getCount() {
		return 4000;
	}


}
