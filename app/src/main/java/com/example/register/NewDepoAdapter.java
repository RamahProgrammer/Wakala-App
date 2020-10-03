package com.example.register;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class  NewDepoAdapter extends FragmentPagerAdapter
{
    private final List<Fragment> fragmentList=new ArrayList<>();
    private final List<String> FragmentListTitles=new ArrayList<>();

    public NewDepoAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return FragmentListTitles.size();
    }


    public void AddFragment(Fragment fragment, String Title)

    {
        fragmentList.add(fragment);
        FragmentListTitles.add(Title);
    }

}
