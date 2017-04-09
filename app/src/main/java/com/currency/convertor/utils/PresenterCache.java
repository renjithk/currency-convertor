package com.currency.convertor.utils;

import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;

import com.currency.convertor.ui.presenter.impl.BasePresenter;

import java.util.concurrent.atomic.AtomicLong;

/**
 * This class is responsible for holding short-lived Presenter references
 * It implements a Least Recently Used (LRU) algorithm to sweep off existing cache
 *
 * Created by Renjith Kandanatt on 08/04/2017.
 */

public class PresenterCache {
    private static final String KEY_PRESENTER_ID = "key_presenter_id";
    private final AtomicLong mCurrentPresenterId;
    private final LruCache<Long, BasePresenter<?>> mPresenterCache;

    public PresenterCache() {
        mCurrentPresenterId = new AtomicLong();
        mPresenterCache = new LruCache<>(2 * 1024 * 1024); //setting default cache size of 20kb
    }

    public <P extends BasePresenter<?>> P restorePresenter(Bundle savedInstanceState) {
        long presenterId = savedInstanceState.getLong(KEY_PRESENTER_ID);
        P presenter = (P) mPresenterCache.get(presenterId);
        mPresenterCache.remove(presenterId);
        Log.i(getClass().toString(), "restorePresenter ? " + presenter);
        return presenter;
    }

    public <P extends BasePresenter<?>> P restorePresenter(long presenterId) {
        P presenter = (P) mPresenterCache.get(presenterId);
        mPresenterCache.remove(presenterId);
        Log.i(getClass().toString(), "restorePresenter ? " + presenter);
        return presenter;
    }

    public long savePresenter(BasePresenter<?> presenter, Bundle outState) {
        long presenterId = mCurrentPresenterId.incrementAndGet();
        mPresenterCache.put(presenterId, presenter);
        outState.putLong(KEY_PRESENTER_ID, presenterId);
        return presenterId;
    }
}
