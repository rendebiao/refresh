package com.rdb.refresh.paging;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.rdb.refresh.view.RecyclerContainer;

public class PagingRecycler<D> extends Paging<D, RecyclerContainer> {

    protected RecyclerView recyclerView;
    protected RecyclerAdapter<D> adapter;

    public PagingRecycler(Context context, Config config, Request request, RecyclerAdapter<D> adapter) {
        super(context, config, request);
        recyclerView = refreshContainer.getRefreshableView();
        adapter.setItems(getDataList());
        this.adapter = adapter;
        refreshContainer.setAdapter(adapter);
    }

    @Override
    protected RecyclerContainer createRefreshContainer(Context context) {
        return new RecyclerContainer(context);
    }

    @Override
    public void notifyDataSetChanged() {
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    protected final boolean isEmpty() {
        return adapter != null && adapter.getItemCount() == 0;
    }

    public final RecyclerView getRefreshableView() {
        return recyclerView;
    }

    public final RecyclerAdapter<D> getAdapter() {
        return adapter;
    }

    @Override
    public void scrollToTop() {
        if (recyclerView != null) {
            recyclerView.smoothScrollToPosition(0);
        }
    }

    @Override
    public final void scrollToBottom() {
        if (recyclerView != null) {
            recyclerView.smoothScrollToPosition(adapter.getItemCount());
        }
    }
}
