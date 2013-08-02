package com.wjz.views;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wjz.R;
import com.wjz.models.testimonies.Testimony;

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
            holder.image = (ImageView) row.findViewById(R.id.image);
            // holder.name = (TextView) row.findViewById(R.id.name);
            // holder.country = (TextView) row.findViewById(R.id.country                                                     );
            holder.testimony = (TextView) row.findViewById(R.id.testimony);

            row.setTag(holder);
        } else
            holder = (ProjectHolder) row.getTag();

        Testimony t = data.get(position);
        // holder.name.setText(t.getName());
        // holder.country.setText(t.getCountry());
        // holder.testimony.setText(t.getTestimony());
        String styledText = "<font color='#003e7c'><b>" + t.getName() +"</b></font> " + t.getTestimony();
        holder.testimony.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
     
        // set the thumbnail
        AssetManager assetManager = context.getAssets();
        InputStream istr;
        try {
            istr = assetManager.open(t.getImagePath());
            Bitmap bitmap = BitmapFactory.decodeStream(istr);
            holder.image.setImageBitmap(bitmap);
            istr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return row;
    }
}

class ProjectHolder {
    ImageView image;
    TextView name;
    TextView country;
    TextView testimony;
}
