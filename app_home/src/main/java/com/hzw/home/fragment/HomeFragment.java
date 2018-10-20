package com.hzw.home.fragment;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hzw.base.BaseFragment;
import com.hzw.common.ARouterManager;
import com.hzw.home.GoodsDetailActivity;
import com.hzw.home.R;

@Route(path = ARouterManager.HomeFragment) //定义路由路径
public class HomeFragment extends BaseFragment implements View.OnClickListener {
    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int setLayoutId() {
        return R.layout.module_fragment_home;
    }

    @Override
    public void initView(View view) {
        view.findViewById(R.id.but_detail).setOnClickListener(this);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

    }

    @Override
    public void lazyLoadData() {
        super.lazyLoadData();
        mActivity.setActivityTitle("首页");
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id==R.id.but_detail){
            ARouter.getInstance().build(ARouterManager.GoodsDetailActivity).navigation();
//            startActivity(GoodsDetailActivity.class);
        }
    }
}
