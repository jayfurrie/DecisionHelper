package com.example.cs465.decisionhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class dh_list_rankvalues extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dh_list_rankvalues);
        setTitle("Rank Factors");
    }

    public void dh_list_rankvalues_dummyOnClick(View view)
    {
        Intent intent = new Intent(this, dh_rank_values.class);
        startActivity(intent);
    }

    public void dh_list_rankvalues_btn_backOnClick(View view)
    {
        Intent intent = new Intent(this, dh_decision_menu.class);
        startActivity(intent);
    }
}
