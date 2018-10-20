package com.hzw.cart.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hzw.base.BaseEvent;
import com.hzw.base.BaseFragment;
import com.hzw.cart.R;
import com.hzw.common.ARouterManager;
import com.hzw.common.SpUtils;

@Route(path = ARouterManager.CartFragment)
public class CartFragment extends BaseFragment {

    private TextView mTvCartDesc;

    public static CartFragment newInstance() {

        Bundle args = new Bundle();

        CartFragment fragment = new CartFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int setLayoutId() {
        return R.layout.module_fragment_cart;
    }

    @Override
    public void initView(View view) {
        mTvCartDesc = view.findViewById(R.id.tv_cart_desc);
    }

    @Override
    public void lazyLoadData() {
        super.lazyLoadData();
        mActivity.setActivityTitle("购物车");
        boolean isLogin = SpUtils.getBoolean(mActivity, SpUtils.LOGIN_KEY);
        if (isLogin) {
            mTvCartDesc.setText("车车空空如也……");
            mTvCartDesc.setTextColor(getResources().getColor(android.R.color.black));
        } else {
            mTvCartDesc.setText("尚未登录……");
            mTvCartDesc.setTextColor(getResources().getColor(android.R.color.darker_gray));
        }
    }

    @Override
    public void onEventHandler(BaseEvent event) {
        super.onEventHandler(event);
        switch (event.eventType) {
            case BaseEvent.LOGIN_EXIT:
                mTvCartDesc.setText("尚未登录……");
                mTvCartDesc.setTextColor(getResources().getColor(android.R.color.darker_gray));
                break;
            case BaseEvent.LOGIN:
                mTvCartDesc.setText("车车空空如也……");
                mTvCartDesc.setTextColor(getResources().getColor(android.R.color.black));
                break;
        }
    }
}
