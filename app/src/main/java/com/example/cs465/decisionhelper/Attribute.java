package com.example.cs465.decisionhelper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.example.cs465.decisionhelper.R.id.button;

public class Attribute extends AppCompatActivity{
    Button button;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dh_add_attributes);

    }
    public void addListenerOnButton() {

        final Context context = this;

        button = (Button) findViewById(R.id.button15);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, PossibilityActivity.class);
                startActivity(intent);

            }

        });

    }
}
