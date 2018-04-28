package com.example.shubham.proyecto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by SHUBHAM on 01-07-2017.
 */

public class StudentManager
{
    Context context;
    StudentHelper ch;
    SQLiteDatabase sb;

    public StudentManager(Context context)
    {
        this.context=context;
        ch=new StudentHelper(context, StudentConstant.DB_NAME,null, StudentConstant.DB_VERSION);

    }


    public SQLiteDatabase openDb()
    {


        sb=  ch.getWritableDatabase();
        return sb;
    }

    public  void closeDb()
    {

        ch.close();


    }



}
