package com.currency.convertor.ui.viewbinder;

import com.currency.convertor.entity.TransactionEntity;

import java.util.List;

/**
 * Created by Renjith Kandanatt on 09/04/2017.
 */
public interface ITransactionView extends IBaseView {
    /**
     * Sets toolbar title
     * @param title a valid title for toolbar
     */
    void setToolbarTitle(String title);

    /**
     * Adds list of transactions to adapter for rendering
     * @param products list of {@link TransactionEntity} type
     */
    void setAdapter(List<TransactionEntity> transactions);

    /**
     * Sets transaction total amount
     * @param totals formatted transaction total amount
     */
    void setTransactionTotal(String totals);
}
