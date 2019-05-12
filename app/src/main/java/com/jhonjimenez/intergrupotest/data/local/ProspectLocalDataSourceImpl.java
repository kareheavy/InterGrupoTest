package com.jhonjimenez.intergrupotest.data.local;

import com.jhonjimenez.intergrupotest.data.local.dao.ProspectDAO;
import com.jhonjimenez.intergrupotest.models.Prospect;
import io.reactivex.Completable;
import io.reactivex.Observable;

import java.util.List;

public class ProspectLocalDataSourceImpl implements ProspectLocalDataSource {

    private ProspectDAO prospectDAO;

    public ProspectLocalDataSourceImpl(ProspectDAO prospectDAO) {
        this.prospectDAO = prospectDAO;
    }

    @Override
    public Observable<List<Prospect>> getProspects() {
        return prospectDAO.getProspect().toObservable();
    }

    @Override
    public Completable insertProspects(List<Prospect> prospects) {
        return prospectDAO.insertProspect(prospects);
    }
}
