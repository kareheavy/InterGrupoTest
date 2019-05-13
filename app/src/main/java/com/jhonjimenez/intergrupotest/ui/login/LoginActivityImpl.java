package com.jhonjimenez.intergrupotest.ui.login;

import android.content.Intent;
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
import com.jhonjimenez.intergrupotest.models.User;
import com.jhonjimenez.intergrupotest.ui.main.MainActivityImpl;
import com.jhonjimenez.intergrupotest.utils.Messages;
import com.jhonjimenez.intergrupotest.utils.RetrofitError;

import javax.inject.Inject;

public class LoginActivityImpl extends AppCompatActivity implements LoginMvc.View {

    @Inject
    LoginMvc.Presenter presenter;

    @BindView(R.id.edittext_email)
    EditText editTextEmail;
    @BindView(R.id.textinputlayout_email)
    TextInputLayout textInputEmail;
    @BindView(R.id.edittext_password)
    EditText editTextPassword;
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

        editTextPassword.setOnEditorActionListener((v, actionId, event) -> {
            boolean handled = false;

            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (presenter.validateInputs()) {
                    presenter.doLogin(editTextEmail.getText().toString(), editTextPassword.getText().toString(), rememberMe.isChecked());
                }

                handled = true;
            }

            return handled;
        });
    }



    @Override
    protected void onStart() {
        super.onStart();
        presenter.getCredential();
    }

    @OnClick(R.id.button)
    public void doLogIn() {

        if (presenter.validateInputs()) {
            presenter.doLogin(editTextEmail.getText().toString(), editTextPassword.getText().toString(), rememberMe.isChecked());
        }
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
        Intent intent = new Intent(this, MainActivityImpl.class);
        startActivity(intent);
        finish();
    }

    @Override
    public EditText getEditTextEmail() {
        return editTextEmail;
    }

    @Override
    public EditText getEditTextPassowrd() {
        return editTextPassword;
    }

    @Override
    public TextInputLayout getTextInputLayoutEmail() {
        return textInputEmail;
    }

    @Override
    public TextInputLayout getTextInputLayoutPassowrd() {
        return textInputPassword;
    }

    @Override
    public void setCredential(User objectUser) {
        editTextEmail.setText(objectUser.getEmail());
        editTextPassword.setText(objectUser.getPassword());
        rememberMe.setChecked(true);
    }

    @Override
    public void showProgressDialog(String title, String message) {
        Messages.showProgressDialog(this, title, message);
    }

    @Override
    public void hideProgressDialog() {
        Messages.dismissProgressDialog();
    }
}
