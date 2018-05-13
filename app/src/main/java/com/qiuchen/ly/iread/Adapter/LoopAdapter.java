package com.qiuchen.ly.iread.Adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qiuchen.ly.iread.DataModel.NewsM;
import com.qiuchen.ly.iread.R;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class LoopAdapter extends PagerAdapter {

    List<NewsM> mList;
    @BindView(R.id.iv_leaderNB)
    ImageView ivLeaderNB;
    @BindView(R.id.tv_leaderNB)
    TextView tvLeaderNB;

    public LoopAdapter(List<NewsM> mList) {
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View v = LayoutInflater.from(container.getContext())
                .inflate(R.layout.item_lunbo, null);
        Glide.with(container.getContext())
                .load(mList.get(position).imglink)
                .into(ivLeaderNB);
        ivLeaderNB.setTag(mList.get(position).link);
        tvLeaderNB .setText(mList.get(position).title);
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @OnClick(R.id.iv_leaderNB)
    public void onViewClicked() {
        //TODO JUMP
    }
}
