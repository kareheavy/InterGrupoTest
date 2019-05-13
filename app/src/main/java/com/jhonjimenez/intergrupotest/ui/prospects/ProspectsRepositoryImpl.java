package com.jhonjimenez.intergrupotest.ui.prospects;

import android.content.Context;
import com.jhonjimenez.intergrupotest.data.local.ProspectLocalDataSource;
import com.jhonjimenez.intergrupotest.data.preferences.SharedPreferencesDataSource;
import com.jhonjimenez.intergrupotest.data.remote.ProspectRemoteDataSource;
import com.jhonjimenez.intergrupotest.models.Prospect;
import com.jhonjimenez.intergrupotest.utils.ConnectionDetector;
import com.jhonjimenez.intergrupotest.utils.Mapping;
import io.reactivex.Completable;
import io.reactivex.Observable;

import java.util.List;

public class ProspectsRepositoryImpl implements ProspectsMVP.Repository {

    private ProspectRemoteDataSource remoteDataSource;
    private ProspectLocalDataSource localDataSource;
    private SharedPreferencesDataSource sharedPreferencesDataSource;
    public Context context;

    public ProspectsRepositoryImpl(ProspectRemoteDataSource remoteDataSource, ProspectLocalDataSource localDataSource,
                                   SharedPreferencesDataSource sharedPreferencesDataSource, Context context) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
        this.sharedPreferencesDataSource = sharedPreferencesDataSource;
        this.context = context;
    }

    @Override
    public Observable<List<Prospect>> getProspects() {

        if(ConnectionDetector.isConnected(context)){
            return remoteDataSource.getProspects(sharedPreferencesDataSource.getToken());
        }else{
            return localDataSource.getProspects();
        }
    }

    @Override
    public Observable<List<Prospect>> getProspectsLocal() {
        return localDataSource.getProspects();
    }

    @Override
    public Completable insertProspects(List<Prospect> prospects) {
        return localDataSource.insertProspects(prospects);
    }

    @Override
    public Completable updateProspect(Prospect prospect) {
        return localDataSource.updateProspect(prospect);
    }

    @Override
    public Completable updateProspectBackup(Prospect prospect) {
        return localDataSource.updateProspectBackup(Mapping.convertProspectToProspectBackup(prospect));
    }
}
