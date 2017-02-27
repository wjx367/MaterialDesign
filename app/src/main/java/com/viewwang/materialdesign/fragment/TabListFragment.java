package com.viewwang.materialdesign.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.viewwang.materialdesign.R;
import com.viewwang.materialdesign.base.BaseFragment;

/**
 * author�� on 2016/9/8 09:43
 * email��
 * desc��slidingtab
 */

public class TabListFragment extends BaseFragment {

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.tablist_fragment_layout;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bindViews();

    }

    private void bindViews() {
        mRefreshLayout = find(R.id.mRefreshLayout);
        mRecyclerView = find(R.id.mRecyclerView);
    }

}
