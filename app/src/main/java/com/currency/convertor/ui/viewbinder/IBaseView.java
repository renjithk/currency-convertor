package com.currency.convertor.ui.viewbinder;

import com.currency.convertor.entity.ProductEntity;

/**
 * Created by Renjith Kandanatt on 08/04/2017.
 */
public interface IBaseView {
    /**
     * Performs list initialisation
     */
    void initList();

    /**
     * Adds presenter to recycler adapter
     */
    void addPresenterToAdapter();
}
