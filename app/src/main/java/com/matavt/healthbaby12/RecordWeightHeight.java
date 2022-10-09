package com.matavt.healthbaby12;

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

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RecordWeightHeight extends Fragment {
    LineChart weightChart, heightChart;
    List<Entry> weightDataList, heightDataList;

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

        Disposable subscribe = MainMenu.hbDB.daoHeightWeight().getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        list -> {
                            for (EntityHeightWeight entity : list) {
                                int age = (int) Duration.between(User.getInstance().getChildDoB().toInstant(),entity.calendar.toInstant()).toDays();
                                weightDataList.add(new Entry(age, entity.weight));
                                heightDataList.add(new Entry(age, entity.height));
                                Log.i("age", String.valueOf(age));
                                Log.i("entity", entity.toString());
                            }
                            LineDataSet weightDataSet = new LineDataSet(weightDataList, "Weight");
                            LineDataSet heightDataSet = new LineDataSet(heightDataList, "Height");
                            LineData weightData = new LineData(weightDataSet);
                            LineData heightData = new LineData(heightDataSet);
                            weightChart.setData(weightData);
                            heightChart.setData(heightData);
                            weightChart.invalidate();
                            heightChart.invalidate();
                        }
                );

        return view;
    }
}