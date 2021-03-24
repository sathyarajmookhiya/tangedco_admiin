package com.mslabs.sipcot.db

import com.mslabs.sipcot.db.TangedcoDatabase.Companion.instance
import com.mslabs.tangetco.api.response.LoginResponse

/**
 * Project           : smartplug
 * File Name         : DatabaseManager
 * Description       :
 * Revision History  : version 1
 * Date              : 2019-09-10
 * Original author   : Kannappan
 * Description       : Initial version
 */
object DatabaseManager {

    private val TAG = "DatabaseManager"


    fun setLoginUserProfileData(loginResponse: LoginResponse) {
        val db = instance
        db!!.userDao().insert(loginResponse)

    }

    fun getLoginUserProfileData(userId:Int):LoginResponse {
        val db = instance
      return  db!!.userDao().getUser(userId)
    }




}


