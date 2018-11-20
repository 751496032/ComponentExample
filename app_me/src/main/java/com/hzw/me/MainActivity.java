package com.hzw.me;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.hzw.base.BaseActivity;
import com.hzw.me.fragment.MeFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity_main);
        loadSingleFragment(MeFragment.newInstance());
    }
}
