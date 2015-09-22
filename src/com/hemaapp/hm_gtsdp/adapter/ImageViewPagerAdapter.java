package com.hemaapp.hm_gtsdp.adapter;

import com.hemaapp.hm_gtsdp.fragment.ImageViewerFragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;


public class ImageViewPagerAdapter extends FragmentPagerAdapter {

	public ImageViewPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int PageNumber) {
		return new ImageViewerFragment(PageNumber);
	}

	@Override
	public int getCount() {
		return 4000;
	}


}
