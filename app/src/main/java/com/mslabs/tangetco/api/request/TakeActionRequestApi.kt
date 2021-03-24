package com.mslabs.tangetco.api.request

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
class TakeActionRequestApi (
    @SerializedName("complaint_id")
    @Expose
    var complaint_id: Int? = 0,

    @SerializedName("region_id")
    @Expose
    var region_id: Int? = 0,
    @SerializedName("circle_id")
    @Expose
    var circle_id: Int? = 0,
    @SerializedName("division_id")
    @Expose
    var division_id: Int? = 0,
    @SerializedName("sub_division_id")
    @Expose
    var sub_division_id: Int? = 0,
    @SerializedName("section_id")
    @Expose
    var sectionId: Int? = 0,
    @SerializedName("status_id")
    @Expose
    var status_id: Int? = 0,
    @SerializedName("description")
    @Expose
    var description: String? = "" ,
    @SerializedName("reason_id")
    @Expose
    var reason_id: Int? = 0
):Parcelable
