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

public class dh_add_factors extends AppCompatActivity {

    final Context context = this;
    private Button button;
    private EditText result;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dh_add_factors);

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
                                        //  ToDo get user input here and save
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
    }

    public void dh_add_values_btn_factor1OnClick(View view)
    {
        Intent intent = new Intent(this, dh_add_values.class);
        startActivity(intent);
    }

    public void dh_add_values_btn_factor2OnClick(View view)
    {
        Intent intent = new Intent(this, dh_add_values.class);
        startActivity(intent);
    }

    public void dh_add_values_btn_factor3OnClick(View view)
    {
        Intent intent = new Intent(this, dh_add_values.class);
        startActivity(intent);
    }

    public void dh_add_factors_btn_backOnClick(View view)
    {
        Intent intent = new Intent(this, dh_decision_menu.class);
        startActivity(intent);
    }
}
