package com.mslabs.sipcot.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mslabs.sipcot.db.dao.*
import com.mslabs.tangetco.api.response.LoginResponse

/**
 * Project           : TangedcoComplaint
 * File Name         : SipcotDatabase
 * Description       :
 * Revision History  : version 1
 * Date              : 2019-09-11
 * Original author   : Kannappan
 * Description       : Initial version
 */
@Database(
    entities = [LoginResponse::class],
    version = TangedcoDatabase.DB_VERSION
)

abstract class TangedcoDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    /**
     * Delete all table data
     */
    fun deleteAllTableData() {
        userDao().delete()

    }

    companion object {
        const val DB_VERSION = 1
        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Since we didn't alter the table, there's nothing else to do here.
            }
        }
        private const val DB_NAME = "sipcot.db"

        /**
         * Get the SipcotDatabase instance
         *
         * @return
         */
        var instance: TangedcoDatabase? = null
            private set

        /**
         * Init application database view
         *
         * @param context
         * @return
         */
        @JvmStatic
        fun initAppDatabase(context: Context) {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    TangedcoDatabase::class.java,
                    DB_NAME
                )
                    .addMigrations(MIGRATION_1_2).allowMainThreadQueries()
                    .build()
            }
        }

        /**
         * Destroy SipcotDatabase instance
         */
        fun destroyInstance() {
            instance = null
        }
    }
}