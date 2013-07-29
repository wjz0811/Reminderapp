package com.wjz.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.wjz.R;

public class IntroPicWelcomeActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intropicwelcomeactivitylayout);
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

                    Intent i = new Intent(IntroPicWelcomeActivity.this, TabDemoActivity.class);

                    startActivity(i);
                    finish();
                }
            }
        };
        splashThread.start();
    }
}
