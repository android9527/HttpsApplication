package io.github.android9527.httpsapplication.volley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by ksnowlv on 15/3/5.
 * 业务层使用的网络接口类，为单例
 */
public class YKNetInterface {
    private volatile static YKNetInterface sNetInterface;

    private Context mContext;
    private RequestQueue mRequestQueue;

    public static synchronized YKNetInterface getInstance() {

        if (null == sNetInterface){
            synchronized (YKNetInterface.class){

                if (null == sNetInterface){
                    sNetInterface = new YKNetInterface();
                }
            }
        }
        return sNetInterface;
    }

    private YKNetInterface() {

    }

    public void addRequest(Request request) {
        mRequestQueue.add(request);
    }

    public void setContext(final Context context) {
        this.mContext = context;
        if (null == mRequestQueue) {
            mRequestQueue = Volley.newRequestQueue(context);
        }
    }
}
