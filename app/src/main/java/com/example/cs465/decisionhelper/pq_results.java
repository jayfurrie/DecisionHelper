package com.example.cs465.decisionhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class pq_results extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pq_results);
        setTitle("Result:");


        int decisionID = getIntent().getIntExtra("decision_id", currDecisionID);
        Log.d("debug", "grabbing top choice for decisionID: " + Integer.toString(decisionID));
        Storage.Choice topChoice = db.getTopChoiceForDecision(decisionID);

        String choiceName = topChoice.name;
        String choiceScore = Double.toString(topChoice.score);

        TextView tv_choiceName = (TextView) findViewById(R.id.pq_results_tv_result_choice);
        TextView tv_choiceScore = (TextView) findViewById(R.id.pq_results_tv_result_score);

        tv_choiceName.setText("Your top choice is: "+ choiceName);
        tv_choiceScore.setText("It has a score of: " + choiceScore);
    }

    public void pq_results_btn_backOnClick(View view)
    {
        Intent intent = new Intent(this, dh_homepage.class);
        startActivity(intent);
    }
}
