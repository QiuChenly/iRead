package com.qiuchen.ly.iread.Base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.qiuchen.ly.iread.App;
import com.qiuchen.ly.iread.Model.SimpleModel;
import com.qiuchen.ly.iread.Presenter.SimplePresenter;
import com.qiuchen.ly.iread.Utils.AUtils;
import com.qiuchen.ly.iread.View.SimpleView;

public abstract class BaseApp<M extends SimpleModel, V extends SimpleView, P extends SimplePresenter<M, V>> extends AppCompatActivity {
    private P mPres;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        //适配小米 和 魅族机型，安卓6.0以上自带支持。
        AUtils.FlymeSetStatusBarLightMode(getWindow(), true);
        AUtils.MIUISetStatusBarLightMode(this, true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        mPres = createPres();
        V mView = createMyView();
        if (mView != null) mPres.attach(mView);
        Create();
    }

    public abstract void Create();

    long last = 0;

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long now = System.currentTimeMillis();
            if (now - last > 2000) {
                Toast.makeText(this, "再按一次退出！", Toast.LENGTH_SHORT).show();
                last = now;
                return true;
            } else
                App.closedApp();
        }
        return super.onKeyUp(keyCode, event);
    }

    protected abstract V createMyView();

    protected abstract P createPres();

    public abstract int getLayout();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPres != null)
            mPres.detach();
    }

}
