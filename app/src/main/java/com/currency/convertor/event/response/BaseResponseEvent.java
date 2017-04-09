package com.currency.convertor.event.response;

import java.util.UUID;

/**
 * Created by Renjith Kandanatt on 09/04/2017.
 */
public abstract class BaseResponseEvent {
    protected UUID mResponseId;

    public UUID getResponseId() {
        return mResponseId;
    }

    public void setResponseId(UUID responseId) {
        this.mResponseId = responseId;
    }
}
