package com.example.cs465.decisionhelper;

// code pulled from http://abhiandroid.com/ui/tabhost

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.Toast;

public class TabsActivity extends TabActivity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);

        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost); // initiate TabHost
        TabHost.TabSpec spec; // Reusable TabSpec for each tab
        Intent intent; // Reusable Intent for each tab

        spec = tabHost.newTabSpec("dh"); // Create a new TabSpec using tab host
        spec.setIndicator("DH"); // set the “DH” as an indicator
        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent(this, DecisionHelperActivity.class);
        spec.setContent(intent);
        tabHost.addTab(spec);

        // Do the same for the other tabs

        spec = tabHost.newTabSpec("pq"); // Create a new TabSpec using tab host
        spec.setIndicator("PQ"); // set the “PQ” as an indicator
        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent(this, pq_start.class);
        spec.setContent(intent);
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("settings"); // Create a new TabSpec using tab host
        spec.setIndicator("S"); // set the “SETTINGS” as an indicator
        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent(this, SettingsActivity.class);
        spec.setContent(intent);
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("help"); // Create a new TabSpec using tab host
        spec.setIndicator("H"); // set the “HELP” as an indicator
        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent(this, HelpActivity.class);
        spec.setContent(intent);
        tabHost.addTab(spec);

        //set tab which one you want to open first time 0 or 1 or 2
        tabHost.setCurrentTab(0);
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                // display the name of the tab whenever a tab is changed
                Toast.makeText(getApplicationContext(), tabId, Toast.LENGTH_SHORT).show();
            }
        });


    }


}