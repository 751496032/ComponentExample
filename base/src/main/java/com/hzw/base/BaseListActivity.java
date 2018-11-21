package com.hzw.base;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public  class BaseListActivity<T> extends BaseActivity implements AdapterView.OnItemClickListener {

    private List<T> mTList=new ArrayList<>();
    private ArrayAdapter<T> mAdapter;
    private Object mData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ListView listView = findViewById(R.id.list_view);
        mAdapter = new ArrayAdapter<T>(this, android.R.layout.simple_list_item_1, mTList);
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(this);

    }

    public void updateData(T[] ts){
        List<T> tList = Arrays.asList(ts);
        mAdapter.addAll(tList);
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}