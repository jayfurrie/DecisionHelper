package com.example.cs465.decisionhelper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class dh_rank_values extends BaseActivity {
    final Context context = this;
    private Button button;
    private EditText result;
    int decisionID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dh_rank_values);
        String name = getIntent().getStringExtra("factor_name");
        setTitle(name);
    }

    // populate list with corresponding values for each factor.




    public void dh_rank_values_btn_OKOnClick(View view)
    {
        Intent intent = new Intent(this, dh_list_rankvalues.class);
        startActivity(intent);
    }
}
