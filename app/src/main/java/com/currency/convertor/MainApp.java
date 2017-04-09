package com.currency.convertor;

import android.app.Application;

import com.currency.convertor.di.component.ApplicationComponent;
import com.currency.convertor.di.component.DaggerApplicationComponent;
import com.currency.convertor.di.module.ApplicationModule;
import com.currency.convertor.worker.Worker;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import dagger.Lazy;

/**
 * Application class
 * Created by Renjith Kandanatt on 08/04/2017.
 */
public class MainApp extends Application {
    @Inject protected Lazy<Worker> mWorker;

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if(null == mApplicationComponent) mApplicationComponent = getComponent();
        mApplicationComponent.inject(this);

        //initialise worker
        if(!EventBus.getDefault().isRegistered(mWorker)) {
            EventBus.getDefault().register(mWorker.get());
        }
    }

    public ApplicationComponent getComponent() {
        if(null == mApplicationComponent) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }
}
