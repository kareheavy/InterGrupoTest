package com.jhonjimenez.intergrupotest.di;

import android.content.Context;
import com.jhonjimenez.intergrupotest.data.local.LoginLocalDataSource;
import com.jhonjimenez.intergrupotest.data.local.LoginLocalDataSourceImpl;
import com.jhonjimenez.intergrupotest.data.preferences.SharedPreferencesDataSource;
import com.jhonjimenez.intergrupotest.data.preferences.SharedPreferencesImpl;
import com.jhonjimenez.intergrupotest.data.remote.LoginRemoteDataSource;
import com.jhonjimenez.intergrupotest.data.remote.LoginRemoteDataSourceImpl;
import com.jhonjimenez.intergrupotest.ui.login.LoginMvc;
import com.jhonjimenez.intergrupotest.ui.login.LoginPresenterImpl;
import com.jhonjimenez.intergrupotest.ui.login.LoginRepositoryImpl;
import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    @Provides
    public LoginMvc.Presenter provideLoginPresenterImpl(LoginMvc.Repository repository, Context context){
        return new LoginPresenterImpl(repository, context);
    }

    @Provides
    public LoginMvc.Repository provideLoginRepositoryImpl(LoginRemoteDataSource loginRemoteDataSource,
                                                          LoginLocalDataSource loginLocalDataSource,
                                                          SharedPreferencesDataSource sharedPreferencesDataSource,
                                                          Context context){
        return new LoginRepositoryImpl(loginRemoteDataSource, loginLocalDataSource, sharedPreferencesDataSource,context);
    }

    @Provides
    public LoginRemoteDataSource provideLoginRemoteDataSource(){
        return new LoginRemoteDataSourceImpl();
    }

}
