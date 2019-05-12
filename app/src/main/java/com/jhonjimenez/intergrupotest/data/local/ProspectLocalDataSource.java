package com.jhonjimenez.intergrupotest.data.local;

import com.jhonjimenez.intergrupotest.models.Prospect;
import io.reactivex.Completable;
import io.reactivex.Observable;

import java.util.List;

public interface ProspectLocalDataSource {
    Observable<List<Prospect>> getProspects();

    Completable insertProspects(List<Prospect> prospects);
}
