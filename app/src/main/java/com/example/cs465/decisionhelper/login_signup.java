package com.example.cs465.decisionhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class login_signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);
    }

    public void login_signup_btn_signupOnClick(View view)
    {
        Intent intent = new Intent(this, login_signupconfirmation.class);
        startActivity(intent);
    }
}
