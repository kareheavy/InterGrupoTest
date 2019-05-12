package com.jhonjimenez.intergrupotest.data.preferences;

public interface SharedPreferencesDataSource {

    void setEmail(String email);
    void setPassword(String password);
    void setToken(String token);
    void setRemenberMe(boolean remenberMe);

    String getEmail();
    String getPassword();
    String getToken();
    boolean getRemenberMe();
}
