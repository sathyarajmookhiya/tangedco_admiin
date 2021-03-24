package com.mslabs.tangetco.api.request

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
class ComplaintRequestApi (
    @SerializedName("field_name")
    @Expose
    var fieldName: String? = null,

    @SerializedName("office_id")
    @Expose
    var officeId: Int = 0,

    @SerializedName("status_id")
    @Expose
    var statusId: Int = 0,

    @SerializedName("complaint_code")
    @Expose
    var complaint_code: Int = 0
): Parcelable