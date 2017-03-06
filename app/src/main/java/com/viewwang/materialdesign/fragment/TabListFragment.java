package com.viewwang.materialdesign.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.viewwang.materialdesign.DemoOneActivity;
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
    private ImageView imageView;
    private Button btn;
    private Button btnDemo1;

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
        imageView = find(R.id.action_image);
        btn = find(R.id.btn);
        btnDemo1 = find(R.id.btn_demo1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((AnimatedVectorDrawableCompat) imageView.getDrawable()).start();
                imageView.setVisibility(View.VISIBLE);
            }
        });
        btnDemo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent = new Intent(getActivity(),DemoOneActivity.class);
                startActivity(intent);
            }
        });

    }

}
