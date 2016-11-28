package com.example.cs465.decisionhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.content.Context;
public class dh_share_invite_friend extends BaseActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dh_share_invite_friend);
        setTitle("Invite a Friend");
    }

    public void dh_share_invite_friend_btn_nextOnClick(View view) {
        Intent intent = new Intent(this, dh_share_reviews.class);
        startActivity(intent);
    }

    public void dh_share_invite_friend_btn_backOnClick(View view) {
        Intent intent = new Intent(this, dh_share_choose_receiver.class);
        startActivity(intent);
    }
}
