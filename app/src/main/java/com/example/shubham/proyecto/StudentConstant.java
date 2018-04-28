package com.example.shubham.proyecto;

/**
 * Created by SHUBHAM on 01-07-2017.
 */

public class StudentConstant
{

    public static final String DB_NAME="Institute";


    public static final int DB_VERSION=1;
    public static final String TBL_ACCOUNT="account";
    public static final String COL_ID="userid";
    public static final String COL_PASS="userpass";
    public static final String COL_TYPE="usertype";

    public static final
    String TBL_ACCOUNT_QUERY="create table "+TBL_ACCOUNT+"("+COL_ID+" text primary key,"+COL_PASS+" text, "+COL_TYPE+" text)";


    public static final String TBL_COURSE="Course";
    public static final String COL_CID="cid";
    public static final String COL_COURSENAME="coursename";
    public static final String COL_FEE="fee";
    public static final String COL_DURATION="duration";



    public static final
    String TBL_COURSE_QUERY="create table "+TBL_COURSE+"("+COL_CID+" text primary key,"+COL_COURSENAME+" text,"+COL_FEE+" int,"+COL_DURATION+" text)";


    public static final String TBL_STUDENT="student";
    public static final String COL_STUDENTID="studentid";
    public static final String COL_NAME="name";
    public static final String COL_ADDRESS="address";
    public static final String COl_PHNO="phoneno";
    public static final String COL_EMAILID="emailid";
    public static final String COL_PID="pid";
    public static final String COL_SUBMITTED="submitted";

    public static final
    String TBL_STUDENT_QUERY=
            "create table "+TBL_STUDENT+"("+COL_STUDENTID+" text primary key,"+COL_NAME+" text,"+COL_ADDRESS+" text,"+COl_PHNO+" long,"+COL_EMAILID+" text,"
                    +COL_CID+" text,"+COL_PID+" text,"+COL_SUBMITTED+" text)";


    public static final String TBL_PROJECTCATEGORY="projectcategory";
    public static final String COL_CATEGORYID="categoryid";
    public static final String COL_CATEGORYNAME="categoryname";
    public static final
    String TBL_PROJECTCATEGORY_QUERY="create table "+TBL_PROJECTCATEGORY+"("+COL_CATEGORYID+" text primary key,"
            +COL_CATEGORYNAME+" text)";

    public static final String TBL_PROJECT="project";
    public static final String COL_PROJECTID="projectid";
    public static final String COL_PROJECTNAME="projectname";
    public static final String COl_DETAILS="details";

public static final
    String TBL_PROJECT_QUERY="create table "+TBL_PROJECT+"("+COL_PROJECTID+" text primary key,"+COL_PROJECTNAME+" text,"+COL_CATEGORYID+" text," +
            COl_DETAILS+" text)";

    public static final String TBL_ASSIGNPROJECT="assignproject";
    public static final String COL_ASSIGNID="assignid";
    public static final String COL_DATEASSIGNED="dateassigned";
    public static final
    String TBL_ASSIGNPROJECT_QUERY="create table "+TBL_ASSIGNPROJECT+"("+COL_ASSIGNID+" int primary key,"+COL_PROJECTID+" text,"+COL_STUDENTID+" text," +
            COL_DATEASSIGNED+" long)";
}

