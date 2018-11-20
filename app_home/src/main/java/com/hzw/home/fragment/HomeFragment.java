package com.hzw.home.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hzw.base.BaseFragment;
import com.hzw.common.ARouterManager;
import com.hzw.home.GoodsDetailActivity;
import com.hzw.home.R;
import com.hzw.provider.BaseProvider;

@Route(path = ARouterManager.HomeFragment) //定义路由路径
public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private TextView mTextView;

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public int setLayoutId() {
        return R.layout.module_fragment_home;
    }

    @Override
    public void initView(View view) {
        view.findViewById(R.id.but_detail).setOnClickListener(this);
        mTextView = view.findViewById(R.id.textView);
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


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){
            BaseProvider provider = (BaseProvider) ARouter.getInstance().build("/me/provider/text").navigation();
            String meText = provider.getMeText();
            mTextView.setText(TextUtils.isEmpty(meText)?"请先初始化Me组件":meText);
            Log.d("BaseProvider:: ",TextUtils.isEmpty(meText)?"请先初始化Me组件":meText);
        }
    }
}
