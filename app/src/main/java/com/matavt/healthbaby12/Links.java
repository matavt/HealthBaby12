/*
Fragment that displays weblinks this is inflated into a fragment frame in the MainMenu Activity
 */

package com.matavt.healthbaby12;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class Links extends Fragment {

    ListView linksList;

    //Data that forms the basis of the URL list
    private final String[] urls = {
            "https://www.pregnancybirthbaby.org.au/",
            "https://www.breastfeeding.asn.au/",
            "https://raisingchildren.net.au/",
            "http://parentlineact.org.au/",
            "https://www.parentline.org.au/",
            "https://parentline.com.au/",
            "https://www.cafhs.sa.gov.au/",
            "https://panda.org.au/"
    };
    private final String[] titles = {
            "Pregnancy Birth & Baby",
            "Australian Breastfeeding Association",
            "Raising Children",
            "Parent line ACT",
            "Parent line NSW",
            "Parent line QLD & NT",
            "Child and Family Health Service SA",
            "Perinatal Anxiety & Depression Australia"
    };
    private final String[] description = {
            "Australian website supporting parents from pregnancy to preschool",
            "Support and information for breast feeding",
            "Australian Parenting website",
            "Parent Support helpline for the ACT",
            "Parent Support helpline for NSW",
            "Parent Support helpline for QLD and the NT",
            "Parenting support services ofr SA",
            "Mental health service for expecting, new and growing families"
    };

    public Links() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_links, container, false);

        linksList = view.findViewById(R.id.linksList);
        //LinksAdapter is an implementation of the ArrayAdapter  used to populate the ListView
        LinksAdapter linksAdapter = new LinksAdapter(getContext(), titles, description, urls);
        linksList.setAdapter(linksAdapter);

        return view;
    }
}