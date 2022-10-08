package com.matavt.healthbaby12;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


public class LinksAdapter extends ArrayAdapter<String> {

    Context context;
    String[] titles;
    String[] description;
    String[] urls;

    public LinksAdapter(Context context, String[] titles, String[] description, String[] urls) {
        super(context, R.layout.single_link, titles);
        this.context = context;
        this.titles = titles;
        this.description = description;
        this.urls = urls;
    }

    @Override
    public View getView(final  int position, View convertView, ViewGroup parent) {
        View singleItem = convertView;
        LinkViewHolder holder = null;
        if (singleItem == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            singleItem = layoutInflater.inflate(R.layout.single_link, parent, false);
            holder = new LinkViewHolder(singleItem);
            singleItem.setTag(holder);
        } else {
            holder = (LinkViewHolder) singleItem.getTag();
        }

        holder.title.setText(titles[position]);
        holder.description.setText(description[position]);
        singleItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urls[position]));
                context.startActivity(intent);
            }
        });
        return singleItem;
    }
}
