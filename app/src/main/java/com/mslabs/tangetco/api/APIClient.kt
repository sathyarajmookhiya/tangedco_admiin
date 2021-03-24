package com.mslabs.tangetco.api


import com.google.gson.GsonBuilder
import com.mslabs.tangetco.BaseApplication
import com.mslabs.tangetco.util.NetworkUtil

import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.IOException
import java.util.*
import javax.net.ssl.HostnameVerifier

/**
 * Project           : smartplug
 * File Name         : APIClient
 * Description       :
 * Revision History  : version 1
 * Date              : 2019-09-06
 * Original author   : Kannappan
 * Description       : Initial version
 */
object APIClient {
    private const val BASE_URL =
        "http://103.234.149.239:8080/admin/rest/"
  /* private const val BASE_URL =
        "https://sipcot.tn.gov.in/portal/solid_waste_mgmt/resources/"*/
    //10 MB
    val client:
    //            okHttpClientBuilder.sslSocketFactory(getSSLConfig(BaseApplication.getApplication().getApplicationContext()), systemDefaultTrustManager());
    //    private static X509TrustManager systemDefaultTrustManager() {
            Retrofit
        get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            /*if (BuildConfig.DEBUG) {
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            } else {
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
            }*/
            val httpCacheDirectory =
                File(BaseApplication.getApplication().cacheDir, "offlineCache")

            //5 MB
            val cache = Cache(httpCacheDirectory, 5 * 1024 * 1024)
            val okHttpClientBuilder = OkHttpClient.Builder()
            okHttpClientBuilder.cache(cache)
            okHttpClientBuilder.addInterceptor(object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): Response {
                    val builder = chain.request().newBuilder()

                    return chain.proceed(builder.build())
                }
            })
            okHttpClientBuilder.addInterceptor { chain ->
                var request = chain.request()
                request = if (hasNetwork()!!)
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                else
                    request.newBuilder().header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                    ).build()
                chain.proceed(request)
            }
            okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
            //            okHttpClientBuilder.sslSocketFactory(getSSLConfig(BaseApplication.getApplication().getApplicationContext()), systemDefaultTrustManager());
            okHttpClientBuilder.hostnameVerifier(HostnameVerifier { s, sslSession -> true })
            val okHttpClient = okHttpClientBuilder.build()
            val gson = GsonBuilder()
                .setLenient()
                .create()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build()
        }

    private fun hasNetwork(): Boolean? {
        return NetworkUtil.isConnectedAndInternetAvailable(
            BaseApplication.getApplication().applicationContext
        )
    }


}