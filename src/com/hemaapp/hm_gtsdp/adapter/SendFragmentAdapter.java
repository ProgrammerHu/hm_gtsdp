package com.hemaapp.hm_gtsdp.adapter;

import com.hemaapp.hm_gtsdp.GtsdpFragmentActivity;
import com.hemaapp.hm_gtsdp.fragment.MySendFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SendFragmentAdapter extends FragmentPagerAdapter {
	private FragmentManager fm;
	private GtsdpFragmentActivity Activity;
	public SendFragmentAdapter(FragmentManager fm, GtsdpFragmentActivity Activity) {
		super(fm);
		this.fm = fm;
		this.Activity= Activity;
	}

	@Override
	public Fragment getItem(int position) {
		return new MySendFragment(position + 1, Activity);
	}

	@Override
	public int getCount() {
		return 2;
	}

}
