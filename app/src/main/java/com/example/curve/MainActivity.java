package com.example.curve;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
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
        arrayList.add(40.5);
        arrayList.add(42.5);
        arrayList.add(41.5);
        arrayList.add(44.5);
        arrayList.add(46.5);
        arrayList.add(46.5);
        arrayList.add(50.5);
        arrayList.add(51.5);
        arrayList.add(57.5);
        arrayList.add(62.5);
        arrayList.add(64.5);
        arrayList.add(42.5);
        arrayList.add(51.5);
        arrayList.add(41.5);
        arrayList.add(41.5);
        for (int i=0;i<arrayList.size();i++){
             dateStorage = new DateStorage();
             dateStorage.value =arrayList.get(i);
             String demo= "2017-06-"+i;
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
