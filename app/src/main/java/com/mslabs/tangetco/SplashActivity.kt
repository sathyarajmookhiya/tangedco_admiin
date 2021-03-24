package com.mslabs.tangetco

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.WindowManager
import com.mslabs.tangetco.common.BaseActivity
import com.mslabs.tangetco.login.LoginActivity
import com.mslabs.tangetco.pinpad.VerifyPinActivity
import com.mslabs.tangetco.util.preference.TangedcoPreferenceManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashActivity : BaseActivity() {

    private val SPLASH_TIME_OUT = 1500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTransparentStatusBar()
        GlobalScope.launch {
            delay(SPLASH_TIME_OUT.toLong())
            moveToActivity()
        }
    }

    fun moveToActivity() {
        val intent = Intent(
            this@SplashActivity,
            if (TextUtils.isEmpty(TangedcoPreferenceManager.getPin())) LoginActivity::class.java else VerifyPinActivity::class.java
        )
        startActivityLeftToRight(intent)
        finish()

    }
    fun setTransparentStatusBar() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.TRANSPARENT
        }
    }


}
