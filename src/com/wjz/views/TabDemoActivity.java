package com.wjz.views;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.wjz.R;

public class TabDemoActivity extends TabActivity{
    private Intent alarmIntent, testimonyIntent, scienceIntent;
    /**
     * Called when the activity is first created.
     */
    private TabSpec alarm, testimony, science;
    private TabHost tabHost;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabdemoactivitylayout);
        getActionBar().setDisplayShowHomeEnabled(true);
        tabHost = (TabHost) findViewById(android.R.id.tabhost);

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
          
        setTabColor();
        
        tabHost.setOnTabChangedListener(new OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                setTabColor();
            }
        });
    }
    
    private void setTabColor(){
    	 for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
             tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#FFFFFF")); //unselected
             TextView tabTitle = (TextView)tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title); // unselected
             tabTitle.setTextColor(Color.parseColor("#000000"));
         }
         
         // set selected tab color on start
         tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#003e7c"));
         TextView tabTitle = (TextView)tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).findViewById(android.R.id.title); // selected
         tabTitle.setTextColor(Color.parseColor("#FFFFFF"));
    }

}



