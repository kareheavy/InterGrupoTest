package com.jhonjimenez.intergrupotest.utils;

import android.content.Context;
import android.widget.EditText;
import com.google.android.material.textfield.TextInputLayout;
import com.jhonjimenez.intergrupotest.R;

public class Validations {

    public static int validateEditTextEmailAdress(EditText editText, TextInputLayout textInputLayout, Context context) {

        if (editText == null) {
            return 1;
        } else if ("".equalsIgnoreCase(editText.getText().toString().trim())) {
            textInputLayout.setError(context.getString(R.string.login_field_required));
            editText.requestFocus();
            return 1;
        } else {
            if (android.util.Patterns.EMAIL_ADDRESS.matcher(editText.getText().toString()).matches()) {
                textInputLayout.setError(null);
                textInputLayout.setErrorEnabled(false);
                return 0;
            } else {
                textInputLayout.setError(context.getString(R.string.login_email_wrong));
                editText.requestFocus();
                return 1;
            }

        }
    }

    public static int validateEditText(EditText editText, TextInputLayout textInputLayout, Context context) {

        if (editText == null) {
            return 1;
        } else if ("".equalsIgnoreCase(editText.getText().toString().trim())) {
            textInputLayout.setError(context.getString(R.string.login_field_required));
            editText.requestFocus();
            return 1;
        } else {
            textInputLayout.setError(null);
            textInputLayout.setErrorEnabled(false);
            return 0;
        }
    }

}
