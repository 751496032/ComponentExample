package com.hzw.login;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hzw.base.BaseActivity;
import com.hzw.base.BaseEvent;
import com.hzw.common.ARouterManager;
import com.hzw.common.SpUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * 登录页面
 */
@Route(path = ARouterManager.LoginActivity)
public class LoginActivity extends BaseActivity {

    private EditText mEtUserName;
    private EditText mEtUserPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_login_activity_main);
        setActivityTitle("登录");
        mEtUserName = findViewById(R.id.et_user_name);
        mEtUserPwd = findViewById(R.id.et_user_pwd);
    }

    public  void onClick(View view){
        int i = view.getId();
        String account = mEtUserName.getText().toString();
        String pwd = mEtUserPwd.getText().toString();
        if (i == R.id.but_login) {

            if (TextUtils.isEmpty(account)){
                Toast.makeText(this,"输入账号",Toast.LENGTH_SHORT).show();
                return;
            }else if (TextUtils.isEmpty(pwd)){
                Toast.makeText(this,"输入密码",Toast.LENGTH_SHORT).show();
                return;
            }
            final ProgressDialog dialog = new ProgressDialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("登录中……");
            dialog.show();
            EventBus.getDefault().post(new BaseEvent(BaseEvent.LOGIN));
            SpUtils.putBoolean(this,SpUtils.LOGIN_KEY,true);
            mEtUserName.postDelayed(new Runnable() {
                @Override
                public void run() {
                    dialog.dismiss();
                    finish();
                }
            },2000);
        }
    }

}
