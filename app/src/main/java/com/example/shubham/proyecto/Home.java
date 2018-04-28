package com.example.shubham.proyecto;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {
SharedPreferences sp;SharedPreferences.Editor se;
    Button btnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnlogin=(Button)findViewById(R.id.btnlogin);

        sp=getSharedPreferences("logininfor",MODE_PRIVATE);
        se=sp.edit();
        se.putString("useridd","admin");
        se.putString("userpswrd","admin");
        se.commit();
    }
    public void login(View v)
    {
        btnlogin.setBackgroundColor(Color.CYAN);
        Intent i=new Intent(this,Login.class);
        startActivity(i);
    }
}
