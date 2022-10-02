package com.matavt.healthbaby12;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Diaper extends Fragment {

    Button confirmButton;
    RadioGroup dirty, weight;
    EditText eDate;
    String date, description;

    public Diaper() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diaper, container, false);
        eDate = view.findViewById(R.id.date);
        dirty = view.findViewById(R.id.radio_group_diaper);
        weight = view.findViewById(R.id.radio_group_full);
        confirmButton = view.findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(view1 -> {
            try {
                StringBuilder sb = new StringBuilder();
                date = eDate.getText().toString();
                switch(dirty.getCheckedRadioButtonId()) {
                    case R.id.radio_wet:
                        sb.append("Diaper is Wet ");
                        break;
                    case R.id.radio_dirt:
                        sb.append("Diaper is Dirty ");
                        break;
                }
                switch(weight.getCheckedRadioButtonId()) {
                    case R.id.radio_light:
                        sb.append("and of light weight.");
                        break;
                    case R.id.radio_medium:
                        sb.append("and of medium weight.");
                        break;
                    case R.id.radio_heavy:
                        sb.append("and of heavy weight.");
                        break;
                }
                description = sb.toString();
                EntityActivity activity = new EntityActivity(
                        date,"Dirty Diaper","",0,description);
                MainMenu.hbDB.daoActivity().insertActivity(activity)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe();
            } catch (Exception e){
                //do nothing
            }
        });


        return view;
    }
}