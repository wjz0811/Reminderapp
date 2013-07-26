package com.hackforchange.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.hackforchange.R;

public class IntroPicActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intro_pic);
		Thread splashThread = new Thread() {
			@Override
			public void run() {
				try {
					int waited = 0;
					while (waited < 1000) {
						sleep(100);
						waited += 100;
					}
				} catch (InterruptedException e) {
					// do nothing
				} finally {

					Intent i = new Intent(IntroPicActivity.this, TabDemoActivity.class);

					startActivity(i);
					finish();
				}
			}
		};
		splashThread.start();
	}
}
