package com.mslabs.tangetco.api.request

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class DashboardApi (
    @SerializedName("field_name")
    @Expose
     var fieldName: String? = null,

    @SerializedName("office_id")
    @Expose
     var officeId: Int? = null
):Parcelable