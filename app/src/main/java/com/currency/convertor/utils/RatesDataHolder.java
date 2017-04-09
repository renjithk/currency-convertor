package com.currency.convertor.utils;

import com.currency.convertor.entity.RateEntity;

import java.util.List;

/**
 * Created by Renjith Kandanatt on 09/04/2017.
 */

public enum RatesDataHolder {
    INSTANCE;
    private List<RateEntity> mRatesList;

    public static boolean hasData() {
        return Utils.isEmptyList(INSTANCE.mRatesList);
    }

    public static void setRates(final List<RateEntity> ratesList) {
        INSTANCE.mRatesList = ratesList;
    }

    public static List<RateEntity> getRates() {
        return INSTANCE.mRatesList;
    }

    public static void reset() {
        INSTANCE.mRatesList = null;
    }
}
