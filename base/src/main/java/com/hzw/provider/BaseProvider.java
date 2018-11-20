package com.hzw.provider;

import android.content.Context;

/**
 * author: HZWei7
 * date:2018/11/19
 * description:
 */
public class BaseProvider implements IBaseProvider{

    private String meText;

    @Override
    public void init(Context context) {

    }

    public void setMeText(String meText) {
        this.meText = meText;
    }

    public String getMeText(){
        return meText;
    }
}
