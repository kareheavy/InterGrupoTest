package com.jhonjimenez.intergrupotest.data.remote;

import com.jhonjimenez.intergrupotest.models.User;
import io.reactivex.Observable;

public interface LoginRemoteDataSource {

    Observable<User> doLogin(String email, String password);
}
