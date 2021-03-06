package com.jhonjimenez.intergrupotest.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.jhonjimenez.intergrupotest.models.Prospect;
import com.jhonjimenez.intergrupotest.models.ProspectBackup;
import com.jhonjimenez.intergrupotest.models.User;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

import java.util.List;

@Dao
public interface ProspectDAO {

    @Insert
    Completable insertProspect(List<Prospect> prospect);

    @Query("SELECT * FROM prospect")
    Maybe<List<Prospect>> getProspect();

    @Update
    Completable updateProspect(Prospect prospect);

    @Insert
    Completable insertProspectBackup(ProspectBackup prospect);

    @Query("DELETE FROM prospect")
    void deleteProspect();

    @Query("DELETE FROM prospect_backup")
    void deleteProspectBackup();
}
