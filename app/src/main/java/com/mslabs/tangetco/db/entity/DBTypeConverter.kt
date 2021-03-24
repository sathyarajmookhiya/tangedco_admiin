package com.mslabs.sipcot.db.entity

import android.text.TextUtils
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
/**
 * Project           : smartplug
 * File Name         : DBTypeConverter
 * Description       :
 * Revision History  : version 1
 * Date              : 2019-09-13
 * Original author   : Kannappan
 * Description       : Initial version
 */
public object DBTypeConverter {

    val moshi = Moshi.Builder().build()

    /* private val gson = Gson()

    @JvmStatic
    @TypeConverter
    fun stringToSomeObjectList(data: String?): List<String> {
        if (data == null || data.length == 2) {
            return emptyList()
        }
        if (!data.contains("[")) {
            if (!TextUtils.isEmpty(data)) {
                val stringList: MutableList<String> =
                    ArrayList()
                stringList.add(data)
                return stringList
            }
            return emptyList()
        }
        val listType = TypeToken.getParameterized(
            MutableList::class.java, String::class.java
        ).type
        return gson.fromJson(data, listType)
    }

    @JvmStatic
    @TypeConverter
    fun someObjectListToString(someObjects: List<String?>?): String {
        return gson.toJson(someObjects)
    }*/


}