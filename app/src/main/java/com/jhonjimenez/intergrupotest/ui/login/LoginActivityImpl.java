package com.jhonjimenez.intergrupotest.ui.login;

import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.android.material.textfield.TextInputLayout;
import com.jhonjimenez.intergrupotest.R;
import com.jhonjimenez.intergrupotest.app.App;
import com.jhonjimenez.intergrupotest.utils.Messages;
import com.jhonjimenez.intergrupotest.utils.RetrofitError;
import com.jhonjimenez.intergrupotest.utils.Validations;

import javax.inject.Inject;

public class LoginActivityImpl extends AppCompatActivity implements LoginMvc.View {

    @Inject
    LoginMvc.Presenter presenter;

    @BindView(R.id.edittext_email)
    EditText email;
    @BindView(R.id.textinputlayout_email)
    TextInputLayout textInputEmail;
    @BindView(R.id.edittext_password)
    EditText password;
    @BindView(R.id.textinputlayout_password)
    TextInputLayout textInputPassword;
    @BindView(R.id.checkbox_remenber_me)
    CheckBox rememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        ((App) getApplication()).getComponent().inject(this);

        presenter.setView(this);

        password.setOnEditorActionListener((v, actionId, event) -> {
            boolean handled = false;

            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (validateInputs()) {
                    presenter.doLogin(email.getText().toString(), password.getText().toString());
                }
                handled = true;
            }

            return handled;
        });

    }


    @OnClick(R.id.button)
    public void doLogIn() {

        if (validateInputs()) {
            presenter.doLogin(email.getText().toString(), password.getText().toString());
        }
    }

    //TODO: change this validation to prenseter
    //TODO: validate remenber me and show dialog
    private boolean validateInputs() {
        int error = 0;

        error += Validations.validateEditTextEmailAdress(email, textInputEmail, this);
        error += Validations.validateEditText(password, textInputPassword, this);

        return error == 0;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dispose();
    }

    @Override
    public void showError(RetrofitError objectError) {
        Messages.showCustomToast(this, 1, objectError.getError());
    }

    @Override
    public void startActivity() {
        Messages.showCustomToast(this, 0, "Inicio sesion exitoso.");
    }
}
