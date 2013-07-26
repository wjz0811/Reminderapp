package com.hackforchange.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.hackforchange.R;
import com.hackforchange.backend.activities.ActivitiesDAO;
import com.hackforchange.models.activities.Activities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/*
 * Presents an activity that displays details of an existing activity
 * Also lets you edit the project (EditActivitiesActivity) or delete the project (right from this java file)
 * by choosing buttons in the ActionBar
 * Pressing the back key will exit the activity
 */
// TODO: show participation history
// TODO: participation history graph?
public class Testimony extends SherlockActivity {
  public static final String[] AllInits = {"id", "name", "country", "testimony"};
  private ArrayList<volunteer> volunteer;
  private VolunteerListAdapter VolunteerlistAdapter;
  private int id;
  private volunteer a;

  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.volunteerListView);

    // read in the ID of the project that this activity must display details of
   id = getIntent().getExtras().getInt("id");
  }

  @Override
  public void onResume() {
    super.onResume();
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    VolunteerDAO aDao = new VolunteerDAO(getApplicationContext());
    a = aDao.getActivityWithId(id);
    TextView name = (TextView) findViewById(R.id.name);
    name.setText(parser.format(d));
    TextView country = (TextView) findViewById(R.id.country);
    country.setText(a.getCountry());
    TextView testimony = (TextView) findViewById(R.id.testimony);
    testimony.setText(a.getTestimony());
    
    }
  }
