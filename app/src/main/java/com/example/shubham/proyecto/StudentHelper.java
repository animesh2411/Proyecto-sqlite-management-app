package com.example.shubham.proyecto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by SHUBHAM on 01-07-2017.
 */


public class StudentHelper extends SQLiteOpenHelper

{


    Context context;
    public StudentHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(StudentConstant.TBL_ACCOUNT_QUERY);
        db.execSQL(StudentConstant.TBL_COURSE_QUERY);
        db.execSQL(StudentConstant.TBL_STUDENT_QUERY);
        db.execSQL(StudentConstant.TBL_PROJECTCATEGORY_QUERY);
        db.execSQL(StudentConstant.TBL_PROJECT_QUERY);
        db.execSQL(StudentConstant.TBL_ASSIGNPROJECT_QUERY);
        Toast.makeText(context,"tablecreated",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int neswVersion) {

    }
}
