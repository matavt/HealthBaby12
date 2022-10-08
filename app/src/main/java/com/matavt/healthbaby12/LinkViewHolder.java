package com.matavt.healthbaby12;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class LinkViewHolder {
    TextView title;
    TextView description;

    LinkViewHolder(View view) {
        title = view.findViewById(R.id.textTitle);
        description = view.findViewById(R.id.textDescription);
    }

}
