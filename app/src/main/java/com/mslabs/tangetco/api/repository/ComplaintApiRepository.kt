package com.mslabs.tangetco.api.repository

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.mslabs.sipcot.api.repository.ApiRepository
import com.mslabs.tangetco.api.APIClient
import com.mslabs.tangetco.api.APIInterface
import com.mslabs.tangetco.api.request.ComplaintRequestApi
import com.mslabs.tangetco.api.request.DashboardApi
import com.mslabs.tangetco.api.response.ComplaintListResponse
import com.mslabs.tangetco.api.response.DashboardResponse
import com.mslabs.tangetco.util.Log
import com.mslabs.tangetco.util.preference.TangedcoPreferenceManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class ComplaintApiRepository : ApiRepository() {
    fun getComplaintList(complaintRequestApi: ComplaintRequestApi?): MutableLiveData<ComplaintListResponse>? {
        val mutableLiveData = MutableLiveData<ComplaintListResponse>()
        GlobalScope.launch {
            val apiInterface = APIClient.client.create(
                APIInterface::class.java
            )
            val call = apiInterface.complaintList("application/json", "application/json", complaintRequestApi)
            call.enqueue(getResponseComplaintListCallback(object : ResponseComplaintListCallback {
                override fun responseComplaintList(responseData: ComplaintListResponse?) {
                    if (responseData!!.Iserror != 1) {
                        Log.d("Data Json : $responseData")
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