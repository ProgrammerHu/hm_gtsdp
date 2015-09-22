package com.hemaapp.hm_gtsdp.adapter;

import com.hemaapp.hm_gtsdp.fragment.MySendFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SendFragmentAdapter extends FragmentPagerAdapter {
	private FragmentManager fm;
	public SendFragmentAdapter(FragmentManager fm) {
		super(fm);
		this.fm = fm;
	}

	@Override
	public Fragment getItem(int position) {
		return new MySendFragment(position);
	}

	@Override
	public int getCount() {
		return 2;
	}

}
