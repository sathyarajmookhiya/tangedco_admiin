package com.mslabs.tangetco;

import android.app.Application;

import com.mslabs.tangetco.util.preference.PreferenceUtil;

/**
 * Project           : Talkio
 * File Name         : BaseApplication
 * Description       :
 * Revision History  : version 1
 * Date              :
 * Original author   : Kannappan
 * Description       : Initial version
 */
public class BaseApplication extends Application {
    private static final String TAG = BaseApplication.class.getSimpleName();
    private static BaseApplication mInstance;

    public static BaseApplication getApplication() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        PreferenceUtil.init(this, TAG, TAG);

        //init stetho
       // BuildConfig.STETHO.init(this);

        // Initialize database
       // SipcotDatabase.initAppDatabase(this);

       // Stetho.initializeWithDefaults(this);

    }

}