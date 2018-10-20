package com.hzw.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hzw.base.BaseActivity;
import com.hzw.common.ARouterManager;

/**
 * 分享页面
 */
@Route(path = ARouterManager.ShareActivity)
public class ShareActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_login_activity_share);
        setActivityTitle("分享");

        Intent intent = getIntent();
        String title = intent.getStringExtra("shareTitle");
        String content = intent.getStringExtra("shareContent");

        TextView tvTitle = findViewById(R.id.tv_share_title);
        TextView tvContent = findViewById(R.id.tv_share_content);
        tvTitle.setText("分享标题： " + title);
        tvContent.setText("分享内容： " + content);
    }
}
