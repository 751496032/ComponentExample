package com.hzw.app;

import android.app.Application;

import com.hzw.BaseApp;

public class App extends BaseApp {


    @Override
    public void onCreate() {
        super.onCreate();

        initModuleApp(this);
    }

    /**
     * 通过反射调用每个组件的initModuleApp函数
     * @param application
     */
    @Override
    public void initModuleApp(Application application) {
        for (String appClassName:AppConfig.apps){

            try {
                Class<?> name = Class.forName(appClassName);
                BaseApp baseApp = (BaseApp) name.newInstance();
                baseApp.initModuleApp(application);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }

        }
    }
}
