package com.hzw.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hzw.common.LogUtils;
import com.hzw.common.CrashHandler;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public abstract class BaseFragment extends Fragment implements CrashHandler.OnUncaughtExceptionListener {

    //子类的类名
    private String mClassSimpleName;
    public BaseActivity mActivity;
    private boolean isFirstLoad=true;

    public abstract int setLayoutId();
    public abstract void initView(View view);


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseActivity) getActivity();
        mClassSimpleName = this.getClass().getSimpleName();
        LogUtils.d(mClassSimpleName,"onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d(mClassSimpleName,"onCreate");
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtils.d(mClassSimpleName,"onCreateView");
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.registerUncaughtExceptionListener(this);
        Thread.setDefaultUncaughtExceptionHandler(crashHandler);
        return inflater.inflate(setLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        LogUtils.d(mClassSimpleName,"onViewCreated");
        super.onViewCreated(view, savedInstanceState);

        //注册事件总线
        EventBus.getDefault().register(this);

        initView(view);
         if (isFirstLoad)lazyLoadData();
    }

    @Override
    public void onStart() {
        LogUtils.d(mClassSimpleName,"onStart");
        super.onStart();

    }


    @Override
    public void onResume() {
        LogUtils.d(mClassSimpleName,"onResume");
        super.onResume();
    }


    @Override
    public void onPause() {
        LogUtils.d(mClassSimpleName,"onPause");
        super.onPause();
    }


    @Override
    public void onStop() {
        LogUtils.d(mClassSimpleName,"onStop");
        super.onStop();

    }

    @Override
    public void onDestroyView() {
        LogUtils.d(mClassSimpleName,"onDestroyView");
        super.onDestroyView();
        CrashHandler.getInstance().unregisterUncaughtExceptionListener();
        //反注册事件总线
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroy() {
        LogUtils.d(mClassSimpleName,"onDestroy");
        super.onDestroy();
    }



    @Override
    public void onDetach() {
        LogUtils.d(mClassSimpleName,"onDetach");
        super.onDetach();
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        LogUtils.d(mClassSimpleName,"setUserVisibleHint: "+ isVisibleToUser);
    }

    /*
    * Fragment初始化后并且触发了show或hide方法后会调用此方法
    * */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        LogUtils.d(mClassSimpleName,"onHiddenChanged: "+ hidden);
        isFirstLoad=false;
        if (!isFirstLoad && !hidden){
            lazyLoadData();
        }
    }

    /**
     * 事件总线订阅者消息处理函数
     * @param event 事件类型
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventHandler(BaseEvent event){
        LogUtils.d("onEventHandler");
    }

    /**
     * 懒加载
     */
    public void lazyLoadData(){

    }

    public void startActivity(Class<? extends Activity> calzz){
        startActivity(new Intent(mActivity,calzz));
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        LogUtils.e(mClassSimpleName,throwable.getMessage());
        mActivity.showExceptionDialog(throwable.getMessage());
    }
}
