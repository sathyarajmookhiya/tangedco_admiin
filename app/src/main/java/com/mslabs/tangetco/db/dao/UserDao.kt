package com.mslabs.sipcot.db.dao

import androidx.room.*
import com.mslabs.tangetco.api.response.LoginResponse

/**
 * Project           : smartplug
 * File Name         : UserDao
 * Description       :
 * Revision History  : version 1
 * Date              : 2019-09-11
 * Original author   : Kannappan
 * Description       : Initial version
 */
@Dao
interface UserDao {
    @Query("SELECT * FROM user")
     fun getAll(): List<LoginResponse>

    @Query("SELECT * FROM `user` where id = :id")
    fun getUser(id: Int?): LoginResponse

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insert(vararg user: LoginResponse?)

    @Update
     fun update(vararg user: LoginResponse?)

    @Delete
     fun delete(vararg user: LoginResponse?)

    @Query("DELETE FROM user")
     fun delete()
}