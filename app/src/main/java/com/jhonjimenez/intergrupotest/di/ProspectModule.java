package com.jhonjimenez.intergrupotest.di;

import android.content.Context;
import com.jhonjimenez.intergrupotest.data.local.ProspectLocalDataSource;
import com.jhonjimenez.intergrupotest.data.preferences.SharedPreferencesDataSource;
import com.jhonjimenez.intergrupotest.data.remote.ProspectRemoteDataSource;
import com.jhonjimenez.intergrupotest.data.remote.ProspectRemoteDataSourceImpl;
import com.jhonjimenez.intergrupotest.ui.prospects.ProspectsMVP;
import com.jhonjimenez.intergrupotest.ui.prospects.ProspectsPresenterImpl;
import com.jhonjimenez.intergrupotest.ui.prospects.ProspectsRepositoryImpl;
import dagger.Module;
import dagger.Provides;

@Module
public class ProspectModule {

    @Provides
    ProspectsMVP.Presenter provideProspectPresenterImpl(ProspectsMVP.Repository repository){
        return new ProspectsPresenterImpl(repository);
    }

    @Provides
    ProspectsMVP.Repository provideProspectResposytoryImpl(ProspectRemoteDataSource prospectRemoteDataSource,
                                                           ProspectLocalDataSource prospectLocalDataSource,
                                                           SharedPreferencesDataSource sharedPreferencesDataSource,
                                                           Context context){
        return new ProspectsRepositoryImpl(prospectRemoteDataSource, prospectLocalDataSource, sharedPreferencesDataSource,
                context);
    }

    @Provides
    ProspectRemoteDataSource provideProspectRemoteDataSource(){
        return new ProspectRemoteDataSourceImpl();
    }
}
