package com.matavt.healthbaby12;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RecordWeightHeight extends Fragment {
    LineChart weightChart, heightChart;
    List<Entry> weightDataList,weight5DataList,weight25DataList,weight50DataList,weight75DataList, weight95DataList;
    List<Entry> heightDataList,height5DataList,height25DataList,height50DataList,height75DataList, height95DataList;

    int[] ageMonths = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24};

    double[] weight5 = {2.6,3.6,4.5,5.2,5.8,6.2,6.6,6.9,7.2,7.4,7.7,7.9,8.1,8.2,8.4,8.6,8.8,8.9,9.1,
            9.3,9.4,9.6,9.8,9.9,10.1};
    double[] weight25 = {3,4.1,5.1,5.9,6.5,7,7.4,7.7,8,8.3,8.5,8.7,9,9.2,9.4,9.6,9.8,10,10.1,10.3,
            10.5,10.7,10.9,11.1,11.3};
    double[] weight50 = {3.3,4.5,5.6,6.4,7,7.5,7.9,8.3,8.6,8.9,9.2,9.4,9.6,9.9,10.1,10.3,10.5,10.7,
            10.9,11.1,11.3,11.5,11.8,12,12.2};
    double[] weight75 = {3.7,4.9,6,6.9,7.6,8.1,8.5,8.9,9.3,9.6,9.9,10.1,10.4,10.6,10.9,11.1,11.3,
            11.6,11.8,12,12.2,12.5,12.7,12.9,13.1};
    double[] weight95 = {4.2,5.5,6.8,7.7,8.4,9,9.5,9.9,10.3,10.6,10.9,11.2,11.5,11.8,12.1,12.3,
            12.6,12.9,13.1,13.4,13.6,13.9,14.2,14.4,14.7};

    double[] height5 = {46.8,51.5,55.1,58.1,60.5,62.4,64.1,65.6,67,68.3,69.5,70.7,71.8,72.9,74,75,
            76,76.9,77.8,78.7,79.6,80.4,81.2,82,82.8};
    double[] height25 = {48.6,53.4,57.1,60.1,62.5,64.5,66.2,67.7,69.1,70.5,71.7,73,74.1,75.3,76.4,
            77.4,78.5,79.5,80.4,81.4,82.3,83.2,84.1,84.9,85.8};
    double[] height50 = {49.9,54.7,58.4,61.4,63.9,65.9,67.6,69.2,70.6,72,73.3,74.5,75.7,76.9,
            78,79.1,80.2,81.2,82.3,83.2,84.2,85.1,86,86.9,87.8};
    double[] height75 = {51.2,56,59.8,62.8,65.3,67.3,69.1,70.6,72.1,73.5,74.8,76.1,77.4,78.6,79.7,
            80.9,82,83,84.1,85.1,86.1,87.1,88,89,89.9};
    double[] height95 = {53,57.9,61.7,64.8,67.3,69.4,71.1,72.7,74.2,75.7,77,78.4,79.7,80.9,82.1,
            83.3,84.5,85.6,86.7,87.8,88.8,89.9,90.9,91.9,92.8};

    public RecordWeightHeight() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_record_weight_height, container, false);
        weightChart = view.findViewById(R.id.weightChart);
        heightChart = view.findViewById(R.id.heightChart);
        weightDataList = new ArrayList<>();
        heightDataList = new ArrayList<>();
        weight5DataList = new ArrayList<>();
        weight25DataList = new ArrayList<>();
        weight50DataList = new ArrayList<>();
        weight75DataList = new ArrayList<>();
        weight95DataList = new ArrayList<>();
        height5DataList = new ArrayList<>();
        height25DataList = new ArrayList<>();
        height50DataList = new ArrayList<>();
        height75DataList = new ArrayList<>();
        height95DataList = new ArrayList<>();

        for (int i = 0; i < ageMonths.length; i++) {
            weight5DataList.add(new Entry(ageMonths[i], (float) weight5[i]));
            weight25DataList.add(new Entry(ageMonths[i], (float) weight25[i]));
            weight50DataList.add(new Entry(ageMonths[i], (float) weight50[i]));
            weight75DataList.add(new Entry(ageMonths[i], (float) weight75[i]));
            weight95DataList.add(new Entry(ageMonths[i], (float) weight95[i]));

            height5DataList.add(new Entry(ageMonths[i], (float) height5[i]));
            height25DataList.add(new Entry(ageMonths[i], (float) height25[i]));
            height50DataList.add(new Entry(ageMonths[i], (float) height50[i]));
            height75DataList.add(new Entry(ageMonths[i], (float) height75[i]));
            height95DataList.add(new Entry(ageMonths[i], (float) height95[i]));
        }

        Disposable subscribe = MainMenu.hbDB.daoHeightWeight().getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        list -> {
                            for (EntityHeightWeight entity : list) {
                                float age = DateFunctions.calculateAgeInMoths(User.getInstance()
                                                .getChildDoB(), entity.calendar);
                                weightDataList.add(new Entry(age, entity.weight));
                                heightDataList.add(new Entry(age, entity.height));
                                Log.i("age", String.valueOf(age));
                                Log.i("entity", entity.toString());
                            }

                            LineDataSet weight5DataSet = new LineDataSet(weight5DataList,"5%");
                            weight5DataSet.setColor(Color.BLUE);
                            weight5DataSet.setDrawCircles(false);
                            weight5DataSet.setDrawValues(false);

                            LineDataSet weight25DataSet = new LineDataSet(weight25DataList,"25%");
                            weight25DataSet.setColor(Color.CYAN);
                            weight25DataSet.setDrawCircles(false);
                            weight25DataSet.setDrawValues(false);

                            LineDataSet weight50DataSet = new LineDataSet(weight50DataList,"50%");
                            weight50DataSet.setColor(Color.GREEN);
                            weight50DataSet.setDrawCircles(false);
                            weight50DataSet.setDrawValues(false);

                            LineDataSet weight75DataSet = new LineDataSet(weight75DataList,"75%");
                            weight75DataSet.setColor(Color.YELLOW);
                            weight75DataSet.setDrawCircles(false);
                            weight75DataSet.setDrawValues(false);

                            LineDataSet weight95DataSet = new LineDataSet(weight95DataList,"95%");
                            weight95DataSet.setColor(Color.RED);
                            weight95DataSet.setDrawCircles(false);
                            weight95DataSet.setDrawValues(false);

                            LineDataSet weightDataSet = new LineDataSet(weightDataList, "Weight");
                            weightDataSet.setColor(Color.BLACK);

                            LineData weightData = new LineData(weightDataSet,weight5DataSet,
                                    weight25DataSet,weight50DataSet,weight75DataSet,weight95DataSet);
                            weightChart.setData(weightData);
                            weightChart.invalidate();


                            LineDataSet height5DataSet = new LineDataSet(height5DataList,"5%");
                            height5DataSet.setColor(Color.BLUE);
                            height5DataSet.setDrawCircles(false);
                            height5DataSet.setDrawValues(false);

                            LineDataSet height25DataSet = new LineDataSet(height25DataList,"25%");
                            height25DataSet.setColor(Color.CYAN);
                            height25DataSet.setDrawCircles(false);
                            height25DataSet.setDrawValues(false);

                            LineDataSet height50DataSet = new LineDataSet(height50DataList,"50%");
                            height50DataSet.setColor(Color.GREEN);
                            height50DataSet.setDrawCircles(false);
                            height50DataSet.setDrawValues(false);

                            LineDataSet height75DataSet = new LineDataSet(height75DataList,"75%");
                            height75DataSet.setColor(Color.YELLOW);
                            height75DataSet.setDrawCircles(false);
                            height75DataSet.setDrawValues(false);

                            LineDataSet height95DataSet = new LineDataSet(height95DataList,"95%");
                            height95DataSet.setColor(Color.RED);
                            height95DataSet.setDrawCircles(false);
                            height95DataSet.setDrawValues(false);

                            LineDataSet heightDataSet = new LineDataSet(heightDataList, "height");
                            heightDataSet.setColor(Color.BLACK);

                            LineData heightData = new LineData(heightDataSet,height5DataSet,
                                    height25DataSet,height50DataSet,height75DataSet,height95DataSet);
                            heightChart.setData(heightData);
                            heightChart.invalidate();

                        }
                );
        return view;
    }
}