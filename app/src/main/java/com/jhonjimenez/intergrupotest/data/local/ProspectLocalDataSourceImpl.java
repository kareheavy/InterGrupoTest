package com.jhonjimenez.intergrupotest.data.local;

import com.jhonjimenez.intergrupotest.data.local.dao.ProspectDAO;
import com.jhonjimenez.intergrupotest.models.Prospect;
import com.jhonjimenez.intergrupotest.models.ProspectBackup;
import com.jhonjimenez.intergrupotest.utils.Mapping;
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

    @Override
    public Completable updateProspect(Prospect prospect) {

        return prospectDAO.updateProspect(prospect);
    }

    @Override
    public Completable updateProspectBackup(ProspectBackup prospectBackup) {
        return prospectDAO.insertProspectBackup(prospectBackup);
    }
}
