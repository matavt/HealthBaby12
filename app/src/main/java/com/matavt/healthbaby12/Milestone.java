package com.matavt.healthbaby12;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.Arrays;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class Milestone extends Fragment {

    ListView milestoneList;
    String[] milestoneName = {
            "Pushes up on arms",
            "Lifts and holds head up",
            "Uses hands to support self while sitting",
            "Rolls from tummy to back",
            "Stands with support",
            "Able to sit and reach without falling",
            "Moves to sitting position",
            "Crawling",
            "Pulls self to standing and walks while holding",
            "First steps unsupported",
            "Walks on own rarely falls",
            "Squats to pickup items"
    };
    boolean checked[];


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
        milestoneList = view.findViewById(R.id.milestoneList);
        checked = new boolean[milestoneName.length];

        MainMenu.hbDB.daoMilestone().getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        list -> {
                            Arrays.fill(checked, false);
                            for (EntityMilestone entity: list) {
                                checked[entity.getId()] = entity.getAchieved();
                            }
                            MilestoneAdapter milestoneAdapter = new MilestoneAdapter(getContext(),
                                    milestoneName,checked);
                            milestoneList.setAdapter(milestoneAdapter);
                        }
                );
        return view;
    }
}