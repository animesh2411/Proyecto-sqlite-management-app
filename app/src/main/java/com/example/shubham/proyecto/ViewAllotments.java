package com.example.shubham.proyecto;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ViewAllotments extends AppCompatActivity {

    ListView lv;
    StudentManager sm;
    String args[];
    int id;
    SQLiteDatabase sb;
    ArrayList<String> allot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_allotments);

        Intent i = getIntent();
        id = i.getIntExtra("assign", 0);
        Toast.makeText(this, String.valueOf(id), Toast.LENGTH_LONG).show();
        args= new String[]{String.valueOf(id)};

        lv=(ListView)findViewById(R.id.lv);
        sm=new StudentManager(this);
        sb=sm.openDb();
        allot=new ArrayList<>();
        fillList();
        ArrayAdapter<String> ad=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,allot);
        lv.setAdapter(ad);

    }

    public void fillList()
    {
        Cursor c = sb.query(StudentConstant.TBL_ASSIGNPROJECT, null, StudentConstant.COL_ASSIGNID+ " =?", args, null, null, null, null);
        {
            if(c!=null && c.moveToFirst())
            {
                do{
                    String pid = c.getString(c.getColumnIndex(StudentConstant.COL_PROJECTID));
                    String  sid =c.getString(c.getColumnIndex(StudentConstant.COL_STUDENTID));
                    long dateassigned = c.getLong(c.getColumnIndex(StudentConstant.COL_DATEASSIGNED));

                    Date date=new Date(dateassigned);
                    @SuppressLint("SimpleDateFormat") SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");
                    String dateText = df2.format(date);
                    allot.add(String.format("Project ID: %s\nStudent ID: %s\nDate Assigned: %s", pid, sid, dateText));
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

