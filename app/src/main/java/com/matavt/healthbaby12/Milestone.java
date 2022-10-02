package com.matavt.healthbaby12;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import java.util.Date;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class Milestone extends Fragment {

    public Milestone() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_milestone, container, false);

        CheckBox checkBox1 = view.findViewById(R.id.checkbox1);
        CheckBox checkBox2 = view.findViewById(R.id.checkbox2);
        CheckBox checkBox3 = view.findViewById(R.id.checkbox3);
        CheckBox checkBox4 = view.findViewById(R.id.checkbox4);
        CheckBox checkBox5 = view.findViewById(R.id.checkbox5);
        CheckBox checkBox6 = view.findViewById(R.id.checkbox6);
        CheckBox checkBox7 = view.findViewById(R.id.checkbox7);
        CheckBox checkBox8 = view.findViewById(R.id.checkbox8);
        CheckBox checkBox9 = view.findViewById(R.id.checkbox9);
        CheckBox checkBox10 = view.findViewById(R.id.checkbox10);
        CheckBox checkBox11 = view.findViewById(R.id.checkbox11);
        CheckBox checkBox12 = view.findViewById(R.id.checkbox12);

        checkBox1.setOnClickListener(view1 -> {
            Date date = new Date();
            String sDate = date.toString();
            boolean check = ((CheckBox) view1).isChecked();
            EntityMilestone milestone;
            if(check) {
                milestone = new EntityMilestone(1,true,sDate);
            } else {
                milestone = new EntityMilestone(1,false,sDate);
            }
            MainMenu.hbDB.daoMilestone().insertMilestone(milestone)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
        });

        checkBox2.setOnClickListener(view12 -> {
            Date date = new Date();
            String sDate = date.toString();
            boolean check = ((CheckBox) view12).isChecked();
            EntityMilestone milestone;
            if (check) {
                milestone = new EntityMilestone(2, true, sDate);
            } else {
                milestone = new EntityMilestone(2, false, sDate);
            }
            MainMenu.hbDB.daoMilestone().insertMilestone(milestone)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
        });

        checkBox3.setOnClickListener(view13 -> {
            Date date = new Date();
            String sDate = date.toString();
            boolean check = ((CheckBox) view13).isChecked();
            EntityMilestone milestone;
            if (check) {
                milestone = new EntityMilestone(3, true, sDate);
            } else {
                milestone = new EntityMilestone(3, false, sDate);
            }
            MainMenu.hbDB.daoMilestone().insertMilestone(milestone)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
        });

        checkBox4.setOnClickListener(view14 -> {
            Date date = new Date();
            String sDate = date.toString();
            boolean check = ((CheckBox) view14).isChecked();
            EntityMilestone milestone;
            if (check) {
                milestone = new EntityMilestone(4, true, sDate);
            } else {
                milestone = new EntityMilestone(4, false, sDate);
            }
            MainMenu.hbDB.daoMilestone().insertMilestone(milestone)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
        });

        checkBox5.setOnClickListener(view15 -> {
            Date date = new Date();
            String sDate = date.toString();
            boolean check = ((CheckBox) view15).isChecked();
            EntityMilestone milestone;
            if (check) {
                milestone = new EntityMilestone(5, true, sDate);
            } else {
                milestone = new EntityMilestone(5, false, sDate);
            }
            MainMenu.hbDB.daoMilestone().insertMilestone(milestone)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
        });

        checkBox6.setOnClickListener(view16 -> {
            Date date = new Date();
            String sDate = date.toString();
            boolean check = ((CheckBox) view16).isChecked();
            EntityMilestone milestone;
            if (check) {
                milestone = new EntityMilestone(6, true, sDate);
            } else {
                milestone = new EntityMilestone(6, false, sDate);
            }
            MainMenu.hbDB.daoMilestone().insertMilestone(milestone)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
        });

        checkBox7.setOnClickListener(view17 -> {
            Date date = new Date();
            String sDate = date.toString();
            boolean check = ((CheckBox) view17).isChecked();
            EntityMilestone milestone;
            if (check) {
                milestone = new EntityMilestone(7, true, sDate);
            } else {
                milestone = new EntityMilestone(7, false, sDate);
            }
            MainMenu.hbDB.daoMilestone().insertMilestone(milestone)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
        });

        checkBox8.setOnClickListener(view18 -> {
            Date date = new Date();
            String sDate = date.toString();
            boolean check = ((CheckBox) view18).isChecked();
            EntityMilestone milestone;
            if (check) {
                milestone = new EntityMilestone(8, true, sDate);
            } else {
                milestone = new EntityMilestone(8, false, sDate);
            }
            MainMenu.hbDB.daoMilestone().insertMilestone(milestone)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
        });

        checkBox9.setOnClickListener(view19 -> {
            Date date = new Date();
            String sDate = date.toString();
            boolean check = ((CheckBox) view19).isChecked();
            EntityMilestone milestone;
            if (check) {
                milestone = new EntityMilestone(9, true, sDate);
            } else {
                milestone = new EntityMilestone(9, false, sDate);
            }
            MainMenu.hbDB.daoMilestone().insertMilestone(milestone)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
        });

        checkBox10.setOnClickListener(view110 -> {
            Date date = new Date();
            String sDate = date.toString();
            boolean check = ((CheckBox) view110).isChecked();
            EntityMilestone milestone;
            if (check) {
                milestone = new EntityMilestone(10, true, sDate);
            } else {
                milestone = new EntityMilestone(10, false, sDate);
            }
            MainMenu.hbDB.daoMilestone().insertMilestone(milestone)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
        });

        checkBox11.setOnClickListener(view111 -> {
            Date date = new Date();
            String sDate = date.toString();
            boolean check = ((CheckBox) view111).isChecked();
            EntityMilestone milestone;
            if (check) {
                milestone = new EntityMilestone(11, true, sDate);
            } else {
                milestone = new EntityMilestone(11, false, sDate);
            }
            MainMenu.hbDB.daoMilestone().insertMilestone(milestone)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
        });

        checkBox12.setOnClickListener(view112 -> {
            Date date = new Date();
            String sDate = date.toString();
            boolean check = ((CheckBox) view112).isChecked();
            EntityMilestone milestone;
            if (check) {
                milestone = new EntityMilestone(12, true, sDate);
            } else {
                milestone = new EntityMilestone(12, false, sDate);
            }
            MainMenu.hbDB.daoMilestone().insertMilestone(milestone)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
        });

        return view;
    }
}