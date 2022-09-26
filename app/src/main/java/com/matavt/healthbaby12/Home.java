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
    Button heightWeightButton;

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
        heightWeightButton = (Button) view.findViewById(R.id.fragHomeWeightHeight);
        heightWeightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFragment(new HeightWeight());
//                loadWeightHeight();
            }
        });

        return view;
    }

//    void loadWeightHeight(){
//        fragMan = getParentFragmentManager();
//        fragTran = fragMan.beginTransaction();
//        fragTran.replace(R.id.fragmentFrame, new HeightWeight()).setReorderingAllowed(true).addToBackStack(null);
//        fragTran.commit();
//    }

    void callFragment(Fragment fragment){
        fragMan = getParentFragmentManager();
        fragTran = fragMan.beginTransaction();
        fragTran.replace(R.id.fragmentFrame, fragment).setReorderingAllowed(true).addToBackStack(null);
        fragTran.commit();
    }
}