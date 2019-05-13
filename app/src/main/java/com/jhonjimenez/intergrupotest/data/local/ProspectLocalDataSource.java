package com.jhonjimenez.intergrupotest.data.local;

import com.jhonjimenez.intergrupotest.models.Prospect;
import com.jhonjimenez.intergrupotest.models.ProspectBackup;
import io.reactivex.Completable;
import io.reactivex.Observable;

import java.util.List;

public interface ProspectLocalDataSource {
    Observable<List<Prospect>> getProspects();

    Completable insertProspects(List<Prospect> prospects);

    Completable updateProspect(Prospect prospect);

    Completable updateProspectBackup(ProspectBackup prospectBackup);
}
