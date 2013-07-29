package com.wjz.views;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.wjz.R;
import com.wjz.models.testimonies.Testimony;

import java.util.List;

public class TestimonyListAdapter extends ArrayAdapter<Testimony> {
    Context context;
    int layoutResourceId;
    List<Testimony> data = null;
    View row;

    public TestimonyListAdapter(Context context, int layoutResourceId, List<Testimony> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        row = convertView;
        ProjectHolder holder = null;


        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ProjectHolder();
            //holder.image = (ImageView) row.findViewById(R.id.image);
            holder.name = (TextView) row.findViewById(R.id.name);
            holder.country = (TextView) row.findViewById(R.id.country);
            holder.testimony = (TextView) row.findViewById(R.id.testimony);

            row.setTag(holder);
        } else
            holder = (ProjectHolder) row.getTag();
        return row;
    }
}

class ProjectHolder {
    //ImageView image;
    TextView name;
    TextView country;
    TextView testimony;
}
