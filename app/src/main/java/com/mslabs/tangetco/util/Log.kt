package com.mslabs.tangetco.util


object Log {

    private const val TAG = "SIPCOT"
    private const val DEBUG = true

    fun i(message: String?) {
        if (DEBUG) {
            android.util.Log.i(TAG, message)
        }
    }

    fun d(message: String?) {
        if (DEBUG) {
            android.util.Log.e(TAG, message)
        }
    }

    fun e(message: String?) {
        if (DEBUG) {
            android.util.Log.e(TAG, message)
        }
    }

    fun e(e: Exception) {
        if (DEBUG) {
            e.printStackTrace()
        }
    }

    fun v(message: String?) {
        if (DEBUG) {
            android.util.Log.v(TAG, message)
        }
    }

}