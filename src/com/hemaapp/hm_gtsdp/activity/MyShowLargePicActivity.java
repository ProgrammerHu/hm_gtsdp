package com.hemaapp.hm_gtsdp.activity;

import android.os.Bundle;
import android.view.WindowManager;

import com.hemaapp.hm_FrameWork.showlargepic.ShowLargePicActivity;

public class MyShowLargePicActivity extends ShowLargePicActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
	}
}
