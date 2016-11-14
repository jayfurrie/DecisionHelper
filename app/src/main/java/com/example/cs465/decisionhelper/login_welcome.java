package com.example.cs465.decisionhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class login_welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_welcome);
    }

    public void login_welcome_btn_loginOnClick(View view)
    {
        Intent intent = new Intent(this, login_login.class);
        startActivity(intent);
    }

    public void login_welcome_btn_signupOnClick(View view)
    {
        Intent intent = new Intent(this, login_signup.class);
        startActivity(intent);
    }
}
