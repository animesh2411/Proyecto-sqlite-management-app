package com.example.shubham.proyecto;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddCourses extends AppCompatActivity {
  EditText txtcid,txtcname,txtfee,txtduration;
    StudentManager sm;
    SQLiteDatabase sb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_courses);
        txtcid = (EditText) findViewById(R.id.txtcid);
        txtcname = (EditText) findViewById(R.id.txtcname);
        txtfee = (EditText) findViewById(R.id.txtfee);
        txtduration = (EditText) findViewById(R.id.txtduration);
        sm=new StudentManager(this);
        sb=sm.openDb();
    }
    public void insertData(View v) {
        String cid = txtcid.getText().toString();
        String cname = txtcname.getText().toString();
        int fee = Integer.parseInt(txtfee.getText().toString());
        String duration = txtduration.getText().toString();
        boolean fieldsOK = validate(new EditText[]{txtcid, txtcname, txtfee, txtduration});
        if (!fieldsOK) {
            Toast.makeText(this, "Fill all fields!", Toast.LENGTH_SHORT).show();
        } else if (fieldsOK) {

            ContentValues cv = new ContentValues();
            cv.put(StudentConstant.COL_CID, cid);
            cv.put(StudentConstant.COL_COURSENAME, cname);
            cv.put(StudentConstant.COL_FEE, fee);
            cv.put(StudentConstant.COL_DURATION, duration);
            long rw = sb.insert(StudentConstant.TBL_COURSE, null, cv);
            if (rw > 0) {
                Toast.makeText(this, "Course Successfully Inserted" + rw, Toast.LENGTH_LONG).show();
                txtcid.setText("");
                txtcname.setText("");
                txtfee.setText("");
                txtduration.setText("");
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
