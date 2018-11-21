package com.hzw.common;

/**
 * 路由管理类
 * 命名规则：/模块名/特殊描述/目标页面名称（特殊描述可选）
 * 需要登录的页面操作，带login_after字段
 */

public final class ARouterManager {

    public static final String LOGIN_AFTER="login_after";

    public static final String HomeFragment = "/home/HomeFragment";

    public static final String CartFragment="/cart/CartFragment";

    public static final String MeFragment="/me/CartFragment";

    public static final String LoginActivity="/login/LoginActivity";

    public static final String GoodsDetailActivity="/home/GoodsDetailActivity";

    public static final String ShareActivity="/login/login_after/ShareActivity";

}
