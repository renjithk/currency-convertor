package com.currency.convertor.convertor;

import com.currency.convertor.utils.Currency;
import com.currency.convertor.utils.RatesDataHolder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Renjith Kandanatt on 09/04/2017.
 */

public class Plot {
    private static Plot mObject;
    private List<Point> mPoints;
    private List<Side> mSides;

    public static Plot instance() {
        if(null == mObject) {
            mObject = new Plot();
        }
        return mObject;
    }

    private Plot() {
        mPoints = new ArrayList<>();
        mSides = new ArrayList<>();

        //build points using available currencies
        buildPoints();

        //now build sides using those points
        buildSides();
    }

    private void buildPoints() {
        int i = 0;
        for(Currency currency : Currency.values()) {
            mPoints.add(new Point(String.valueOf(i++), currency.toString()));
        }
    }

    private void buildSides() {
        for(int i = 0; i < RatesDataHolder.getRates().size(); i++) {
            addSide(i, findPointIndex(RatesDataHolder.getRates().get(i).getFrom()),
                    findPointIndex(RatesDataHolder.getRates().get(i).getTo()),
                    RatesDataHolder.getRates().get(i).getRate());
        }
    }

    private void addSide(int id, int startIndex, int endIndex, BigDecimal rate) {
        mSides.add(new Side(id, mPoints.get(startIndex), mPoints.get(endIndex), rate));
    }

    public int findPointIndex(String currency) {
        Currency c = Currency.valueOf(currency);
        if(Currency.GBP == c) return Currency.GBP.ordinal();
        if(Currency.USD == c) return Currency.USD.ordinal();
        if(Currency.AUD == c) return Currency.AUD.ordinal();
        if(Currency.CAD == c) return Currency.CAD.ordinal();
        if(Currency.EUR == c) return Currency.EUR.ordinal();
        else return -1;
    }

    public List<Point> getPoints() {
        return mPoints;
    }

    public List<Side> getSides() {
        return mSides;
    }
}
