package com.jhonjimenez.intergrupotest.ui.login;

import android.content.Context;
import com.jhonjimenez.intergrupotest.data.local.LoginLocalDataSource;
import com.jhonjimenez.intergrupotest.data.remote.LoginRemoteDataSource;
import com.jhonjimenez.intergrupotest.models.User;
import io.reactivex.Completable;
import io.reactivex.Observable;

public class LoginRepositoryImpl implements LoginMvc.Repository{

    private LoginRemoteDataSource remoteDataSource;
    private LoginLocalDataSource localDataSource;
    public Context context;

    public LoginRepositoryImpl(LoginRemoteDataSource remoteDataSource, LoginLocalDataSource localDataSource, Context context) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
        this.context = context;
    }

    @Override
    public Observable<User> doLogin(String email, String password) {
        return remoteDataSource.doLogin(email, password).map(user -> {
            user.setPassword(password);
            return user;
        });
    }

    @Override
    public Completable insertUser(User user) {
        return localDataSource.insertUser(user);
    }
}
