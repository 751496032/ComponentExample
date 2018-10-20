package com.hzw.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hzw.base.BaseActivity;
import com.hzw.common.ARouterManager;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private BottomNavigationView mNavigationView;
    private ArrayList<Fragment> mFragments;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
        switchFragment(0);
        mNavigationView = findViewById(R.id.bottom_nav);
        mNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                switch (itemId) {
                    case R.id.item_home:
                        switchFragment(0);
                        break;
                    case R.id.item_cart:
                        switchFragment(1);
                        break;
                    case R.id.item_me:
                        switchFragment(2);
                        break;
                }
                return true; //必须返回true，否则拦截点击切换效果
            }
        });





    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        mFragments.add((Fragment) ARouter.getInstance().build(ARouterManager.HomeFragment).navigation());
        mFragments.add((Fragment) ARouter.getInstance().build(ARouterManager.CartFragment).navigation());
        mFragments.add((Fragment) ARouter.getInstance().build(ARouterManager.MeFragment).navigation());


    }


    private void switchFragment(int position) {
        if (mFragments==null || mFragments.isEmpty()) return;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        //把添加过的Fragment全部先隐藏
        int size = mFragments.size();
        for (int i = 0; i <size ; i++) {
            Fragment fragment = mFragments.get(i);
            if (fragment==null) continue;
            boolean itemAdd = fragment.isAdded();
            if (itemAdd) transaction.hide(fragment);
        }

        Fragment fragment = mFragments.get(position);
        if (fragment==null) return;
        boolean added = fragment.isAdded();
        //未添加则执行添加操作，反之执行显示操作
        if (!added) {
            transaction.add(R.id.fl_container, fragment);
        } else {
            transaction.show(fragment);
        }
        transaction.commitAllowingStateLoss();

    }
}
