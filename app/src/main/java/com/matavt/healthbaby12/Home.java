package com.matavt.healthbaby12;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Home extends Fragment {

    FragmentManager fragMan;
    FragmentTransaction fragTran;
    Button heightWeightButton, activityButton, milestoneButton, recordButton, linkButton;

    public Home() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        heightWeightButton = view.findViewById(R.id.fragHomeWeightHeight);
        heightWeightButton.setOnClickListener(view1 -> callFragment(new HeightWeight()));
        activityButton = view.findViewById(R.id.fragActivity);
        activityButton.setOnClickListener(view13 -> callFragment(new ActivityEntry()));
        milestoneButton = view.findViewById(R.id.fragRecordMilestone);
        milestoneButton.setOnClickListener(view12 -> callFragment(new Milestone()));
        recordButton = view.findViewById(R.id.fragReviewRecord);
        recordButton.setOnClickListener(view13 -> callFragment(new Records()));
        linkButton = view.findViewById(R.id.fragLinks);
        linkButton.setOnClickListener(view14 -> callFragment(new Links()));

        return view;
    }

    void callFragment(Fragment fragment){
        fragMan = getParentFragmentManager();
        fragTran = fragMan.beginTransaction();
        fragTran.replace(R.id.fragmentFrame, fragment).setReorderingAllowed(true).addToBackStack(null);
        fragTran.commit();
    }
}