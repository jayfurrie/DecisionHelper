package com.example.cs465.decisionhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class dh_share_reviews extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dh_share_reviews);
        setTitle("Share Reviews");
    }
    public void dh_share_reviews_btn_sendOnClick(View view) {
        Intent intent = new Intent(this, dh_decision_menu.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(),
                "Success!", Toast.LENGTH_SHORT).show();
    }
    public void dh_share_reviews_btn_cancelOnClick(View view) {
        Intent intent = new Intent(this, dh_decision_menu.class);
        startActivity(intent);
    }

}
