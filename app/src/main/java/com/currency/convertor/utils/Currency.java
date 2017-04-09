package com.currency.convertor.utils;

/**
 * Created by Renjith Kandanatt on 09/04/2017.
 */

public enum Currency {
    GBP("GBP", "£"),
    USD("USD", "$"),
    AUD("AUD", "A$"),
    CAD("CAD", "CA$"),
    EUR("EUR", "€");

    private final String mSymbol;
    private final String mCurrency;

    Currency(String value, String symbol) {
        this.mCurrency = value;
        this.mSymbol = symbol;
    }

    public String getSymbol() {
        return mSymbol;
    }

    @Override
    public String toString(){
        return mCurrency;
    }
}
