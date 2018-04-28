package com.example.shubham.proyecto;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class UpdateAccount extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lv;
    ArrayList<String> users;
    StudentManager sm;
    String name, type;
    SQLiteDatabase sb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_account);
        sm=new StudentManager(this);
        sb=sm.openDb();

        lv = (ListView) findViewById(R.id.lv);

        users = new ArrayList<String>();
        fillList();
        ArrayAdapter<String> ad = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, users);
        lv.setAdapter(ad);
        lv.setOnItemClickListener(this);


    }

    public void fillList() {
        Cursor c = sb.query(StudentConstant.TBL_ACCOUNT, null, null, null, null, null, null);
        if (c != null && c.moveToFirst()) {
            do {
                name = c.getString(c.getColumnIndex(StudentConstant.COL_ID));
                type = c.getString(c.getColumnIndex(StudentConstant.COL_TYPE));
                users.add(name);
            } while (c.moveToNext());
            c.close();
            sb.close();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String userid=users.get(position);
//        Toast.makeText(this, userid, Toast.LENGTH_LONG).show();
        Intent i=new Intent(this,Update.class);
        i.putExtra("userid", userid);
        startActivity(i);



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
