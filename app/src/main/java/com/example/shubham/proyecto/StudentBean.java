package com.example.shubham.proyecto;

/**
 * Created by SHUBHAM on 05-07-2017.
 */

public class StudentBean {
    private String name, type;
    private String id;

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    private long phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString()
    {
        return this.name +"\n"+this.type;
    }


    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
