package com.example.cs465.decisionhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class dh_add_values extends AppCompatActivity{
    Button button;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dh_add_values);
    }

    public void dh_add_values_btn_salary1OnClick(View view)
    {
        Intent intent = new Intent(this, dh_weigh_values.class);
        startActivity(intent);
    }

    public void dh_add_values_btn_salary2OnClick(View view)
    {
        Intent intent = new Intent(this, dh_weigh_values.class);
        startActivity(intent);
    }

    public void dh_add_values_btn_salary3OnClick(View view)
    {
        Intent intent = new Intent(this, dh_weigh_values.class);
        startActivity(intent);
    }

}