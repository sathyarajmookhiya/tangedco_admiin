package com.mslabs.sipcot.api.repository

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mslabs.tangetco.api.APIClient
import com.mslabs.tangetco.api.APIInterface
import com.mslabs.tangetco.api.request.LoginApi
import com.mslabs.tangetco.api.response.LoginResponse
import com.mslabs.tangetco.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


/**
 * Project           : Smarthome
 * File Name         : LoginApiRepository
 * Description       :
 * Revision History  : version 1
 * Date              : 13/07/20
 * Original author   : Kannappan
 * Description       : Initial version
 */
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class UserApiRepository : ApiRepository() {


    fun userLogin(loginApi: LoginApi?): MutableLiveData<LoginResponse>? {
        val mutableLiveData = MutableLiveData<LoginResponse>()
        GlobalScope.launch {
            val apiInterface = APIClient.client.create(
                APIInterface::class.java
            )


            val gson = GsonBuilder().create()
            val json = gson.toJson(loginApi) // obj is your object
            Log.d("Login Api $json")


            val call = apiInterface.login("application/json","application/json",loginApi)
            call.enqueue(getLoginResponseCallback(object : ResponseCallback {
                override fun responseData(responseData: LoginResponse?) {
                    if (responseData!!.iserror!=1) {
                        Log.d("Data Json : $responseData")
                        val loginResponse = Gson().fromJson(Gson().toJson(responseData), LoginResponse::class.java)

                        Log.d("Login Response : "+loginResponse.sectionId)



                    }
                }
            }, mutableLiveData))
        }
        return mutableLiveData
    }




}