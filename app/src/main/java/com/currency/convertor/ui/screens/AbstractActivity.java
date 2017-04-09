package com.currency.convertor.ui.screens;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.currency.convertor.R;
import com.currency.convertor.ui.custom.BaseRecyclerWithEmptyView;
import com.currency.convertor.utils.PresenterCache;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Base level implementations
 * Created by Renjith Kandanatt on 08/04/2017.
 */

public class AbstractActivity extends AppCompatActivity {
    @Inject protected PresenterCache mPresenterCache;

    @Bind(R.id.toolbar) protected Toolbar mToolbar;
    @Bind(R.id.list_recycler) protected BaseRecyclerWithEmptyView mRecycler;
    @Bind(R.id.empty_view) protected TextView mEmptyView;

    protected static final String KEY_LAYOUT_MANAGER_STATE = "recycler.layout.manager.state";

    public void initList() {
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setItemAnimator(new DefaultItemAnimator());
        mRecycler.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecycler.setEmptyView(mEmptyView);
    }
}
