package com.currency.convertor.event.response;

import com.currency.convertor.entity.ProductEntity;
import com.currency.convertor.entity.TransactionEntity;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by Renjith Kandanatt on 09/04/2017.
 */
public class ProductResponseEvent extends BaseResponseEvent {
    private final List<ProductEntity> mProducts;
    private final HashMap<String, List<TransactionEntity>> mProductTransactions;

    public ProductResponseEvent(UUID id, List<ProductEntity> products,
                                HashMap<String, List<TransactionEntity>> productTransactions) {
        setResponseId(id);
        mProducts = products;
        mProductTransactions = productTransactions;
    }

    public List<ProductEntity> getProducts() {
        return mProducts;
    }

    public HashMap<String, List<TransactionEntity>> getProductTransactions() {
        return mProductTransactions;
    }
}
