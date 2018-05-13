package com.qiuchen.ly.iread.Presenter;

import android.util.Log;
import android.util.Patterns;

import com.bumptech.glide.load.engine.Resource;
import com.qiuchen.ly.iread.DataModel.NewsM;
import com.qiuchen.ly.iread.DataModel.ResourceDynamicM;
import com.qiuchen.ly.iread.Model.NewsModel;
import com.qiuchen.ly.iread.Net.API_URL;
import com.qiuchen.ly.iread.View.NewsView;

import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewsPresenter extends SimplePresenter<NewsModel, NewsView> {
    private static final String TAG = "NewsPresenter";

    @Override
    NewsModel createModel() {
        return new NewsModel();
    }


}
