package com.jhonjimenez.intergrupotest.di;

import android.app.Application;
import android.content.Context;
import com.jhonjimenez.intergrupotest.app.App;
import com.jhonjimenez.intergrupotest.data.preferences.SharedPreferencesDataSource;
import com.jhonjimenez.intergrupotest.data.preferences.SharedPreferencesImpl;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class AppModule {

    private App app;


    public AppModule(App app) {
        this.app = app;
    }

    @Singleton
    @Provides
    public Context provideContext() {
        return app;
    }

    @Provides
    public SharedPreferencesDataSource provideSharedPreferencesDataSource(Context context){
        return new SharedPreferencesImpl(context);
    }

}
