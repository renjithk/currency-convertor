package com.currency.convertor.ui.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.MenuItem;

import com.currency.convertor.MainApp;
import com.currency.convertor.R;
import com.currency.convertor.entity.ProductEntity;
import com.currency.convertor.entity.TransactionEntity;
import com.currency.convertor.ui.adapter.ProductAdapter;
import com.currency.convertor.ui.presenter.IProductView;
import com.currency.convertor.ui.presenter.impl.ProductListPresenter;
import com.currency.convertor.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProductListActivity extends AbstractActivity implements IProductView {
    @Inject protected ProductAdapter mAdapter;
    @Inject protected ProductListPresenter mPresenter;

    @Bind(R.id.layout_coordinator) protected CoordinatorLayout mAnchor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((MainApp) getApplication()).getComponent().inject(this);

        setContentView(R.layout.layout_product_list);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        super.onCreate(savedInstanceState);
        mPresenter.attachView(this);
        if(null == savedInstanceState) {
            mPresenter.init();
        } else {
            mPresenter = mPresenterCache.restorePresenter(savedInstanceState);
            mPresenter.attachView(this);
            mPresenter.restore();
            //TODO restore methods layout manager
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        mPresenterCache.savePresenter(mPresenter, outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        mPresenter.detachView();
    }

    /** View implementations */

    public void addPresenterToAdapter() {
        mAdapter.addPresenter(mPresenter);
    }

    @Override
    public void setAdapter(List<ProductEntity> products) {
        mAdapter.setData(products);
        if(null == mRecycler.getAdapter()) mRecycler.setAdapter(mAdapter);
        else mAdapter.notifyDataSetChanged();
    }

    @Override
    public void openTransactionDetails(String productName, ArrayList<TransactionEntity> transactions) {
        Intent intent = new Intent(this, TransactionListActivity.class);
        intent.putExtra(AppConstants.KEY_PRODUCT_NAME, productName);
        intent.putExtra(AppConstants.KET_PRODUCT_TRANSACTIONS, transactions);
        startActivity(intent);
    }

    @Override
    public void showNoTransactionsAvailable() {
        Snackbar.make(mAnchor, R.string.no_transactions_available,
                      Snackbar.LENGTH_INDEFINITE)
                .setAction(android.R.string.ok, null).show();
    }
}
