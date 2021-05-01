package com.mslabs.tangetco.api;


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
import com.mslabs.tangetco.api.response.ListDataResponse;
import com.mslabs.tangetco.api.response.LoginResponse;
import com.mslabs.tangetco.api.response.ReasonList;
import com.mslabs.tangetco.api.response.RegionList;
import com.mslabs.tangetco.api.response.SectionsList;
import com.mslabs.tangetco.api.response.SubDivisionList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Project           : TangedcoComplaint
 * File Name         : APIInterface
 * Description       :
 * Revision History  : version 1
 * Date              : 2019-09-06
 * Original author   : Kannappan
 * Description       : Initial version
 */
public interface APIInterface {

    @POST("login/post")
    Call<LoginResponse> login(@Header("Content-Type") String contentType, @Header("Accept") String accept, @Body LoginApi api);

    @POST("dashboard/post")
    Call<DashboardResponse> dashboardList(@Header("Content-Type") String contentType, @Header("Accept") String accept, @Body DashboardApi api);

    @POST("complaint_list/post")
    Call<ComplaintListResponse> complaintList(@Header("Content-Type") String contentType, @Header("Accept") String accept, @Body ComplaintRequestApi api);

    @POST("take_action/post")
    Call<BaseResponse> takeAction(@Header("Content-Type") String contentType, @Header("Accept") String accept, @Body TakeActionRequestApi api);

    @POST("transfer_action/post")
    Call<BaseResponse> transferAction(@Header("Content-Type") String contentType, @Header("Accept") String accept, @Body TakeActionRequestApi api);

    @GET("regions/get")
    Call<RegionList> getRegion();

    @POST("circles/post")
    Call<CircleList> getCircle(@Header("Content-Type") String contentType , @Header("Accept") String accept , @Body  FetchCircleInput fetchCircleInput);

    @POST("divisions/post")
    Call<DivisionList> getDivision(@Header("Content-Type") String contentType , @Header("Accept") String accept , @Body FetchDivisionInput fetchDivisionInput);

    @POST("subDivisions/post")
    Call<SubDivisionList> getSubDivisions(@Header("Content-Type") String contentType , @Header("Accept") String accept , @Body FetchSubDivisionInput fetchSubDivisionInput);

    @POST("sections/post")
    Call<SectionsList> getSections(@Header("Content-Type") String contentType , @Header("Accept") String accept , @Body FetchSectionInput fetchSectionInput);

  @POST("reasons/post")
    Call<ReasonList> getReasons(@Header("Content-Type") String contentType , @Header("Accept") String accept , @Body FetchReasonsInput fetchReasonsInput);

}
