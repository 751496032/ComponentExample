package com.hzw.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hzw.base.BaseActivity;
import com.hzw.base.BaseEvent;
import com.hzw.common.ARouterManager;
import com.hzw.common.LogUtils;
import com.hzw.common.SpUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

@Route(path = ARouterManager.GoodsDetailActivity)
@SuppressWarnings("all")
public class GoodsDetailActivity extends BaseActivity {

    private String mGoodsName="肤美灵洗面奶";
    private String mGoodsDesc="抖音同款黄胖子 肤美灵洗面奶 去黑头螨虫收毛孔国货护肤洁面男女";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_activity_goods_detail);
        setActivityTitle("商品详情");


        TextView tvGoodsName = findViewById(R.id.tv_goods_name);
        tvGoodsName.setText("商品名称：" + mGoodsName);

        boolean isLogin = SpUtils.getBoolean(this, SpUtils.LOGIN_KEY);

    }


    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.but_share) {
            ARouter.getInstance().build(ARouterManager.ShareActivity)
                    .withString("shareTitle",mGoodsName)
                    .withString("shareContent",mGoodsDesc)
                    .navigation(this, new NavCallback() {
                @Override
                public void onArrival(Postcard postcard) {

                }

                @Override
                public void onInterrupt(Postcard postcard) {
                    super.onInterrupt(postcard);
                    LogUtils.i("onInterrupt");
                }
            });
        }
    }

    @Override
    public void onEventHandler(BaseEvent event) {
        super.onEventHandler(event);
        LogUtils.d("收到：" + event.eventType);
        switch (event.eventType) {
            case BaseEvent.LOGIN:
                break;
        }
    }
}
