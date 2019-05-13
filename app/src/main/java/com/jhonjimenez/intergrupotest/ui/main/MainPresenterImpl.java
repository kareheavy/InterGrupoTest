package com.jhonjimenez.intergrupotest.ui.main;

import io.reactivex.disposables.Disposable;

import javax.inject.Inject;

public class MainPresenterImpl implements MainMVP.Presenter {

    private Disposable disposable;
    private MainMVP.Repository repository;
    private MainMVP.View view;

    @Inject
    public MainPresenterImpl(MainMVP.Repository repository) {
        this.repository = repository;
    }

    @Override
    public void closeSession() {
        if (view != null) {

            new Thread() {
                public void run() {
                    repository.deleteUser();
                    repository.deleteProspetc();
                    repository.deleteProspetcBackup();

                    view.closeSession();
                }
            }.start();

        }
    }

    @Override
    public void dispose() {
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }

    @Override
    public void setView(MainMVP.View view) {
        this.view = view;
    }
}
