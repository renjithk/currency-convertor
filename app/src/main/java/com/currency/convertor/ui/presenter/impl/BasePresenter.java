package com.currency.convertor.ui.presenter.impl;

import com.currency.convertor.ui.presenter.IBasePresenter;
import com.currency.convertor.ui.viewbinder.IBaseView;

import org.greenrobot.eventbus.EventBus;

import java.util.UUID;

/**
 * This acts as the base class for all presenters in the app.
 * It provides a base implementation and handles a reference to the View
 *
 * Created by Renjith Kandanatt on 17/03/2016.
 */
public abstract class BasePresenter<T extends IBaseView> implements IBasePresenter<T> {
    protected T mView;
    private UUID mViewUUid;

    @Override
    public void attachView(T view) {
        if(!isViewAttached()) {
            mView = view;
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void detachView() {
        EventBus.getDefault().unregister(this);
        mView = null;
    }

    /**
     * Checks if view is attached to the active fragment
     * @return true if the view is attached and false otherwise
     */
    public boolean isViewAttached() {
        return null != mView;
    }

    public void setViewUuid(UUID uuid) {
        this.mViewUUid = uuid;
    }

    protected UUID whoAmI() {
        return mViewUUid;
    }
}
