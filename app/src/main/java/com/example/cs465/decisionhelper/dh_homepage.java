package com.example.cs465.decisionhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class dh_homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dh_homepage);
    }
    public void dh_homepage_btn_newdecisionOnClick(View view)
    {
        Intent intent = new Intent(this, dh_decision_menu.class);
        startActivity(intent);
    }
}