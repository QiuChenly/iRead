package com.qiuchen.ly.iread.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class MainVPAdapter extends FragmentPagerAdapter {
    private List<Fragment> fg;

    public MainVPAdapter(FragmentManager fm, List<Fragment> fg) {
        super(fm);
        this.fg = fg;
    }

    @Override
    public Fragment getItem(int position) {
        return fg.get(position);
    }

    @Override
    public int getCount() {
        return fg.size();
    }
}
