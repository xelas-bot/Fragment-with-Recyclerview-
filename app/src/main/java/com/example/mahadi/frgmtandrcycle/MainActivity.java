package com.example.mahadi.frgmtandrcycle;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPageAdapter viewPageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout_id);
        viewPager = (ViewPager) findViewById(R.id.pageViewId);

        viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager());

        //     Add Fragment
        viewPageAdapter.AddFrmt(new Frmt_call("Fri"), "Friday");
        viewPageAdapter.AddFrmt(new FrmtContact(), "Saturday");

        viewPageAdapter.AddFrmt(new Frmt_fav(), "Sunday");
        viewPageAdapter.AddFrmt(new Frmt_call("Mon"), "Monday");
        viewPageAdapter.AddFrmt(new Frmt_call("Tues"), "Tuesday");
        viewPageAdapter.AddFrmt(new Frmt_call("Wed"), "Wednesday");
        viewPageAdapter.AddFrmt(new Frmt_call("Thur"), "Thursday");


        viewPager.setAdapter(viewPageAdapter);
        tabLayout.setupWithViewPager(viewPager);

        //tabLayout.getTabAt(0).setIcon(R.drawable.ic_call_black_24dp);
        //tabLayout.getTabAt(1).setIcon(R.drawable.ic_group_black_24dp);
        //tabLayout.getTabAt(2).setIcon(R.drawable.ic_favorite_black_24dp);

        //Remove ActionBar Shadow

        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);
    }
}
