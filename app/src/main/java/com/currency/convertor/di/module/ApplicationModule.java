package com.currency.convertor.di.module;

import android.content.Context;

import com.currency.convertor.MainApp;
import com.currency.convertor.convertor.Plot;
import com.currency.convertor.di.ApplicationContext;
import com.currency.convertor.utils.PresenterCache;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * This class is responsible for providing application level dependencies.
 *
 * Created by Renjith Kandanatt on 08/04/2017.
 */
@Module
public class ApplicationModule {
    private final MainApp mApplication;

    public ApplicationModule(MainApp application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    PresenterCache providePresenterCache() {
        return new PresenterCache();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        builder.excludeFieldsWithoutExposeAnnotation();

        //create gson object from the builder
        return builder.create();
    }
}
