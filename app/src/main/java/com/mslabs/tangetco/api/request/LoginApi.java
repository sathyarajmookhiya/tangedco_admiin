package com.mslabs.tangetco.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


/**
 * Project           : TangedcoComplaint
 * File Name         : LoginApi
 * Description       :
 * Revision History  : version 1
 * Date              : 2019-09-08
 * Original author   : Kannappan
 * Description       : Initial version
 */
public class LoginApi implements Serializable {


    @SerializedName("username")
    @Expose
    private String username;


    @SerializedName("password")
    @Expose
    private String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
