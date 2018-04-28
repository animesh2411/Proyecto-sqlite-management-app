package com.example.shubham.proyecto;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by SHUBHAM on 17-07-2017.
 */

public class EnrollmentFragment extends Fragment implements View.OnClickListener{
    EditText txtsid,txtname,txtadd,txtpno,txtemail;
    StudentManager sm;
    SQLiteDatabase sb;
    Spinner spcid,sppid;
    ArrayList<String>course;
    Button btn;
    ArrayList<String>project;
//    RadioGroup rg;
//    RadioButton rb;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.enrollmentfragment, container, false);
        txtsid=(EditText)rootView.findViewById(R.id.txtsid);
        txtname=(EditText)rootView.findViewById(R.id.txtname);
        txtadd=(EditText)rootView.findViewById(R.id.txtadd);
        txtpno=(EditText)rootView.findViewById(R.id.txtpno);
        txtemail=(EditText)rootView.findViewById(R.id.txtemail);
      //  txtstatus=(EditText)rootView.findViewById(R.id.txtstatus);
//        rg=(RadioGroup)rootView.findViewById(R.id.rg);
        txtpno.setText("0");

        sm=new StudentManager(getContext());
        sb=sm.openDb();

        btn=(Button)rootView.findViewById(R.id.btn);
        spcid=(Spinner)rootView.findViewById(R.id.spcid);
//        sppid=(Spinner)rootView.findViewById(R.id.sppid);
        course=new ArrayList<>();
        project=new ArrayList<>();
        filllist();
        ArrayAdapter<String> ad=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,course);
//        ArrayAdapter<String> adi=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,project);

        spcid.setAdapter(ad);
//        sppid.setAdapter(adi);
        btn.setOnClickListener(this);

        return rootView;

    }
    public void filllist()
    {
        Cursor c=sb.query(StudentConstant.TBL_COURSE,null,null,null,null,null,null,null);
        if(c!=null && c.moveToFirst())
        {
            do{
                String id=c.getString(c.getColumnIndex(StudentConstant.COL_CID));
                course.add(id);

            }while(c.moveToNext());

            c.close();
        }
//        Cursor cw=sb.query(StudentConstant.TBL_PROJECT,null,null,null,null,null,null,null);
//        if(cw!=null && cw.moveToFirst())
//        {
//            do{
//                String idi=cw.getString(cw.getColumnIndex(StudentConstant.COL_PROJECTID));
//                project.add(idi);
//
//            }while(cw.moveToNext());
//
//            cw.close();
//        }
    }

    @Override
    public void onClick(View v) {
//        int id=rg.getCheckedRadioButtonId();
//        rb=(RadioButton)rg.findViewById(id);
//        String status=rb.getText().toString();
        String sid=txtsid.getText().toString();
        String name=txtname.getText().toString();
        String address=txtadd.getText().toString();
        long pno = Long.parseLong(txtpno.getText().toString());
        String email=txtemail.getText().toString();
      //  String status=txtstatus.getText().toString();
        String cid=spcid.getSelectedItem().toString();
//        String pid=sppid.getSelectedItem().toString();
        String pid =null;

        boolean fieldsOK = validate(new EditText[]{txtsid, txtname, txtadd, txtpno, txtemail});
        if (!fieldsOK) {
            Toast.makeText(getContext(), "Fill all fields!", Toast.LENGTH_SHORT).show();
        } else if (fieldsOK) {

            ContentValues cv = new ContentValues();

            cv.put(StudentConstant.COL_STUDENTID, sid);
            cv.put(StudentConstant.COL_NAME, name);
            cv.put(StudentConstant.COL_ADDRESS, address);
            cv.put(StudentConstant.COl_PHNO, pno);
            cv.put(StudentConstant.COL_EMAILID, email);
            cv.put(StudentConstant.COL_CID, cid);
            cv.put(StudentConstant.COL_PID, pid);
            cv.put(StudentConstant.COL_SUBMITTED, "not submitted");

            long rw = sb.insert(StudentConstant.TBL_STUDENT, null, cv);
            if (rw > 0) {
                Toast.makeText(getContext(), "Student Sucessfully added", Toast.LENGTH_LONG).show();
                txtsid.setText("");
                txtname.setText("");
                txtadd.setText("");

                txtpno.setText("0");
                txtemail.setText("");
                // txtdetails.setText(" ");
            }
        }
        if(sm!=null)
        {
            sm.closeDb();
        }



    }
    private boolean validate(EditText[] fields){
        for(int i=0; i<fields.length; i++){
            EditText currentField=fields[i];
            if(currentField.getText().toString().length()<=0){
                return false;
            }
        }
        return true;
    }


}
