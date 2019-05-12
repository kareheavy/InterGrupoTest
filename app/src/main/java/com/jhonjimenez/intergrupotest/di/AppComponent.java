package com.jhonjimenez.intergrupotest.di;

import com.jhonjimenez.intergrupotest.ui.login.LoginActivityImpl;
import com.jhonjimenez.intergrupotest.ui.prospects.ProspectsFragmentImpl;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {AppModule.class, LoginModule.class, RoomModule.class, ProspectModule.class})
public interface AppComponent {

    void inject(LoginActivityImpl target);

    void inject(ProspectsFragmentImpl target);
}
