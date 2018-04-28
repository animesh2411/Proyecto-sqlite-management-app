package com.example.shubham.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by SHUBHAM on 16-07-2017.
 */

public class ProjectFragment extends Fragment implements AdapterView.OnItemClickListener {
    ListView lv;
    ArrayList<String> account;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.projectfragment, container, false);
        lv = (ListView) rootView.findViewById(R.id.lv);
        account = new ArrayList<>();

        fillist();
        ArrayAdapter<String>
                ad = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, account);
        lv.setAdapter(ad);
        lv.setOnItemClickListener(this);
        return rootView;
    }

    public void fillist() {
        account.add("Add Project");
        account.add("View Project");


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String ac = account.get(position);
        if (ac.equals("Add Project")) {
            Intent i = new Intent(getContext(), AddProject.class);
            startActivity(i);
        } else if (ac.equals("View Project")) {
            Intent o = new Intent(getContext(), ViewProject.class);
            startActivity(o);

        }
    }
}
