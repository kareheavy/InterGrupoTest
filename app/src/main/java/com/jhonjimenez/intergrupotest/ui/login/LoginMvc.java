package com.jhonjimenez.intergrupotest.ui.login;

import com.jhonjimenez.intergrupotest.models.User;
import com.jhonjimenez.intergrupotest.utils.RetrofitError;
import io.reactivex.Completable;
import io.reactivex.Observable;


public interface LoginMvc {

    interface View{

        void showError(RetrofitError objectError);
        void startActivity();
    }

    interface Presenter{
        void setView(LoginMvc.View view);
        void doLogin(String email, String password);
        void dispose();
    }

    interface Repository{
        Observable<User> doLogin(String email, String password);
        Completable insertUser(User user);
    }

}
