package com.example.cs465.decisionhelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class dh_homepage extends AppCompatActivity {
    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dh_homepage);
    }

    public void dh_homepage_btn_newdecisionOnClick(View view) {
        Intent intent = new Intent(this, dh_decision_menu.class);
        startActivity(intent);
    }

    public void dh_homepage_btn_jobdecisionOnClick(View view) {
        Intent intent = new Intent(this, dh_decision_menu.class);
        startActivity(intent);
    }*/



    private static String TAG = dh_homepage.class.getSimpleName();

    ListView mDrawerList;
    RelativeLayout mDrawerPane;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dh_homepage);

        mNavItems.add(new NavItem("Decision Helper", "Begin making better decisions!"));
        mNavItems.add(new NavItem("Personality Quiz", "Discover your personality!"));
        mNavItems.add(new NavItem("Settings", "Change account specific settings!"));
        mNavItems.add(new NavItem("Help", "Confused? FAQ here!"));

        mDrawerLayout = (DrawerLayout) findViewById(R.id.dh_homepage_drawer_layout);

        mDrawerPane = (RelativeLayout) findViewById(R.id.dh_homepage_drawerPane);
        mDrawerList = (ListView) findViewById(R.id.dh_homepage_navList);
        DrawerListAdapter adapter = new DrawerListAdapter(this, mNavItems);
        mDrawerList.setAdapter(adapter);

        //Drawer Item click listeners
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                selectItemFromDrawer(position);
            }
        });
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
    public void dh_homepage_btn_newdecisionOnClick(View view)
    {
        Intent intent = new Intent(this, dh_decision_menu.class);
        startActivity(intent);
    }
    public void dh_homepage_btn_jobdecisionOnClick(View view)
    {
        Intent intent = new Intent(this, dh_decision_menu.class);
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