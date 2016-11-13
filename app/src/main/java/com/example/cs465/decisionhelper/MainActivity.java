package com.example.cs465.decisionhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //setContentView(R.layout.activity_pq_start);
    }

    //uncomment to allow for testing personality quiz activity
    /*
    public void startQuiz(View view)
    {
        Intent intent = new Intent(this, pq_questions.class);
        startActivity(intent);

    }
    */

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
