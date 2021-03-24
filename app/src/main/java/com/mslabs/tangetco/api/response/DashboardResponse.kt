package com.mslabs.tangetco.api.response

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class DashboardResponse(
    @SerializedName("data")
    @Expose
    val dashboardList: List<DashboardList> = ArrayList(),
    @SerializedName("iserror")
    @Expose
    var Iserror: Int=0,
    var message: String = ""
) : Parcelable

@Parcelize
class DashboardList(
    @SerializedName("TotalCount")
    @Expose
    val TotalCount: Int = 0,
    @SerializedName("InProgress")
    @Expose
    val InProgress: Int = 0 ,
    @SerializedName("Complaintname")
    @Expose
    val Complaintname: String = "" ,
    @SerializedName("Pending")
    @Expose
    val Pending: Int = 0
) : Parcelable
