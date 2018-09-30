package com.example.abdulaziz.exapndablelistviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;

    private CustomAdapter customAdapter;

    private int exapndedCollapsePositon= -1;

    private List<String > headerString;
    private HashMap<String,List<String>> childString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepareDataList();

        expandableListView = findViewById(R.id.exapand_list);
        customAdapter = new CustomAdapter(this,headerString,childString);

        expandableListView.setAdapter(customAdapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {

                String headerText = headerString.get(i);
                Toast.makeText(getApplicationContext(),headerText,Toast.LENGTH_SHORT).show();

                return false;
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int i) {
                String headerCollapse  = headerString.get(i);
                Toast.makeText(getApplicationContext(),"Collapse :"+headerCollapse,Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {

                String childText = childString.get(headerString.get(i)).get(i1);
                Toast.makeText(getApplicationContext(),"Child clicked : "+childText,Toast.LENGTH_SHORT).show();

                return false;
            }
        });

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {
                if (exapndedCollapsePositon!= -1 && exapndedCollapsePositon != i){
                    expandableListView.collapseGroup(exapndedCollapsePositon);
                }
                exapndedCollapsePositon = i;
            }
        });

    }

    public void prepareDataList(){

        String[] countriesNames = getResources().getStringArray(R.array.countries_array);
        String[] countryName = getResources().getStringArray(R.array.country_array);

        headerString = new ArrayList<>();
        childString = new HashMap<>();

        for (int i=0; i<countriesNames.length; i++){

            headerString.add(countriesNames[i]);

            List<String> child = new ArrayList<>();
            child.add(countryName[i]);

            childString.put(headerString.get(i),child);
        }
    }
}
