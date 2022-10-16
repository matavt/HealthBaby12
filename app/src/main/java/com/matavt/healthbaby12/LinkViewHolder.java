//Holds a single view from the LinksAdapter
package com.matavt.healthbaby12;

import android.view.View;
import android.widget.TextView;

public class LinkViewHolder {
    public final TextView title;
    public final TextView description;

    LinkViewHolder(View view) {
        title = view.findViewById(R.id.textTitle);
        description = view.findViewById(R.id.textDescription);
    }
}
