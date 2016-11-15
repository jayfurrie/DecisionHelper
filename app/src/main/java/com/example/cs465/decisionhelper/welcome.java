package com.example.cs465.decisionhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void loginbuttonOnClick(View view)
    {
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }

    public void signupButtonOnClick(View view)
    {
        Intent intent = new Intent(this, signUp.class);
        startActivity(intent);
    }
}