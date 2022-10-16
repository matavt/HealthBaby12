/*
This fragment is displayed in the childFrame fragment frame of the MainActivity only if a child
hasn't previously been created.
 */

package com.matavt.healthbaby12;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;
import java.util.GregorianCalendar;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AddChild extends Fragment {

    private DatePickerDialog datePickerDialog;
    private Button datePickerButton;
    private EditText eName;
    private int[] dateArray;

    public AddChild() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_child, container, false);

        //Build the dataPicker dialog and set it to display today's date by default
        initDatePicker();
        datePickerButton = view.findViewById(R.id.datePickerButton);
        datePickerButton.setText(DateFunctions.getTodaysDate());
        datePickerButton.setOnClickListener(this::openDatePicker);

        eName = view.findViewById(R.id.childName);
        Button confirmButton = view.findViewById(R.id.confirmButton);

        //on confirmation write the child's information to the room DB instance
        confirmButton.setOnClickListener(view1 -> {
            String name = eName.getText().toString();
            EntityChild child = new EntityChild(name, new GregorianCalendar(dateArray[0], dateArray[1],dateArray[2]));
            MainMenu.hbDB.daoChild().insertChild(child)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
        });
        return view;
    }

    //Setups the DatePicker dialog the date is written to the dataArray.
    //The dialog is set to display today's date.
    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, month, day) -> {
            String dateString = DateFunctions.createStringFromDate(year, month+1, day);
            datePickerButton.setText(dateString);
            dateArray = new int[]{year, month, day};
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(getContext(), dateSetListener, year, month, day);
    }

    public void openDatePicker(View view){
        datePickerDialog.show();
    }

}