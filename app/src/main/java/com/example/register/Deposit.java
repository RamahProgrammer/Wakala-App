package com.example.register;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.logging.Handler;

public class Deposit extends AppCompatActivity {



    private Handler mHandler;
    TextView ujum,sim;
    private StringRequest request;
    private RequestQueue rq;
    ArrayList<messageModel> messageModelArrayList =new ArrayList<messageModel>();
    private static final String allmessages = "http://192.168.43.242/emoney/deposit.php";
    MessageAdapter  messageAdapter;
    RecyclerView recyclerView;


    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        tabLayout= (TabLayout) findViewById(R.id.tabs);


        // mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        //  tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        mSectionsPagerAdapter.AddFragment(new fragment_depo1(), "Deposit");
        mSectionsPagerAdapter.AddFragment(new fragment_depo2withdraw(), "Withdraw");



        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);


    }


// super.onOptionsItemSelected(item);






}