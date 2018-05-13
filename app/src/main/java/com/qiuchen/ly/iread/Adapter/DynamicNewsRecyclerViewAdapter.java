package com.qiuchen.ly.iread.Adapter;

import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.qiuchen.ly.iread.DataModel.NewsM;
import com.qiuchen.ly.iread.DataModel.ResourceDynamicM;
import com.qiuchen.ly.iread.R;

import java.util.List;

import butterknife.BindView;

public class DynamicNewsRecyclerViewAdapter extends RecyclerView.Adapter<BaseVH> {
    List<NewsM> newsList;
    List<ResourceDynamicM> RSList;
    @BindView(R.id.vp_loop)
    ViewPager vpLoop;
    @BindView(R.id.ll_Bip)
    LinearLayout llBip;

    public void setData(List<NewsM> newsList, List<ResourceDynamicM> RSList) {
        this.newsList = newsList;
        this.RSList = RSList;
    }

    public static final int item_normal = 634;
    public static final int item_spec = 704;

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return item_spec;
        else return item_normal;
    }

    @NonNull
    @Override
    public BaseVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int id = R.layout.item_type_normal;
        if (viewType == item_spec) {
            id = R.layout.item_type_spec;
        }
        return new BaseVH(LayoutInflater.from(parent.getContext())
                .inflate(id, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseVH holder, int position) {
        if (getItemViewType(position) == item_spec) {
            SPEC(holder);
        } else if (getItemViewType(position) == item_normal) {
            NORMAL(holder);
        }
    }

    void SPEC(BaseVH view) {
        vpLoop = view.itemView.findViewById(R.id.vp_loop);
        LoopAdapter lop = new LoopAdapter(newsList);
        vpLoop.setOffscreenPageLimit(5);
        vpLoop.setAdapter(lop);
        vpLoop.setCurrentItem(0);
        Loop l = new Loop(vpLoop, 1000, new android.os.Handler(Looper.getMainLooper()));
        l.start();
    }


    class Loop extends Thread {
        ViewPager vp;
        int t;
        android.os.Handler hand;
        int now = 0;

        public Loop(ViewPager vp, int timer, android.os.Handler hand) {
            this.vp = vp;
            t = timer;
            this.hand = hand;
        }

        @Override
        public void run() {
            while (vp != null) {
                hand.post(new Runnable() {
                    @Override
                    public void run() {
                        vp.setCurrentItem(++now);
                    }
                });
                try {
                    Thread.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void stopThis() {
            vp = null;
        }
    }

    void NORMAL(BaseVH view) {

    }

    @Override
    public int getItemCount() {
        return RSList.size() + 1;
    }
}
