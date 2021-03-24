package com.mslabs.tangetco.api.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class RegionList(
    @SerializedName("region") var regionList: List<ListDataResponse>?,
    @SerializedName("iserror") var iserror: Int?, @SerializedName("messaage") var messaage: String?
) : Parcelable

@Parcelize
data class CircleList(
    @SerializedName("circle") var circleList: List<ListDataResponse>?,
    @SerializedName("iserror") var iserror: Int?, @SerializedName("messaage") var messaage: String?
) : Parcelable

@Parcelize
data class DivisionList(
    @SerializedName("division") var divisionList: List<ListDataResponse>?,
    @SerializedName("iserror") var iserror: Int?, @SerializedName("messaage") var messaage: String?
) : Parcelable


@Parcelize
data class SubDivisionList(
    @SerializedName("sub_division") var subDivisionList: List<ListDataResponse>?,
    @SerializedName("iserror") var iserror: Int?, @SerializedName("messaage") var messaage: String?
) : Parcelable

@Parcelize
data class SectionsList(
    @SerializedName("section") var sectionList: List<ListDataResponse>?,
    @SerializedName("iserror") var iserror: Int?, @SerializedName("messaage") var messaage: String?
) : Parcelable


@Parcelize
data class ReasonList(
    @SerializedName("reason") var reasonList: List<ListDataResponse>?,
    @SerializedName("iserror") var iserror: Int?, @SerializedName("messaage") var messaage: String?
) : Parcelable



@Parcelize
data class ListDataResponse(
    @SerializedName("name") var name: String?,
    @SerializedName("id") var id: Int?
) : Parcelable