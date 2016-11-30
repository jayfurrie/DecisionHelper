package com.example.cs465.decisionhelper;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import java.util.List;


public class dh_add_choices extends BaseActivity {
    final Context context = this;
    private Button button;
    private EditText result;
    int decisionID;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dh_add_choices);
        decisionID = getIntent().getIntExtra("decision_id", currDecisionID);
        setTitle("Add Choices");

        // find the add new choice button
        button = (Button) findViewById(R.id.dh_add_choices_btn_newchoice);
        // add button listener
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.activity_dh_addchoice_dialog, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.dh_addchoice_dialog_et);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        String name = userInput.getText().toString();
                                        // public long createChoice(String name, int decision_id)
                                        int choiceID = (int) db.createChoice(name, decisionID);
                                        addChoicesToView();
                                        openChoice(name, choiceID);

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
        addChoicesToView();
    }

    private void addChoicesToView() {
        LinearLayout myChoices = (LinearLayout) findViewById(R.id.dh_my_choices_list);
        myChoices.removeAllViews();
        List<Storage.Choice> choices = db.getAllChoicesForDecision(decisionID);
        for (int i = 0; i < choices.size(); i++) {
            Storage.Choice d = choices.get(i);
            Button b = new Button(this);
            b.setText(d.name);
            b.setTag(d.id);
            this.setChoiceOnClickListener(b, this);
            myChoices.addView(b);
        }

    }

    private void setChoiceOnClickListener(Button b, final Context context) {
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                openChoice(b.getText().toString(), (int)b.getTag());
            }
        });
    }

    private void openChoice(String name, int id) {
        Intent intent = new Intent(context, dh_addfactors_textinput.class);
        currChoiceName = name;
        currChoiceID = id;
        currDecisionID = decisionID;
        intent.putExtra("choice_name", name);
        intent.putExtra("choice_id", id);
        intent.putExtra("decision_id", decisionID);
        startActivity(intent);
    }

    public void dh_add_choices_btn_backOnClick(View view)
    {
        Intent intent = new Intent(this, dh_decision_menu.class);
        intent.putExtra("decision_id", decisionID);
        startActivity(intent);
    }

}
