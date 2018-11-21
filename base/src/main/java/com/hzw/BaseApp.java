package com.hzw;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hzw.base.BuildConfig;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public abstract class BaseApp extends Application{
    public static final String TAG = "BaseApp";
    private static BaseApp sApplication;
    private RefWatcher mWatcher;

    public abstract void initModuleApp(Application application);

    public static BaseApp getApplication() {
        return sApplication;
    }

    public RefWatcher getRefWatcher(){
        return  mWatcher;
    }


    @Override public void onCreate() {
        super.onCreate();
        sApplication=this;
        initLeakCanary();
        initARouter();

    }

    private void initLeakCanary() {
        mWatcher = LeakCanary.install(this);
    }

    private void initARouter() {
        if (BuildConfig.DEBUG) {   // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }

}
