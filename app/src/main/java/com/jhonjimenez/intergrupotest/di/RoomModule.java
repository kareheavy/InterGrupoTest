package com.jhonjimenez.intergrupotest.di;

import android.content.Context;
import androidx.room.Room;
import com.jhonjimenez.intergrupotest.data.local.LoginLocalDataSource;
import com.jhonjimenez.intergrupotest.data.local.LoginLocalDataSourceImpl;
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
    LoginLocalDataSource providesDaoToLoginLocalDataSourceImpl(UserDAO iserDAO){
        return new LoginLocalDataSourceImpl(iserDAO);
    }
}
