package com.hzw.me.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hzw.base.BaseEvent;
import com.hzw.common.ARouterManager;
import com.hzw.common.SpUtils;
import com.hzw.me.R;
import com.hzw.base.BaseFragment;
import com.hzw.provider.BaseProvider;

import org.greenrobot.eventbus.EventBus;

@Route(path = ARouterManager.MeFragment)
public class MeFragment extends BaseFragment implements View.OnClickListener {

    private boolean isLogin;
    private TextView mTvNickName;
    private TextView mTvIntro;
    private Button mButLogin;
    private Button mButExitLogin;

    public static MeFragment newInstance() {

        Bundle args = new Bundle();

        MeFragment fragment = new MeFragment();
        return fragment;
    }

    @Override
    public int setLayoutId() {
        return R.layout.module_fragment_me;
    }

    @Override
    public void initView(View view) {
        mTvNickName = view.findViewById(R.id.tv_nick_name);
        mTvIntro = view.findViewById(R.id.tv_intro);
        mButLogin = view.findViewById(R.id.but_login);
        mButExitLogin = view.findViewById(R.id.but_exit_login);
        mButLogin.setOnClickListener(this);
        mButExitLogin.setOnClickListener(this);

        BaseProvider provider = (BaseProvider) ARouter.getInstance().build("/me/provider/text").navigation();
        provider.setMeText(mTvIntro.getText().toString());
    }

    @Override
    public void lazyLoadData() {
        super.lazyLoadData();
        mActivity.setActivityTitle("用户中心");
        isLogin = SpUtils.getBoolean(mActivity, SpUtils.LOGIN_KEY);
        updateViews();

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.but_login) {

            ARouter.getInstance().build(ARouterManager.LoginActivity).navigation();
        } else if (id == R.id.but_exit_login) {
            isLogin = false;
            updateViews();
            SpUtils.putBoolean(mActivity, SpUtils.LOGIN_KEY, false);
            EventBus.getDefault().post(new BaseEvent(BaseEvent.LOGIN_EXIT));
        }
    }


    @Override
    public void onEventHandler(BaseEvent event) {
        super.onEventHandler(event);
        switch (event.eventType) {
            case BaseEvent.LOGIN:
                isLogin = true;
                updateViews();
                break;
        }
    }

    private void updateViews() {
        if (isLogin) {
            mTvIntro.setVisibility(View.VISIBLE);
            mTvNickName.setVisibility(View.VISIBLE);
            mButExitLogin.setVisibility(View.VISIBLE);
            mButLogin.setVisibility(View.GONE);
        }else {
            mTvIntro.setVisibility(View.GONE);
            mTvNickName.setVisibility(View.GONE);
            mButExitLogin.setVisibility(View.GONE);
            mButLogin.setVisibility(View.VISIBLE);
        }
    }
}
