package com.example.register;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

public class depo extends AppCompatActivity {

    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depo);

        tabLayout=(TabLayout)findViewById(R.id.tabs);
        appBarLayout=(AppBarLayout)findViewById(R.id.appbar);
        viewPager=(ViewPager)findViewById(R.id.container);

        depoAdapter adapter=new depoAdapter(getSupportFragmentManager());

        adapter.AddFragment(new fragment_depo1(), "Deposit");
        adapter.AddFragment(new fragment_depo2withdraw(), "Withdraw");


        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}