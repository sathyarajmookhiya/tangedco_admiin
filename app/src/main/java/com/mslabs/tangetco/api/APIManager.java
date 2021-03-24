package com.mslabs.tangetco.api;

import androidx.lifecycle.MutableLiveData;

import com.mslabs.sipcot.api.repository.UserApiRepository;
import com.mslabs.tangetco.BaseApplication;
import com.mslabs.tangetco.R;
import com.mslabs.tangetco.api.repository.ComplaintApiRepository;
import com.mslabs.tangetco.api.repository.DashboardApiRepository;
import com.mslabs.tangetco.api.repository.MasterListRepository;
import com.mslabs.tangetco.api.repository.TakeActionRepositary;
import com.mslabs.tangetco.api.repository.TransferActionRepository;
import com.mslabs.tangetco.api.request.ComplaintRequestApi;
import com.mslabs.tangetco.api.request.DashboardApi;
import com.mslabs.tangetco.api.request.FetchCircleInput;
import com.mslabs.tangetco.api.request.FetchDivisionInput;
import com.mslabs.tangetco.api.request.FetchReasonsInput;
import com.mslabs.tangetco.api.request.FetchSectionInput;
import com.mslabs.tangetco.api.request.FetchSubDivisionInput;
import com.mslabs.tangetco.api.request.LoginApi;
import com.mslabs.tangetco.api.request.TakeActionRequestApi;
import com.mslabs.tangetco.api.response.BaseResponse;
import com.mslabs.tangetco.api.response.CircleList;
import com.mslabs.tangetco.api.response.ComplaintListResponse;
import com.mslabs.tangetco.api.response.DashboardResponse;
import com.mslabs.tangetco.api.response.DivisionList;
import com.mslabs.tangetco.api.response.LoginResponse;
import com.mslabs.tangetco.api.response.ReasonList;
import com.mslabs.tangetco.api.response.RegionList;
import com.mslabs.tangetco.api.response.SectionsList;
import com.mslabs.tangetco.api.response.SubDivisionList;
import com.mslabs.tangetco.util.NetworkUtil;

import org.jetbrains.annotations.Nullable;

/**
 * Project           : smartplug
 * File Name         : APIManager
 * Description       :
 * Revision History  : version 1
 * Date              : 2019-09-08
 * Original author   : Kannappan
 * Description       : Initial version
 */
public class APIManager {
    private static final APIManager ourInstance = new APIManager();
    private static final String TAG = "APIManager";

    private APIManager() {
    }

    public static APIManager getInstance() {
        return ourInstance;
    }


    private boolean checkInternetConnected(MutableLiveData<LoginResponse> mutableLiveData) {
        if (!NetworkUtil.isConnectedAndInternetAvailable(BaseApplication.getApplication().getApplicationContext())) {
            LoginResponse responseData = new LoginResponse();
            responseData.setMessage(BaseApplication.getApplication().getString(R.string.mobile_phone_doesnot_have_an_internet_connectivity));
            responseData.setIserror(1);
            mutableLiveData.postValue(responseData);
            return false;
        }
        return true;
    }

    private boolean checkInternetConnectedRegion(MutableLiveData<RegionList> mutableLiveData) {
        if (!NetworkUtil.isConnectedAndInternetAvailable(BaseApplication.getApplication().getApplicationContext())) {
            RegionList responseData = new RegionList(null, 1, BaseApplication.getApplication().getString(R.string.mobile_phone_doesnot_have_an_internet_connectivity));
            mutableLiveData.postValue(responseData);
            return false;
        }
        return true;
    }

    private boolean checkInternetConnectedCircle(MutableLiveData<CircleList> mutableLiveData) {
        if (!NetworkUtil.isConnectedAndInternetAvailable(BaseApplication.getApplication().getApplicationContext())) {
            CircleList responseData = new CircleList(null, 1, BaseApplication.getApplication().getString(R.string.mobile_phone_doesnot_have_an_internet_connectivity));
            mutableLiveData.postValue(responseData);
            return false;
        }
        return true;
    }

    private boolean checkInternetConnectedDivision(MutableLiveData<DivisionList> mutableLiveData) {
        if (!NetworkUtil.isConnectedAndInternetAvailable(BaseApplication.getApplication().getApplicationContext())) {
            DivisionList responseData = new DivisionList(null, 1, BaseApplication.getApplication().getString(R.string.mobile_phone_doesnot_have_an_internet_connectivity));
            mutableLiveData.postValue(responseData);
            return false;
        }
        return true;
    }

    private boolean checkInternetConnectedSubDivision(MutableLiveData<SubDivisionList> mutableLiveData) {
        if (!NetworkUtil.isConnectedAndInternetAvailable(BaseApplication.getApplication().getApplicationContext())) {
            SubDivisionList responseData = new SubDivisionList(null, 1, BaseApplication.getApplication().getString(R.string.mobile_phone_doesnot_have_an_internet_connectivity));
            mutableLiveData.postValue(responseData);
            return false;
        }
        return true;
    }

    private boolean checkInternetConnectedSection(MutableLiveData<SectionsList> mutableLiveData) {
        if (!NetworkUtil.isConnectedAndInternetAvailable(BaseApplication.getApplication().getApplicationContext())) {
            SectionsList responseData = new SectionsList(null, 1, BaseApplication.getApplication().getString(R.string.mobile_phone_doesnot_have_an_internet_connectivity));
            mutableLiveData.postValue(responseData);
            return false;
        }
        return true;
    }
    private boolean checkInternetConnectedReason(MutableLiveData<ReasonList> mutableLiveData) {
        if (!NetworkUtil.isConnectedAndInternetAvailable(BaseApplication.getApplication().getApplicationContext())) {
            ReasonList responseData = new ReasonList(null, 1, BaseApplication.getApplication().getString(R.string.mobile_phone_doesnot_have_an_internet_connectivity));
            mutableLiveData.postValue(responseData);
            return false;
        }
        return true;
    }

    private boolean checkInternetTakeActionConnected(MutableLiveData<BaseResponse> mutableLiveData) {
        if (!NetworkUtil.isConnectedAndInternetAvailable(BaseApplication.getApplication().getApplicationContext())) {
            BaseResponse responseData = new BaseResponse();
            responseData.setMessage(BaseApplication.getApplication().getString(R.string.mobile_phone_doesnot_have_an_internet_connectivity));
            responseData.setIserror(1);
            mutableLiveData.postValue(responseData);
            return false;
        }
        return true;
    }

    private boolean checkDashboardInternetConnected(MutableLiveData<DashboardResponse> mutableLiveData) {
        if (!NetworkUtil.isConnectedAndInternetAvailable(BaseApplication.getApplication().getApplicationContext())) {
            DashboardResponse responseData = new DashboardResponse();
            responseData.setMessage(BaseApplication.getApplication().getString(R.string.mobile_phone_doesnot_have_an_internet_connectivity));
            responseData.setIserror(1);
            mutableLiveData.postValue(responseData);
            return false;
        }
        return true;
    }

    private boolean checkComplaintListInternetConnected(MutableLiveData<ComplaintListResponse> mutableLiveData) {
        if (!NetworkUtil.isConnectedAndInternetAvailable(BaseApplication.getApplication().getApplicationContext())) {
            ComplaintListResponse responseData = new ComplaintListResponse();
            responseData.setMessage(BaseApplication.getApplication().getString(R.string.mobile_phone_doesnot_have_an_internet_connectivity));
            responseData.setIserror(1);
            mutableLiveData.postValue(responseData);
            return false;
        }
        return true;
    }


    @Nullable
    private MutableLiveData<LoginResponse> getResponseLoginDataMutableLiveData() {
        final MutableLiveData<LoginResponse> mutableLiveData = new MutableLiveData<>();
        if (!checkInternetConnected(mutableLiveData)) {
            return mutableLiveData;
        }
        return null;
    }


    @Nullable
    private MutableLiveData<RegionList> getResponseRegionDataMutableLiveData() {
        final MutableLiveData<RegionList> mutableLiveData = new MutableLiveData<>();
        if (!checkInternetConnectedRegion(mutableLiveData)) {
            return mutableLiveData;
        }
        return null;
    }

    @Nullable
    private MutableLiveData<CircleList> getResponseCircleDataMutableLiveData() {
        final MutableLiveData<CircleList> mutableLiveData = new MutableLiveData<>();
        if (!checkInternetConnectedCircle(mutableLiveData)) {
            return mutableLiveData;
        }
        return null;
    }

    @Nullable
    private MutableLiveData<DivisionList> getResponseDivisionDataMutableLiveData() {
        final MutableLiveData<DivisionList> mutableLiveData = new MutableLiveData<>();
        if (!checkInternetConnectedDivision(mutableLiveData)) {
            return mutableLiveData;
        }
        return null;
    }

    @Nullable
    private MutableLiveData<SubDivisionList> getResponseSubDivisionDataMutableLiveData() {
        final MutableLiveData<SubDivisionList> mutableLiveData = new MutableLiveData<>();
        if (!checkInternetConnectedSubDivision(mutableLiveData)) {
            return mutableLiveData;
        }
        return null;
    }

    @Nullable
    private MutableLiveData<SectionsList> getResponseSectionDataMutableLiveData() {
        final MutableLiveData<SectionsList> mutableLiveData = new MutableLiveData<>();
        if (!checkInternetConnectedSection(mutableLiveData)) {
            return mutableLiveData;
        }
        return null;
    }
 @Nullable
    private MutableLiveData<ReasonList> getResponseReasonDataMutableLiveData() {
        final MutableLiveData<ReasonList> mutableLiveData = new MutableLiveData<>();
        if (!checkInternetConnectedReason(mutableLiveData)) {
            return mutableLiveData;
        }
        return null;
    }

    @Nullable
    private MutableLiveData<BaseResponse> getTakeActionMutableLiveData() {
        final MutableLiveData<BaseResponse> mutableLiveData = new MutableLiveData<>();
        if (!checkInternetTakeActionConnected(mutableLiveData)) {
            return mutableLiveData;
        }
        return null;
    }


    @Nullable
    private MutableLiveData<DashboardResponse> getResponseDashboardDataMutableLiveData() {
        final MutableLiveData<DashboardResponse> mutableLiveData = new MutableLiveData<>();
        if (!checkDashboardInternetConnected(mutableLiveData)) {
            return mutableLiveData;
        }
        return null;
    }

    @Nullable
    private MutableLiveData<ComplaintListResponse> getResponseComplaintListMutableLiveData() {
        final MutableLiveData<ComplaintListResponse> mutableLiveData = new MutableLiveData<>();
        if (!checkComplaintListInternetConnected(mutableLiveData)) {
            return mutableLiveData;
        }
        return null;
    }

    public MutableLiveData<LoginResponse> userLogin(final LoginApi loginApi) {

        final MutableLiveData<LoginResponse> mutableLiveData = getResponseLoginDataMutableLiveData();
        if (mutableLiveData != null) return mutableLiveData;
        UserApiRepository apiRepository = new UserApiRepository();
        return apiRepository.userLogin(loginApi);
    }

    public MutableLiveData<DashboardResponse> dashboardList(final DashboardApi dashboardApi) {
        final MutableLiveData<DashboardResponse> mutableLiveData = getResponseDashboardDataMutableLiveData();
        if (mutableLiveData != null) return mutableLiveData;
        DashboardApiRepository apiRepository = new DashboardApiRepository();
        return apiRepository.getDashboardList(dashboardApi);
    }

    public MutableLiveData<ComplaintListResponse> complaintList(final ComplaintRequestApi complaintRequestApi) {
        final MutableLiveData<ComplaintListResponse> mutableLiveData = getResponseComplaintListMutableLiveData();
        if (mutableLiveData != null) return mutableLiveData;
        ComplaintApiRepository apiRepository = new ComplaintApiRepository();
        return apiRepository.getComplaintList(complaintRequestApi);
    }

    public MutableLiveData<BaseResponse> takeAction(final TakeActionRequestApi takeActionRequestApi) {
        final MutableLiveData<BaseResponse> mutableLiveData = getTakeActionMutableLiveData();
        if (mutableLiveData != null) return mutableLiveData;
        TakeActionRepositary apiRepository = new TakeActionRepositary();
        return apiRepository.sendTakeAction(takeActionRequestApi);
    }


    public MutableLiveData<BaseResponse> transferAction(final TakeActionRequestApi takeActionRequestApi) {
        final MutableLiveData<BaseResponse> mutableLiveData = getTakeActionMutableLiveData();
        if (mutableLiveData != null) return mutableLiveData;
        TransferActionRepository apiRepository = new TransferActionRepository();
        return apiRepository.sendTransferAction(takeActionRequestApi);
    }

    public MutableLiveData<RegionList> getRegion() {
        final MutableLiveData<RegionList> mutableLiveData = getResponseRegionDataMutableLiveData();
        if (mutableLiveData != null) return mutableLiveData;
        MasterListRepository apiRepository = new MasterListRepository();
        return apiRepository.getRegion();
    }

    public MutableLiveData<CircleList> getCircle(FetchCircleInput fetchCircleInput) {
        final MutableLiveData<CircleList> mutableLiveData = getResponseCircleDataMutableLiveData();
        if (mutableLiveData != null) return mutableLiveData;
        MasterListRepository apiRepository = new MasterListRepository();
        return apiRepository.getCircle(fetchCircleInput);
    }

    public MutableLiveData<DivisionList> getDivision(FetchDivisionInput fetchDivisionInput) {
        final MutableLiveData<DivisionList> mutableLiveData = getResponseDivisionDataMutableLiveData();
        if (mutableLiveData != null) return mutableLiveData;
        MasterListRepository apiRepository = new MasterListRepository();
        return apiRepository.getDivision(fetchDivisionInput);
    }

    public MutableLiveData<SubDivisionList> getSubDivision(FetchSubDivisionInput fetchDivisionInput) {
        final MutableLiveData<SubDivisionList> mutableLiveData = getResponseSubDivisionDataMutableLiveData();
        if (mutableLiveData != null) return mutableLiveData;
        MasterListRepository apiRepository = new MasterListRepository();
        return apiRepository.getSubDivision(fetchDivisionInput);
    }

    public MutableLiveData<SectionsList> getSection(FetchSectionInput fetchSectionInput) {
        final MutableLiveData<SectionsList> mutableLiveData = getResponseSectionDataMutableLiveData();
        if (mutableLiveData != null) return mutableLiveData;
        MasterListRepository apiRepository = new MasterListRepository();
        return apiRepository.getSection(fetchSectionInput);
    }

    public MutableLiveData<ReasonList> getReasons(FetchReasonsInput fetchReasonsInput) {
        final MutableLiveData<ReasonList> mutableLiveData = getResponseReasonDataMutableLiveData();
        if (mutableLiveData != null) return mutableLiveData;
        MasterListRepository apiRepository = new MasterListRepository();
        return apiRepository.getReasons(fetchReasonsInput);
    }

}
