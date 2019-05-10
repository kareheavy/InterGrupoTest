package com.jhonjimenez.intergrupotest.di;

import android.app.Application;
import android.content.Context;
import com.jhonjimenez.intergrupotest.app.App;
import dagger.Module;

import javax.inject.Singleton;

@Module
public class AppModule {

    private App app;


    public AppModule(App app) {
        this.app = app;
    }

    @Singleton
    public Context provideContext() {
        return app;
    }

}
