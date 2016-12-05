package com.example.cs465.decisionhelper;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class dh_rank_factors extends BaseActivity {
    int decisionID;
    int max=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dh_rank_factors);
        setTitle("Rank Factors");
        decisionID = getIntent().getIntExtra("decision_id", currDecisionID);

        displayFactors();
    }

    private void displayFactors() {
        LinearLayout myFactors = (LinearLayout) findViewById(R.id.dh_factors_list);
        myFactors.removeAllViews();
        List<Storage.Factor> factors = db.getAllFactorsForDecision(decisionID);
        for (int i = 0; i < factors.size(); i++) {
            Storage.Factor f = factors.get(i);
            int score = Math.max(db.getScoreForFactor(login_login.username, f.id), 0);

            SeekBar sb = new SeekBar(this);
            sb.setTag(f.id);
            sb.setMax( max );

            sb.setProgress(score);

            TextView textView = new TextView(this);
            textView.setText("How much do you care about "+f.name+"?");
            textView.setTextSize(20);
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            myFactors.addView(textView);
            myFactors.addView(sb);
        }
    }

    public void dh_rank_factors_btn_cancelOnClick(View view)
    {
        Intent intent = new Intent(this, dh_decision_menu.class);
        intent.putExtra("decision_id", decisionID);
        startActivity(intent);
    }

    public void dh_rank_factors_btn_OKOnClick(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        LinearLayout myFactors = (LinearLayout) findViewById(R.id.dh_factors_list);
        int count = myFactors.getChildCount();

        ArrayList<Integer> minMaxCheckList = new ArrayList<Integer>();
        for (int i = 0; i < count; i++)
        {
            if (i % 2 == 0) {
                continue;
            }
            SeekBar sb = (SeekBar) myFactors.getChildAt(i);
            int factorID = parseInt(sb.getTag().toString());
            int score = sb.getProgress();
            minMaxCheckList.add(score);
        }
        if (!minMaxCheck(minMaxCheckList))
        {
            builder.setTitle("Error");
            builder.setMessage("Please use values of 0 and 100 to rank your minimum and maximum factors, respectively.");
            builder.setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    //do nothing
                }
            });
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.show();
            return;
        }
        for (int i = 0; i < count; i++) {
            if (i % 2 == 0) {
                continue;
            }
            SeekBar sb = (SeekBar) myFactors.getChildAt(i);
            int factorID = parseInt(sb.getTag().toString());
            int score = sb.getProgress();

            db.assignScoreToFactor(login_login.username, score, factorID);
        }
        Intent intent = new Intent(this, dh_decision_menu.class);
        startActivity(intent);
        intent.putExtra("decision_id", decisionID);
    }

    private boolean minMaxCheck(ArrayList<Integer> minMaxCheckList)
    {
        if (minMaxCheckList.contains(0) && minMaxCheckList.contains(100))
        {
            return true;
        }
        return false;
    }

}
