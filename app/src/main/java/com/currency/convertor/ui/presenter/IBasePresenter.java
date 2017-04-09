package com.currency.convertor.ui.presenter;

import com.currency.convertor.ui.viewbinder.IBaseView;

/**
 * Each presenter in the app must implement this interface.
 * Otherwise extends BasePresenter class with corresponding view
 *
 * Created by Renjith Kandanatt on 17/03/2016.
 */
public interface IBasePresenter<V extends IBaseView> {
    /**
     * Calls to attach a view
     * Every Presenter must have a reference to the view exposed by corresponding view interface
     * Call this method to pragmatically attach related view during its lifecycle
     * @param view view to be attached to the Presenter
     */
    void attachView(V view);

    /**
     * Calls to detach a view
     * Every Presenter must detach its view when the view is destroyed or paused
     * Call this method to pragmatically detach related view during its lifecycle
     */
    void detachView();
}