package com.example.cs465.decisionhelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;


public class dh_decision_menu extends BaseActivity {
    int decisionID;
    String decisionName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dh_decision_menu);
        // use this for future db calls
        decisionID = getIntent().getIntExtra("decision_id", currDecisionID);
        decisionName = getIntent().getStringExtra("decision_name");
        if (decisionName == null) {
            decisionName = currDecisionName;
        }
        setTitle(decisionName);
        setButtonEnabledness();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setButtonEnabledness();
    }

    private void setButtonEnabledness() {
        int currStep = db.getCurrentStepForDecision(decisionID);
        if (currStep == 4) {
            currStep = 6;
        }
        LinearLayout buttonsList = (LinearLayout) findViewById(R.id.dh_decision_menu_buttons);
        for (int i = 0; i < buttonsList.getChildCount() - 1; i++) {
            buttonsList.getChildAt(i).setEnabled(i <= currStep);
        }
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
