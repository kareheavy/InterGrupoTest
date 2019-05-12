package com.jhonjimenez.intergrupotest.data.local;

import com.jhonjimenez.intergrupotest.models.User;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface LoginLocalDataSource {
    Completable insertUser(User user);

    Observable<User> getUser(String email, String password);
}
