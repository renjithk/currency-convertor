package com.currency.convertor.event.response;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created by Renjith Kandanatt on 09/04/2017.
 */
public class TransactionResponseEvent extends BaseResponseEvent {
    private final String mTotalTransactionAmount;

    public TransactionResponseEvent(UUID uuid, String totalTransactionAmount) {
        setResponseId(uuid);
        mTotalTransactionAmount = totalTransactionAmount;
    }

    public String getTotalTransactionAmount() {
        return mTotalTransactionAmount;
    }
}
