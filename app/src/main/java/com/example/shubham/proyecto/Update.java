package com.example.shubham.proyecto;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Update extends AppCompatActivity {
  EditText  txtpswrd;
    TextView username, usertype;
    StudentManager sm;
    SQLiteDatabase sb;
    String id, name, type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        txtpswrd=(EditText)findViewById(R.id.txtpswrd);
        username=(TextView) findViewById(R.id.username);
        sm=new StudentManager(this);
        sb=sm.openDb();
        Intent i=getIntent();
        id=i.getStringExtra("userid");
        username.setText(id);

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

    public void updateRecord(View v) {

        String pswrd=txtpswrd.getText().toString();
        String args[]={id};

        boolean fieldsOK = validate(new EditText[]{txtpswrd});
        if (!fieldsOK) {
            Toast.makeText(this, "Password cannot be left empty!", Toast.LENGTH_SHORT).show();
        } else if (fieldsOK) {

            ContentValues cv = new ContentValues();
            cv.put(StudentConstant.COL_PASS, pswrd);

            long l = sb.update(StudentConstant.TBL_ACCOUNT, cv, StudentConstant.COL_ID + " =?", args);
            if (l > 0) {
                Toast.makeText(this, "Password Successfully Updated ", Toast.LENGTH_LONG).show();
            }
            sm.closeDb();
        }


    }
}
