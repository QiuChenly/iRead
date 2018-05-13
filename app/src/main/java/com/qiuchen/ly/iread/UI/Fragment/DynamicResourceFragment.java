package com.qiuchen.ly.iread.UI.Fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.github.magiepooh.recycleritemdecoration.ItemDecorations;
import com.github.magiepooh.recycleritemdecoration.VerticalItemDecoration;
import com.qiuchen.ly.iread.Adapter.DynamicNewsRecyclerViewAdapter;
import com.qiuchen.ly.iread.Base.BaseFragment;
import com.qiuchen.ly.iread.DataModel.NewsM;
import com.qiuchen.ly.iread.DataModel.ResourceDynamicM;
import com.qiuchen.ly.iread.Model.DynamicRModel;
import com.qiuchen.ly.iread.Presenter.DynamicRPresenter;
import com.qiuchen.ly.iread.Presenter.NewsPresenter;
import com.qiuchen.ly.iread.R;
import com.qiuchen.ly.iread.View.DynamicView;

import java.util.ArrayList;
import java.util.List;

public class DynamicResourceFragment extends BaseFragment<DynamicRModel, DynamicView, DynamicRPresenter> implements DynamicView, SwipeRefreshLayout.OnRefreshListener {
    @Override
    protected DynamicView createMyView() {
        return this;
    }

    @Override
    protected DynamicRPresenter createPres() {
        return new DynamicRPresenter();
    }


    @Override
    public int getLayout() {
        return R.layout.fragment_dynamicr;
    }

    SwipeRefreshLayout swipe_down;
    RecyclerView rv_recyclerView;
    DynamicNewsRecyclerViewAdapter AP;

    @Override
    public void onViewCreated() {
        swipe_down = getView().findViewById(R.id.swipe_down);
        rv_recyclerView = getView().findViewById(R.id.rv_recyclerView);

        swipe_down.setOnRefreshListener(this);
        AP = new DynamicNewsRecyclerViewAdapter();
        RecyclerView.ItemDecoration itemDecoration = ItemDecorations.vertical(this.getContext())
                .type(DynamicNewsRecyclerViewAdapter.item_spec, R.drawable.recyclerview_splitline)
                .type(DynamicNewsRecyclerViewAdapter.item_normal, R.drawable.recyclerview_splitline)
                .create();
        rv_recyclerView.addItemDecoration(itemDecoration);
        rv_recyclerView.setAdapter(AP);
        getmPres().initUrl();
    }


    @Override
    public void getNews(List<NewsM> newsList, List<ResourceDynamicM> RSList) {
        swipe_down.setRefreshing(false);
        AP.setData(newsList, RSList);
        AP.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        getmPres().GetNews();
    }
}
