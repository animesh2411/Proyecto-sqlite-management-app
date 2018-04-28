package com.example.shubham.proyecto;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by SHUBHAM on 16-07-2017.
 */

public class AllotmentFragment extends Fragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    EditText txtaid,txtdate;
    StudentManager sm;
    DatePickerDialog dlg;
    SQLiteDatabase sb;
    Spinner psp;Spinner ssp;
    Button btn, btndate;
    int year, month, day;
    String args[];

    ArrayList<String>project;
    ArrayList<String>student;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.allotmentfragment, container, false);
         psp=(Spinner)rootView.findViewById(R.id.psp);
        ssp=(Spinner)rootView.findViewById(R.id.ssp);
        txtaid=(EditText)rootView.findViewById(R.id.txtaid);
        txtdate=(EditText)rootView.findViewById(R.id.txtdate);
        btn=(Button)rootView.findViewById(R.id.btn);
        btndate=(Button)rootView.findViewById(R.id.btndate);

        sm=new StudentManager(getContext());
        sb=sm.openDb();
        project=new ArrayList<String>();
        student=new ArrayList<String>();

        ArrayAdapter<String> ad= new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,project);
        ArrayAdapter<String> adi= new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,student);

        filllist();
        psp.setAdapter(ad);
        ssp.setAdapter(adi);
        btn.setOnClickListener(this);
        btndate.setOnClickListener(this);


        return rootView;

    }
    public void filllist()
    {
        Cursor c=sb.query(StudentConstant.TBL_PROJECT,null,null,null,null,null,null,null);
        if(c!=null && c.moveToFirst())
        {
            do{
                String id=c.getString(c.getColumnIndex(StudentConstant.COL_PROJECTID));
                project.add(id);

            }while(c.moveToNext());

            c.close();
        }
        Cursor cw=sb.query(StudentConstant.TBL_STUDENT,null,null,null,null,null,null,null);
        if(cw!=null && cw.moveToFirst())
        {
            do{
                String idi=cw.getString(cw.getColumnIndex(StudentConstant.COL_STUDENTID));
                student.add(idi);

            }while(cw.moveToNext());

            cw.close();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn) {

            String id = txtaid.getText().toString();
            String pid = psp.getSelectedItem().toString();
            String sid = ssp.getSelectedItem().toString();

            boolean fieldsOK = validate(new EditText[]{txtaid, txtdate});
            if (!fieldsOK) {
                Toast.makeText(getContext(), "Fill all fields!", Toast.LENGTH_SHORT).show();
            } else if (fieldsOK) {

                ContentValues cv = new ContentValues();

                cv.put(StudentConstant.COL_ASSIGNID, id);
                SimpleDateFormat formatter = new SimpleDateFormat("dd-m-yyyy");
                try {
                    Date date = formatter.parse(String.valueOf(txtdate.getText()));
                    long mills = date.getTime();
                    cv.put(StudentConstant.COL_DATEASSIGNED, mills);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                cv.put(StudentConstant.COL_STUDENTID, sid);
                cv.put(StudentConstant.COL_PROJECTID, pid);

                long rw = sb.insert(StudentConstant.TBL_ASSIGNPROJECT, null, cv);

                String[] args = {sid};
                ContentValues cvs = new ContentValues();
                cvs.put(StudentConstant.COL_PID, pid);

                long l = sb.update(StudentConstant.TBL_STUDENT, cvs, StudentConstant.COL_STUDENTID + " =?", args);
                if (rw > 0 && l > 0) {
                    Toast.makeText(getContext(), "Project Assigned and Student table Updated", Toast.LENGTH_LONG).show();
                    txtaid.setText("");
                    txtdate.setText("");
                }
            }
        }
        if (v.getId() == R.id.btndate) {
            Calendar c = Calendar.getInstance();
            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DATE);

            dlg = new DatePickerDialog(getContext(), this, year, month, day);
            dlg.show();

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


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//        Toast.makeText(getContext(), String.format("Year:%d Month: %d Date%d", year, month, dayOfMonth), Toast.LENGTH_LONG).show();
        txtdate.setText(new StringBuilder().append(dayOfMonth).append("-").append(month+1).append("-").append(year));

    }
}
