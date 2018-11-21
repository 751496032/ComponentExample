package com.hzw.base;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.hzw.BaseApp;
import com.hzw.common.CrashHandler;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class BaseActivity extends AppCompatActivity implements CrashHandler.OnUncaughtExceptionListener {
    private static final String TAG = "BaseActivity";
    private ActionBar mActionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);

        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.registerUncaughtExceptionListener(this);
        Thread.setDefaultUncaughtExceptionHandler(crashHandler);

        requestPermissions();
    }



    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    protected void onStop() {
        super.onStop();

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        BaseApp.getApplication().getRefWatcher().watch(this);
        CrashHandler.getInstance().unregisterUncaughtExceptionListener();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventHandler(BaseEvent event){

    }

    public void requestPermissions(){

        int selfPermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (selfPermission!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},100);
        }else {
            initData();
        }
    }

    public void initData() {
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==100 && grantResults.length>0  && grantResults[0]== PackageManager.PERMISSION_GRANTED){
            initData();
        }
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        showExceptionDialog(throwable.getMessage());
    }

    public  ActionBar  setActivityTitle(String title){
        if (mActionBar==null){
            mActionBar = getSupportActionBar();
        }
        if (mActionBar !=null){
            mActionBar.setTitle(title);
        }
        return mActionBar;
    }

/*
    public  ActionBar setBackNavigationHandler(){
        if (mActionBar==null){
            mActionBar = getSupportActionBar();
        }
        return mActionBar;
    }
*/

    public  void loadSingleFragment(int viewId, Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(viewId,fragment);
        transaction.commitAllowingStateLoss();
    }

    public  void loadSingleFragment( Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fl_container,fragment);
        transaction.commitAllowingStateLoss();
    }

    public void startActivity(Class<? extends Activity> clazz){
        startActivity(new Intent(this,clazz));
    }


    public void  showExceptionDialog(String msg){
        new AlertDialog.Builder(this)
                .setMessage("程序异常，将退出当前页面\n"+msg)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).create().show();
    }


}
