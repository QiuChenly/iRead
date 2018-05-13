package com.qiuchen.ly.iread.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;

public class NewsVP extends MainVPAdapter {
    List<String> list;

    public NewsVP(FragmentManager fm, List<Fragment> fg, List<String> list) {
        super(fm, fg);
        this.list = list;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position);
    }
}
