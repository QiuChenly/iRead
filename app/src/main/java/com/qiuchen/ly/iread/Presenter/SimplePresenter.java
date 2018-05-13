package com.qiuchen.ly.iread.Presenter;

import com.qiuchen.ly.iread.Model.SimpleModel;
import com.qiuchen.ly.iread.Net.BaseRequest;
import com.qiuchen.ly.iread.View.SimpleView;

public abstract class SimplePresenter<M extends SimpleModel, V extends SimpleView> extends BaseRequest {
    private V mView;
    private M model;

    abstract M createModel();

    public void attach(V mView) {
        this.mView = mView;
        model = createModel();
    }

    public void detach() {
        this.mView = null;
        this.model = null;
    }

    public V getView() {
        return mView;
    }

    public M getModel() {
        return model;
    }

}
