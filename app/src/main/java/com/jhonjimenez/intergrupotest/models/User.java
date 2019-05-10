package com.jhonjimenez.intergrupotest.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.jhonjimenez.intergrupotest.utils.Constants;

@Entity(tableName = Constants.NAME_TABLE_USER)
public class User {

    @PrimaryKey(autoGenerate = true)
    private int _ID;
    private String email;
    private String password;
    private String authToken;


    public int get_ID() {
        return _ID;
    }

    public void set_ID(int _ID) {
        this._ID = _ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
