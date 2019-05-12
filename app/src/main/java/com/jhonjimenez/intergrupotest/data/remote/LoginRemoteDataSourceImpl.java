package com.jhonjimenez.intergrupotest.data.remote;

import com.jhonjimenez.intergrupotest.data.remote.api.LoginApi;
import com.jhonjimenez.intergrupotest.data.remote.client.RetrofitClient;
import com.jhonjimenez.intergrupotest.models.User;
import com.jhonjimenez.intergrupotest.utils.Constants;
import io.reactivex.Observable;

public class LoginRemoteDataSourceImpl extends RetrofitClient implements LoginRemoteDataSource {
    @Override
    public Observable<User> doLogin(String email, String password) {
        return create(LoginApi.class, Constants.BASE_URL, "").doLogin(email, password);
    }
}
