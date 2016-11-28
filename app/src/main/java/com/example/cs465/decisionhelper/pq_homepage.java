package com.example.cs465.decisionhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class pq_homepage extends BaseActivity {
    public final static String pq_Question_Activity = "com.example.cs465.decisionhelper.pq_questions";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pq_homepage);
        setTitle("Personality Quiz");
    }

    /** called when the user clicks the Start Quiz button */
    public void pq_homepage_btn_startquizOnClick(View view)
    {
        Intent intent = new Intent(this, pq_questions.class);
        startActivity(intent);

    }
}
