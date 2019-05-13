package com.jhonjimenez.intergrupotest.ui.login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import androidx.room.EmptyResultSetException;
import com.google.gson.Gson;
import com.jhonjimenez.intergrupotest.data.local.LoginLocalDataSourceImpl;
import com.jhonjimenez.intergrupotest.models.User;
import com.jhonjimenez.intergrupotest.utils.RetrofitError;
import com.jhonjimenez.intergrupotest.utils.Validations;
import io.reactivex.SingleObserver;
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
    private Context context;

    @Inject
    public LoginPresenterImpl(LoginMvc.Repository repository, Context context) {
        this.repository = repository;
        this.context = context;
    }

    @Override
    public void setView(LoginMvc.View view) {
        this.view = view;
    }

    @SuppressLint("CheckResult")
    @Override
    public void doLogin(String email, String password, boolean remenberMe) {
        if (view != null) {

            view.showProgressDialog("Inicio de sesión", "Consultando información.");

            disposable = repository.doLogin(email, password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(user -> {
                        repository.getUser(user)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(user1 -> {
                                    view.hideProgressDialog();
                                    view.startActivity();
                                }, throwable -> {
                                    if (throwable instanceof EmptyResultSetException) {
                                        repository.insertUser(user)
                                                .subscribeOn(Schedulers.io())
                                                .observeOn(AndroidSchedulers.mainThread())
                                                .subscribe(() -> {
                                                    if (remenberMe)
                                                        repository.setSharedPreferences(user);
                                                    view.hideProgressDialog();
                                                    view.startActivity();
                                                }, throwable1 -> {
                                                    view.hideProgressDialog();
                                                    RetrofitError objectError = new RetrofitError();
                                                    objectError.setError("Algo salio mal, vuelve a intentarlo.");
                                                    view.showError(objectError);
                                                });
                                    }else{
                                        view.hideProgressDialog();
                                        RetrofitError objectError = new RetrofitError();
                                        objectError.setError("Algo salio mal, vuelve a intentarlo.");
                                        view.showError(objectError);
                                    }
                                });

                    }, throwable -> {

                        view.hideProgressDialog();

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
                            if (throwable instanceof EmptyResultSetException) {
                                RetrofitError objectError = new RetrofitError();
                                objectError.setError("El usuario no se encuentra registrado localmente.");
                                view.showError(objectError);
                            } else {
                                RetrofitError objectError = new RetrofitError();
                                objectError.setError("Algo salio mal, comunicate con admin.");
                                view.showError(objectError);
                            }
                        }
                    });
        }
    }


    @Override
    public void dispose() {
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }

    @Override
    public boolean validateInputs() {
        if (view != null) {
            int error = 0;

            error += Validations.validateEditTextEmailAdress(view.getEditTextEmail(), view.getTextInputLayoutEmail(), context);
            error += Validations.validateEditText(view.getEditTextPassowrd(), view.getTextInputLayoutPassowrd(), context);

            return error == 0;
        }

        return false;
    }

    @Override
    public void getCredential() {
        User objectUser = repository.getCredential();

        if (objectUser != null) {
            if (view != null) {
                view.setCredential(objectUser);
            }
        }
    }
}
