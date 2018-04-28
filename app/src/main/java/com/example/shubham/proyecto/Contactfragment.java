package com.example.shubham.proyecto;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by SHUBHAM on 15-07-2017.
 */

public class Contactfragment extends Fragment implements AdapterView.OnItemClickListener {
    ListView lv;
    StudentManager sm;
    SQLiteDatabase sb;
    ArrayList<PhoneBean> s;
    PhoneBean p, p1;
    String name, phone;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.contactfragment, container, false);
        sm = new StudentManager(getContext());
        sb = sm.openDb();
        lv = (ListView) rootView.findViewById(R.id.lv);
        s = new ArrayList<>();
        filllist();
        ArrayAdapter<PhoneBean> ad = new ArrayAdapter<PhoneBean>(getContext(), android.R.layout.simple_list_item_1, s);

        lv.setAdapter(ad);
        lv.setOnItemClickListener(this);

//        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
//                smsIntent.setType("vnd.android-dir/mms-sms");
//                smsIntent.putExtra("address", "12125551212");
//                smsIntent.putExtra("sms_body","Body of Message");
//                startActivity(smsIntent);
//            }
//        });

        return rootView;
    }

    public void filllist() {
        Cursor c = sb.query(StudentConstant.TBL_STUDENT, null, null, null, null, null, null);
        if (c != null && c.moveToFirst()) {
            do {
                String name = c.getString(c.getColumnIndex(StudentConstant.COL_NAME));
                long phno = c.getLong(c.getColumnIndex(StudentConstant.COl_PHNO));
                p = new PhoneBean();
                p.setName(name);
                p.setPhno(String.valueOf(phno));
                s.add(p);
            } while (c.moveToNext());

            c.close();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        p1 = s.get(position);
        name = p1.getName();
        phone = p1.getPhno();
        Toast.makeText(getContext(), "Calling " + name, Toast.LENGTH_LONG).show();

        Intent i = new Intent(Intent.ACTION_CALL);
        Uri u = Uri.parse("tel:" + phone);
        i.setData(u);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(i);
    }

    public void onDestroy(){
        super.onDestroy();
        if (sm!=null){
            sm.closeDb();
        }
    }
}


