package com.mslabs.tangetco.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

import androidx.annotation.WorkerThread;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Project           : smartplug
 * File Name         : NetworkUtil
 * Description       :
 * Revision History  : version 1
 * Date              : 2019-09-28
 * Original author   : Kannappan
 * Description       : Initial version
 */
public class NetworkUtil {

    /**
     * Get the network info
     *
     * @param context
     * @return
     */
    public static NetworkInfo getNetworkInfo(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }

    /**
     * Check if there is any connectivity
     *
     * @param context
     * @return
     */
    public static boolean isConnected(Context context) {
        NetworkInfo info = getNetworkInfo(context);
        return (info != null && info.isConnected());
    }

    /**
     * Check if there is any connectivity
     *
     * @param context
     * @return
     */
    public static boolean isConnectedAndInternetAvailable(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return isNetworkOnline(context);
        } else {
            NetworkInfo info = getNetworkInfo(context);
            return (info != null && info.isConnected());
        }
    }

    /**
     * @return
     */
    @WorkerThread
    public static boolean isInternetAvailable() {
        try {
            InetAddress address = InetAddress.getByName("www.google.com");
            return !"".equals(address);
        } catch (UnknownHostException e) {
            // Log error
        }
        return false;
    }

    /**
     * Check if there is any connectivity to a Wifi network
     *
     * @param context
     * @return
     */
    public static boolean isConnectedWifi(Context context) {
        NetworkInfo info = getNetworkInfo(context);
        return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_WIFI);
    }

    /**
     * Check if there is any connectivity to a mobile network
     *
     * @param context
     * @return
     */
    public static boolean isConnectedMobile(Context context) {
        NetworkInfo info = getNetworkInfo(context);
        return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_MOBILE);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static boolean isNetworkOnline(Context context) {
        boolean isOnline = false;
        try {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            assert manager != null;
            NetworkCapabilities capabilities = manager.getNetworkCapabilities(manager.getActiveNetwork());  // need ACCESS_NETWORK_STATE permission
            isOnline = capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isOnline;
    }

}
