package com.example.cs465.decisionhelper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class dh_add_factors extends AppCompatActivity {
    Button button;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dh_add_factors);

    }
    public void addListenerOnButton() {

        final Context context = this;

        button = (Button) findViewById(R.id.dh_add_factors_btn_newfactor);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, dh_add_values.class);
                startActivity(intent);

            }

        });

    }

    public void dh_add_values_btn_factor1OnClick(View view)
    {
        Intent intent = new Intent(this, dh_add_values.class);
        startActivity(intent);
    }

    public void dh_add_values_btn_factor2OnClick(View view)
    {
        Intent intent = new Intent(this, dh_add_values.class);
        startActivity(intent);
    }

    public void dh_add_values_btn_factor3OnClick(View view)
    {
        Intent intent = new Intent(this, dh_add_values.class);
        startActivity(intent);
    }

    public void dh_add_factors_btn_backOnClick(View view)
    {
        Intent intent = new Intent(this, dh_decision_menu.class);
        startActivity(intent);
    }
}
