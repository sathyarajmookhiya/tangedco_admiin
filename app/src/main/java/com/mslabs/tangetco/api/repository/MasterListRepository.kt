package com.mslabs.tangetco.api.repository

import androidx.lifecycle.MutableLiveData
import com.mslabs.sipcot.api.repository.ApiRepository
import com.mslabs.tangetco.api.APIClient
import com.mslabs.tangetco.api.APIInterface
import com.mslabs.tangetco.api.request.*
import com.mslabs.tangetco.api.response.*
import com.mslabs.tangetco.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MasterListRepository : ApiRepository() {
    fun getRegion(): MutableLiveData<RegionList>? {
        val mutableLiveData = MutableLiveData<RegionList>()
        GlobalScope.launch {
            val apiInterface = APIClient.client.create(
                APIInterface::class.java
            )
            val call = apiInterface.getRegion()
            call.enqueue(getResponseRegionCallback(object : ResponseRegionCallback {
                override fun responseRegionList(responseData: RegionList?) {
                    if (responseData!!.iserror != 1) {
                        Log.d("Data Json : $responseData")
                        /*   val complaintListResponse =
                               Gson().fromJson(Gson().toJson(responseData), ComplaintListResponse::class.java)
                           TangedcoPreferenceManager.saveComplaintList(complaintListResponse)*/
                    }
                }
            }, mutableLiveData))
        }
        return mutableLiveData
    }

    fun getCircle(fetchCircleInput: FetchCircleInput): MutableLiveData<CircleList>? {
        val mutableLiveData = MutableLiveData<CircleList>()
        GlobalScope.launch {
            val apiInterface = APIClient.client.create(
                APIInterface::class.java
            )
            val call =
                apiInterface.getCircle("application/json", "application/json", fetchCircleInput)
            call.enqueue(getResponseCircleCallback(object : ResponseCircleCallback {
                override fun responseCircleList(responseData: CircleList?) {
                    if (responseData!!.iserror != 1) {
                        Log.d("Data Json : $responseData")
                        /*   val complaintListResponse =
                               Gson().fromJson(Gson().toJson(responseData), ComplaintListResponse::class.java)
                           TangedcoPreferenceManager.saveComplaintList(complaintListResponse)*/
                    }
                }
            }, mutableLiveData))
        }
        return mutableLiveData
    }

    fun getDivision(fetchDivisionInput: FetchDivisionInput): MutableLiveData<DivisionList>? {
        val mutableLiveData = MutableLiveData<DivisionList>()
        GlobalScope.launch {
            val apiInterface = APIClient.client.create(
                APIInterface::class.java
            )
            val call =
                apiInterface.getDivision("application/json", "application/json", fetchDivisionInput)
            call.enqueue(getResponseDivisionCallback(object : ResponseDivisionCallback {
                override fun responseDivisionList(responseData: DivisionList?) {
                    if (responseData!!.iserror != 1) {
                        Log.d("Data Json : $responseData")
                        /*   val complaintListResponse =
                               Gson().fromJson(Gson().toJson(responseData), ComplaintListResponse::class.java)
                           TangedcoPreferenceManager.saveComplaintList(complaintListResponse)*/
                    }
                }
            }, mutableLiveData))
        }
        return mutableLiveData
    }

    fun getSubDivision(fetchSubDivisionInput: FetchSubDivisionInput): MutableLiveData<SubDivisionList>? {
        val mutableLiveData = MutableLiveData<SubDivisionList>()
        GlobalScope.launch {
            val apiInterface = APIClient.client.create(
                APIInterface::class.java
            )
            val call =
                apiInterface.getSubDivisions("application/json", "application/json", fetchSubDivisionInput)
            call.enqueue(getResponseSubDivisionCallback(object : ResponseSubDivisionCallback {
                override fun responseSubDivisionList(responseData: SubDivisionList?) {
                    if (responseData!!.iserror != 1) {
                        Log.d("Data Json : $responseData")
                        /*   val complaintListResponse =
                               Gson().fromJson(Gson().toJson(responseData), ComplaintListResponse::class.java)
                           TangedcoPreferenceManager.saveComplaintList(complaintListResponse)*/
                    }
                }
            }, mutableLiveData))
        }
        return mutableLiveData
    }

    fun getSection(fetchSectionInput: FetchSectionInput): MutableLiveData<SectionsList>? {
        val mutableLiveData = MutableLiveData<SectionsList>()
        GlobalScope.launch {
            val apiInterface = APIClient.client.create(
                APIInterface::class.java
            )
            val call =
                apiInterface.getSections("application/json", "application/json", fetchSectionInput)
            call.enqueue(getResponseSectionsCallback(object : ResponseSectionCallback {
                override fun responseSectionsList(responseData: SectionsList?) {
                    if (responseData!!.iserror != 1) {
                        Log.d("Data Json : $responseData")
                        /*   val complaintListResponse =
                               Gson().fromJson(Gson().toJson(responseData), ComplaintListResponse::class.java)
                           TangedcoPreferenceManager.saveComplaintList(complaintListResponse)*/
                    }
                }
            }, mutableLiveData))
        }
        return mutableLiveData
    }

    fun getReasons(fetchReasonsInput: FetchReasonsInput): MutableLiveData<ReasonList>? {
        val mutableLiveData = MutableLiveData<ReasonList>()
        GlobalScope.launch {
            val apiInterface = APIClient.client.create(
                APIInterface::class.java
            )
            val call =
                apiInterface.getReasons("application/json", "application/json", fetchReasonsInput)
            call.enqueue(getResponseReasonsCallback(object : ResponseReasonCallback {
                override fun responseReasonsList(responseData: ReasonList?) {
                    if (responseData!!.iserror != 1) {
                        Log.d("Data Json : $responseData")
                        /*   val complaintListResponse =
                               Gson().fromJson(Gson().toJson(responseData), ComplaintListResponse::class.java)
                           TangedcoPreferenceManager.saveComplaintList(complaintListResponse)*/
                    }
                }
            }, mutableLiveData))
        }
        return mutableLiveData
    }

}