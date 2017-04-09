package com.currency.convertor.di.component;

import android.content.Context;

import com.currency.convertor.MainApp;
import com.currency.convertor.convertor.Plot;
import com.currency.convertor.di.ApplicationContext;
import com.currency.convertor.di.module.ApplicationModule;
import com.currency.convertor.ui.screens.ProductListActivity;
import com.currency.convertor.ui.screens.TransactionListActivity;
import com.currency.convertor.utils.PresenterCache;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Dependency Injection API component
 *
 * Created by Renjith Kandanatt on 08/04/2017.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(MainApp mainApp);
    void inject(ProductListActivity products);
    void inject(TransactionListActivity transactions);

    @ApplicationContext Context context();

    PresenterCache presenterCache();
    Gson gson();
}
