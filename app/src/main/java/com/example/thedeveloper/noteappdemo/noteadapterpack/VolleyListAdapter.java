package com.example.thedeveloper.noteappdemo.noteadapterpack;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.thedeveloper.noteappdemo.R;
import com.example.thedeveloper.noteappdemo.notepackages.ModelHr;

import java.util.ArrayList;

/**
 * Created by The Developer on 6/3/17.
 */

public class VolleyListAdapter extends BaseAdapter {

   private Context context;
   private ArrayList<ModelHr> modelHrs;

    public VolleyListAdapter(Context context, ArrayList<ModelHr> modelHrs) {
        this.context = context;
        this.modelHrs = modelHrs;
    }

    @Override
    public int getCount() {
        return modelHrs.size();
    }

    @Override
    public Object getItem(int position)
    {
        return modelHrs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){

            convertView = View.inflate(context , R.layout.volley_item_layout,null);
        }

        TextView tvname = (TextView) convertView.findViewById(R.id.companynametext);
        TextView tvnumber = (TextView) convertView.findViewById(R.id.hrnumbertext);
        TextView tvemail = (TextView) convertView.findViewById(R.id.hremailidtext);
        ImageView icons = (ImageView) convertView.findViewById(R.id.demoImages);

        ModelHr model = modelHrs.get(position);

        tvname.setText(model.getCompanyName());
        tvnumber.setText(model.getCompanyNumber());
        tvemail.setText(model.getCompanyEmail());


        ColorGenerator generator = ColorGenerator.MATERIAL;
        String alphabet = String.valueOf(model.getCompanyName().toUpperCase().charAt(0));

        TextDrawable drawable = TextDrawable.builder()
                .beginConfig()
                .width(60)
                .height(60)
                .endConfig()
                .buildRoundRect(alphabet, generator.getRandomColor(),15);
        icons.setImageDrawable(drawable);
        return convertView;
    }
}
