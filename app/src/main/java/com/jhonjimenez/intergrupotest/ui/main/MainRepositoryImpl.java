package com.jhonjimenez.intergrupotest.ui.main;

import android.content.Context;
import com.jhonjimenez.intergrupotest.data.local.MainLocalDataSource;
import com.jhonjimenez.intergrupotest.data.preferences.SharedPreferencesDataSource;
import io.reactivex.Completable;

public class MainRepositoryImpl implements MainMVP.Repository {

    private MainLocalDataSource localDataSource;
    private SharedPreferencesDataSource sharedPreferencesDataSource;
    public Context context;

    public MainRepositoryImpl(MainLocalDataSource localDataSource, SharedPreferencesDataSource sharedPreferencesDataSource, Context context) {
        this.localDataSource = localDataSource;
        this.sharedPreferencesDataSource = sharedPreferencesDataSource;
        this.context = context;
    }

    @Override
    public void deleteUser() {
        sharedPreferencesDataSource.setEmail("");
        sharedPreferencesDataSource.setPassword("");
        sharedPreferencesDataSource.setToken("");
        sharedPreferencesDataSource.setRemenberMe(false);

        localDataSource.deleteUser();
    }

    @Override
    public void deleteProspetc() {
         localDataSource.deleteProspects();
    }

    @Override
    public void deleteProspetcBackup() {
         localDataSource.deleteProspectsBackup();
    }
}
