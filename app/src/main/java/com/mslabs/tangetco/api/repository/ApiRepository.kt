package com.mslabs.sipcot.api.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.mslabs.tangetco.BaseApplication
import com.mslabs.tangetco.R
import com.mslabs.tangetco.api.APIConstant
import com.mslabs.tangetco.api.response.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

/**
 * Project           : Smarthome
 * File Name         : ApiRepository
 * Description       :
 * Revision History  : version 1
 * Date              : 13/07/20
 * Original author   : Kannappan
 * Description       : Initial version
 */
open class ApiRepository {

    private val TAG: String = "ApiRepository"


    fun getLoginResponseCallback(
        responseDataCallback: ResponseCallback?,
        mutableLiveData: MutableLiveData<LoginResponse>
    ): Callback<LoginResponse?>? {
        return object : Callback<LoginResponse?> {
            override fun onResponse(
                call: Call<LoginResponse?>,
                response: Response<LoginResponse?>
            ) {
                if (response.code() == APIConstant.API_STATUS_CODE) {
                    val responseData = response.body()
                    if (responseData != null && responseData.iserror == 0) {
                        val json = Gson().toJson(responseData)
                        Log.d(TAG, "API RESPONSE DATA: $json")
                        mutableLiveData.postValue(responseData)
                        responseDataCallback?.responseData(responseData)
                    } else if (responseData!!.iserror == 1) {
                        mutableLiveData.postValue(responseData)
                    }
                } else if (response.code() == APIConstant.API_STATUS_ERROR || response.code() == APIConstant.API_STATUS_INAVLIID) {
                    Log.d(TAG, "API RESPONSE DATA CODE: $response.code()")

                    val responseData = LoginResponse()
                    responseData.message = BaseApplication.getApplication()
                        .getString(R.string.server_error)
                    responseData.iserror = 1
                    mutableLiveData.postValue(responseData)
                } else {
                    val gson = Gson()
                    if (response.errorBody() != null) {
                        try {
                            val responseError = response.errorBody()!!.string()
                            val responseData = gson.fromJson(
                                responseError,
                                LoginResponse::class.java
                            )
                            mutableLiveData.postValue(responseData)
                            Log.e(
                                TAG,
                                "API response failed Error body: $responseError"
                            )
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }
            }

            override fun onFailure(
                call: Call<LoginResponse?>,
                t: Throwable
            ) {
                Log.e(TAG, "Unable to submit post to API." + t.message)
                val responseData = LoginResponse()
                responseData.message = "The server is not responding. Please try again later."
                //                responseData.setStatusMessage("Please check your internet connectivity and try again later.");
                mutableLiveData.postValue(responseData)
            }
        }
    }


    interface ResponseCallback {
        fun responseData(responseData: LoginResponse?)
    }


    fun getDashboardResponseCallback(
        responseDashboardCallback: ResponseDashboardCallback?,
        mutableLiveData: MutableLiveData<DashboardResponse>
    ): Callback<DashboardResponse?>? {
        return object : Callback<DashboardResponse?> {
            override fun onResponse(
                call: Call<DashboardResponse?>,
                response: Response<DashboardResponse?>
            ) {
                if (response.code() == APIConstant.API_STATUS_CODE) {
                    val responseData = response.body()
                    if (responseData != null && responseData.Iserror == 0) {
                        val json = Gson().toJson(responseData)
                        Log.d(TAG, "API RESPONSE DATA: $json")
                        mutableLiveData.postValue(responseData)
                        responseDashboardCallback?.responseDashboardData(responseData)
                    } else if (responseData!!.Iserror == 1) {
                        mutableLiveData.postValue(responseData)
                    }
                } else if (response.code() == APIConstant.API_STATUS_ERROR || response.code() == APIConstant.API_STATUS_INAVLIID) {
                    Log.d(TAG, "API RESPONSE DATA CODE: $response.code()")

                    val responseData = DashboardResponse()
                    responseData.message = BaseApplication.getApplication()
                        .getString(R.string.server_error)
                    responseData.Iserror = 1
                    mutableLiveData.postValue(responseData)
                } else {
                    val gson = Gson()
                    if (response.errorBody() != null) {
                        try {
                            val responseError = response.errorBody()!!.string()
                            val responseData = gson.fromJson(
                                responseError,
                                DashboardResponse::class.java
                            )
                            mutableLiveData.postValue(responseData)
                            Log.e(
                                TAG,
                                "API response failed Error body: $responseError"
                            )
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }
            }

            override fun onFailure(
                call: Call<DashboardResponse?>,
                t: Throwable
            ) {
                Log.e(TAG, "Unable to submit post to API." + t.message)
                val responseData = DashboardResponse()
                responseData.message = "The server is not responding. Please try again later."
                //                responseData.setStatusMessage("Please check your internet connectivity and try again later.");
                mutableLiveData.postValue(responseData)
            }
        }
    }


    interface ResponseDashboardCallback {
        fun responseDashboardData(responseData: DashboardResponse?)
    }

    fun getResponseComplaintListCallback(
        responseComplaintListCallback: ResponseComplaintListCallback?,
        mutableLiveData: MutableLiveData<ComplaintListResponse>
    ): Callback<ComplaintListResponse?>? {
        return object : Callback<ComplaintListResponse?> {
            override fun onResponse(
                call: Call<ComplaintListResponse?>,
                response: Response<ComplaintListResponse?>
            ) {
                if (response.code() == APIConstant.API_STATUS_CODE) {
                    val responseData = response.body()
                    if (responseData != null && responseData.Iserror == 0) {
                        val json = Gson().toJson(responseData)
                        Log.d(TAG, "API RESPONSE DATA: $json")
                        mutableLiveData.postValue(responseData)
                        responseComplaintListCallback?.responseComplaintList(responseData)
                    } else if (responseData!!.Iserror == 1) {
                        mutableLiveData.postValue(responseData)
                    }
                } else if (response.code() == APIConstant.API_STATUS_ERROR || response.code() == APIConstant.API_STATUS_INAVLIID) {
                    Log.d(TAG, "API RESPONSE DATA CODE: $response.code()")

                    val responseData = ComplaintListResponse()
                    responseData.message = BaseApplication.getApplication()
                        .getString(R.string.server_error)
                    responseData.Iserror = 1
                    mutableLiveData.postValue(responseData)
                } else {
                    val gson = Gson()
                    if (response.errorBody() != null) {
                        try {
                            val responseError = response.errorBody()!!.string()
                            val responseData = gson.fromJson(
                                responseError,
                                ComplaintListResponse::class.java
                            )
                            mutableLiveData.postValue(responseData)
                            Log.e(
                                TAG,
                                "API response failed Error body: $responseError"
                            )
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }
            }

            override fun onFailure(
                call: Call<ComplaintListResponse?>,
                t: Throwable
            ) {
                Log.e(TAG, "Unable to submit post to API." + t.message)
                val responseData = ComplaintListResponse()
                responseData.message = "The server is not responding. Please try again later."
                //                responseData.setStatusMessage("Please check your internet connectivity and try again later.");
                mutableLiveData.postValue(responseData)
            }
        }
    }

    fun getResponseTakeActionCallback(
        responseTakeActionCallback: ResponseTakeActionCallback?,
        mutableLiveData: MutableLiveData<BaseResponse>
    ): Callback<BaseResponse?>? {
        return object : Callback<BaseResponse?> {
            override fun onResponse(
                call: Call<BaseResponse?>,
                response: Response<BaseResponse?>
            ) {
                if (response.code() == APIConstant.API_STATUS_CODE) {
                    val responseData = response.body()
                    if (responseData != null && responseData.Iserror == 0) {
                        val json = Gson().toJson(responseData)
                        Log.d(TAG, "API RESPONSE DATA: $json")
                        mutableLiveData.postValue(responseData)
                        responseTakeActionCallback?.responseTakeAction(responseData)
                    } else if (responseData!!.Iserror == 1) {
                        mutableLiveData.postValue(responseData)
                    }
                } else if (response.code() == APIConstant.API_STATUS_ERROR || response.code() == APIConstant.API_STATUS_INAVLIID) {
                    Log.d(TAG, "API RESPONSE DATA CODE: $response.code()")

                    val responseData = BaseResponse()
                    responseData.message = BaseApplication.getApplication()
                        .getString(R.string.server_error)
                    responseData.Iserror = 1
                    mutableLiveData.postValue(responseData)
                } else {
                    val gson = Gson()
                    if (response.errorBody() != null) {
                        try {
                            val responseError = response.errorBody()!!.string()
                            val responseData = gson.fromJson(
                                responseError,
                                BaseResponse::class.java
                            )
                            mutableLiveData.postValue(responseData)
                            Log.e(
                                TAG,
                                "API response failed Error body: $responseError"
                            )
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }
            }

            override fun onFailure(
                call: Call<BaseResponse?>,
                t: Throwable
            ) {
                Log.e(TAG, "Unable to submit post to API." + t.message)
                val responseData = BaseResponse()
                responseData.message = "The server is not responding. Please try again later."
                //                responseData.setStatusMessage("Please check your internet connectivity and try again later.");
                mutableLiveData.postValue(responseData)
            }
        }
    }

    fun getResponseRegionCallback(
        responseRegionCallback: ResponseRegionCallback?,
        mutableLiveData: MutableLiveData<RegionList>
    ): Callback<RegionList?>? {
        return object : Callback<RegionList?> {
            override fun onResponse(
                call: Call<RegionList?>,
                response: Response<RegionList?>
            ) {
                if (response.code() == APIConstant.API_STATUS_CODE) {
                    val responseData = response.body()
                    if (responseData != null && responseData.iserror == 0) {
                        val json = Gson().toJson(responseData)
                        Log.d(TAG, "API RESPONSE DATA: $json")
                        mutableLiveData.postValue(responseData)
                        responseRegionCallback?.responseRegionList(responseData)
                    } else if (responseData!!.iserror == 1) {
                        mutableLiveData.postValue(responseData)
                    }
                } else if (response.code() == APIConstant.API_STATUS_ERROR || response.code() == APIConstant.API_STATUS_INAVLIID) {
                    Log.d(TAG, "API RESPONSE DATA CODE: $response.code()")

                    val responseData = RegionList(
                        null, 1, BaseApplication.getApplication()
                            .getString(R.string.server_error)
                    )

                    mutableLiveData.postValue(responseData)
                } else {
                    val gson = Gson()
                    if (response.errorBody() != null) {
                        try {
                            val responseError = response.errorBody()!!.string()
                            val responseData = gson.fromJson(
                                responseError,
                                RegionList::class.java
                            )
                            mutableLiveData.postValue(responseData)
                            Log.e(
                                TAG,
                                "API response failed Error body: $responseError"
                            )
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }
            }

            override fun onFailure(
                call: Call<RegionList?>,
                t: Throwable
            ) {
                Log.e(TAG, "Unable to submit post to API." + t.message)
                val responseData = RegionList(null,1,"The server is not responding. Please try again later.")
                mutableLiveData.postValue(responseData)
            }
        }
    }

    fun getResponseCircleCallback(
        responseCircleCallback: ResponseCircleCallback?,
        mutableLiveData: MutableLiveData<CircleList>
    ): Callback<CircleList?>? {
        return object : Callback<CircleList?> {
            override fun onResponse(
                call: Call<CircleList?>,
                response: Response<CircleList?>
            ) {
                if (response.code() == APIConstant.API_STATUS_CODE) {
                    val responseData = response.body()
                    if (responseData != null && responseData.iserror == 0) {
                        val json = Gson().toJson(responseData)
                        Log.d(TAG, "API RESPONSE DATA: $json")
                        mutableLiveData.postValue(responseData)
                        responseCircleCallback?.responseCircleList(responseData)
                    } else if (responseData!!.iserror == 1) {
                        mutableLiveData.postValue(responseData)
                    }
                } else if (response.code() == APIConstant.API_STATUS_ERROR || response.code() == APIConstant.API_STATUS_INAVLIID) {
                    Log.d(TAG, "API RESPONSE DATA CODE: $response.code()")

                    val responseData = CircleList(
                        null, 1, BaseApplication.getApplication()
                            .getString(R.string.server_error)
                    )

                    mutableLiveData.postValue(responseData)
                } else {
                    val gson = Gson()
                    if (response.errorBody() != null) {
                        try {
                            val responseError = response.errorBody()!!.string()
                            val responseData = gson.fromJson(
                                responseError,
                                CircleList::class.java
                            )
                            mutableLiveData.postValue(responseData)
                            Log.e(
                                TAG,
                                "API response failed Error body: $responseError"
                            )
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }
            }

            override fun onFailure(
                call: Call<CircleList?>,
                t: Throwable
            ) {
                Log.e(TAG, "Unable to submit post to API." + t.message)
                val responseData = CircleList(null,1,"The server is not responding. Please try again later.")
                mutableLiveData.postValue(responseData)
            }
        }
    }


    fun getResponseDivisionCallback(
        responseDivisionCallback: ResponseDivisionCallback?,
        mutableLiveData: MutableLiveData<DivisionList>
    ): Callback<DivisionList?>? {
        return object : Callback<DivisionList?> {
            override fun onResponse(
                call: Call<DivisionList?>,
                response: Response<DivisionList?>
            ) {
                if (response.code() == APIConstant.API_STATUS_CODE) {
                    val responseData = response.body()
                    if (responseData != null && responseData.iserror == 0) {
                        val json = Gson().toJson(responseData)
                        Log.d(TAG, "API RESPONSE DATA: $json")
                        mutableLiveData.postValue(responseData)
                        responseDivisionCallback?.responseDivisionList(responseData)
                    } else if (responseData!!.iserror == 1) {
                        mutableLiveData.postValue(responseData)
                    }
                }
                else if (response.code() == APIConstant.API_STATUS_ERROR || response.code() == APIConstant.API_STATUS_INAVLIID) {
                    Log.d(TAG, "API RESPONSE DATA CODE: $response.code()")

                    val responseData = DivisionList(
                        null, 1, BaseApplication.getApplication()
                            .getString(R.string.server_error)
                    )

                    mutableLiveData.postValue(responseData)
                } else {
                    val gson = Gson()
                    if (response.errorBody() != null) {
                        try {
                            val responseError = response.errorBody()!!.string()
                            val responseData = gson.fromJson(
                                responseError,
                                DivisionList::class.java
                            )
                            mutableLiveData.postValue(responseData)
                            Log.e(
                                TAG,
                                "API response failed Error body: $responseError"
                            )
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }
            }

            override fun onFailure(
                call: Call<DivisionList?>,
                t: Throwable
            ) {
                Log.e(TAG, "Unable to submit post to API." + t.message)
                val responseData = DivisionList(null,1,"The server is not responding. Please try again later.")
                mutableLiveData.postValue(responseData)
            }
        }
    }
    fun getResponseSubDivisionCallback(
        responseSubDivisionCallback: ResponseSubDivisionCallback?,
        mutableLiveData: MutableLiveData<SubDivisionList>
    ): Callback<SubDivisionList?>? {
        return object : Callback<SubDivisionList?> {
            override fun onResponse(
                call: Call<SubDivisionList?>,
                response: Response<SubDivisionList?>
            ) {
                if (response.code() == APIConstant.API_STATUS_CODE) {
                    val responseData = response.body()
                    if (responseData != null && responseData.iserror == 0) {
                        val json = Gson().toJson(responseData)
                        Log.d(TAG, "API RESPONSE DATA: $json")
                        mutableLiveData.postValue(responseData)
                        responseSubDivisionCallback?.responseSubDivisionList(responseData)
                    } else if (responseData!!.iserror == 1) {
                        mutableLiveData.postValue(responseData)
                    }
                } else if (response.code() == APIConstant.API_STATUS_ERROR || response.code() == APIConstant.API_STATUS_INAVLIID) {
                    Log.d(TAG, "API RESPONSE DATA CODE: $response.code()")

                    val responseData = SubDivisionList(
                        null, 1, BaseApplication.getApplication()
                            .getString(R.string.server_error)
                    )

                    mutableLiveData.postValue(responseData)
                } else {
                    val gson = Gson()
                    if (response.errorBody() != null) {
                        try {
                            val responseError = response.errorBody()!!.string()
                            val responseData = gson.fromJson(
                                responseError,
                                SubDivisionList::class.java
                            )
                            mutableLiveData.postValue(responseData)
                            Log.e(
                                TAG,
                                "API response failed Error body: $responseError"
                            )
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }
            }

            override fun onFailure(
                call: Call<SubDivisionList?>,
                t: Throwable
            ) {
                Log.e(TAG, "Unable to submit post to API." + t.message)
                val responseData = SubDivisionList(null,1,"The server is not responding. Please try again later.")
                mutableLiveData.postValue(responseData)
            }
        }
    }

  fun getResponseSectionsCallback(
      responseSectionCallback: ResponseSectionCallback?,
        mutableLiveData: MutableLiveData<SectionsList>
    ): Callback<SectionsList?>? {
        return object : Callback<SectionsList?> {
            override fun onResponse(
                call: Call<SectionsList?>,
                response: Response<SectionsList?>
            ) {
                if (response.code() == APIConstant.API_STATUS_CODE) {
                    val responseData = response.body()
                    if (responseData != null && responseData.iserror == 0) {
                        val json = Gson().toJson(responseData)
                        Log.d(TAG, "API RESPONSE DATA: $json")
                        mutableLiveData.postValue(responseData)
                        responseSectionCallback?.responseSectionsList(responseData)
                    } else if (responseData!!.iserror == 1) {
                        mutableLiveData.postValue(responseData)
                    }
                } else if (response.code() == APIConstant.API_STATUS_ERROR || response.code() == APIConstant.API_STATUS_INAVLIID) {
                    Log.d(TAG, "API RESPONSE DATA CODE: $response.code()")

                    val responseData = SectionsList(
                        null, 1, BaseApplication.getApplication()
                            .getString(R.string.server_error)
                    )

                    mutableLiveData.postValue(responseData)
                } else {
                    val gson = Gson()
                    if (response.errorBody() != null) {
                        try {
                            val responseError = response.errorBody()!!.string()
                            val responseData = gson.fromJson(
                                responseError,
                                SectionsList::class.java
                            )
                            mutableLiveData.postValue(responseData)
                            Log.e(
                                TAG,
                                "API response failed Error body: $responseError"
                            )
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }
            }

            override fun onFailure(
                call: Call<SectionsList?>,
                t: Throwable
            ) {
                Log.e(TAG, "Unable to submit post to API." + t.message)
                val responseData = SectionsList(null,1,"The server is not responding. Please try again later.")
                mutableLiveData.postValue(responseData)
            }
        }
    }
 fun getResponseReasonsCallback(
     responseReasonCallback: ResponseReasonCallback?,
        mutableLiveData: MutableLiveData<ReasonList>
    ): Callback<ReasonList?>? {
        return object : Callback<ReasonList?> {
            override fun onResponse(
                call: Call<ReasonList?>,
                response: Response<ReasonList?>
            ) {
                if (response.code() == APIConstant.API_STATUS_CODE) {
                    val responseData = response.body()
                    if (responseData != null && responseData.iserror == 0) {
                        val json = Gson().toJson(responseData)
                        Log.d(TAG, "API RESPONSE DATA: $json")
                        mutableLiveData.postValue(responseData)
                        responseReasonCallback?.responseReasonsList(responseData)
                    } else if (responseData!!.iserror == 1) {
                        mutableLiveData.postValue(responseData)
                    }
                } else if (response.code() == APIConstant.API_STATUS_ERROR || response.code() == APIConstant.API_STATUS_INAVLIID) {
                    Log.d(TAG, "API RESPONSE DATA CODE: $response.code()")

                    val responseData = ReasonList(
                        null, 1, BaseApplication.getApplication()
                            .getString(R.string.server_error)
                    )

                    mutableLiveData.postValue(responseData)
                } else {
                    val gson = Gson()
                    if (response.errorBody() != null) {
                        try {
                            val responseError = response.errorBody()!!.string()
                            val responseData = gson.fromJson(
                                responseError,
                                ReasonList::class.java
                            )
                            mutableLiveData.postValue(responseData)
                            Log.e(
                                TAG,
                                "API response failed Error body: $responseError"
                            )
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }
            }

            override fun onFailure(
                call: Call<ReasonList?>,
                t: Throwable
            ) {
                Log.e(TAG, "Unable to submit post to API." + t.message)
                val responseData = ReasonList(null,1,"The server is not responding. Please try again later.")
                mutableLiveData.postValue(responseData)
            }
        }
    }


    interface ResponseComplaintListCallback {
        fun responseComplaintList(responseData: ComplaintListResponse?)
    }

    interface ResponseTakeActionCallback {
        fun responseTakeAction(responseData: BaseResponse?)
    }

    interface ResponseRegionCallback {
        fun responseRegionList(responseData: RegionList?)
    }
    interface ResponseCircleCallback {
        fun responseCircleList(responseData: CircleList?)
    }
    interface ResponseDivisionCallback {
        fun responseDivisionList(responseData: DivisionList?)
    }
    interface ResponseSubDivisionCallback {
        fun responseSubDivisionList(responseData: SubDivisionList?)
    }
    interface ResponseSectionCallback {
        fun responseSectionsList(responseData: SectionsList?)
    }
    interface ResponseReasonCallback {
        fun responseReasonsList(responseData: ReasonList?)
    }
}