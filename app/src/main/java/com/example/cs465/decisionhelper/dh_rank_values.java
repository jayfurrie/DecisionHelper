package com.example.cs465.decisionhelper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.List;

import static java.lang.Integer.parseInt;

public class dh_rank_values extends BaseActivity {
    //int decisionID;
    int factorID;
    String factorName;
    int sbMax = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dh_rank_values);
        factorID = getIntent().getIntExtra("factor_id", 0);
        factorName = getIntent().getStringExtra("factor_name");
        if (factorName == null) {
            Storage.Factor f = db.getFactorByID(factorID);
            factorName = f.name;
        }
        setTitle(factorName);

        addValuesToView();
    }

    // populate list with corresponding values for each factor
    private void addValuesToView() {
        LinearLayout valuesLayout = (LinearLayout) findViewById(R.id.dh_rank_values_ll_list);
        valuesLayout.removeAllViews();
        List<Storage.Value> values = db.getAllValuesForFactor(factorID);

        for (int i = 0; i < values.size(); i++) {
            // add new text view
            int height = 200;
            Storage.Value v = values.get(i);
            TextView textView = new TextView(this);
            textView.setTextSize(26);
            textView.setText(v.name + ": ");
            textView.setHeight(height);
            textView.setGravity(Gravity.BOTTOM);
            valuesLayout.addView(textView);


            // add new seek bar
            SeekBar seekBar = new SeekBar(this);
            seekBar.setMax(sbMax);
            seekBar.setTag(Integer.toString(v.id));
            valuesLayout.addView(seekBar);
            // trying to add the text view scales..... didn't work. App crashed. (not a priority for now).
            //LinearLayout scaleLayout = (LinearLayout) findViewById(R.id.dh_rank_values_tv_sb_scale);
            //valuesLayout.addView(scaleLayout);
            //LinearLayout scaleLayout = new LinearLayout(this);
            //scaleLayout.setOrientation(LinearLayout.HORIZONTAL);
            //TextView tvMin = (TextView)  findViewById(R.id.dh_rank_values_tv_min);
            //TextView tvMax = (TextView)  findViewById(R.id.dh_rank_values_tv_max);
            //scaleLayout.addView(tvMin);
            //scaleLayout.addView(tvMax);
            // TODO add the the text view scales for the seek bar (0 to 100)

        }

    }





    public void dh_rank_values_btn_OKOnClick(View view)
    {
        //this stores the score
        int sbScore;
        //this stores the valueID
        int sbID;
        Intent intent = new Intent(this, dh_list_rankvalues.class);
        startActivity(intent);
        LinearLayout valuesLayout = (LinearLayout) findViewById(R.id.dh_rank_values_ll_list);
        //grabs all the values related to this factor
        List<Storage.Value> values = db.getAllValuesForFactor(factorID);

        for (int i = 0; i < valuesLayout.getChildCount(); i++) {
            // need to get the child at (i)
            View child = (View) valuesLayout.getChildAt(i);
            //valueID = (int) db.createValueForFactor(textView.getText().toString(), factorID);
            // check if view is a seek bar
            if (child.getClass().getName().equalsIgnoreCase("android.widget.SeekBar")){
                // if its a seek bar, get the value and save it to the db for the previous value ID
                SeekBar sb = (SeekBar) valuesLayout.getChildAt(i);
                // get the seek bar value
                sbScore = sb.getProgress();
                sbID = Integer.parseInt(sb.getTag().toString());
                // save to the db
                Log.d("debug", "[storing] id: " + Integer.toString(sbID) + " value: " + Integer.toString(sbScore));
                db.assignScoreToValue(login_login.username,sbScore,sbID);
            }

            //if not a seekbar, ignore

        }
    }
}
