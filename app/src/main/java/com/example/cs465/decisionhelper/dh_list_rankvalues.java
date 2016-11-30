package com.example.cs465.decisionhelper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class dh_list_rankvalues extends BaseActivity {
    final Context context = this;
    private Button button;
    int decisionID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dh_list_rankvalues);
        setTitle("Rank Factors");
        decisionID = getIntent().getIntExtra("decision_id", currDecisionID);
        addFactorsToView();
    }

    // populate the list of factors previously entered by user
    private void addFactorsToView() {
        LinearLayout myFactors = (LinearLayout) findViewById(R.id.dh_factors_list_for_ranking);
        myFactors.removeAllViews();
        List<Storage.Factor> factors = db.getAllFactorsForDecision(decisionID);

        for (int i = 0; i < factors.size(); i++) {
            Storage.Factor f = factors.get(i);
            Button b = new Button(this);
            b.setText(f.name);
            b.setTag(f.id);
            this.setFactorOnClickListener(b, this);
            myFactors.addView(b);
        }

    }

    private void setFactorOnClickListener(Button b, final Context context) {
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                openFactor(b.getText().toString(), (int)b.getTag());
            }
        });
    }

    private void openFactor(String name, int id) {
        Intent intent = new Intent(context, dh_rank_values.class);
        intent.putExtra("factor_name", name);
        intent.putExtra("decision_id", id);
        startActivity(intent);
    }


   // public void dh_list_rankvalues_dummyOnClick(View view)
    //{
      //  Intent intent = new Intent(this, dh_rank_values.class);
        //startActivity(intent);
    //}

    public void dh_list_rankvalues_btn_backOnClick(View view)
    {
        Intent intent = new Intent(this, dh_decision_menu.class);
        startActivity(intent);
    }
}
