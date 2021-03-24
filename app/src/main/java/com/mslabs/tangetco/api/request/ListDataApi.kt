package com.mslabs.tangetco.api.request

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class FetchCircleInput(
    @SerializedName("region_id") var region_id: Int?) : Parcelable


@Parcelize
data class FetchDivisionInput(
    @SerializedName("circle_id") var circle_id: Int?) : Parcelable

@Parcelize
data class FetchSubDivisionInput(
    @SerializedName("division_id") var division_id: Int?) : Parcelable

@Parcelize
data class FetchSectionInput(
    @SerializedName("sub_division_id") var sub_division_id: Int?) : Parcelable
@Parcelize
data class FetchReasonsInput(
    @SerializedName("complaint_code") var complaint_code: Int?) : Parcelable