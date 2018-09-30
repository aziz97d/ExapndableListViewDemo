package com.example.abdulaziz.exapndablelistviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class CustomAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String > headerString;
    private HashMap<String,List<String>> childString;

    public CustomAdapter(Context context, List<String> headerString, HashMap<String, List<String>> childString) {
        this.context = context;
        this.headerString = headerString;
        this.childString = childString;
    }

    @Override
    public int getGroupCount() {
        return headerString.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return childString.get(headerString.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return headerString.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return childString.get(headerString.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

        String headerText = (String) getGroup(i);

        if (view==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.group_view,null);
        }

        TextView textView = view.findViewById(R.id.group_text_id);
        textView.setText(headerText);

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {

        String childText = (String) getChild(i,i1);

        if (view==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.child_view,null);
        }

        TextView textView = view.findViewById(R.id.child_text_id);
        textView.setText(childText);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
