package com.jhonjimenez.intergrupotest.ui.prospects;


import com.jhonjimenez.intergrupotest.models.Prospect;
import com.jhonjimenez.intergrupotest.utils.RetrofitError;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

import java.util.List;

public interface ProspectsMVP {

    interface View{

        void showProgressDialog(String title, String message);
        void hideProgressDialog();
        void showError(RetrofitError objectError);
        void showProspects(List<Prospect> prospects1);
        void hideEmptyState();
    }
    interface Presenter{
        void setView(ProspectsMVP.View view);
        void dispose();
        void getProspects();
        void updateProspect(Prospect prospect);
    }
    interface Repository{
        Observable<List<Prospect>> getProspects();
        Observable<List<Prospect>> getProspectsLocal();
        Completable insertProspects(List<Prospect> prospects);
        Completable updateProspect(Prospect prospect);
        Completable updateProspectBackup(Prospect prospect);
    }
}
