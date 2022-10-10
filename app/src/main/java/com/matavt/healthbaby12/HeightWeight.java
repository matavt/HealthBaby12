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
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HeightWeight extends Fragment {

    Button confirmButton;
    String sWeight, sHeight;
    float weight, height;
    EditText eWeight, eHeight;
    TextView data;

    public HeightWeight() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_height_weight, container, false);

        eWeight = view.findViewById(R.id.weightTextInput);
        eHeight = view.findViewById(R.id.heightTextInput);
        data = view.findViewById(R.id.weightHeightData);
        final Disposable subscribe = MainMenu.hbDB.daoHeightWeight().getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        list -> {
                            StringBuilder sb = new StringBuilder();
                            for (EntityHeightWeight entity : list) {
                                sb.append(entity.toString()).append("\n");
                            }
                            data.setText(sb.toString());
                        }
                );

        confirmButton = view.findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(view1 -> {
            try {
                sWeight = eWeight.getText().toString();
                weight = Float.parseFloat(sWeight);
                sHeight = eHeight.getText().toString();
                height = Float.parseFloat(sHeight);
                EntityHeightWeight heightWeight = new EntityHeightWeight(weight, height);
                MainMenu.hbDB.daoHeightWeight().insertHeightWeight(heightWeight)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe();
                eWeight.setText("");
                eHeight.setText("");
            }
            catch (Exception e) {
                data.setText(String.format("error: %s", e));
            }
            });

        return view;
    }

}