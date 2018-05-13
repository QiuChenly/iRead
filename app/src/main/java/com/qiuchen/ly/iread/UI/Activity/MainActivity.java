package com.qiuchen.ly.iread.UI.Activity;

import android.support.v4.app.Fragment;

import com.qiuchen.ly.iread.Adapter.MainVPAdapter;
import com.qiuchen.ly.iread.Base.BaseApp;
import com.qiuchen.ly.iread.Model.MainModel;
import com.qiuchen.ly.iread.Presenter.MainPresenter;
import com.qiuchen.ly.iread.R;
import com.qiuchen.ly.iread.UI.Fragment.NewsFragment;
import com.qiuchen.ly.iread.Utils.SuperNoScrollViewPager;
import com.qiuchen.ly.iread.View.MainView;

import java.util.ArrayList;
import java.util.List;

/**
 * 本类作为基本主页类，集成自baseapp抽象类。
 * 主要实现功能：主页面切换，基本碎片化布局容器父类
 */
public class MainActivity extends BaseApp<MainModel, MainView, MainPresenter> implements MainView {


    @Override
    protected MainView createMyView() {
        return this;
    }

    @Override
    protected MainPresenter createPres() {
        return new MainPresenter();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    SuperNoScrollViewPager SNSVP_mainContent;
    MainVPAdapter SNSVP_mainContentAdapter;

    @Override
    public void Create() {
        SNSVP_mainContent = findViewById(R.id.SNSVP_mainContent);
        SNSVP_mainContent.setOffscreenPageLimit(4);

        //defined four Fragment instance
        List<Fragment> mList = new ArrayList<>();
        mList.add(new NewsFragment());
        SNSVP_mainContentAdapter = new MainVPAdapter(getSupportFragmentManager(), mList);

        SNSVP_mainContent.setAdapter(SNSVP_mainContentAdapter);
        SNSVP_mainContent.setCurrentItem(0);

    }
}
