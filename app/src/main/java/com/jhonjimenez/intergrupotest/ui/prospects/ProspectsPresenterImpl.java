package com.jhonjimenez.intergrupotest.ui.prospects;

import android.annotation.SuppressLint;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.room.EmptyResultSetException;
import com.jhonjimenez.intergrupotest.utils.RetrofitError;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import javax.inject.Inject;

public class ProspectsPresenterImpl implements ProspectsMVP.Presenter {

    @Nullable
    private ProspectsMVP.View view;
    private ProspectsMVP.Repository repository;
    private Disposable disposable;

    @Inject
    public ProspectsPresenterImpl(ProspectsMVP.Repository repository) {
        this.repository = repository;
    }

    @Override
    public void setView(ProspectsMVP.View view) {
        this.view = view;
    }

    @Override
    public void dispose() {
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }

    @SuppressLint("CheckResult")
    @Override
    public void getProspects() {
        if (view != null) {
            view.showProgressDialog("Consultando...", "Consultando prospectos.");

            disposable = repository.getProspects()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(prospects -> {
                        repository.getProspectsLocal()
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(prospects1 -> {

                                    if (prospects1.isEmpty()) {
                                        repository.insertProspects(prospects)
                                                .subscribeOn(Schedulers.io())
                                                .observeOn(AndroidSchedulers.mainThread())
                                                .subscribe(() -> {
                                                    Log.i(ProspectsPresenterImpl.class.getName(), "");
                                                }, throwable1 -> {
                                                    RetrofitError objectError = new RetrofitError();
                                                    objectError.setError("Algo salio mal, vuelve a intentarlo.");
                                                    view.showError(objectError);
                                                });
                                    } else {
                                        Log.i(ProspectsPresenterImpl.class.getName(), "");
                                    }

                                }, throwable -> {
                                    view.hideProgressDialog();
                                    Log.i(ProspectsPresenterImpl.class.getName(), "");
                                });
                    }, throwable -> {
                        view.hideProgressDialog();
                        Log.i(ProspectsPresenterImpl.class.getName(), "");
                    });
        }
    }
}
