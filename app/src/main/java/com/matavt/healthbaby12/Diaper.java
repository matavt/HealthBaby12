package com.matavt.healthbaby12;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.util.Calendar;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Diaper extends Fragment {

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button confirmButton, datePickerButton, timePickerButton;
    RadioGroup dirty, weight;
    private int[] dateArray, timeArray;

    public Diaper() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diaper, container, false);
        initDatePicker();
        initTimePicker();

        dirty = view.findViewById(R.id.radio_group_diaper);
        weight = view.findViewById(R.id.radio_group_full);

        datePickerButton=view.findViewById(R.id.datePickerButton);
        datePickerButton.setText(DateFunctions.getTodaysDate());
        datePickerButton.setOnClickListener(this::openDatePicker);

        timePickerButton=view.findViewById(R.id.timePickerButton);
        timePickerButton.setText("08:00");
        timePickerButton.setOnClickListener(this::openTimePicker);

        confirmButton = view.findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(view1 -> {

            try {
                StringBuilder sb = new StringBuilder();
                sb.append(DateFunctions.createStringFromDate(dateArray[0], dateArray[1], dateArray[2]));
                sb.append(" : ");
                switch(weight.getCheckedRadioButtonId()) {
                    case R.id.radio_light:
                        sb.append("Light ");
                        break;
                    case R.id.radio_medium:
                        sb.append("Normal ");
                        break;
                    case R.id.radio_heavy:
                        sb.append("Heavy ");
                        break;
                }

                switch(dirty.getCheckedRadioButtonId()) {
                    case R.id.radio_wet:
                        sb.append("Wet Diaper changed at ");
                        break;
                    case R.id.radio_dirt:
                        sb.append("Dirty Diaper changed at  ");
                        break;
                }
                sb.append(DateFunctions.createStringFromTime(timeArray[0],timeArray[1]));
                EntityActivity activity = new EntityActivity(sb.toString());
                MainMenu.hbDB.daoActivity().insertActivity(activity)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe();
            } catch (Exception e){
                //do nothing
            }
        });


        return view;
    }

    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, month, day) -> {
            month = month + 1;
            String dateString = DateFunctions.createStringFromDate(year, month, day);
            datePickerButton.setText(dateString);
            dateArray = new int[]{year, month, day};
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(getContext(), dateSetListener, year, month, day);
    }

    public void openDatePicker(View view){
        datePickerDialog.show();
    }

    private void initTimePicker(){
        TimePickerDialog.OnTimeSetListener timeSetListener = (timePicker, hour, minute) -> {
            String timeString = DateFunctions.createStringFromTime(hour, minute);
            timePickerButton.setText(timeString);
            timeArray = new int[]{hour,minute};
        };

        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR);
        int minute= cal.get(Calendar.MINUTE);
        timePickerDialog = new TimePickerDialog(getContext(), timeSetListener,hour,minute,true);
    }

    public void openTimePicker(View view) {
        timePickerDialog.show();
    }
}