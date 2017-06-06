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
import com.example.thedeveloper.noteappdemo.notepackages.DemoList;

import java.util.ArrayList;


public class ListOfDemoAdapter extends BaseAdapter {


    private Context context;
    private ArrayList<DemoList> demoLists;

    public ListOfDemoAdapter(Context context, ArrayList<DemoList> demoLists) {
        this.context = context;
        this.demoLists = demoLists;
    }

    @Override
    public int getCount() {
        return demoLists.size();
    }

    @Override
    public Object getItem(int position) {
        return demoLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {

            convertView = View.inflate(context, R.layout.demo_layout, null);

        }

        TextView tv1 = (TextView) convertView.findViewById(R.id.demoTitleText);
        TextView tv2 = (TextView) convertView.findViewById(R.id.demoDescText);
        ImageView icon = (ImageView) convertView.findViewById(R.id.demoImages);

        DemoList demoList = demoLists.get(position);

        tv1.setText(demoList.getLibName());
        tv2.setText(demoList.getLibDescription());

        ColorGenerator generator = ColorGenerator.MATERIAL;
        String list_intro = String.valueOf(demoList.getLibName().toUpperCase().charAt(0));

        TextDrawable drawable = TextDrawable.builder()
                .beginConfig()
                .width(60)
                .height(60)
                .endConfig()
                .buildRoundRect(list_intro, generator.getRandomColor(), 15);

        icon.setImageDrawable(drawable);

        return convertView;
    }
}
