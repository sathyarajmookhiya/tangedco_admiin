package com.mslabs.tangetco.util.preference


import android.text.TextUtils
import com.mslabs.sipcot.db.entity.DBTypeConverter.moshi
import com.mslabs.tangetco.api.response.ComplaintListResponse
import com.mslabs.tangetco.api.response.DashboardResponse
import com.mslabs.tangetco.api.response.LoginResponse
import com.mslabs.tangetco.util.preference.PreferenceKey.*
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Types

/**
 * Project           : Smarthome
 * File Name         : SipcotPreferenceManager
 * Description       :
 * Revision History  : version 1
 * Date              : 2020-05-29
 * Original author   : Kannappan
 * Description       : Initial version
 */
object TangedcoPreferenceManager {

    fun setIntroVisitedFirstTime(isVisited: Boolean) {
        PreferenceUtil.getInstance()
            .setBooleanValue(PreferenceKey.INTRO_VISITED_FIRST_TIME, isVisited)
    }

    fun isIntroScreenVisited(): Boolean {
        return PreferenceUtil.getInstance()
            .getBooleanValue(PreferenceKey.INTRO_VISITED_FIRST_TIME, false)
    }

    fun isUserLoggedIn(): Boolean {
        return PreferenceUtil.getInstance()
            .getBooleanValue(PreferenceKey.IS_USER_AUTHENTICATED, false)
    }

    fun setUserLoggedIn(isUserLoggedIn: Boolean) {
        return PreferenceUtil.getInstance()
            .setBooleanValue(PreferenceKey.IS_USER_AUTHENTICATED, isUserLoggedIn)
    }

    fun getConnectedDeviceSerialNumber(): String? {
        return PreferenceUtil.getInstance().getStringValue(PreferenceKey.DEVICE_SERIAL_NUMBER, null)
    }

    fun setConnectedDeviceSerialNumber(deviceSerialNumber: String?) {
        PreferenceUtil.getInstance()
            .setStringValue(PreferenceKey.DEVICE_SERIAL_NUMBER, deviceSerialNumber)
    }

    fun storeHomeNetworkSSID(homeSSID: String?) {
        PreferenceUtil.getInstance().setStringValue(PreferenceKey.HOME_NETWORK_SSID, homeSSID)
    }

    fun getHomeNetworkSSID(): String? {
        return PreferenceUtil.getInstance().getStringValue(PreferenceKey.HOME_NETWORK_SSID, "")
    }

    /* fun getPassword(): String? {
         return PreferenceUtil.getInstance()
             .getEncStringValue(PreferenceKey.LOGGED_IN_USER_PASSWORD, "")
     }

     fun setPassword(password: String?) {
         PreferenceUtil.getInstance()
             .setEncStringValue(PreferenceKey.LOGGED_IN_USER_PASSWORD, password)
     }

     fun getUserName(): String? {
         return PreferenceUtil.getInstance()
             .getEncStringValue(PreferenceKey.LOGGED_IN_USER_NUMBER, "")
     }

     fun setUserName(phoneNumber: String?) {
         PreferenceUtil.getInstance()
             .setEncStringValue(PreferenceKey.LOGGED_IN_USER_NUMBER, phoneNumber)
     }*/

    fun getUserId(): Int? {
        return PreferenceUtil.getInstance()
            .getIntValue(PreferenceKey.LOGGED_IN_USER_ID, 0)
    }

    fun setUserId(userId: Int?) {
        PreferenceUtil.getInstance()
            .setIntValue(PreferenceKey.LOGGED_IN_USER_ID, userId!!)
    }

    fun getPin(): String? {
        return PreferenceUtil.getInstance()
            .getStringValue(PreferenceKey.LOGGED_IN_PIN_PAD, "")
    }

    fun setPin(pin: String?) {
        PreferenceUtil.getInstance()
            .setStringValue(PreferenceKey.LOGGED_IN_PIN_PAD, pin!!)
    }

    /*  fun getTokenId(): String? {
          return PreferenceUtil.getInstance()
              .getEncStringValue(PreferenceKey.LOGGED_IN_USER_TOKEN_ID, null)
      }*/
/*

    fun setTokenId(tokenId: String?) {
        PreferenceUtil.getInstance()
            .setEncStringValue(PreferenceKey.LOGGED_IN_USER_TOKEN_ID, tokenId)
    }
*/

    fun getSelectedGroupId(): String? {
        return PreferenceUtil.getInstance()
            .getStringValue(PreferenceKey.GROUP_ID, null)
    }

    fun setSelectedGroupId(groupId: String?) {
        PreferenceUtil.getInstance()
            .setStringValue(PreferenceKey.GROUP_ID, groupId)
    }

    fun saveDashboardList(dashboardResponse: DashboardResponse?) {
        val data = Types.newParameterizedType(DashboardResponse::class.java)
        val adapter: JsonAdapter<DashboardResponse> = moshi.adapter(data)
        PreferenceUtil.getInstance().setStringValue(DASHBOARD_ID, adapter.toJson(dashboardResponse))
    }

    fun getDashboardList(): DashboardResponse? {
        val data = Types.newParameterizedType(DashboardResponse::class.java)
        val adapter: JsonAdapter<DashboardResponse> = moshi.adapter(data)
        val stringData = PreferenceUtil.getInstance().getStringValue(DASHBOARD_ID, null)
        if (!TextUtils.isEmpty(stringData)) return adapter.fromJson(stringData)
        return null
    }

    fun saveComplaintList(complaintListResponse: ComplaintListResponse?) {
        val data = Types.newParameterizedType(ComplaintListResponse::class.java)
        val adapter: JsonAdapter<ComplaintListResponse> = moshi.adapter(data)
        PreferenceUtil.getInstance()
            .setStringValue(COMPLAINT_ID, adapter.toJson(complaintListResponse))
    }

    fun getComplaintList(): ComplaintListResponse? {
        val data = Types.newParameterizedType(ComplaintListResponse::class.java)
        val adapter: JsonAdapter<ComplaintListResponse> = moshi.adapter(data)
        val stringData = PreferenceUtil.getInstance().getStringValue(COMPLAINT_ID, null)
        if (!TextUtils.isEmpty(stringData)) return adapter.fromJson(stringData)
        return null
    }

    fun saveProfile(loginResponse: LoginResponse?) {
        val data = Types.newParameterizedType(LoginResponse::class.java)
        val adapter: JsonAdapter<LoginResponse> = moshi.adapter(data)
        PreferenceUtil.getInstance().setStringValue(LOGIN_DATA, adapter.toJson(loginResponse))
    }

    fun getProfile(): LoginResponse? {
        val data = Types.newParameterizedType(LoginResponse::class.java)
        val adapter: JsonAdapter<LoginResponse> = moshi.adapter(data)
        val stringData = PreferenceUtil.getInstance().getStringValue(LOGIN_DATA, null)
        if (!TextUtils.isEmpty(stringData)) return adapter.fromJson(stringData)
        return null
    }

}