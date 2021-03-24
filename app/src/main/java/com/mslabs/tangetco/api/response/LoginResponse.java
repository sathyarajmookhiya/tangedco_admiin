package com.mslabs.tangetco.api.response;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "user")
public class LoginResponse implements Serializable {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    @Expose
    private Integer id;
    @NonNull
    @SerializedName("circle_id")
    @Expose
    private Integer circleId;

    @NonNull
    @SerializedName("iserror")
    @Expose
    Integer Iserror;

    @NonNull
    @SerializedName("section_id")
    @Expose
    private Integer sectionId;

    @NonNull
    @SerializedName("section_name")
    @Expose
    private String sectionName;

    @NonNull
    @SerializedName("region_id")
    @Expose
    private Integer regionId;

    @NonNull
    @SerializedName("division_id")
    @Expose
    private Integer divisionId;

    @NonNull
    @SerializedName("sub_division_id")
    @Expose
    private Integer subDivisionId;

    @NonNull
    @SerializedName("sub_division_name")
    @Expose
    private String subDivisionName;

    @NonNull
    @SerializedName("role_id")
    @Expose
    private Integer roleId;

    @NonNull
    @SerializedName("division_name")
    @Expose
    private String divisionName;

    @NonNull
    @SerializedName("region_name")
    @Expose
    private String regionName;

    @NonNull
    @SerializedName("circle_name")
    @Expose
    private String circleName;

    @NonNull
    @SerializedName("office_id")
    @Expose
    private Integer officeId;

    @NonNull
    @SerializedName("officer_id")
    @Expose
    private Integer officerId;



    @NonNull
    public String getCircleName() {
        return circleName;
    }

    public void setCircleName(@NonNull String circleName) {
        this.circleName = circleName;
    }

    @NonNull
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(@NonNull Integer roleId) {
        this.roleId = roleId;
    }

    @Ignore
    @SerializedName("message")
    @Expose
    private String message;

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    @NonNull
    public Integer getIserror() {
        return Iserror;
    }

    public void setIserror(@NonNull Integer iserror) {
        Iserror = iserror;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @NonNull
    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(@NonNull Integer sectionId) {
        this.sectionId = sectionId;
    }

    @NonNull
    public Integer getCircleId() {
        return circleId;
    }

    public void setCircleId(@NonNull Integer circleId) {
        this.circleId = circleId;
    }
    @NonNull
    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(@NonNull String sectionName) {
        this.sectionName = sectionName;
    }

    @NonNull
    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(@NonNull Integer regionId) {
        this.regionId = regionId;
    }

    @NonNull
    public Integer getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(@NonNull Integer divisionId) {
        this.divisionId = divisionId;
    }

    @NonNull
    public Integer getSubDivisionId() {
        return subDivisionId;
    }

    public void setSubDivisionId(@NonNull Integer subDivisionId) {
        this.subDivisionId = subDivisionId;
    }
    @NonNull
    public String getSubDivisionName() {
        return subDivisionName;
    }

    public void setSubDivisionName(@NonNull String subDivisionName) {
        this.subDivisionName = subDivisionName;
    }

    @NonNull
    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(@NonNull String divisionName) {
        this.divisionName = divisionName;
    }

    @NonNull
    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(@NonNull String regionName) {
        this.regionName = regionName;
    }


    @NonNull
    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(@NonNull Integer officeId) {
        this.officeId = officeId;
    }

    @NonNull
    public Integer getOfficerId() {
        return officerId;
    }

    public void setOfficerId(@NonNull Integer officerId) {
        this.officerId = officerId;
    }
}