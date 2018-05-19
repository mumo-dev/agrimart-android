package com.example.mumo.agrimart.utils;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class PagingScrollListener extends RecyclerView.OnScrollListener {
    private GridLayoutManager mLayoutManager;

    public PagingScrollListener(GridLayoutManager layoutManager) {
        mLayoutManager = layoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleCount = mLayoutManager.getChildCount();
        int totalItemCount = mLayoutManager.getItemCount();
        int firstVisibleItemCount = mLayoutManager.findFirstVisibleItemPosition();

    }

    protected abstract void loadMoreItems();
    public abstract int getTotalPageCount();
    public abstract boolean isLoading();
    public abstract boolean isLastPage();


}
