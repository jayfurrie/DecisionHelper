package com.example.cs465.decisionhelper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.List;

public class dh_rank_factors extends BaseActivity {
    int decisionID;
    int max=100;
    int factorId;
    final Context context = this;
   // SeekBar sb[]=new SeekBar[100];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dh_rank_factors);
        setTitle("Rank Factors");
        decisionID = getIntent().getIntExtra("decision_id", 0);
        LinearLayout myFactors = (LinearLayout) findViewById(R.id.rank_factor);
      // myFactors.removeAllViews();
        List<Storage.Factor> factors = db.getAllFactorsForDecision(decisionID);
        for (int i = 0; i < factors.size(); i++) {
            Storage.Factor f = factors.get(i);
            SeekBar sb=new SeekBar(context);
            //sb=new SeekBar(context);
            //sb.setId(i);
           // sb[i].setMax( max );
            TextView textView = new TextView(this);
            textView.setText("How do you care about "+f.name+"?");
            textView.setTextSize(20);
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
           // if(!myFactors.equals(null))
            myFactors.addView(textView);
            myFactors.addView(sb);
        }
    }

    public void dh_rank_factors_btn_cancelOnClick(View view)
    {
        Intent intent = new Intent(this, dh_decision_menu.class);
        startActivity(intent);
    }

    public void dh_rank_factors_btn_OKOnClick(View view)
    {
        Intent intent = new Intent(this, dh_decision_menu.class);
        startActivity(intent);
        // TODO save the factor rankings from user input on the slider bars
    }

}
