package com.currency.convertor.ui.presenter.impl;

import android.content.Context;
import android.os.Parcelable;

import com.currency.convertor.R;
import com.currency.convertor.di.ApplicationContext;
import com.currency.convertor.entity.TransactionEntity;
import com.currency.convertor.event.request.TransactionRequestEvent;
import com.currency.convertor.event.response.TransactionResponseEvent;
import com.currency.convertor.ui.presenter.impl.BasePresenter;
import com.currency.convertor.ui.viewbinder.ITransactionView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Renjith Kandanatt on 09/04/2017.
 */
public class TransactionListPresenter extends BasePresenter<ITransactionView>{
    private final Context mContext;
    private String mScreenTitle;
    private List<TransactionEntity> mTransactions;
    private String mTransactionTotals;

    @Inject
    public TransactionListPresenter(@ApplicationContext Context context) {
        mContext = context;
    }

    public void init(String screenTitle, List<TransactionEntity> transactions) {
        mScreenTitle = screenTitle;
        mTransactions = transactions;

        //fire a request event to calculate transaction total
        TransactionRequestEvent event = new TransactionRequestEvent(whoAmI(), transactions);
        EventBus.getDefault().post(event);
    }

    public void restore() {
        initUI();
        setData();
    }

    /** EventBus subscribers */

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onTransactionResponseEvent(TransactionResponseEvent event) {
        if(whoAmI() == event.getResponseId()) {
            mTransactionTotals = event.getTotalTransactionAmount();
            initUI();
            setData();
            EventBus.getDefault().removeStickyEvent(event);
        }
    }

    /** Private methods */

    private void initUI() {
        mView.setToolbarTitle(mContext.getString(R.string.total_transaction_title, mScreenTitle));
        mView.initList();
    }

    private void setData() {
        mView.setAdapter(mTransactions);
        mView.setTransactionTotal(mTransactionTotals);
    }
}
