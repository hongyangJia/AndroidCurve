package com.example.curve;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import curve.com.DateStorage;
import curve.com.GraphView;

public class MainActivity extends AppCompatActivity {

    private  GraphView graphView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        graphView = new GraphView(this);
        setContentView(graphView);

        final List<DateStorage> dateStorages = new ArrayList<>();
        DateStorage dateStorage;
        ArrayList<Double> arrayList = new ArrayList<>();
        arrayList.add(73.9);
        arrayList.add(73.0);
        arrayList.add(73.6);
        arrayList.add(73.6);
        arrayList.add(72.8);
        arrayList.add(72.7);
        arrayList.add(72.5);
        arrayList.add(72.9);
        arrayList.add(73.7);
        arrayList.add(73.5);
        arrayList.add(73.4);
        arrayList.add(73.3);
        arrayList.add(73.1);
        arrayList.add(74.1);
        arrayList.add(73.6);
        arrayList.add(70.5);
        arrayList.add(70.5);
        arrayList.add(70.5);
        arrayList.add(70.5);
        arrayList.add(73.6);

        for (int i=0;i<arrayList.size();i++){
             dateStorage = new DateStorage();
             dateStorage.value =arrayList.get(i);
             String demo= "2017-06-"+i+1;
             dateStorage.date = edit(demo);
             dateStorages.add(dateStorage);
        }

        graphView.setDateStorage(dateStorages);

    }

    private  String   edit(String date){
        date = date.substring(5,date.length());
        Log.e("date",date);
        return date;
    }
}
