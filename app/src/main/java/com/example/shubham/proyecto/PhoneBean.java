package com.example.shubham.proyecto;

/**
 * Created by SHUBHAM on 22-07-2017.
 */

public class PhoneBean {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    String name;String phno;

    public String toString()
    {
        return name+"\n"+phno;
    }
}
