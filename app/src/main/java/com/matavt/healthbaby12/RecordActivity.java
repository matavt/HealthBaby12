package com.matavt.healthbaby12;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RecordActivity extends Fragment {

    TextView data;
    String dataString;
    Button sendButton;

    public RecordActivity() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_record_activity, container, false);
        data = view.findViewById(R.id.recordText);
        final Disposable subscribe = MainMenu.hbDB.daoActivity().getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        list -> {
                            StringBuilder sb = new StringBuilder();
                            for (EntityActivity entity : list) {
                                sb.append(entity.toString()).append("\n");
                            }
                            dataString = sb.toString();
                            data.setText(dataString);
                        });

        sendButton = view.findViewById(R.id.sendButton);
        sendButton.setOnClickListener(view1 -> {
            String fileName = User.getInstance().getChildName() + "_activities.txt";
            File file = new File(getContext().getCacheDir(), fileName);
            try {
                FileWriter inFile= new FileWriter(file);
                inFile.append(dataString);
                inFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String subject = User.getInstance().getChildName() + " Activity file";
            String message = "Please see attached file containing a record of activities for " + User.getInstance().getChildName();
            message = message + "\n regards \n" + User.getInstance().getUserName();

            final Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setType("plain/text");
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
            emailIntent.putExtra(Intent.EXTRA_TEXT, message);
            emailIntent.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(getActivity(),"com.matavt.healthbaby12.fileProvider",file));

            startActivity(emailIntent);

        });

        return view;
    }
}