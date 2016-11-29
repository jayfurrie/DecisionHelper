package com.example.cs465.decisionhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class login_login extends AppCompatActivity {
    //holds username for all other activities. access with login_login.usernameText
    public static String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_login);
    }

    public void login_login_btn_loginOnClick(View view)
    {
        EditText et_username = (EditText) findViewById(R.id.login_login_et_username);
        username = et_username.getText().toString();
        Intent intent = new Intent(this, dh_homepage.class);
        startActivity(intent);
    }

}
