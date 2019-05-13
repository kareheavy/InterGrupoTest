package com.jhonjimenez.intergrupotest.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;
import com.jhonjimenez.intergrupotest.utils.Constants;

import java.io.Serializable;

@Entity(tableName = Constants.NAME_TABLE_PROSPECT)
public class Prospect implements Serializable {

    @NonNull
    @PrimaryKey
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
    @SerializedName("address")
    private String address;
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("updatedAt")
    private String updatedAt;
    @SerializedName("statusCd")
    private Integer statusCd;
    @SerializedName("zoneCode")
    private String zoneCode;
    @SerializedName("neighborhoodCode")
    private String neighborhoodCode;
    @SerializedName("cityCode")
    private String cityCode;
    @SerializedName("sectionCode")
    private String sectionCode;
    @SerializedName("roleId")
    private Integer roleId;
    @SerializedName("observation")
    private String observation;
    @SerializedName("disable")
    private Boolean disable;
    @SerializedName("visited")
    private Boolean visited;
    @SerializedName("callcenter")
    private Boolean callcenter;
    @SerializedName("acceptSearch")
    private Boolean acceptSearch;
    @SerializedName("campaignCode")
    private String campaignCode;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(Integer statusCd) {
        this.statusCd = statusCd;
    }

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    public String getNeighborhoodCode() {
        return neighborhoodCode;
    }

    public void setNeighborhoodCode(String neighborhoodCode) {
        this.neighborhoodCode = neighborhoodCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Boolean getDisable() {
        return disable;
    }

    public void setDisable(Boolean disable) {
        this.disable = disable;
    }

    public Boolean getVisited() {
        return visited;
    }

    public void setVisited(Boolean visited) {
        this.visited = visited;
    }

    public Boolean getCallcenter() {
        return callcenter;
    }

    public void setCallcenter(Boolean callcenter) {
        this.callcenter = callcenter;
    }

    public Boolean getAcceptSearch() {
        return acceptSearch;
    }

    public void setAcceptSearch(Boolean acceptSearch) {
        this.acceptSearch = acceptSearch;
    }

    public String getCampaignCode() {
        return campaignCode;
    }

    public void setCampaignCode(String campaignCode) {
        this.campaignCode = campaignCode;
    }

}
