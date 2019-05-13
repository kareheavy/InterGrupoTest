package com.jhonjimenez.intergrupotest.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.jhonjimenez.intergrupotest.models.User;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

import java.util.List;

@Dao
public interface UserDAO {

    @Insert
    Completable insertUser(User user);

    @Query("SELECT * FROM user WHERE email = :email AND password = :password")
    Single<User> getUser(String email, String password);


    @Query("DELETE FROM user")
    void deleteUser();
}
