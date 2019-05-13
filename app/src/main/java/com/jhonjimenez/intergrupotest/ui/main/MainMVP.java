package com.jhonjimenez.intergrupotest.ui.main;

import io.reactivex.Completable;

public interface MainMVP {

    interface View{
        void closeSession();
    }

    interface Presenter{

        void closeSession();
        void dispose();
        void setView(MainMVP.View view);
    }

    interface Repository{

        void deleteUser();

        void deleteProspetc();

        void deleteProspetcBackup();
    }
}
