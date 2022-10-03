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

public class Food extends Fragment {

    Button confirmButton;
    EditText eDate, eVolume, eDescription;
    String date, description;
    float volume;

    public Food() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food, container, false);
        eDate = view.findViewById(R.id.date);
        eDescription = view.findViewById(R.id.description);
        eVolume = view.findViewById(R.id.volume);
        confirmButton = view.findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(view1 -> {
            try {
                date = eDate.getText().toString();
                description = eDescription.getText().toString();
                volume = Float.parseFloat(eVolume.getText().toString());
                EntityActivity activity = new EntityActivity("Eaten Food");
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