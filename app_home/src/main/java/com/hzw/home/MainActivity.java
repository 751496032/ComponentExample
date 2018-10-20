package com.hzw.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hzw.base.BaseActivity;
import com.hzw.home.fragment.HomeFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity_main);

        loadSingleFragment(R.id.fl_container, HomeFragment.newInstance());

    }
}
