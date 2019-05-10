package com.jhonjimenez.intergrupotest.ui.login;

import android.annotation.SuppressLint;
import android.util.Log;
import com.google.gson.Gson;
import com.jhonjimenez.intergrupotest.utils.RetrofitError;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.io.IOException;
import java.net.SocketTimeoutException;

public class LoginPresenterImpl implements LoginMvc.Presenter {

    @Nullable
    private LoginMvc.View view;
    private LoginMvc.Repository repository;
    private Disposable disposable;

    @Inject
    public LoginPresenterImpl(LoginMvc.Repository repository) {
        this.repository = repository;
    }

    @Override
    public void setView(LoginMvc.View view) {
        this.view = view;
    }

    @SuppressLint("CheckResult")
    @Override
    public void doLogin(String email, String password) {
        if (view != null) {
            disposable = repository.doLogin(email, password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(user -> {
                        repository.insertUser(user)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(() -> {
                                    Log.i(LoginPresenterImpl.class.getName(), "User");
                                }, throwable -> {
                                    Log.i(LoginPresenterImpl.class.getName(), "Error");
                                });

                    }, throwable -> {

                        if (throwable instanceof HttpException) {
                            ResponseBody responseBody = ((HttpException) throwable).response().errorBody();

                            if (responseBody != null) {
                                Gson gson = new Gson();
                                RetrofitError objectError = gson.fromJson(responseBody.string(), RetrofitError.class);
                                view.showError(objectError);
                            }

                        } else if (throwable instanceof SocketTimeoutException) {
                            RetrofitError objectError = new RetrofitError();
                            objectError.setError("Algo salio mal, vuelve a intentarlo.");
                            view.showError(objectError);
                        } else if (throwable instanceof IOException) {
                            RetrofitError objectError = new RetrofitError();
                            objectError.setError("Algo salio mal con tu conexión, vuelve a intentarlo.");
                            view.showError(objectError);
                        } else {
                            RetrofitError objectError = new RetrofitError();
                            objectError.setError("Algo salio mal, comunicate con admin.");
                            view.showError(objectError);
                        }
                    });
        }
    }


    @Override
    public void dispose() {
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }
}
