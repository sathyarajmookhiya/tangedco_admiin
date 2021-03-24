/*
 *
 *  * Copyright (c) Koninklijke Philips N.V., 2017
 *  * All rights are reserved. Reproduction or dissemination
 *  * in whole or in part is prohibited without the prior written
 *  * consent of the copyright holder.
 *
 */

package com.mslabs.tangetco.login;

import android.app.Application;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;


import com.mslabs.sipcot.db.DatabaseManager;
import com.mslabs.tangetco.api.APIManager;
import com.mslabs.tangetco.api.request.DashboardApi;
import com.mslabs.tangetco.api.request.LoginApi;
import com.mslabs.tangetco.api.response.DashboardResponse;
import com.mslabs.tangetco.api.response.LoginResponse;
import com.mslabs.tangetco.util.JavaMD5Example;

/**
 * Project           : talkio
 * File Name         : SignupViewModel
 * Description       :
 * Revision History  : version 1
 * Date              : 2020-07-02
 * Original author   : Kannappan
 * Description       : Initial version
 */
public class LoginViewModel extends AndroidViewModel {

    private static final String TAG = LoginViewModel.class.getSimpleName();


    public MutableLiveData<String> loginUserName = new MutableLiveData<String>();
    public MutableLiveData<String> loginPassword = new MutableLiveData<String>();
    public LoginResponse loginResponse = new LoginResponse();
    MutableLiveData<String> loginStatus = new MutableLiveData<>();
    MutableLiveData<String> statusMessage = new MutableLiveData<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }


    @SuppressWarnings("UnusedParameters")
    public void onFirstNameTextChanged(CharSequence s, int start, int before, int count) {
        if (s != null) {
            loginUserName.setValue(s.toString());
        }
    }
 @SuppressWarnings("UnusedParameters")
    public void onLastNameTextChanged(CharSequence s, int start, int before, int count) {
        if (s != null) {
            loginUserName.setValue(s.toString());
        }
    }

    public void onNameFocusChange(View view, InputMethodManager inputMethodManager,
                                  boolean hasFocus) {
        if (!hasFocus) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @SuppressWarnings("UnusedParameters")
    public void onPhoneNumberTextChanged(CharSequence s, int start, int before, int count) {
        if (s != null) {
            loginUserName.setValue(s.toString());
        }
    }

    public void onPhoneNumberFocusChange(View view, InputMethodManager inputMethodManager,
                                         boolean hasFocus) {
        if (!hasFocus) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @SuppressWarnings("UnusedParameters")
    public void onPasswordTextChanged(CharSequence s, int start, int before, int count) {
        if (s != null) {
            loginPassword.setValue(s.toString());
        }
    }

    public void onPasswordFocusChange(View view, InputMethodManager inputMethodManager,
                                      boolean hasFocus) {
        if (!hasFocus) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @SuppressWarnings("UnusedParameters")
    public void onEmailTextChanged(CharSequence s, int start, int before, int count) {
        if (s != null) {
            loginUserName.setValue(s.toString());
        }
    }

    public void onEmailFocusChange(View view, InputMethodManager inputMethodManager,
                                   boolean hasFocus) {
        if (!hasFocus) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void saveProfileData(LoginResponse loginResponse)
    {
        this.loginResponse=loginResponse;
    }
    public LoginResponse getProfileData()
    {
        return loginResponse;
    }


    public LoginResponse userProfile(int userId)
    {
      return  DatabaseManager.INSTANCE.getLoginUserProfileData(userId);
    }


  MutableLiveData<LoginResponse> loginUser(String userName, String Password) {
        LoginApi loginApi = new LoginApi();
        loginApi.setUsername(userName);
        loginApi.setPassword(Password);
        JavaMD5Example javaMD5Example=new JavaMD5Example();
        String pass= javaMD5Example.getMD5EncryptedValue(loginPassword.getValue());
       loginApi.setPassword(pass);
       Log.d("TAG","UserName : "+loginUserName.getValue()+"Password : "+loginPassword.getValue()+"Password Enc: "+pass);
        return APIManager.getInstance().userLogin(loginApi);
    }

  public MutableLiveData<DashboardResponse> dashboardList(String fieldName, Integer officeId) {
        DashboardApi dashboardApi = new DashboardApi();
        dashboardApi.setFieldName(fieldName);
        dashboardApi.setOfficeId(officeId);
        Log.d("TAG","fieldName : "+fieldName +"OfficeId : "+officeId);
        return APIManager.getInstance().dashboardList(dashboardApi);
    }
}
