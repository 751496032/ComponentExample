package com.hzw.me;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.hzw.BaseApp;

/**
 * author: HZWei7
 * date:2018/11/18
 * description:
 */
public class MeApp extends BaseApp {
    private Context mContext;

    /**
     * 在onCreate中初始化是组件独立运行时用来初始化
     * 在合并组合时是不会调用onCreate
     */
    @Override
    public void onCreate() {
        super.onCreate();
        this.mContext=this;
    }

    /**
     * 合并组合时初始化数据
     * @param application
     */
    @Override
    public void initModuleApp(Application application) {
        mContext=application.getApplicationContext();

        Log.d(TAG,"MeApp 初始化数据");
    }
}
