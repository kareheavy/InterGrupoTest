package com.jhonjimenez.intergrupotest.data.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SharedPreferencesImpl implements SharedPreferencesDataSource {

    private static final String PREF_KEY_CURRENT_EMAIL = "PREF_KEY_CURRENT_EMAIL";
    private static final String PREF_KEY_CURRENT_PASSWORD = "PREF_KEY_CURRENT_PASSWORD";
    private static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";
    private static final String PREF_KEY_REMENBER_ME = "PREF_KEY_REMENBER_ME";

    private SharedPreferences sharedPreferences;

    @Inject
    public SharedPreferencesImpl(Context context){
        sharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
    }

    @Override
    public void setEmail(String email) {
        sharedPreferences.edit().putString(PREF_KEY_CURRENT_EMAIL, email).apply();
    }

    @Override
    public void setPassword(String password) {
        sharedPreferences.edit().putString(PREF_KEY_CURRENT_PASSWORD, password).apply();
    }

    @Override
    public void setToken(String token) {
        sharedPreferences.edit().putString(PREF_KEY_ACCESS_TOKEN, token).apply();
    }

    @Override
    public void setRemenberMe(boolean remenberMe) {
        sharedPreferences.edit().putBoolean(PREF_KEY_REMENBER_ME, remenberMe).apply();
    }

    @Override
    public String getEmail() {
        return sharedPreferences.getString(PREF_KEY_CURRENT_EMAIL, null);
    }

    @Override
    public String getPassword() {
        return sharedPreferences.getString(PREF_KEY_CURRENT_PASSWORD, null);
    }

    @Override
    public String getToken() {
        return sharedPreferences.getString(PREF_KEY_ACCESS_TOKEN, null);
    }

    @Override
    public boolean getRemenberMe() {
        return sharedPreferences.getBoolean(PREF_KEY_REMENBER_ME, false);
    }
}
