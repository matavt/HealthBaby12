package com.matavt.healthbaby12;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Bottle extends Fragment {

    Button confirmButton;
    EditText eDate, eVolume;
    Float volume;
    String date;
    TextView data;
    public Bottle() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottle, container, false);
        eDate = view.findViewById(R.id.date);
        eVolume = view.findViewById(R.id.volume);
        confirmButton = view.findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(view1 -> {
            try {
                date = eDate.getText().toString();
                volume = Float.parseFloat(eVolume.getText().toString());
                EntityActivity activity = new EntityActivity(date,"Bottle feed","",volume,"");
                MainMenu.hbDB.daoActivity().insertActivity(activity)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe();
            } catch (Exception e) {
                //do nothing
            }
        });
        return view;
    }
}