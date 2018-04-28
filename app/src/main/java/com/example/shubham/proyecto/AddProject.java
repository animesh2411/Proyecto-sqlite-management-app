package com.example.shubham.proyecto;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AddProject extends AppCompatActivity {
    Spinner sp;
    StudentManager sm;
    SQLiteDatabase sb;
    EditText txtpid,txtpname,txtdetails;
    ArrayList<String>project;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);
        txtpid=(EditText)findViewById(R.id.txtpid);
        txtpname=(EditText)findViewById(R.id.txtpname);
        txtdetails=(EditText)findViewById(R.id.txtdetails);
        sp=(Spinner)findViewById(R.id.sp);
        sm=new StudentManager(this);
        sb=sm.openDb();
        project=new ArrayList<String>();
        ArrayAdapter<String> ad= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,project);
        filllist();
        sp.setAdapter(ad);



    }
    public void filllist()
    {
        Cursor c=sb.query(StudentConstant.TBL_PROJECTCATEGORY,null,null,null,null,null,null,null);
        if(c!=null && c.moveToFirst())
        {
            do{
                String id=c.getString(c.getColumnIndex(StudentConstant.COL_CATEGORYID));
                project.add(id);

            }while(c.moveToNext());

            c.close();
        }
    }
    public void insertData(View v) {
        String pid = txtpid.getText().toString();
        String pn = txtpname.getText().toString();
        String cid = sp.getSelectedItem().toString();
        String d = txtdetails.getText().toString();
        boolean fieldsOK = validate(new EditText[]{txtpid, txtpname, txtdetails});
        if (!fieldsOK) {
            Toast.makeText(this, "Fill all fields!", Toast.LENGTH_SHORT).show();
        } else if (fieldsOK) {

        ContentValues cv = new ContentValues();

        cv.put(StudentConstant.COL_PROJECTID, pid);
        cv.put(StudentConstant.COL_PROJECTNAME, pn);
        cv.put(StudentConstant.COL_CATEGORYID, cid);
        cv.put(StudentConstant.COl_DETAILS, d);
        long rw = sb.insert(StudentConstant.TBL_PROJECT, null, cv);
        if (rw > 0) {
            Toast.makeText(this, "Project Sucessfully added", Toast.LENGTH_LONG).show();
            txtpid.setText("");
            txtpname.setText("");
            txtdetails.setText("");
        }

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
    protected void onDestroy() {
        super.onDestroy();

        if(sm!=null)
        {
            sm.closeDb();
        }

    }
}
