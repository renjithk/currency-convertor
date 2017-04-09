package com.currency.convertor.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.currency.convertor.convertor.Plot;
import com.currency.convertor.convertor.Point;
import com.currency.convertor.convertor.RatesConvertor;
import com.currency.convertor.utils.Currency;
import com.currency.convertor.utils.Utils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.LinkedList;

/**
 * Entity class used to map each transaction entry
 *
 * Created by Renjith Kandanatt on 08/04/2017.
 */
public class TransactionEntity implements Parcelable {
    @Expose @SerializedName("amount")
    private BigDecimal mAmount;

    @Expose @SerializedName("sku")
    private String mSku;

    @Expose @SerializedName("currency")
    private String mCurrency;

    @Override
    public String toString() {
        return mSku;
    }

    private BigDecimal convertedGBP = BigDecimal.ZERO;

    public String inGBP() {
        return Utils.formatNumber(convertedGBP);
    }

    public BigDecimal inGBPAsBigDecimal() {
        return convertedGBP;
    }

    public String amountAsString() {
        return Currency.valueOf(mCurrency).getSymbol() + Utils.formatNumber(mAmount);
    }

    /**
     * Uses rate convertor to convert transaction amount to GBP
     */
    public void toGBP() {
        if(Currency.valueOf(mCurrency) == Currency.GBP) {
            convertedGBP = mAmount;
        } else {
            RatesConvertor ratesConvertor = new RatesConvertor();
            try {
                ratesConvertor.execute(Plot.instance().getPoints().get(Plot.instance().findPointIndex(mCurrency)));
            }catch (ArrayIndexOutOfBoundsException ex){
                convertedGBP = BigDecimal.ZERO;
                return;
            }
            LinkedList<Point> points = ratesConvertor.getPath(Plot.instance().getPoints().get(Currency.GBP.ordinal()));

            //convert only if we have rates
            if(points != null && points.size() > 0) {
                for (Point point : points) {
                    BigDecimal sideRate = ratesConvertor.getRates().get(point);

                    if (0 == sideRate.compareTo(BigDecimal.ZERO)) continue;
                    if (0 == convertedGBP.compareTo(BigDecimal.ZERO)) {
                        convertedGBP = convertedGBP.add(mAmount.multiply(sideRate));
                    } else {
                        convertedGBP = convertedGBP.multiply(sideRate);
                    }
                }
            }
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(this.mAmount);
        dest.writeString(this.mSku);
        dest.writeString(this.mCurrency);
        dest.writeSerializable(this.convertedGBP);
    }

    protected TransactionEntity(Parcel in) {
        this.mAmount = (BigDecimal) in.readSerializable();
        this.mSku = in.readString();
        this.mCurrency = in.readString();
        this.convertedGBP = (BigDecimal) in.readSerializable();
    }

    public static final Creator<TransactionEntity> CREATOR = new Creator<TransactionEntity>() {
        @Override
        public TransactionEntity createFromParcel(Parcel source) {
            return new TransactionEntity(source);
        }

        @Override
        public TransactionEntity[] newArray(int size) {
            return new TransactionEntity[size];
        }
    };
}
