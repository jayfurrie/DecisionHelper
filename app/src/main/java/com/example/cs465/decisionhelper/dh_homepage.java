package com.example.cs465.decisionhelper;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class dh_homepage extends BaseActivity {
    final Context context = this;
    private Button button;
    private EditText result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dh_homepage);
        setTitle("Decision Helper");


        // find the add new decision button
        button = (Button) findViewById(R.id.dh_homepage_btn_newdecision);
        // add button listener
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.activity_dh_homepage_dialog_newdecision, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.dh_homepage_dialog_newdecision_et);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        String name = userInput.getText().toString();
                                        // public long createDecision(String name, String owner)
                                        int decisionID = (int) db.createDecision(name, login_login.username);
                                        openDecision(name, decisionID);
                                        addDecisionsToView();
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });

        addDecisionsToView();
    }

    private void addDecisionsToView() {
        LinearLayout myDecisions = (LinearLayout) findViewById(R.id.dh_my_decisions_list);
        myDecisions.removeAllViews();
        List<Storage.Decision> decisions = db.getAllDecisionsForOwner(login_login.username);
        for (int i = 0; i < decisions.size(); i++) {
            Storage.Decision d = decisions.get(i);
            Button b = new Button(this);
            b.setText(d.name);
            b.setTag(d.id);
            this.setDecisionOnClickListener(b, this);
            myDecisions.addView(b);
        }

    }

    private void setDecisionOnClickListener(Button b, final Context context) {
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                openDecision(b.getText().toString(), (int)b.getTag());
            }
        });
    }

    private void openDecision(String name, int id) {
        Intent intent = new Intent(context, dh_decision_menu.class);
        currDecisionName = name;
        currDecisionID = id;
        intent.putExtra("decision_name", name);
        intent.putExtra("decision_id", id);
        startActivity(intent);
    }

}