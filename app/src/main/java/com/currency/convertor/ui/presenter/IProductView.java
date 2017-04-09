package com.currency.convertor.ui.presenter;

import com.currency.convertor.entity.ProductEntity;
import com.currency.convertor.entity.TransactionEntity;
import com.currency.convertor.ui.viewbinder.IBaseView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Renjith Kandanatt on 08/04/2017.
 */
public interface IProductView extends IBaseView {
    /**
     * Adds list of product to adapter for rendering
     * @param products list of {@link ProductEntity} type
     */
    void setAdapter(List<ProductEntity> products);

    /**
     * Open details transactions page
     * @param productName
     * @param transactions list of {@link TransactionEntity} type
     */
    void openTransactionDetails(String productName, ArrayList<TransactionEntity> transactions);

    /**
     * Alerts user when there are no transaction details against a product
     */
    void showNoTransactionsAvailable();
}
