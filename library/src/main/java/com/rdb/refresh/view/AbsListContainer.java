package com.rdb.refresh.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.BaseAdapter;

public abstract class AbsListContainer<T extends AbsListView> extends RefreshContainer<T> {

    private BaseAdapter adapter;

    public AbsListContainer(Context context) {
        super(context);
    }

    public AbsListContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AbsListContainer(Context context, T refreshableView) {
        super(context, refreshableView);
    }

    @Override
    protected RefreshController initRefreshController() {
        return new AbsListController();
    }

    @Override
    protected void onLoadControllerChanged() {
        updateAdapterInner();
    }

    public void setAdapter(BaseAdapter adapter) {
        this.adapter = adapter;
        updateAdapterInner();
    }

    private void updateAdapterInner() {
        if (adapter == null) {
            refreshableView.setAdapter(null);
        } else {
            if (loadController == null) {
                throw new RuntimeException("unset LoadController");
            } else {
                AbsListAdapter absListAdapter = new AbsListAdapter(loadController, this, adapter);
                refreshableView.setAdapter(absListAdapter);
            }
        }
    }
}