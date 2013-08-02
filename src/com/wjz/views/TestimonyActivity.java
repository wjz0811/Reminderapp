package com.wjz.views;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.wjz.R;
import com.wjz.backend.testimonies.TestimonyDAO;
import com.wjz.models.testimonies.Testimony;

import java.util.List;

public class TestimonyActivity extends Activity {
    private ListView testimonyListView;
    private List<Testimony> testimony_data;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testimonyactivitylayout);
    }

    @Override
    public void onResume(){
        super.onResume();
        testimonyListView = (ListView) findViewById(R.id.testimonylistview);

        // fetch all the testimonies and put them into a list
        TestimonyDAO tDao = new TestimonyDAO(getApplicationContext());
        testimony_data = tDao.getAllTestimonies();

        TestimonyListAdapter adapter = new TestimonyListAdapter(TestimonyActivity.this,R.layout.testimonylist_row,testimony_data);
        testimonyListView.setAdapter(adapter);
    }
}