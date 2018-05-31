package io.github.android9527.httpsapplication;

import android.app.Application;

import io.github.android9527.httpsapplication.okhttp.OkHttp3util;
import io.github.android9527.httpsapplication.volley.YKNetInterface;

/**
 * Created by chenfeiyue on 2018/5/31.
 * Description ：
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        YKNetInterface.getInstance().setContext(this);
        OkHttp3util.initClient(this);
    }
}
