package com.example.cs465.decisionhelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class dh_decision_menu extends BaseActivity {
    int decisionID;
    String decisionName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dh_decision_menu);
        // use this for future db calls
        decisionID = getIntent().getIntExtra("decision_id", 0);
        decisionName = getIntent().getStringExtra("decision_name");
        if (decisionName == null) {
            decisionName = "Decision Helper";
        }
        setTitle(decisionName);
    }

    public void dh_decision_menu_btn_addfactorsOnClick(View view)
    {
        startNewActivity(dh_add_factors.class);
    }

    public void dh_decision_menu_btn_rankOnClick(View view)
    {
        startNewActivity(dh_rank_factors.class);
    }

    public void dh_decision_menu_btn_addchoicesOnClick(View view)
    {
        startNewActivity(dh_add_choices.class);
    }

    public void dh_decision_menu_btn_rankchoicesOnClick(View view)
    {
        startNewActivity(dh_list_rankvalues.class);
    }

    public void dh_decision_menu_btn_resultOnClick(View view)
    {
        startNewActivity(dh_result.class);
    }

    public void dh_decision_menu_btn_shareOnClick(View view)
    {
        startNewActivity(dh_share_choose_receiver.class);
    }

    private void startNewActivity(Class<?> calledActivity) {
        Intent intent = new Intent(this, calledActivity);
        intent.putExtra("decision_id", decisionID);
        startActivity(intent);
    }

    public void dh_decision_menu_btn_backOnClick(View view)
    {
        Intent intent = new Intent(this, dh_homepage.class);
        startActivity(intent);
    }
}
