package com.example.cs465.decisionhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class signUpConfirmation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_confirmation);
    }

    public void dh_button_registration_backOnClick(View view)
    {
        Intent intent = new Intent(this, DecisionHelperActivity.class);
        startActivity(intent);

    }
}
