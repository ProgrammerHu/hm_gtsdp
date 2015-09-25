package com.hemaapp.hm_gtsdp.adapter;

import com.hemaapp.hm_gtsdp.GtsdpActivity;
import com.hemaapp.hm_gtsdp.GtsdpFragmentActivity;
import com.hemaapp.hm_gtsdp.fragment.ImageViewerFragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;


public class ImageViewPagerAdapter extends FragmentPagerAdapter {
	GtsdpFragmentActivity activity;
	public ImageViewPagerAdapter(FragmentManager fm, GtsdpFragmentActivity activity) {
		super(fm);
		this.activity = activity;
	}

	@Override
	public Fragment getItem(int PageNumber) {
		return new ImageViewerFragment(PageNumber, activity);
	}

	@Override
	public int getCount() {
		return 4000;
	}


}
