package com.zhou.doyin;

import android.app.Application;
import android.content.Context;

/**
 * Created by zhou on 2019/1/6.
 */
public class AppProfile extends Application {
    public static Context mcontext;

    @Override
    public void onCreate() {
        super.onCreate();
        mcontext = this;
    }

    public static Context getContext() {
        return mcontext;
    }
}
