package com.currency.convertor.event.request;

import com.currency.convertor.entity.TransactionEntity;

import java.util.List;
import java.util.UUID;

/**
 * Created by Renjith Kandanatt on 09/04/2017.
 */
public class TransactionRequestEvent extends BaseRequestEvent {
    private final List<TransactionEntity> mTransactions;

    public TransactionRequestEvent(UUID uuid, List<TransactionEntity> transactions) {
        setRequestId(uuid);
        mTransactions = transactions;
    }

    public List<TransactionEntity> getTransactions() {
        return mTransactions;
    }
}
