package com.jhonjimenez.intergrupotest.data.local;

import com.jhonjimenez.intergrupotest.models.User;
import io.reactivex.Completable;

public interface LoginLocalDataSource {
    Completable insertUser(User user);
}
