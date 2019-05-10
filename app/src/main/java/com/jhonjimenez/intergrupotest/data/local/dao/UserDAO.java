package com.jhonjimenez.intergrupotest.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import com.jhonjimenez.intergrupotest.models.User;
import io.reactivex.Completable;

@Dao
public interface UserDAO {
    @Insert
    Completable insertUser(User user);
}
