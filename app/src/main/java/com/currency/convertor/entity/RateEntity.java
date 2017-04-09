package com.currency.convertor.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

/**
 * Entity class used for mapping exchange rates entry
 *
 * Created by Renjith Kandanatt on 09/04/2017.
 */
public class RateEntity {
    @Expose @SerializedName("from")
    private String mFrom;
    @Expose @SerializedName("to")
    private String mTo;
    @Expose @SerializedName("rate")
    private BigDecimal mRate;

    public String getFrom() {
        return mFrom;
    }

    public String getTo() {
        return mTo;
    }

    public BigDecimal getRate() {
        return mRate;
    }
}
