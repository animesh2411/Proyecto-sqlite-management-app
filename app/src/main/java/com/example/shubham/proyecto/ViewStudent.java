package com.example.shubham.proyecto;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewStudent extends AppCompatActivity {
  ListView lv;
    StudentManager sm;
    SQLiteDatabase sb;
    ArrayList<StudentBean>student;
    StudentBean s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);
        lv=(ListView)findViewById(R.id.lv);
        sm=new StudentManager(this);
        sb=sm.openDb();
        student =new ArrayList<>();

        filllist();
        ArrayAdapter<StudentBean> ad= new ArrayAdapter<StudentBean>(this,android.R.layout.simple_selectable_list_item,student);
        lv.setAdapter(ad);
    }
    public  void filllist()
    {

        Intent obj=getIntent();
       String d= obj.getStringExtra("status");
        String args[]={d};
        Cursor c=sb.query(StudentConstant.TBL_STUDENT,null,StudentConstant.COL_SUBMITTED+" =?",args,null,null,null);
        {
            if(c!=null && c.moveToFirst())
            {
                do{
//                    String status = c.getString((c.getColumnIndex(StudentConstant.COL_SUBMITTED)));
                    String id = c.getString(c.getColumnIndex(StudentConstant.COL_STUDENTID));
                    String name = c.getString(c.getColumnIndex(StudentConstant.COL_NAME));
                    s = new StudentBean();
                    s.setName(id);
                    s.setType(name);
                    student.add(s);
                }while(c.moveToNext());
                c.close();
            }
        }

    }
}
