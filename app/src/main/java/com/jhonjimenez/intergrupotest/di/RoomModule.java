package com.jhonjimenez.intergrupotest.di;

import android.content.Context;
import androidx.room.Room;
import com.jhonjimenez.intergrupotest.data.local.*;
import com.jhonjimenez.intergrupotest.data.local.dao.ProspectDAO;
import com.jhonjimenez.intergrupotest.data.local.dao.UserDAO;
import com.jhonjimenez.intergrupotest.data.local.database.IGDataBase;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class RoomModule {

    private IGDataBase igDataBase;

    public RoomModule(Context context) {

        igDataBase = Room.databaseBuilder(context,IGDataBase.class,"ig-db").build();
    }

    @Singleton
    @Provides
    IGDataBase providesIgDataBase(){
        return igDataBase;
    }

    @Singleton
    @Provides
    UserDAO providesUserDao(IGDataBase igDataBase){
        return igDataBase.getUserDAO();
    }

    @Singleton
    @Provides
    ProspectDAO providesProspectDao(IGDataBase igDataBase){
        return igDataBase.getProspectDAO();
    }

    @Singleton
    @Provides
    LoginLocalDataSource providesDaoToLoginLocalDataSourceImpl(UserDAO userDAO){
        return new LoginLocalDataSourceImpl(userDAO);
    }

    @Singleton
    @Provides
    ProspectLocalDataSource providesDaoToProspectLocalDataSourceImpl(ProspectDAO prospectDAO){
        return new ProspectLocalDataSourceImpl(prospectDAO);
    }

    @Singleton
    @Provides
    MainLocalDataSource providesDaoToMainLocalDataSourceImpl(ProspectDAO prospectDAO, UserDAO userDAO){
        return new MainLocalDataSourceImpl(userDAO, prospectDAO);
    }


}
