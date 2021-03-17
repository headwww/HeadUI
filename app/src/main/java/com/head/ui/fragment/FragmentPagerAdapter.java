package com.head.ui.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.List;

public class FragmentPagerAdapter extends androidx.fragment.app.FragmentPagerAdapter {
    List<Fragment> list;
    public FragmentPagerAdapter(@NonNull FragmentManager fm , List<Fragment> list) {
        super(fm);
        this.list=list;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
