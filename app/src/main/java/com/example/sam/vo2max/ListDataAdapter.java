package com.example.sam.vo2max;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aran & Samuel on 2017-02-11.
 */

public class ListDataAdapter extends ArrayAdapter {
    List list = new ArrayList();
    public ListDataAdapter(Context context, int resource)
    {
        super(context, resource);
    }

    static class LayoutHandler
    {
        TextView NAME, AGE, WEIGHT, POWER3, POWER4, POWER5, VO2MAX3, VO2MAX4, VO2MAX5;
    }

    @Override
    public void add(Object object)
    {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        LayoutHandler layoutHandler;
        if(row == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            row = layoutInflater.inflate(R.layout.row_layout, parent, false);
            layoutHandler = new LayoutHandler();
            layoutHandler.NAME = (TextView)row.findViewById(R.id.text_user_name);
            layoutHandler.POWER3 = (TextView)row.findViewById(R.id.text_user_power3);
            layoutHandler.POWER4 = (TextView)row.findViewById(R.id.text_user_power4);
            layoutHandler.POWER5 = (TextView)row.findViewById(R.id.text_user_power5);
            layoutHandler.VO2MAX3 = (TextView)row.findViewById(R.id.text_user_vo2max3);
            layoutHandler.VO2MAX4 = (TextView)row.findViewById(R.id.text_user_vo2max4);
            layoutHandler.VO2MAX5 = (TextView)row.findViewById(R.id.text_user_vo2max5);

            row.setTag(layoutHandler);
        }
        else
        {
            layoutHandler = (LayoutHandler) row.getTag();
        }
        DataProvider dataProvider = (DataProvider)this.getItem(position);
        layoutHandler.NAME.setText(dataProvider.getName());
        layoutHandler.POWER3.setText(dataProvider.getPower3());
        layoutHandler.POWER4.setText(dataProvider.getPower4());
        layoutHandler.POWER5.setText(dataProvider.getPower5());
        layoutHandler.VO2MAX3.setText(dataProvider.getVo2max5());
        layoutHandler.VO2MAX4.setText(dataProvider.getVo2max5());
        layoutHandler.VO2MAX5.setText(dataProvider.getVo2max5());
        return row;
    }
}
