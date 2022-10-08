package com.matavt.healthbaby12;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ActivityEntry extends Fragment {

    FragmentManager fragMan;
    FragmentTransaction fragTran;
    Button bottleButton, breastButton, foodButton, liquidButton, diaperButton,sleepButton;
    TextView data;


    public ActivityEntry() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity_entry, container, false);

        data = view.findViewById(R.id.activityData);

        bottleButton = (Button) view.findViewById(R.id.bottleButton);
        bottleButton.setOnClickListener(view1 -> callFragment(new Bottle()));
        breastButton = (Button) view.findViewById(R.id.breastButton);
        breastButton.setOnClickListener(view12 -> callFragment(new BreastFeed()));
        foodButton = (Button) view.findViewById(R.id.foodButton);
        foodButton.setOnClickListener(view13 -> callFragment(new Food()));
        liquidButton = (Button) view.findViewById(R.id.liquidButton);
        liquidButton.setOnClickListener(view14 -> callFragment(new Liquid()));
        diaperButton = (Button) view.findViewById(R.id.diaperButton);
        diaperButton.setOnClickListener(view15 -> callFragment(new Diaper()));
        sleepButton = (Button) view.findViewById(R.id.sleepButton);
        sleepButton.setOnClickListener(view16 -> callFragment(new Sleep()));

        final Disposable subscribe = MainMenu.hbDB.daoActivity().getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        list -> {
                            StringBuilder sb = new StringBuilder();
                            for (EntityActivity entity : list) {
                                sb.append(entity.toString()).append("\n");
                            }
                            data.setText(sb.toString());
                        });
        subscribe.dispose();
        return view;
    }

    void callFragment(Fragment fragment){
        fragMan = getParentFragmentManager();
        fragTran = fragMan.beginTransaction();
        fragTran.replace(R.id.activityFrame, fragment).setReorderingAllowed(true).addToBackStack(null);
        fragTran.commit();
    }
}