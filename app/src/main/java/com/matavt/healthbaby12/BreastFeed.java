package com.matavt.healthbaby12;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class BreastFeed extends Fragment {

    Button confirmButton;
    EditText eStartDate, eEndDate, eBreast;
    String startDate, endDate, breast;

    public BreastFeed() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_breast_feed, container, false);
        eStartDate = view.findViewById(R.id.startDate);
        eEndDate = view.findViewById(R.id.endDate);
        eBreast = view.findViewById(R.id.breastInput);
        confirmButton = view.findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(view1 -> {
            try {
                startDate = eStartDate.getText().toString();
                endDate = eEndDate.getText().toString();
                breast = eBreast.getText().toString();
                EntityActivity activity = new EntityActivity("Breast Feed");
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