package com.example.shubham.proyecto;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
  EditText txtuserid,txtpswrd;
    SharedPreferences sp;

    StudentManager sm;
    SQLiteDatabase sb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        sp=getSharedPreferences("logininfor",MODE_PRIVATE);
        txtpswrd=(EditText)findViewById(R.id.txtpswrd);
        txtuserid=(EditText)findViewById(R.id.txtuserid);
        sm=new StudentManager(this);
        sb=sm.openDb();

    }
    public void submit(View v)
    {
        String userid=txtuserid.getText().toString();
        String password=txtpswrd.getText().toString();
        String id=sp.getString("useridd",null);
        String pass=sp.getString("userpswrd",null);
        System.out.println(id);
        System.out.println(pass);
        System.out.println(userid);
        System.out.println(password);


        if(userid.equalsIgnoreCase(id) && password.equalsIgnoreCase(pass))
        {  System.out.println("entered if");
            Intent i=new Intent(this,AdminPortal.class);
            startActivity(i);

        }
        else
        {System.out.println("entered else");
            int f=0;
            String args[]={userid};
            Cursor c=sb.query(StudentConstant.TBL_ACCOUNT,null,StudentConstant.COL_ID+" =?",args,null,null,null,null);
          //  Cursor s=sb.query()
            if(c!=null && c.moveToFirst()) {
                do {

                    String usertype = c.getString(c.getColumnIndex(StudentConstant.COL_TYPE));
                    Toast.makeText(this,usertype,Toast.LENGTH_LONG).show();


                    if (usertype.equalsIgnoreCase("academic head")) {
                        Intent o = new Intent(this, AcademicHead.class);
                        f=1;
                        startActivity(o);

                    } else if (usertype.equalsIgnoreCase("counseller")) {
                        Intent w = new Intent(this, Counsellor.class);
                        f=1;
                        startActivity(w);
                    }
                }while(c.moveToNext());
            }

                      if(f==0) {
                          AlertDialog.Builder ad = new AlertDialog.Builder(this);
                          ad.setTitle("Warning");
                          ad.setMessage("Invalid User ID or Password" + "\n" + "Try Again!!");
                          ad.setPositiveButton("ok", null);
                          AlertDialog a = ad.create();
                          a.show();
                      }
                }





        }




    }

