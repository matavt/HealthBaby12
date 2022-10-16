//Holds a single milestone view.
package com.matavt.healthbaby12;

import android.view.View;
import android.widget.CheckBox;

public class MilestoneViewHolder {
    public final CheckBox checkBox;

    MilestoneViewHolder(View view){
        checkBox = view.findViewById(R.id.milestoneCheck);
    }
}
