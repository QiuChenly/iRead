package com.qiuchen.ly.iread.Presenter;

import com.qiuchen.ly.iread.Model.MainModel;
import com.qiuchen.ly.iread.View.MainView;

public class MainPresenter extends SimplePresenter<MainModel,MainView> {
    @Override
    MainModel createModel() {
        return new MainModel();
    }
}
