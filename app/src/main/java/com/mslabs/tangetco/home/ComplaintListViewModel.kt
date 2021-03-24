package com.mslabs.tangetco.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.mslabs.tangetco.api.APIManager
import com.mslabs.tangetco.api.request.ComplaintRequestApi
import com.mslabs.tangetco.api.request.TakeActionRequestApi
import com.mslabs.tangetco.api.response.BaseResponse
import com.mslabs.tangetco.api.response.ComplaintListResponse


class ComplaintListViewModel(application: Application) :
    AndroidViewModel(application) {
    var complaintName = MutableLiveData<String>()

    fun complaintList(complaintRequestApi: ComplaintRequestApi): MutableLiveData<ComplaintListResponse> {
        return APIManager.getInstance().complaintList(complaintRequestApi)
    }
    fun takeAction(takeActionRequestApi: TakeActionRequestApi): MutableLiveData<BaseResponse> {
        return APIManager.getInstance().takeAction(takeActionRequestApi)
    }

    companion object {
        private val TAG = ComplaintListViewModel::class.java.simpleName
    }

    fun setComplaintName(complaintName:String)
    {
        this.complaintName.value=complaintName
    }

}
