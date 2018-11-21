package com.hzw.interceptor;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hzw.common.ARouterManager;
import com.hzw.common.SpUtils;

/**
 * 页面跳转拦截器
 * 应用场景：如某些页面需要登录才可操作，可通过拦截器来统一处理跳转页面
 */
@Interceptor(priority = 7)
public class ARouterInterceptor implements IInterceptor {
    Context mContext;

    /**
     * The operation of this interceptor.
     *
     * @param postcard meta
     * @param callback cb
     */
    @Override
    public void process(final Postcard postcard, final InterceptorCallback callback) {
        boolean isLogin = SpUtils.getBoolean(mContext, SpUtils.LOGIN_KEY);
        String path = postcard.getPath();
        if (!isLogin&&path.contains(ARouterManager.LOGIN_AFTER)){
            //未登录
            ARouter.getInstance().build(ARouterManager.LoginActivity).navigation();
            callback.onInterrupt(null);
        }else {
            callback.onContinue(postcard);
        }
    }

    /**
     * Do your init work in this method, it well be call when processor has been load.
     * 在路由初始化时会加载拦截器
     * @param context ctx
     */
    @Override
    public void init(Context context) {
        mContext = context;
        Log.e("testService", ARouterInterceptor.class.getName() + " has init.");
    }
}
