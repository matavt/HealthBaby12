/*
Fragment to allow the user to select what recorded data they want to view.
Each child fragment is loaded into the fragment on selection of the corresponding button
 */
package com.matavt.healthbaby12;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Records extends Fragment {

    FragmentManager fragMan;
    FragmentTransaction fragTran;
    public Records() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_records, container, false);
        Button weightButton = view.findViewById(R.id.weightHeightButton);
        weightButton.setOnClickListener(view1 -> callFragment(new RecordWeightHeight()));
        Button activityButton = view.findViewById(R.id.activitiesButton);
        activityButton.setOnClickListener(view1 -> callFragment(new RecordActivity()));
        Button milestoneButton = view.findViewById(R.id.milestoneButton);
        milestoneButton.setOnClickListener(view1 -> callFragment(new RecordMilestone()));

        return view;
    }

    void callFragment(Fragment fragment){
        fragMan = getParentFragmentManager();
        fragTran = fragMan.beginTransaction();
        fragTran.replace(R.id.recordFrame, fragment).setReorderingAllowed(true).addToBackStack(null);
        fragTran.commit();
    }
}