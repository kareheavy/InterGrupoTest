package com.jhonjimenez.intergrupotest.app;

import android.app.Application;
import com.facebook.stetho.Stetho;
import com.jhonjimenez.intergrupotest.di.*;

public class App extends Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);

        component = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .loginModule(new LoginModule(this))
                .roomModule(new RoomModule(this))
                .build();
    }

    public AppComponent getComponent() {
        return component;
    }
}
