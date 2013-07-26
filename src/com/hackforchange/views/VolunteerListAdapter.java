package com.hackforchange.views;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.hackforchange.R;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import android.view.LayoutInflater;

public class VolunteerListAdapter extends ArrayAdapter<Volunteer> {
  Context context;
  int layoutResourceId;
  List<Volunteer> data = null;
  View row;

  public VolunteerListAdapter (Context context, int layoutResourceId, List<Volunteer> data) {
    super(context, layoutResourceId, data);
    this.layoutResourceId = layoutResourceId;
    this.context = context;
    this.data = data;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    row = convertView;
    ProjectHolder holder = null;


    if(row == null)
    {
      LayoutInflater inflater = ((Activity)context).getLayoutInflater();
      row = inflater.inflate(layoutResourceId, parent, false);

      holder = new ProjectHolder();
      holder.image = (ImageView)row.findViewById(R.id.image);
      holder.name = (TextView)row.findViewById(R.id.name);
      holder.country = (TextView)row.findViewById(R.id.country);
      holder.testimony = (TextView)row.findViewById(R.id.testimony);

      row.setTag(holder);
    }
    else
      holder = (ProjectHolder)row.getTag();
    return row;
  }
}

class ProjectHolder {
  //ImageView image;
  ImageView image;
  TextView name;
  TextView country;
  TextView testimony;
}
