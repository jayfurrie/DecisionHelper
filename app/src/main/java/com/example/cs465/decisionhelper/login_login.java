package com.example.cs465.decisionhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class login_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_login);
    }

    public void login_login_btn_loginOnClick(View view)
    {
        Intent intent = new Intent(this, dh_homepage.class);
        startActivity(intent);
    }

}
