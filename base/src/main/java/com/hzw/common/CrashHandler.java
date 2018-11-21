package com.hzw.common;

import java.lang.ref.WeakReference;

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static CrashHandler mCrashHandler;
    private OnUncaughtExceptionListener mOnUncaughtExceptionListener;

    private CrashHandler() {
    }

    public static CrashHandler getInstance(){
        if (mCrashHandler==null){
            synchronized (CrashHandler.class){
                if (mCrashHandler==null){
                    mCrashHandler=new CrashHandler();
                }
            }
        }
        return mCrashHandler;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {

        if (mOnUncaughtExceptionListener!=null){
            mOnUncaughtExceptionListener.uncaughtException(t,e);
        }

    }

    public void registerUncaughtExceptionListener(OnUncaughtExceptionListener onUncaughtExceptionListener) {
        mOnUncaughtExceptionListener = new WeakReference<>(onUncaughtExceptionListener).get();
    }

    public interface OnUncaughtExceptionListener{

        void uncaughtException(Thread thread,Throwable throwable);

    }

    public void  unregisterUncaughtExceptionListener(){
        if (mOnUncaughtExceptionListener!=null){
            mOnUncaughtExceptionListener=null;
        }
    }


}
