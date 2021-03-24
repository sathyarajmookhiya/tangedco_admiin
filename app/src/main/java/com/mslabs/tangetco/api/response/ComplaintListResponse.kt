package com.mslabs.tangetco.api.response

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class ComplaintListResponse(
    @SerializedName("complaint_list")
    @Expose
    val complaintList: List<ComplaintList> = ArrayList()
    , @SerializedName("iserror")
    @Expose
    var Iserror: Int = 0, var message :String="") : Parcelable

@Parcelize
class ComplaintList(
    @SerializedName("src")
    @Expose
    val src: String? = null,
    @SerializedName("status_name")
    @Expose
    val statusName: String? = null,
    @SerializedName("description")
    @Expose
    val description: String? = null,
    @SerializedName("section")
    @Expose
    val section: String? = null,
    @SerializedName("complaint_no")
    @Expose
    val complaintNo: Int = 0,
    @SerializedName("complaint_type")
    @Expose
    val complaint_type: String? = null,
    @SerializedName("street")
    @Expose
    val street: String? = null,
    @SerializedName("district")
    @Expose
    val district: String? = null,
    @SerializedName("image_1")
    @Expose
    val image_1: String? = null,
    @SerializedName("image_2")
    @Expose
    val image_2: String? = null,
    @SerializedName("category")
    @Expose
    val category: Int = 0,
    @SerializedName("landmark")
    @Expose
    val landmark: String? = null,
    @SerializedName("circle")
    @Expose
    val circle: String? = null
) : Parcelable