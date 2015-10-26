package com.yahoo.simpletodo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*public class TodoAdapter extends SimpleAdapter {

    public ArrayList<HashMap<String, String>> list;

    *//**
     * Constructor
     *
     * @param context  The context where the View associated with this SimpleAdapter is running
     * @param data     A List of Maps. Each entry in the List corresponds to one row in the list. The
     *                 Maps contain the data for each row, and should include all the entries specified in
     *                 "from"
     * @param resource Resource identifier of a view layout that defines the views for this list
     *                 item. The layout file should include at least those named views defined in "to"
     * @param from     A list of column names that will be added to the Map associated with each
     *                 item.
     * @param to       The views that should display column in the "from" parameter. These should all be
     *                 TextViews. The first N views in this list are given the values of the first N columns
     *//*
    public TodoAdapter(Context context, ArrayList<HashMap<String, String>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int todoPosition) {
        return list.get(todoPosition);
    }

    @Override
    public long getItemId(int todoPosition) {
        return 0;
    }

    @Override
    public View getView(int todoPosition, View convertView, ViewGroup parent) {
        LayoutInflater inflater =
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.todo_list, parent, false);
            firstCol = (TextView) convertView.findViewById(R.id.items);
            secondCol = (TextView) convertView.findViewById(R.id.priority);
        }
        HashMap<String, String> map=list.get(todoPosition);
        firstCol.setText(map.get("first"));
        secondCol.setText(map.get("second"));
        return convertView;
    }
}*/
