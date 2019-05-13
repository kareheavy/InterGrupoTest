package com.jhonjimenez.intergrupotest.data.local;

import io.reactivex.Completable;

public interface MainLocalDataSource {

    void deleteUser();

    void deleteProspects();

    void deleteProspectsBackup();
}
