package com.mslabs.tangetco.home.SectionForm

import android.os.Parcel
import android.os.Parcelable

class Child(
    var Pending: String?,var InProgress: String?) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ,
                parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(Pending)
        parcel.writeString(InProgress)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Child> {
        override fun createFromParcel(parcel: Parcel): Child {
            return Child(parcel)
        }

        override fun newArray(size: Int): Array<Child?> {
            return arrayOfNulls(size)
        }
    }
}