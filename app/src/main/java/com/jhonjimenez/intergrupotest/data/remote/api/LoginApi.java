package com.jhonjimenez.intergrupotest.data.remote.api;

import com.jhonjimenez.intergrupotest.models.User;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LoginApi {

    @GET("application/login")
    Observable<User> doLogin(@Query("email") String email, @Query("password") String password);
}
