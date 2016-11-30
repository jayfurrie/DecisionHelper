package com.example.cs465.decisionhelper;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class dh_add_factors extends BaseActivity {

    final Context context = this;
    private Button button;
    private EditText result;
    int decisionID;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dh_add_factors);
        setTitle("Add Factors");
        decisionID = getIntent().getIntExtra("decision_id", currDecisionID);

        // find the add new factor button
        button = (Button) findViewById(R.id.dh_add_factors_btn_newfactor);
        // add button listener
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.activity_dh_addfactor_dialog, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.dh_addfactor_dialog_et);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        String name = userInput.getText().toString();
                                        int factorID = (int) db.createFactor(name, decisionID);
                                        addFactorsToView();
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });
        addFactorsToView();
    }
    private void addFactorsToView() {
        LinearLayout myFactors = (LinearLayout) findViewById(R.id.dh_factors_list);
        myFactors.removeAllViews();
        List<Storage.Factor> factors = db.getAllFactorsForDecision(decisionID);

        for (int i = 0; i < factors.size(); i++) {
            Storage.Factor f = factors.get(i);
            TextView textView = new TextView(this);
            textView.setText(f.name);
            textView.setTextSize(26);
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            myFactors.addView(textView);
        }

    }

}
