package com.example.shubham.proyecto;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by SHUBHAM on 15-07-2017.
 */

public class ReportFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    Spinner type,select;
    StudentManager sm;
    SQLiteDatabase sb;
    ArrayList<String >ty;
    ArrayList<String>courses;
    ArrayList<String>training;
    ArrayAdapter<String > ad, tr, cr;
    String name, tid, cid;
    Button btnreport, btnassign;
    EditText txtassign;
    StudentBean s = new StudentBean(), s1 = new StudentBean();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.reportfragment, container, false);
        type = (Spinner) rootView.findViewById(R.id.type);
        select = (Spinner) rootView.findViewById(R.id.select);
        btnreport = (Button) rootView.findViewById(R.id.btnreport);
        btnreport.setOnClickListener(this);
        btnassign = (Button) rootView.findViewById(R.id.btnassign);
        btnassign.setOnClickListener(this);
        txtassign = (EditText) rootView.findViewById(R.id.txtassign);
        sm = new StudentManager(getContext());
        sb = sm.openDb();
        type.setOnItemSelectedListener(this);
        courses = new ArrayList<>();
        training = new ArrayList<>();
        ty=new ArrayList<>();

        filllist();
        ad=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,ty);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(ad);

        return rootView ;
    }

    private void filllist() {
        ty.add("Course Wise");
        ty.add("Project category type/Training type");
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
      name= (String) type.getSelectedItem();
        select.setAdapter(null);
        courses.clear();
        training.clear();
//        cr.notifyDataSetChanged();
//        tr.notifyDataSetChanged();


        Toast.makeText(getContext(), name, Toast.LENGTH_SHORT).show();

        if(name.equalsIgnoreCase("Course Wise")){
            fillCourse();
        }
        if(name.equalsIgnoreCase("Project category type/Training type")){
            fillTraining();
        }

//        select.setAdapter(cr);
//        select.setAdapter(tr);

    }

    private void fillTraining() {
        Cursor v=sb.query(StudentConstant.TBL_STUDENT,null,null,null,null,null,null, null);
        if(v!=null && v.moveToFirst())
        {
            do{
                tid=v.getString(v.getColumnIndex(StudentConstant.COL_PID));
                training.add(tid);
//                String p=v.getString(v.getColumnIndex(StudentConstant.COL_CATEGORYNAME));
//                training.add(String.format("Training ID: %s\nTraining Name: %s", q, p));
            } while(v.moveToNext());
            v.close();
        }
        tr=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,training);
        tr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select.setAdapter(tr);
    }

    private void fillCourse() {
        Cursor c=sb.query(StudentConstant.TBL_STUDENT, null, null, null, null, null, null);
        if(c!=null && c.moveToFirst())
        {
            do{
                cid=c.getString(c.getColumnIndex(StudentConstant.COL_CID));
//                String b=c.getString(c.getColumnIndex(StudentConstant.COL_COURSENAME));
//                courses.add(String.format("Course ID: %s\nCourse Name: %s", a, b));
//                courses.add(s);
                courses.add(cid);
            }while(c.moveToNext());
            c.close();
        }
        cr=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,courses);
        cr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select.setAdapter(cr);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnreport) {
            String mm = (String) select.getSelectedItem();
            if (name.equals("Course Wise")) {
                Intent i = new Intent(getContext(), ViewCReports.class);
                i.putExtra("id", mm);
                startActivity(i);
            }
            if (name.equals("Project category type/Training type")) {
                Intent i = new Intent(getContext(), ViewRReports.class);
                i.putExtra("id", mm);
                startActivity(i);

            }
        }
        if (v.getId() == R.id.btnassign) {

            if(txtassign.getText().toString().length()<=0){
                Toast.makeText(getContext(), "Field entry required!", Toast.LENGTH_SHORT).show();
            }
            else {
                Intent i = new Intent(getContext(), ViewAllotments.class);
                i.putExtra("assign", Integer.parseInt(txtassign.getText().toString()));
                startActivity(i);
            }
        }
    }
}

