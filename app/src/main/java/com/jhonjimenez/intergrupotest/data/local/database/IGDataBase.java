package com.jhonjimenez.intergrupotest.data.local.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.jhonjimenez.intergrupotest.data.local.dao.ProspectDAO;
import com.jhonjimenez.intergrupotest.data.local.dao.UserDAO;
import com.jhonjimenez.intergrupotest.models.Prospect;
import com.jhonjimenez.intergrupotest.models.User;

@Database(entities = {User.class, Prospect.class},version = 1, exportSchema = false)
public abstract class IGDataBase extends RoomDatabase {

    public abstract UserDAO getUserDAO();
    public abstract ProspectDAO getProspectDAO();
}
