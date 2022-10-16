/*
Main Fragment for recording a milestone this is inflated in the the fragment frame of the
MainMenu activity.

The list is inflated to a ListView with an implementation of ArrayAdapter.

Details are then save to the Room database instance.
*/
package com.matavt.healthbaby12;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.matavt.healthbaby12.EntityMilestone;
import com.matavt.healthbaby12.MainMenu;
import com.matavt.healthbaby12.MilestoneAdapter;

import java.util.Arrays;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Milestone extends Fragment {

    private ListView milestoneList;
    //List of milestones
    private final String[] milestoneName = {
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
    boolean[] checked;


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
        //Set the checked array is the same size as the milestoneName array
        checked = new boolean[milestoneName.length];

        //We can't inflate the ListView in the main thread as use room DB calls to only allow
        //milestone that haven't been achieved to be checked off.
        //This also ensure it is continuously updated without locking the UI
        Disposable subscribe = MainMenu.hbDB.daoMilestone().getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        list -> {
                            //Milestones are added to the DB as they are checked off so we need to
                            //initialise the array with False then get the entries that have been written.
                            Arrays.fill(checked, false);
                            for (EntityMilestone entity : list) {
                                checked[entity.getId()] = entity.getAchieved();
                            }
                            MilestoneAdapter milestoneAdapter = new MilestoneAdapter(getContext(),
                                    milestoneName, checked);
                            milestoneList.setAdapter(milestoneAdapter);
                        }
                );
        return view;
    }
}