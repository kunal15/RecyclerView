package com.khatrigmail.kunal15.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    Toolbar t;
    TextView name,number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        t = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(t);
        getSupportActionBar().setTitle("InfoActivity");
        Intent i = getIntent();
        name = (TextView) findViewById(R.id.tvName);
        number = (TextView) findViewById(R.id.tvNumber);
        name.setText(i.getStringExtra("Name"));
        number.setText(i.getStringExtra("Number"));
    }
}
