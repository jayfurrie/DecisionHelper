package com.example.cs465.decisionhelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class dh_decision_menu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dh_decision_menu);

    }

    public void dh_decision_menu_btn_addfactorsOnClick(View view)
    {
        Intent intent = new Intent(this, dh_add_factors.class);
        startActivity(intent);
    }

    public void dh_decision_menu_btn_rankOnClick(View view)
    {
        Intent intent = new Intent(this, dh_rank_factors.class);
        startActivity(intent);
    }

    public void dh_decision_menu_btn_addchoicesOnClick(View view)
    {
        Intent intent = new Intent(this, dh_add_choices.class);
        startActivity(intent);
    }

    public void dh_decision_menu_btn_resultOnClick(View view)
    {
        Intent intent = new Intent(this, dh_result.class);
        startActivity(intent);
    }

    public void dh_decision_menu_btn_shareOnClick(View view)
    {
        Intent intent = new Intent(this, dh_share_choose_receiver.class);
        startActivity(intent);
    }

    public void dh_decision_menu_btn_backOnClick(View view)
    {
        Intent intent = new Intent(this, dh_homepage.class);
        startActivity(intent);
    }
}
