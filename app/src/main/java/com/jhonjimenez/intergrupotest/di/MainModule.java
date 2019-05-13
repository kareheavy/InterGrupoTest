package com.jhonjimenez.intergrupotest.di;

import android.content.Context;
import com.jhonjimenez.intergrupotest.data.local.MainLocalDataSource;
import com.jhonjimenez.intergrupotest.data.preferences.SharedPreferencesDataSource;
import com.jhonjimenez.intergrupotest.ui.main.MainMVP;
import com.jhonjimenez.intergrupotest.ui.main.MainPresenterImpl;
import com.jhonjimenez.intergrupotest.ui.main.MainRepositoryImpl;
import com.jhonjimenez.intergrupotest.ui.prospects.ProspectsMVP;
import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    MainMVP.Presenter provideMainPresenterImpl(MainMVP.Repository repository) {
        return new MainPresenterImpl(repository);
    }

    @Provides
    MainMVP.Repository provideMainResposytoryImpl(MainLocalDataSource localDataSource,
                                                  SharedPreferencesDataSource sharedPreferencesDataSource,
                                                  Context context) {
        return new MainRepositoryImpl(localDataSource, sharedPreferencesDataSource, context);
    }
}
