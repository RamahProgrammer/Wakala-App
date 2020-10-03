package com.example.register;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

public class swiping extends AppCompatActivity {

    private ViewPager mslideviewpages;
    private LinearLayout mdotLayout;
    private TextView[] dot;

    private SlideAdapter slideAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swiping);
        mslideviewpages =(ViewPager) findViewById(R.id.slideviewpages);
        mdotLayout = (LinearLayout) findViewById(R.id.dotsLayout);

   slideAdapter = new SlideAdapter(this);
   mslideviewpages.setAdapter(slideAdapter);
   dotindicator(0);
    }
    public void dotindicator(int position){
        dot=new TextView[4];
        mdotLayout.removeAllViews();
        for(int i=0;i<dot.length;i++){
            dot[i]=new TextView(this);
            dot[i].setText(Html.fromHtml("&#8226;"));
            dot[i].setTextSize(35);
            dot[i].setTextColor(getResources().getColor(R.color.colorAccent));
            mdotLayout.addView(dot[i]);

        }
        if(dot.length>0){
            dot[position].setTextColor(getResources().getColor(R.color.colorAccent));
        }
    }
    ViewPager.OnPageChangeListener viewListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1s) {

        }

        @Override
        public void onPageSelected(int i) {
            dotindicator(i);
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}
