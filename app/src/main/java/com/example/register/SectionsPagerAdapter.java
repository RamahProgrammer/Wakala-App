package com.example.register;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SectionsPagerAdapter extends FragmentPagerAdapter
{
    private final List<Fragment> fragmentList=new ArrayList<>();
    private final List<String> FragmentListTitles=new ArrayList<>();


    public SectionsPagerAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int i)
    {
        return fragmentList.get(i);
    }

    @Override
    public int getCount()
    {
        return FragmentListTitles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int i) {
        return FragmentListTitles.get(i);
    }


    public void AddFragment(Fragment fragment, String Title)

    {
        fragmentList.add(fragment);
        FragmentListTitles.add(Title);

    }

}
