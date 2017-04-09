package com.currency.convertor.entity;

/**
 * Entity used to track a product and its transactions
 *
 * Created by Renjith Kandanatt on 09/04/2017.
 */
public class ProductEntity {
    private final String mName;
    private final int mTransactionCount;

    public ProductEntity(String name, int transactionCount) {
        mName = name;
        mTransactionCount = transactionCount;
    }

    public int getTransactionCount() {
        return mTransactionCount;
    }

    @Override
    public String toString() {
        return mName;
    }
}
