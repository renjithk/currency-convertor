package com.currency.convertor.convertor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Class responsible for finding best rates
 *
 * Created by Renjith Kandanatt on 09/04/2017.
 */
public class RatesConvertor {
    private final List<Point> mPoints;
    private final List<Side> mSides;
    private Set<Point> mSetPoints;
    private Set<Point> mUnSetPoints;
    private Map<Point, Point> mPredecessors;
    private Map<Point, BigDecimal> mRates;

    public RatesConvertor() {
        // create a copy of the array so that we can operate on this array not on original
        this.mPoints = new ArrayList(Plot.instance().getPoints());
        this.mSides = new ArrayList(Plot.instance().getSides());
    }

    public void execute(Point source) {
        mSetPoints = new HashSet<>();
        mUnSetPoints = new HashSet<>();
        mRates = new HashMap<>();
        mPredecessors = new HashMap<>();
        mRates.put(source, new BigDecimal(0.0));
        mUnSetPoints.add(source);
        while (mUnSetPoints.size() > 0) {
            Point node = getMinimum(mUnSetPoints);
            mSetPoints.add(node);
            mUnSetPoints.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(Point node) {
        List<Point> adjacentNodes = getNeighbors(node);
        for (Point target : adjacentNodes) {
            if (1 == getShortestDistance(target)
                    .compareTo(getShortestDistance(node).add(getDistance(node, target)))) {
                mRates.put(target, getDistance(node, target));
                mPredecessors.put(target, node);
                mUnSetPoints.add(target);
            }
        }
    }

    private BigDecimal getDistance(Point node, Point target) {
        for (Side edge : mSides) {
            if (edge.getStart().equals(node) && edge.getEnd().equals(target)) {
                return edge.getRate();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private List<Point> getNeighbors(Point node) {
        List<Point> neighbors = new ArrayList<>();
        for (Side edge : mSides) {
            if (edge.getStart().equals(node)
                    && !isSettled(edge.getEnd())) {
                neighbors.add(edge.getEnd());
            }
        }
        return neighbors;
    }

    private Point getMinimum(Set<Point> vertexes) {
        Point minimum = null;
        for (Point vertex : vertexes) {
            if (minimum == null) {
                minimum = vertex;
            } else {
                if (-1 == getShortestDistance(vertex).compareTo(getShortestDistance(minimum))) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(Point vertex) {
        return mSetPoints.contains(vertex);
    }

    private BigDecimal getShortestDistance(Point destination) {
        BigDecimal r = mRates.get(destination);
        if (r == null) {
            return new BigDecimal(Double.MAX_VALUE);
        } else {
            return r;
        }
    }

    public LinkedList<Point> getPath(Point target) {
        LinkedList<Point> path = new LinkedList<>();
        Point step = target;
        // check if a path exists
        if (mPredecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (mPredecessors.get(step) != null) {
            step = mPredecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }

    public Map<Point, BigDecimal> getRates() {
        return mRates;
    }
}
