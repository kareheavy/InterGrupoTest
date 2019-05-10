package com.jhonjimenez.intergrupotest.di;

import android.content.Context;
import com.jhonjimenez.intergrupotest.data.local.LoginLocalDataSource;
import com.jhonjimenez.intergrupotest.data.local.LoginLocalDataSourceImpl;
import com.jhonjimenez.intergrupotest.data.remote.LoginRemoteDataSource;
import com.jhonjimenez.intergrupotest.data.remote.LoginRemoteDataSourceImpl;
import com.jhonjimenez.intergrupotest.ui.login.LoginMvc;
import com.jhonjimenez.intergrupotest.ui.login.LoginPresenterImpl;
import com.jhonjimenez.intergrupotest.ui.login.LoginRepositoryImpl;
import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    Context context;

    public LoginModule(Context context) {
        this.context = context;
    }

    @Provides
    public LoginMvc.Presenter provideLoginPresenterImpl(LoginMvc.Repository repository){
        return new LoginPresenterImpl(repository);
    }

    @Provides
    public LoginMvc.Repository provideLoginRepositoryImpl(LoginRemoteDataSource loginRemoteDataSource, LoginLocalDataSource loginLocalDataSource, Context context){
        return new LoginRepositoryImpl(loginRemoteDataSource, loginLocalDataSource, context);
    }

    @Provides
    public LoginRemoteDataSource provideLoginRemoteDataSource(){
        return new LoginRemoteDataSourceImpl();
    }

    @Provides
    public Context provideContext(){
        return context;
    }

}
