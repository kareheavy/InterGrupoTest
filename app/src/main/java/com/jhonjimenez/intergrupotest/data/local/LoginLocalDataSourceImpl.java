package com.jhonjimenez.intergrupotest.data.local;

import com.jhonjimenez.intergrupotest.data.local.dao.UserDAO;
import com.jhonjimenez.intergrupotest.models.User;
import io.reactivex.Completable;

import javax.inject.Inject;

public class LoginLocalDataSourceImpl implements LoginLocalDataSource{

    private UserDAO userDAO;

    @Inject
    public LoginLocalDataSourceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public Completable insertUser(User user) {
        return userDAO.insertUser(user);
    }
}
