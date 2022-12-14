package com.matavt.healthbaby12;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import java.util.GregorianCalendar;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MilestoneAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] mileStoneName;
    private final boolean[] checked;


    public MilestoneAdapter(Context context, String[] mileStoneName, boolean[] checked) {
        super(context, R.layout.single_milstone, R.id.milestoneCheck,mileStoneName);
        this.context = context;
        this.mileStoneName = mileStoneName;
        this.checked = checked;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View singleMilestone = convertView;
        MilestoneViewHolder milestoneViewHolder;
        //We don't need to inflate a view that is already inflated.
        if(singleMilestone == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            singleMilestone = layoutInflater.inflate(R.layout.single_milstone, parent, false);
            milestoneViewHolder = new MilestoneViewHolder(singleMilestone);
            singleMilestone.setTag(milestoneViewHolder);
        } else {
            milestoneViewHolder = (MilestoneViewHolder) singleMilestone.getTag();
        }
        milestoneViewHolder.checkBox.setText(mileStoneName[position]);
        milestoneViewHolder.checkBox.setChecked(checked[position]);
        if(checked[position]){
            milestoneViewHolder.checkBox.setEnabled(false);
        }
        //As a milestone is checked off the entry in the RoomDB is updated with the milestone details
        // and today's date.
        //Milestones are only written to the DB as they are checked off.
        milestoneViewHolder.checkBox.setOnClickListener(view -> {
            GregorianCalendar calendar = new GregorianCalendar();
            boolean check = ((CheckBox) view).isChecked();
            EntityMilestone milestone = new EntityMilestone(position,check,calendar,mileStoneName[position]);
            MainMenu.hbDB.daoMilestone().insertMilestone(milestone)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
        });
        return singleMilestone;
    }
}
