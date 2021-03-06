package com.rdb.refresh.demo.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.rdb.refresh.demo.R;
import com.rdb.refresh.view.GridContainer;
import com.rdb.refresh.view.LoadController;
import com.rdb.refresh.view.RefreshContainer;
import com.rdb.refresh.view.RefreshLayout;

public class GridActivity extends AppCompatActivity {

    private int count;
    private BaseAdapter adapter;
    private GridContainer refreshContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_layout);
        getSupportActionBar().setTitle("GridView");
        getSupportActionBar().setElevation(0);
        refreshContainer = findViewById(R.id.refreshContainer);
        adapter = new Adapter(getLayoutInflater());
        refreshContainer.setMode(RefreshLayout.BOTH);
        //设置单独控制器 点击加载
        refreshContainer.setRefreshLoadController(new LoadController(R.layout.item_load_layout) {

            @Override
            public boolean autoLoad() {
                return false;
            }

            @Override
            public void updateLoadView(View view, boolean loading, boolean hasMore) {
                TextView loadView = view.findViewById(R.id.loadView);
                ProgressBar progressBar = view.findViewById(R.id.progressBar);
                loadView.setText(loading ? "正在加载" : "点击加载更多");
                progressBar.setVisibility(loading ? View.VISIBLE : View.GONE);
                if (!loading) {
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            refreshContainer.startLoading();
                        }
                    });
                } else {
                    view.setOnClickListener(null);
                }
            }
        });
        refreshContainer.setAdapter(adapter);
        refreshContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                count = 0;
                load();
            }
        });
        refreshContainer.setOnLoadListener(new RefreshContainer.OnLoadListener() {
            @Override
            public void onLoad() {
                load();
            }
        });
        refreshContainer.startRefreshingDelay(500, true);
    }

    private void load() {
        refreshContainer.postDelayed(new Runnable() {
            @Override
            public void run() {
                count += 10;
                adapter.notifyDataSetChanged();
                refreshContainer.notifyComplete(count <= 100);
            }
        }, 1000);
    }

    class Adapter extends BaseAdapter {

        private final LayoutInflater inflater;

        public Adapter(LayoutInflater inflater) {
            this.inflater = inflater;
        }


        @Override
        public int getCount() {
            return count;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_text_layout, parent, false);
            }
            TextView textView = convertView.findViewById(R.id.textView);
            textView.setText("---" + position + "---");
            return convertView;
        }
    }
}
