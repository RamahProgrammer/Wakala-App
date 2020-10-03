package com.example.register;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class SlideAdapter extends PagerAdapter{
     private Context context;
    private   LayoutInflater layoutInflater;
    private ImageView slide_imageView;
      public SlideAdapter(Context context)
      {
          this.context = context;


      }
      public int[] slide_images = {
              R.drawable.slide4,
              R.drawable.slide3,
              R.drawable.slide2,
              R.drawable.slide1


      };
    /*  public String[] slide_headings = {
              "1",
              "2",
              "3",
              "4"


    @Override
    public int getCount() {

        return slide_headings.length;
    } */

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (RelativeLayout) o;

    }

    @Override
    public int getCount() {
        return slide_images.length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.imageView2);
        slideImageView.setImageResource(slide_images[position]);
        container.addView(view);



        return  view;
    }
    @Override
    public void  destroyItem(ViewGroup container, int position, Object object){
       // super.destroyItem(container, position, object);
      container.removeView((RelativeLayout)object);
    }


}
