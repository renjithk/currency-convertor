package com.currency.convertor.convertor;

/**
 * Created by Renjith Kandanatt on 09/04/2017.
 */

public class Point {
    private String mId;
    private final String mName;

    public Point(String id, String name) {
        mId = id;
        mName = name;
    }

    @Override
    public String toString() {
        return mName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((mId == null) ? 0 : mId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Point other = (Point) obj;
        if (mId == null) {
            if (other.mId != null)
                return false;
        } else if (!mId.equals(other.mId))
            return false;
        return true;
    }
}
