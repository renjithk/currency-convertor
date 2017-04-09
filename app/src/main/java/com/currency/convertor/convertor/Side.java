package com.currency.convertor.convertor;

import java.math.BigDecimal;

/**
 * Created by Renjith Kandanatt on 09/04/2017.
 */

public class Side {
    private final Point mEnd;
    private final Point mStart;
    private final int mId;
    private final BigDecimal mRate;

    public Side(int id, Point start, Point end, BigDecimal rate) {
        mId = id;
        mStart = start;
        mEnd = end;
        mRate = rate;
    }

    public Point getEnd() {
        return mEnd;
    }

    public Point getStart() {
        return mStart;
    }

    public BigDecimal getRate() {
        return mRate;
    }
}
