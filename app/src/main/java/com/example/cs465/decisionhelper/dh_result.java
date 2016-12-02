package com.example.cs465.decisionhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class dh_result extends BaseActivity {
    int decisionID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dh_result);
        decisionID = getIntent().getIntExtra("decision_id", currDecisionID);
        setTitle("Result");

        // set result and score
        TextView result = (TextView) findViewById(R.id.dh_result_tv_result);
        Storage.Choice choice = db.getTopChoiceForDecision(decisionID);
        String topChoice = choice.name;
        result.setText(topChoice);

        TextView score = (TextView) findViewById(R.id.dh_result_tv_result_score);
        Double topScore = choice.score;
        String topScoreString = Double.toString(topScore);
        score.setText(topScoreString);

    }



    public void dh_result_btn_backOnClick(View view)
    {
        Intent intent = new Intent(this, dh_decision_menu.class);
        startActivity(intent);
    }
}
