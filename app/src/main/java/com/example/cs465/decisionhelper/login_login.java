package com.example.cs465.decisionhelper;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class login_login extends AppCompatActivity {
    //holds username for all other activities. access with login_login.usernameText
    final Context context = this;
    public static String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_login);
    }

    public void login_login_btn_loginOnClick(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        Log.d("debug", "login button OnClick method is running");
        EditText et_username = (EditText) findViewById(R.id.login_login_et_username);
        username = et_username.getText().toString();
        Log.d("debug", "username is: " + username);
        Log.d("debug", "default is: " + getString(R.string.login_login_et_usernametext));
        if (username.equals(getString(R.string.login_login_et_usernametext)))
        {
            //user has not changed username from default
            Log.d("debug", "username hasn't changed from default");
            Log.d("debug", "username is too short");
            builder.setTitle("Error");
            builder.setMessage("Please enter your username.");
            builder.setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    //do nothing
                }
            });
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.show();
            return;
        }
        else if (username.length() <= 4)
        {
            Log.d("debug", "username is too short");
            builder.setTitle("Error");
            builder.setMessage("Username must be longer than 4 characters");
            builder.setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    //do nothing
                }
            });
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.show();
            return;
        }
        Log.d("debug", "login successful, let's go!");
        Intent intent = new Intent(this, dh_homepage.class);
        startActivity(intent);
    }

}
