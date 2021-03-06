package com.rdb.refresh.demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.rdb.refresh.Refresh;
import com.rdb.refresh.demo.R;
import com.rdb.refresh.view.LoadController;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setElevation(0);
        findViewById(R.id.viewButton).setOnClickListener(this);
        findViewById(R.id.scrollButton).setOnClickListener(this);
        findViewById(R.id.listButton1).setOnClickListener(this);
        findViewById(R.id.listButton2).setOnClickListener(this);
        findViewById(R.id.gridButton1).setOnClickListener(this);
        findViewById(R.id.gridButton2).setOnClickListener(this);
        findViewById(R.id.recyclerButton1).setOnClickListener(this);
        findViewById(R.id.recyclerButton2).setOnClickListener(this);
        findViewById(R.id.expandableButton1).setOnClickListener(this);
        findViewById(R.id.expandableButton2).setOnClickListener(this);
        LoadController defaultControoler = new LoadController(R.layout.item_load_layout) {

            @Override
            public void updateLoadView(View view, boolean loading, boolean hasMore) {
                TextView loadView = view.findViewById(R.id.loadView);
                ProgressBar progressBar = view.findViewById(R.id.progressBar);
                loadView.setText(loading ? "正在加载" : "上拉加载更多");
                progressBar.setVisibility(loading ? View.VISIBLE : View.GONE);
            }
        };
        //设置默认控制器 上拉加载
        Refresh.setDefaultLoadController(defaultControoler);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.viewButton) {
            Intent intent = new Intent(this, ViewActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.scrollButton) {
            Intent intent = new Intent(this, ScrollActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.listButton1) {
            Intent intent = new Intent(this, ListActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.listButton2) {
            Intent intent = new Intent(this, ListPageActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.gridButton1) {
            Intent intent = new Intent(this, GridActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.gridButton2) {
            Intent intent = new Intent(this, GridPageActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.recyclerButton1) {
            Intent intent = new Intent(this, RecyclerActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.recyclerButton2) {
            Intent intent = new Intent(this, PageActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.expandableButton1) {
            Intent intent = new Intent(this, ExpListActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.expandableButton2) {
            Intent intent = new Intent(this, ExpListPageActivity.class);
            startActivity(intent);
        }
    }
}
