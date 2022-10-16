/*
Child fragment of Records this displays the recorded achieved milestones with date of achievement.
 */

package com.matavt.healthbaby12;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RecordMilestone extends Fragment {

    public RecordMilestone() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_record_milestone, container, false);
        TextView data = view.findViewById(R.id.recordMilestoneList);
        //Load and display information from the RoomDB
        final Disposable subscribe = MainMenu.hbDB.daoMilestone().getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        list -> {
                            StringBuilder sb = new StringBuilder();
                            for (EntityMilestone entity : list) {
                                if (entity.getAchieved()) {
                                    sb.append(entity).append("\n\n");
                                }
                            }
                            data.setText(sb.toString());
                        }
                );
        return view;
    }
}