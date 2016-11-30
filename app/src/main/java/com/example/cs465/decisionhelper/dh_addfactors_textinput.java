package com.example.cs465.decisionhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import static java.lang.Integer.parseInt;

public class dh_addfactors_textinput extends BaseActivity {
    int decisionID;
    int choiceID;
    String choiceName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dh_addfactors_textinput);
        decisionID = getIntent().getIntExtra("decision_id", currDecisionID);
        choiceID = getIntent().getIntExtra("choice_id", currChoiceID);
        choiceName = getIntent().getStringExtra("choice_name");
        if (choiceName == null) {
            choiceName = currChoiceName;
        }
        setTitle(choiceName);

        addFactorsToView();
    }

    private void addFactorsToView() {
        LinearLayout factorsLayout = (LinearLayout) findViewById(R.id.dh_factors_list);
        LinearLayout valuesLayout = (LinearLayout) findViewById(R.id.dh_values_list);
        factorsLayout.removeAllViews();
        valuesLayout.removeAllViews();
        List<Storage.Factor> factors = db.getAllFactorsForDecision(decisionID);

        for (int i = 0; i < factors.size(); i++) {
            int height = 200;
            Storage.Factor f = factors.get(i);
            TextView textView = new TextView(this);
            textView.setTextSize(26);
            textView.setText(f.name + ": ");
            textView.setHeight(height);
            textView.setGravity(Gravity.BOTTOM);
            factorsLayout.addView(textView);

            Storage.Value v = db.getValueForFactorAndChoice(choiceID, f.id);
            EditText textEdit = new EditText(this);
            textEdit.setHeight(height);
            textEdit.setGravity(Gravity.BOTTOM);
            textEdit.setTextSize(26);
            textEdit.setTag(R.id.dh_factors_list, Integer.toString(f.id));
            if (v != null) {
                textEdit.setText(v.name);
                textEdit.setTag(R.id.dh_values_list, Integer.toString(v.id));
            }
            valuesLayout.addView(textEdit);
        }

    }

    public void dh_addfactors_textinput_btn_OKOnClick(View view)
    {
        Intent intent = new Intent(this, dh_add_choices.class);
        startActivity(intent);
        LinearLayout valuesLayout = (LinearLayout) findViewById(R.id.dh_values_list);
        for (int i = 0; i < valuesLayout.getChildCount(); i++) {
            EditText editText = (EditText) valuesLayout.getChildAt(i);
            if (editText.getText().toString().trim().isEmpty()) {
                continue;
            }
            int factorID = parseInt(editText.getTag(R.id.dh_factors_list).toString());
            int valueID;
            if (editText.getTag(R.id.dh_values_list) != null) {
                db.removeFactorToValueForChoiceAndFactor(choiceID, factorID);
            }

            valueID = (int) db.createValueForFactor(editText.getText().toString(), factorID);
            db.assignValueToFactorForChoice(choiceID, factorID, valueID);
        }
    }

    public void dh_addfactors_textinput_btn_cancelOnClick(View view)
    {
        Intent intent = new Intent(this, dh_decision_menu.class);
        intent.putExtra("decision_id", decisionID);
        startActivity(intent);
    }

}
