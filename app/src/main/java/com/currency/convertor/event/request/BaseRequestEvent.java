package com.currency.convertor.event.request;

import java.util.UUID;

/**
 * Abstract parent class for all request events
 *
 * Created by Renjith Kandanatt on 09/04/2017.
 */
public class BaseRequestEvent {
    protected UUID mRequestId;

    public UUID getRequestId() {
        return mRequestId;
    }

    public void setRequestId(UUID requestId) {
        this.mRequestId = requestId;
    }
}
