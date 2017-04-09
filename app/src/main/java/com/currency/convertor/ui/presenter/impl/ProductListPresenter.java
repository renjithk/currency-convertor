package com.currency.convertor.ui.presenter.impl;

import android.content.Context;
import android.os.Parcelable;

import com.currency.convertor.di.ApplicationContext;
import com.currency.convertor.entity.ProductEntity;
import com.currency.convertor.entity.TransactionEntity;
import com.currency.convertor.event.request.ProductRequestEvent;
import com.currency.convertor.event.response.ProductResponseEvent;
import com.currency.convertor.ui.helper.ViewUtils;
import com.currency.convertor.ui.presenter.IProductView;
import com.currency.convertor.utils.AppConstants;
import com.currency.convertor.utils.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

/**
 * Presenter class feeding data on to transactions list
 *
 * Created by Renjith Kandanatt on 08/04/2017.
 */
public class ProductListPresenter extends BasePresenter<IProductView> {

    private final Context mContext;
    private List<ProductEntity> mProducts;
    private HashMap<String, List<TransactionEntity>> mProductTransactions;

    @Inject
    public ProductListPresenter(@ApplicationContext Context context) {
        mContext = context;
    }

    public void init() {
        setViewUuid(Utils.generateRandomUUID());
        mView.initList();
        mView.addPresenterToAdapter();

        //fire a request event
        String transactionJson = ViewUtils.getJsonFor(AppConstants.TRANSACTION_1_JSON, mContext);
        String ratesJson = ViewUtils.getJsonFor(AppConstants.RATES_1_JSON, mContext);
        ProductRequestEvent event = new ProductRequestEvent(whoAmI(), transactionJson, ratesJson);
        EventBus.getDefault().post(event);
    }

    public void restore() {
        mView.initList();
        mView.setAdapter(mProducts);
        mView.addPresenterToAdapter();
    }

    /**
     * Occurs when item is clicked
     * @param product name of the product being clicked
     */
    public void onItemClick(String product) {
        if(mProductTransactions.containsKey(product)) {
            mView.openTransactionDetails(product, (ArrayList<TransactionEntity>) mProductTransactions.get(product));
        } else {
            mView.showNoTransactionsAvailable();
        }
    }

    /** EventBus subscribers */

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onTransactionResponseEvent(ProductResponseEvent event) {
        if (whoAmI() == event.getResponseId()) {
            mProducts = event.getProducts();
            mProductTransactions = event.getProductTransactions();

            mView.setAdapter(event.getProducts());
            EventBus.getDefault().removeStickyEvent(event);
        }
    }


}
