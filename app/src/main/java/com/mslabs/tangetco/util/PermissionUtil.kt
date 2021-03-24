package com.mslabs.tangetco.util

import android.Manifest.permission.*
import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat

/**
 * Created by mslabs on 21/12/17.
 */object PermissionUtil {

    val APP_PERMISSION = 1

    val isAndroidMWithModifySettingsRequirementDefect: Boolean
        get() = Build.VERSION.RELEASE == "6.0"

    @TargetApi(Build.VERSION_CODES.M)
    fun hasRequiredAppPermissionReadWrite(context: Context): Boolean {
        return ActivityCompat.checkSelfPermission(context, READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun requestAppPermissionReadWrite(activity: Activity) {
        val permissions = arrayOf(READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE)
        activity.requestPermissions(permissions, APP_PERMISSION)
    }



    @TargetApi(Build.VERSION_CODES.M)
    fun hasRequiredAppPermissionCamera(context: Context): Boolean {
        return ActivityCompat.checkSelfPermission(context, CAMERA) == PackageManager.PERMISSION_GRANTED
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun requestAppPermissionCamera(activity: Activity) {
        val permissions = arrayOf(CAMERA)
        activity.requestPermissions(permissions, APP_PERMISSION)
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun requestAppPermissionIfNotDenied(activity: Activity) {
        if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, READ_EXTERNAL_STORAGE)) {
            requestAppPermissionReadWrite(activity)
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun shouldAskForAppPermission(activity: Activity): Boolean {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity,
            READ_EXTERNAL_STORAGE)
    }


   /* fun isForceAppUpdateRequired(minVersionCode: Int): Boolean {
        return BuildConfig.VERSION_CODE < minVersionCode
    }

    fun isAppUpdateRequired(minVersionCode: Int, maxVersionCode: Int): Boolean {
        return BuildConfig.VERSION_CODE > minVersionCode && BuildConfig.VERSION_CODE <= maxVersionCode
    }
*/
}
