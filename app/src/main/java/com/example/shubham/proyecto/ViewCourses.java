package com.example.shubham.proyecto;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewCourses extends AppCompatActivity {
   ListView lv;
    StudentManager sm;
    SQLiteDatabase sb;
    ArrayList<String>course;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_courses);
        lv=(ListView)findViewById(R.id.lv);
        sm=new StudentManager(this);
        sb=sm.openDb();
        course=new ArrayList<>();
        fillList();
        ArrayAdapter<String> ad=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,course);
        lv.setAdapter(ad);

    }
    public void fillList()
    {
     Cursor c=sb.query(StudentConstant.TBL_COURSE,null,null,null,null,null,null);
        {
            if(c!=null && c.moveToFirst())
            {
                do{
                    String id=c.getString(c.getColumnIndex(StudentConstant.COL_CID));
                    String name=c.getString(c.getColumnIndex(StudentConstant.COL_COURSENAME));
                    course.add("\nCourse ID: "+id+"\nCourse Name: "+name);

                }while(c.moveToNext());
            }
            c.close();
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
}
