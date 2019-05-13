package com.jhonjimenez.intergrupotest.ui.login;

import android.content.Context;
import com.jhonjimenez.intergrupotest.data.local.LoginLocalDataSource;
import com.jhonjimenez.intergrupotest.data.preferences.SharedPreferencesDataSource;
import com.jhonjimenez.intergrupotest.data.remote.LoginRemoteDataSource;
import com.jhonjimenez.intergrupotest.models.User;
import com.jhonjimenez.intergrupotest.utils.ConnectionDetector;
import com.jhonjimenez.intergrupotest.utils.EncryptDecrypt;
import io.reactivex.Completable;
import io.reactivex.Observable;


public class LoginRepositoryImpl implements LoginMvc.Repository{

    private LoginRemoteDataSource remoteDataSource;
    private LoginLocalDataSource localDataSource;
    private SharedPreferencesDataSource sharedPreferencesDataSource;
    public Context context;

    public LoginRepositoryImpl(LoginRemoteDataSource remoteDataSource, LoginLocalDataSource localDataSource,
                               SharedPreferencesDataSource sharedPreferencesDataSource, Context context) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
        this.sharedPreferencesDataSource = sharedPreferencesDataSource;
        this.context = context;
    }

    @Override
    public Observable<User> doLogin(String email, String password) {

        if(ConnectionDetector.isConnected(context)){
            return remoteDataSource.doLogin(email, password).map(user -> {
                user.setPassword(password);
                return user;
            });
        }else{
            return localDataSource.getUser(email, password);
        }

    }

    @Override
    public Completable insertUser(User user) {
        user.setPassword(EncryptDecrypt.encrypt(user.getPassword()));
        sharedPreferencesDataSource.setToken(user.getAuthToken());
        return localDataSource.insertUser(user);
    }

    @Override
    public void setSharedPreferences(User user) {
        sharedPreferencesDataSource.setEmail(user.getEmail());
        sharedPreferencesDataSource.setPassword(user.getPassword());
        sharedPreferencesDataSource.setRemenberMe(true);
    }

    @Override
    public Observable<User> getUser(User user) {
       return localDataSource.getUser(user.getEmail(), user.getPassword());
    }

    @Override
    public User getCredential() {
        if(sharedPreferencesDataSource.getRemenberMe()){
            User objectUser = new User();
            objectUser.setEmail(sharedPreferencesDataSource.getEmail());
            String password = EncryptDecrypt.decrypt(sharedPreferencesDataSource.getPassword());
            objectUser.setPassword(password);
            return objectUser;
        }else{
            return null;
        }

    }


}
