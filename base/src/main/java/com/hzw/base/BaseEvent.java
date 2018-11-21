package com.hzw.base;

public class BaseEvent {

    public static final int LOGIN_EXIT=0;//退出登录
    public static final int LOGIN=1;//登录

    public int eventType;

    public BaseEvent(int eventType) {
        this.eventType = eventType;
    }
}
