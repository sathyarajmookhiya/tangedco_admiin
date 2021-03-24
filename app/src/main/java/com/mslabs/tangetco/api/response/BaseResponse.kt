package com.mslabs.tangetco.api.response

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
class BaseResponse(
    @SerializedName("iserror")
    @Expose
    var Iserror: Int = 0,
    var message: String = ""
) : Parcelable
