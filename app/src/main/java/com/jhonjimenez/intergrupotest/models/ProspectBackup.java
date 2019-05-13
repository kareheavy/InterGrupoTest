package com.jhonjimenez.intergrupotest.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;
import com.jhonjimenez.intergrupotest.utils.Constants;

import java.io.Serializable;
@Entity(tableName = Constants.NAME_TABLE_PROSPECT_BACKUP)
public class ProspectBackup implements Serializable {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int _ID;
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("surname")
    private String surname;
    @SerializedName("telephone")
    private String telephone;
    @SerializedName("schProspectIdentification")
    private String schProspectIdentification;
    @SerializedName("statusCd")
    private Integer statusCd;

    public int get_ID() {
        return _ID;
    }

    public void set_ID(int _ID) {
        this._ID = _ID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSchProspectIdentification() {
        return schProspectIdentification;
    }

    public void setSchProspectIdentification(String schProspectIdentification) {
        this.schProspectIdentification = schProspectIdentification;
    }

    public Integer getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(Integer statusCd) {
        this.statusCd = statusCd;
    }
}
