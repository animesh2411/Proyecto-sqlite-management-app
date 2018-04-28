package com.example.shubham.proyecto;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by SHUBHAM on 17-07-2017.
 */

public class ProjectStatusFragment extends Fragment implements View.OnClickListener{
  EditText txtsid;
    RadioGroup rg;
    RadioButton rb;
    Button btnstatus,btnshow;
    StudentManager sm;
    SQLiteDatabase sb;
    Spinner sp;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.projectstatusfragment, container, false);

        sp=(Spinner)rootView.findViewById(R.id.sp) ;
        btnstatus=(Button)rootView.findViewById(R.id.btnstatus);
        btnshow=(Button)rootView.findViewById(R.id.btnshow) ;

        txtsid=(EditText)rootView.findViewById(R.id.txtsid);
        rg=(RadioGroup)rootView.findViewById(R.id.rg);
        btnstatus.setOnClickListener(this);
        btnshow.setOnClickListener(this);

        sm=new StudentManager(getContext());
        sb=sm.openDb();
        return rootView;

    }

    @Override
    public void onClick(View v) {

            if (v.getId()== R.id.btnstatus) {
                String id = txtsid.getText().toString();
                int idi = rg.getCheckedRadioButtonId();
                rb = (RadioButton) rg.findViewById(idi);
                String args[] = {id};
                String status = rb.getText().toString();


                if(id.length()<=0 && rb.getId()==idi){
                    Toast.makeText(getContext(), "Field entry required!", Toast.LENGTH_SHORT).show();
                }
                else {
                    ContentValues cv = new ContentValues();
                    cv.put(StudentConstant.COL_SUBMITTED, status);
                    long l = sb.update(StudentConstant.TBL_STUDENT, cv, StudentConstant.COL_STUDENTID + " =?", args);
                    if (l > 0) {
                        Toast.makeText(getContext(), "updated", Toast.LENGTH_LONG).show();

                    }
                }
            }

            if (v.getId() == R.id.btnshow) {
                String s=(String)sp.getSelectedItem();
                System.out.println(s);
                Intent i=new Intent(getContext(),ViewStudent.class);
                i.putExtra("status",s);
                startActivity(i);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(sm!=null)
            sm.closeDb();

    }
}
