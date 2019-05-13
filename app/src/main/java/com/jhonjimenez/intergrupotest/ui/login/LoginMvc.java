package com.jhonjimenez.intergrupotest.ui.login;

import android.widget.EditText;
import com.google.android.material.textfield.TextInputLayout;
import com.jhonjimenez.intergrupotest.models.User;
import com.jhonjimenez.intergrupotest.utils.RetrofitError;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;


public interface LoginMvc {

    interface View{

        void showError(RetrofitError objectError);
        void startActivity();
        EditText getEditTextEmail();
        EditText getEditTextPassowrd();
        TextInputLayout getTextInputLayoutEmail();
        TextInputLayout getTextInputLayoutPassowrd();
        void setCredential(User objectUser);

        void showProgressDialog(String title, String message);

        void hideProgressDialog();
    }

    interface Presenter{
        void setView(LoginMvc.View view);
        void doLogin(String email, String password, boolean remenberMe);
        void dispose();
        boolean validateInputs();
        void getCredential();
    }

    interface Repository{
        Observable<User> doLogin(String email, String password);
        Completable insertUser(User user);
        void setSharedPreferences(User user);
        Observable<User> getUser(User user);
        User getCredential();
    }

}
