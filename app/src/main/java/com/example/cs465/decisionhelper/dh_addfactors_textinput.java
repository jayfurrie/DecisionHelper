package com.example.cs465.decisionhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class dh_addfactors_textinput extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dh_addfactors_textinput);
    }

    public void dh_addfactors_textinput_btn_OKOnClick(View view)
    {
        Intent intent = new Intent(this, dh_add_choices.class);
        startActivity(intent);
    }

    public void dh_addfactors_textinput_btn_cancelOnClick(View view)
    {
        Intent intent = new Intent(this, dh_decision_menu.class);
        startActivity(intent);
    }
}
