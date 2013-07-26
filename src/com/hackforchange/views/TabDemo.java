package com.hackforchange.views;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.hackforchange.R;

public class TabDemo extends TabActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tabs);
		TabHost tabHost=(TabHost)findViewById(R.id.tabHost);
		// no need to call TabHost.Setup()

		// Alarm Tab
		TabSpec alarm = tabHost.newTabSpec("alarm");
		alarm.setIndicator("alarm");
		alarm.setContent(R.id.alarm);

		// Testimony Tab
		TabSpec testimony = tabHost.newTabSpec("testimony");
		testimony.setIndicator("testimony");
		testimony.setContent(R.id.testimony);

		// Science Tab
		TabSpec science = tabHost.newTabSpec("science");	
		science.setIndicator("science");
		science.setContent(R.id.science);

		tabHost.addTab(alarm);
		tabHost.addTab(testimony);
		tabHost.addTab(science);
	}
}