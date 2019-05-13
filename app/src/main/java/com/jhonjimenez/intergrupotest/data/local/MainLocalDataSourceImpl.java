package com.jhonjimenez.intergrupotest.data.local;

import com.jhonjimenez.intergrupotest.data.local.dao.ProspectDAO;
import com.jhonjimenez.intergrupotest.data.local.dao.UserDAO;
import io.reactivex.Completable;

import javax.inject.Inject;

public class MainLocalDataSourceImpl implements MainLocalDataSource{

    private UserDAO userDAO;
    private ProspectDAO prospectDAO;

    @Inject
    public MainLocalDataSourceImpl(UserDAO userDAO, ProspectDAO prospectDAO) {
        this.userDAO = userDAO;
        this.prospectDAO = prospectDAO;
    }

    @Override
    public void deleteUser() {
         userDAO.deleteUser();
    }

    @Override
    public void deleteProspects() {
         prospectDAO.deleteProspect();
    }

    @Override
    public void deleteProspectsBackup() {
         prospectDAO.deleteProspectBackup();
    }
}
