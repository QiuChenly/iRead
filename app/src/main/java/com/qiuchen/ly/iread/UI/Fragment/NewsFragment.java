package com.qiuchen.ly.iread.UI.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.qiuchen.ly.iread.Adapter.NewsVP;
import com.qiuchen.ly.iread.Base.BaseFragment;
import com.qiuchen.ly.iread.DataModel.NewsM;
import com.qiuchen.ly.iread.DataModel.ResourceDynamicM;
import com.qiuchen.ly.iread.Model.NewsModel;
import com.qiuchen.ly.iread.Presenter.NewsPresenter;
import com.qiuchen.ly.iread.R;
import com.qiuchen.ly.iread.View.NewsView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewsFragment extends BaseFragment<NewsModel, NewsView, NewsPresenter> implements NewsView {
    @Override
    protected NewsView createMyView() {
        return this;
    }

    @Override
    protected NewsPresenter createPres() {
        return new NewsPresenter();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_news;
    }

    ViewPager vp;

    @Override
    public void onViewCreated() {
        List<Fragment> mList = new ArrayList<>();
        mList.add(new DynamicResourceFragment());
        mList.add(new Fragment());
        NewsVP a = new NewsVP(getChildFragmentManager(), mList, Arrays.asList("动态", "更多"));
        vp = getView().findViewById(R.id.vp_searchContent);
        vp.setAdapter(a);
        ((TabLayout) getView().findViewById(R.id.tl_ziyuan)).setupWithViewPager(vp);
    }

}
