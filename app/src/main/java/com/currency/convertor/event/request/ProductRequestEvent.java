package com.currency.convertor.event.request;

import java.util.UUID;

/**
 * Request event denoting action to fetch transactions
 *
 * Created by Renjith Kandanatt on 09/04/2017.
 */
public class ProductRequestEvent extends BaseRequestEvent{
    private final String mTransactionJson;
    private final String mRatesJson;

    public ProductRequestEvent(UUID uuid, String transactionJson, String ratesJson) {
        setRequestId(uuid);
        this.mTransactionJson = transactionJson;
        this.mRatesJson = ratesJson;
    }

    public String getTransactionJson() {
        return mTransactionJson;
    }

    public String getRatesJson() {
        return mRatesJson;
    }
}
