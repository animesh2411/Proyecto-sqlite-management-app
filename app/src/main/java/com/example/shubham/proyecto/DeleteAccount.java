package com.example.shubham.proyecto;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class DeleteAccount extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Spinner sp;
    Button btnshow;
    StudentManager sm;
    SQLiteDatabase sb;
    StudentBean s;
    int rw;
    String name, type, atype=null, aname=null, args[];
    ArrayList<StudentBean> RList;
    ArrayAdapter<StudentBean> ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_account);
        btnshow = (Button) findViewById(R.id.btndelete);
        btnshow.setOnClickListener(this);
        sp = (Spinner) findViewById(R.id.sp);
        sm = new StudentManager(this);
        sb = sm.openDb();
        RList = new ArrayList<>();
        fillList();
        ad = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, RList);
        sp.setAdapter(ad);
        sp.setOnItemSelectedListener(this);
    }

    public void fillList(){
        Cursor c = sb.query(StudentConstant.TBL_ACCOUNT, null, null, null, null, null, null);
        if(c!=null && c.moveToFirst()){
            do{
                name = c.getString(c.getColumnIndex(StudentConstant.COL_ID));
                type = c.getString(c.getColumnIndex(StudentConstant.COL_TYPE));
                s = new StudentBean();
                s.setName(name);
                s.setType(type);
                RList.add(s);
            } while (c.moveToNext());
            c.close();
        }
    }

    public void onClick(View v) {
//        atype = ((StudentBean)sp.getSelectedItem()).getType();
//        aname = ((StudentBean)sp.getSelectedItem()).getName();
        args = new String[]{((StudentBean) sp.getSelectedItem()).getName(), ((StudentBean) sp.getSelectedItem()).getType()};
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle("Delete Account");
        adb.setMessage("Do you want to delete that account?");
        adb.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                rw = sb.delete(StudentConstant.TBL_ACCOUNT, StudentConstant.COL_ID + " =? and " + StudentConstant.COL_TYPE + " =?", args);
                if (rw > 0) {
                    Toast.makeText(DeleteAccount.this, "Account Deleted", Toast.LENGTH_LONG).show();
                    sp.setAdapter(null);
                    RList.clear();
                    fillList();
                    ad.notifyDataSetChanged();
                    sp.setAdapter(ad);
                }

            }
        });
        adb.setNegativeButton("No", null);
        adb.setIcon(R.drawable.launcher);
        AlertDialog a = adb.create();
        a.show();
    }
    protected void onDestroy(){
        super.onDestroy();

        if (sm!=null){
            sm.closeDb();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
