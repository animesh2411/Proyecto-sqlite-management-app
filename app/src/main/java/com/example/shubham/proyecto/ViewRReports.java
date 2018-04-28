package com.example.shubham.proyecto;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewRReports extends AppCompatActivity {

    ListView lv;
    StudentManager sm;
    String id, args[];
    SQLiteDatabase sb;
    ArrayList<String> course;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rreports);

        Intent i = getIntent();
        id = i.getStringExtra("id");
        Toast.makeText(this, id, Toast.LENGTH_LONG).show();
//        i.getStringExtra("name");
        args= new String[]{id};

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
        Cursor c = sb.query(StudentConstant.TBL_STUDENT, null, StudentConstant.COL_PID+ " =?", args, null, null, null, null);
        {
            if(c!=null && c.moveToFirst())
            {
                do{
                    String name = c.getString(c.getColumnIndex(StudentConstant.COL_NAME));
                    String  status =c.getString(c.getColumnIndex(StudentConstant.COL_SUBMITTED));
                    course.add(String.format("Student Name: %s\nStatus: %s", name, status));
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

