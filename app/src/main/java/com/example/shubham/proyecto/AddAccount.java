package com.example.shubham.proyecto;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddAccount extends AppCompatActivity {
   EditText txtuserid,txtpswrd;StudentManager sm;SQLiteDatabase sb;
    RadioGroup rg;
    RadioButton rb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);
        txtuserid=(EditText)findViewById(R.id.txtuserid);
        txtpswrd=(EditText)findViewById(R.id.txtpswrd);

        rg=(RadioGroup)findViewById(R.id.rg);
        sm=new StudentManager(this);
        sb=sm.openDb();



    }
    public void insertData(View v) {
        int id = rg.getCheckedRadioButtonId();
        rb = (RadioButton) rg.findViewById(id);
        String usertype = rb.getText().toString();
        String userid = txtuserid.getText().toString();
        String pswrd = txtpswrd.getText().toString();

        boolean fieldsOK = validate(new EditText[]{txtuserid, txtpswrd});
        if (!fieldsOK || id == -1) {
            Toast.makeText(this, "Fill all fields!", Toast.LENGTH_SHORT).show();
        } else if (fieldsOK) {

            ContentValues cv = new ContentValues();
            cv.put(StudentConstant.COL_ID, userid);
            cv.put(StudentConstant.COL_PASS, pswrd);
            cv.put(StudentConstant.COL_TYPE, usertype);
            long rw = sb.insert(StudentConstant.TBL_ACCOUNT, null, cv);
            if (rw > 0) {
                Toast.makeText(this, "Account Successfully Inserted " + rw, Toast.LENGTH_LONG).show();
                txtuserid.setText("");
                txtpswrd.setText("");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

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
