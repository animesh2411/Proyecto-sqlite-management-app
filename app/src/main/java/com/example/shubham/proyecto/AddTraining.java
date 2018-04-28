package com.example.shubham.proyecto;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddTraining extends AppCompatActivity {
    EditText txtcid,txtname;
    StudentManager sm;
    SQLiteDatabase sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_training);
        sm=new StudentManager(this);
        sb=sm.openDb();
        txtcid=(EditText)findViewById(R.id.txtcid);
        txtname=(EditText)findViewById(R.id.txtname);
    }
    public void insertData(View v) {

        String id = txtcid.getText().toString();
        String name = txtname.getText().toString();

        boolean fieldsOK = validate(new EditText[]{txtcid, txtname});
        if (!fieldsOK) {
            Toast.makeText(this, "Fill all fields!", Toast.LENGTH_SHORT).show();
        } else if (fieldsOK) {
            ContentValues cv = new ContentValues();
            cv.put(StudentConstant.COL_CATEGORYID, id);
            cv.put(StudentConstant.COL_CATEGORYNAME, name);
            long rq = sb.insert(StudentConstant.TBL_PROJECTCATEGORY, null, cv);
            if (rq > 0) {
                Toast.makeText(this, "Training Succesfully Inserted", Toast.LENGTH_LONG).show();
                txtcid.setText("");
                txtname.setText("");
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
