package com.qiuchen.ly.iread.View;

import com.qiuchen.ly.iread.DataModel.NewsM;
import com.qiuchen.ly.iread.DataModel.ResourceDynamicM;

import java.util.List;

public interface DynamicView extends SimpleView {
    void getNews(List<NewsM> newsList, List<ResourceDynamicM> RSList);
}
