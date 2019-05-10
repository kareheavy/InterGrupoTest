package com.jhonjimenez.intergrupotest.di;

import com.jhonjimenez.intergrupotest.ui.login.LoginActivityImpl;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {AppModule.class, LoginModule.class, RoomModule.class})
public interface AppComponent {

    void inject(LoginActivityImpl target);
}
