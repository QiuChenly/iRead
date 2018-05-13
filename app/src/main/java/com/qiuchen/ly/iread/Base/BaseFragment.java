package com.qiuchen.ly.iread.Base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qiuchen.ly.iread.Model.SimpleModel;
import com.qiuchen.ly.iread.Presenter.SimplePresenter;
import com.qiuchen.ly.iread.View.SimpleView;

public abstract class BaseFragment<M extends SimpleModel, V extends SimpleView, P extends SimplePresenter<M, V>> extends Fragment {
    private P mPres;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayout(),null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPres = createPres();
        V mView = createMyView();
        if (mView != null) mPres.attach(mView);
        onViewCreated();
    }

    protected abstract V createMyView();

    protected abstract P createPres();

    public abstract int getLayout();
    public abstract void onViewCreated();

    public P getmPres() {
        return mPres;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPres != null)
            mPres.detach();
    }
}
