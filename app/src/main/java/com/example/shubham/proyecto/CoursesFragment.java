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

public class CoursesFragment extends Fragment implements AdapterView.OnItemClickListener {
    ArrayList<String> account;
    ListView lv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.coursesfragment, container, false);
        lv = (ListView) rootView.findViewById(R.id.lv);
        account = new ArrayList<>();
        ArrayAdapter<String>
                ad = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, account);
        fillist();
        lv.setAdapter(ad);
        lv.setOnItemClickListener(this);
        return rootView;
    }

    public void fillist() {
        account.add("Add Training");
        account.add("Add Courses");
        account.add("View Courses");
        account.add("View Training");


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String ac = account.get(position);
        if (ac.equals("Add Courses")) {
            Intent i = new Intent(getContext(), AddCourses.class);
            startActivity(i);
        } else if (ac.equals("Add Training")) {
            Intent o = new Intent(getContext(), AddTraining.class);
            startActivity(o);

        } else if (ac.equals("View Courses")) {
            Intent o = new Intent(getContext(), ViewCourses.class);
            startActivity(o);

        }
        else if (ac.equals("View Training")) {
            Intent o = new Intent(getContext(), ViewTraining.class);
            startActivity(o);

        }
    }
}
