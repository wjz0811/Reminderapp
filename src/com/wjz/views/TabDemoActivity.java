package com.wjz.views;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import com.wjz.R;

public class TabDemoActivity extends TabActivity implements TabHost.OnTabChangeListener {
    private Intent alarmIntent, testimonyIntent, scienceIntent;
    /**
     * Called when the activity is first created.
     */
    private TabSpec alarm, testimony, science;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabdemoactivitylayout);
        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);

        // Alarm Tab
        alarm = tabHost.newTabSpec("alarm");
        alarm.setIndicator("alarm");
        alarmIntent = new Intent(TabDemoActivity.this, AlarmActivity.class);
        alarm.setContent(alarmIntent);

        // Testimony Tab
        testimony = tabHost.newTabSpec("testimony");
        testimony.setIndicator("testimony");
        testimonyIntent = new Intent(TabDemoActivity.this, TestimonyActivity.class);
        testimony.setContent(testimonyIntent);

        // Science Tab
        science = tabHost.newTabSpec("science");
        science.setIndicator("science");
        scienceIntent = new Intent(TabDemoActivity.this, ScienceActivity.class);
        science.setContent(scienceIntent);

        tabHost.addTab(alarm);
        tabHost.addTab(testimony);
        tabHost.addTab(science);
    }

    @Override
    public void onTabChanged(String tabId) {
        /*if (tabId.equals("alarm")) {
        } else if (tabId.equals("testimony")) {
        } else if (tabId.equals("science")) {
        }*/
    }
}


