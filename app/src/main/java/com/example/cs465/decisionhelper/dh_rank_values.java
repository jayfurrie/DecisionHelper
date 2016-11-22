package com.example.cs465.decisionhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class dh_rank_values extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dh_rank_values);
    }

    public void dh_rank_values_btn_OKOnClick(View view)
    {
        Intent intent = new Intent(this, dh_list_rankvalues.class);
        startActivity(intent);
    }
}
