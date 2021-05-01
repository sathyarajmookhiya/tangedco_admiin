package com.mslabs.tangetco.api.repository

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mslabs.sipcot.api.repository.ApiRepository
import com.mslabs.tangetco.api.APIClient
import com.mslabs.tangetco.api.APIInterface
import com.mslabs.tangetco.api.request.DashboardApi
import com.mslabs.tangetco.api.request.LoginApi
import com.mslabs.tangetco.api.response.DashboardResponse
import com.mslabs.tangetco.api.response.LoginResponse
import com.mslabs.tangetco.util.Log
import com.mslabs.tangetco.util.preference.TangedcoPreferenceManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class DashboardApiRepository : ApiRepository() {


    fun getDashboardList(dashboardApi: DashboardApi?): MutableLiveData<DashboardResponse>? {
        val mutableLiveData = MutableLiveData<DashboardResponse>()
        GlobalScope.launch {
            val apiInterface = APIClient.client.create(
                APIInterface::class.java
            )
            val gson = GsonBuilder().create()
            val json = gson.toJson(dashboardApi) // obj is your object
            Log.d("DashboardApi $json")
            val call = apiInterface.dashboardList("application/json", "application/json", dashboardApi)
            call.enqueue(getDashboardResponseCallback(object : ResponseDashboardCallback {
                override fun responseDashboardData(responseData: DashboardResponse?) {
                    if (responseData!!.Iserror != 1) {
                        val gson = GsonBuilder().create()
                        val json = gson.toJson(responseData) // obj is your object
                        Log.d("Data Json : $json")
                        val dashboardResponse =
                            Gson().fromJson(Gson().toJson(responseData), DashboardResponse::class.java)
                        TangedcoPreferenceManager.saveDashboardList(dashboardResponse)
                    }
                }
            }, mutableLiveData))
        }
        return mutableLiveData
    }

}