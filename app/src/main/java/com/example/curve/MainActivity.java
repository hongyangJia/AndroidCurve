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
        arrayList.add(78.1);
        arrayList.add(78.2);
        arrayList.add(78.7);
        arrayList.add(77.1);
        arrayList.add(76.1);
        arrayList.add(75.1);
        arrayList.add(74.4);
        arrayList.add(73.5);
        arrayList.add(72.6);
        arrayList.add(71.8);
        arrayList.add(71.5);
        arrayList.add(71.6);
        arrayList.add(72.7);
        arrayList.add(74.8);
        arrayList.add(75.4);
        arrayList.add(75.5);
        arrayList.add(75.5);
        arrayList.add(74.5);
        arrayList.add(73.5);
        arrayList.add(72.5);
        arrayList.add(71.5);



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
