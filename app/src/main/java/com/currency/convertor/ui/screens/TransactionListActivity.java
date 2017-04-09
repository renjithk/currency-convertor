package com.currency.convertor.ui.screens;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.currency.convertor.MainApp;
import com.currency.convertor.R;
import com.currency.convertor.entity.TransactionEntity;
import com.currency.convertor.ui.adapter.TransactionAdapter;
import com.currency.convertor.ui.presenter.impl.TransactionListPresenter;
import com.currency.convertor.ui.viewbinder.ITransactionView;
import com.currency.convertor.utils.AppConstants;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Renjith Kandanatt on 08/04/2017.
 */
public class TransactionListActivity extends AbstractActivity implements ITransactionView {
    @Inject protected TransactionAdapter mAdapter;
    @Inject protected TransactionListPresenter mPresenter;

    @Bind(R.id.total_amount) protected TextView mTotalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((MainApp) getApplication()).getComponent().inject(this);

        setContentView(R.layout.layout_transaction_list);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);
        mPresenter.attachView(this);
        if(null == savedInstanceState) {
            List<TransactionEntity> transactions = getIntent().getParcelableArrayListExtra(AppConstants.KET_PRODUCT_TRANSACTIONS);
            mPresenter.init(getIntent().getStringExtra(AppConstants.KEY_PRODUCT_NAME), transactions);
        } else {
            mPresenter = mPresenterCache.restorePresenter(savedInstanceState);
            mPresenter.attachView(this);
            mPresenter.restore();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        mPresenterCache.savePresenter(mPresenter, outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home: {
                //NavUtils.navigateUpFromSameTask(this);
                this.finish();
                break;
            }
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        mPresenter.detachView();
    }

    /** View implementations */

    @Override
    public void setToolbarTitle(String title) {
        mToolbar.setTitle(title);
    }

    @Override
    public void setAdapter(List<TransactionEntity> transactions) {
        mAdapter.setData(transactions);
        if(null == mRecycler.getAdapter()) mRecycler.setAdapter(mAdapter);
        else mAdapter.notifyDataSetChanged();
    }

    @Override
    public void addPresenterToAdapter() {
        mAdapter.addPresenter(mPresenter);
    }

    @Override
    public void setTransactionTotal(String totals) {
        mTotalAmount.setText(getString(R.string.transaction_sum, totals));
    }
}
