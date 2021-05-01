package com.mslabs.tangetco.api.repository

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mslabs.sipcot.api.repository.ApiRepository
import com.mslabs.tangetco.api.APIClient
import com.mslabs.tangetco.api.APIInterface
import com.mslabs.tangetco.api.request.ComplaintRequestApi
import com.mslabs.tangetco.api.request.TakeActionRequestApi
import com.mslabs.tangetco.api.response.BaseResponse
import com.mslabs.tangetco.api.response.ComplaintListResponse
import com.mslabs.tangetco.util.Log
import com.mslabs.tangetco.util.preference.TangedcoPreferenceManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class TakeActionRepositary : ApiRepository() {
    fun sendTakeAction(takeActionRequestApi: TakeActionRequestApi?): MutableLiveData<BaseResponse>? {
        val mutableLiveData = MutableLiveData<BaseResponse>()
        GlobalScope.launch {
            val apiInterface = APIClient.client.create(
                APIInterface::class.java
            )
            val gson = GsonBuilder().create()
            val json = gson.toJson(takeActionRequestApi) // obj is your object
            Log.d("takeActionRequestApi $json")
            val call = apiInterface.takeAction("application/json", "application/json", takeActionRequestApi)
            call.enqueue(getResponseTakeActionCallback(object : ResponseTakeActionCallback {
                override fun responseTakeAction(responseData: BaseResponse?) {
                    if (responseData!!.Iserror != 1) {
                        val gson = GsonBuilder().create()
                        val json = gson.toJson(responseData) // obj is your object
                        Log.d("Data Json : $json")
                        val complaintListResponse =
                            Gson().fromJson(Gson().toJson(responseData), ComplaintListResponse::class.java)
                        TangedcoPreferenceManager.saveComplaintList(complaintListResponse)
                    }
                }
            }, mutableLiveData))
        }
        return mutableLiveData
    }

}