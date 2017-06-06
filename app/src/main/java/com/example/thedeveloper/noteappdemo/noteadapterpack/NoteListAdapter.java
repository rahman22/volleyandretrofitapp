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
import com.example.thedeveloper.noteappdemo.notepackages.HrData;

import java.util.ArrayList;

public class NoteListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<HrData> listHrs;

    public NoteListAdapter(Context context, ArrayList<HrData> listHrs) {
        this.context = context;
        this.listHrs = listHrs;
    }

    @Override
    public int getCount() {
        return listHrs.size();
    }

    @Override
    public Object getItem(int position) {
        return listHrs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        if(convertView == null){

            convertView = View.inflate(context, R.layout.item_list,null);
        }

        TextView tv1 = (TextView) convertView.findViewById(R.id.demoTitleText);
        TextView tv2 = (TextView) convertView.findViewById(R.id.demoDescText);
        TextView tv3 = (TextView) convertView.findViewById(R.id.hremailtext);
        ImageView images = (ImageView) convertView.findViewById(R.id.demoImages);

        HrData hrData = listHrs.get(position);

        tv1.setText(hrData.getName());
        tv2.setText(hrData.getContact());
        tv3.setText(hrData.getEmails());

        ColorGenerator generator = ColorGenerator.MATERIAL;

        String names = String.valueOf(hrData.getName().toUpperCase().charAt(0));

        TextDrawable drawable = TextDrawable.builder()
                .beginConfig()
                .width(60)
                .height(60)
                .endConfig()
                .buildRoundRect(names, generator.getRandomColor(),15);

        images.setImageDrawable(drawable);
        return convertView;
    }
}
