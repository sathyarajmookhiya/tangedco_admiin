package com.mslabs.tangetco.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mslabs.tangetco.api.APIManager
import com.mslabs.tangetco.api.request.*
import com.mslabs.tangetco.api.response.*

class MasterViewModel : ViewModel() {
    fun getRegion(): MutableLiveData<RegionList>? {
        return APIManager.getInstance().getRegion()
    }

    fun getCircle(fetchCircleInput: FetchCircleInput): MutableLiveData<CircleList>? {
        return APIManager.getInstance().getCircle(fetchCircleInput)
    }

    fun getDivision(fetchDivisionInput: FetchDivisionInput): MutableLiveData<DivisionList>? {
        return APIManager.getInstance().getDivision(fetchDivisionInput)
    }

    fun getSubDivision(fetchSubDivisionInput: FetchSubDivisionInput): MutableLiveData<SubDivisionList>? {
        return APIManager.getInstance().getSubDivision(fetchSubDivisionInput)
    }

    fun getSection(fetchSectionInput: FetchSectionInput): MutableLiveData<SectionsList>? {
        return APIManager.getInstance().getSection(fetchSectionInput)
    }

    fun transferAction(takeActionRequestApi: TakeActionRequestApi): MutableLiveData<BaseResponse> {
        return APIManager.getInstance().transferAction(takeActionRequestApi)
    }

    fun getReasons(fetchReasonsInput: FetchReasonsInput): MutableLiveData<ReasonList> {
        return APIManager.getInstance().getReasons(fetchReasonsInput)
    }
    fun takeAction(takeActionRequestApi: TakeActionRequestApi): MutableLiveData<BaseResponse> {
        return APIManager.getInstance().takeAction(takeActionRequestApi)
    }

}