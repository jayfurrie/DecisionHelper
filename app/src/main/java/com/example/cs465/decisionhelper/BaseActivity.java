package com.example.cs465.decisionhelper;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class BaseActivity extends AppCompatActivity {
    ListView mDrawerList;
    RelativeLayout mDrawerPane;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout fullView;
    ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();
    Storage db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void setContentView(int layoutResID)
    {
        fullView = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        FrameLayout activityContainer = (FrameLayout) fullView.findViewById(R.id.activity_content);
        getLayoutInflater().inflate(layoutResID, activityContainer, true);
        super.setContentView(fullView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        setTitle("Use setTitle()");

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            mDrawerToggle = new ActionBarDrawerToggle(this, fullView, R.string.btn_OK, R.string.btn_cancel);
            mDrawerToggle.setDrawerIndicatorEnabled(true);
            fullView.addDrawerListener(mDrawerToggle);
            mDrawerToggle.syncState();
        }

        mNavItems.add(new NavItem("Decision Helper", "Begin making better decisions!"));
        mNavItems.add(new NavItem("Personality Quiz", "Discover your personality!"));
        mNavItems.add(new NavItem("Settings", "Change account specific settings!"));
        mNavItems.add(new NavItem("Help", "Confused? FAQ here!"));

        mDrawerPane = (RelativeLayout) findViewById(R.id.drawerPane);
        mDrawerList = (ListView) findViewById(R.id.navList);
        DrawerListAdapter adapter = new DrawerListAdapter(this, mNavItems);
        mDrawerList.setAdapter(adapter);

        //Drawer Item click listeners
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                selectItemFromDrawer(position);
            }
        });

        db = new Storage(this);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    public void setTitle(String title) {
        TextView textView = (TextView) findViewById(R.id.toolbar_title);
        if (title.length() > 17) {
            title = title.substring(0, 16);
        }
        textView.setText(title);
    }


    private void selectItemFromDrawer(int position)
    {
        Class selectedActivity = null;
        switch (position)
        {
            case 0: selectedActivity = dh_homepage.class;
                break;
            case 1: selectedActivity = pq_homepage.class;
                break;
            case 2: selectedActivity = s_homepage.class;
                break;
            case 3: selectedActivity = h_homepage.class;
                break;
        }
        Intent intent = new Intent(this, selectedActivity);
        startActivity(intent);
    }

    class NavItem
    {
        String mTitle;
        String mSubtitle;

        public NavItem(String title, String subtitle)
        {
            mTitle = title;
            mSubtitle = subtitle;
        }
    }

    class DrawerListAdapter extends BaseAdapter
    {
        Context mContext;
        ArrayList<NavItem> mNavItems;

        public DrawerListAdapter(Context context, ArrayList<NavItem> navItems)
        {
            mContext = context;
            mNavItems = navItems;

        }

        @Override
        public int getCount()
        {
            return mNavItems.size();
        }

        @Override
        public Object getItem(int position)
        {
            return mNavItems.get(position);
        }

        @Override
        public long getItemId(int position)
        {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            View view;

            if (convertView == null)
            {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.drawer_list_item, null);
            }
            else
            {
                view = convertView;
            }

            TextView titleView = (TextView) view.findViewById(R.id.title);
            TextView subtitleView = (TextView) view.findViewById(R.id.subtitle);

            titleView.setText( mNavItems.get(position).mTitle);
            subtitleView.setText( mNavItems.get(position).mSubtitle);

            return view;
        }
    }
}
