package com.mslabs.tangetco.util

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.AndroidViewModel

/**
 * Project           : Smarthome
 * File Name         :
 * Description       :
 * Revision History  : version 1
 * Date              : 2019-08-25
 * Original author   : Kannappan
 * Description       : Initial version
 */
private var mLastClkTime: Long = 0

inline fun <reified T : Any> Context.launchActivity(
    options: Bundle? = null,
    noinline init: Intent.() -> Unit = {}
) {
    val intent = newIntent<T>(this)
    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
    intent.init()
    options?.let { intent.putExtras(it) }

    startActivity(intent)

//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//        startActivity(intent, options)
//    } else {
//        startActivity(intent)
//    }
}

@ColorInt
fun Context.getColorFromAttr(
    @AttrRes attrColor: Int,
    typedValue: TypedValue = TypedValue(),
    resolveRefs: Boolean = true
): Int {
    theme.resolveAttribute(attrColor, typedValue, resolveRefs)
    return typedValue.data
}

fun isConsumeClickEvents(): Boolean {
    if (SystemClock.elapsedRealtime() - mLastClkTime < 1500) {
        return false
    }
    mLastClkTime = SystemClock.elapsedRealtime()
    return true
}

inline fun <reified T : Any> newIntent(context: Context): Intent = Intent(context, T::class.java)

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}

fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransaction { add(frameId, fragment, fragment.javaClass.simpleName) }
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransaction {
        replace(
            frameId,
            fragment,
            fragment.javaClass.simpleName
        )
    }
}

fun Fragment.replaceFragment(activity: AppCompatActivity, fragment: Fragment, frameId: Int) {
    activity.supportFragmentManager.inTransaction {
        replace(
            frameId,
            fragment,
            fragment.javaClass.simpleName
        )
    }
}

fun Fragment.addFragment(activity: AppCompatActivity, fragment: Fragment, frameId: Int) {
    activity.supportFragmentManager.inTransaction {
        add(
            frameId,
            fragment,
            fragment.javaClass.simpleName
        )
    }
}

fun AndroidViewModel.replaceFragment(
    activity: AppCompatActivity,
    fragment: Fragment,
    frameId: Int
) {
    activity.supportFragmentManager.inTransaction {
        replace(
            frameId,
            fragment,
            fragment.javaClass.simpleName
        )
    }
}

fun replaceFragmentFromAdapter(activity: AppCompatActivity, fragment: Fragment, frameId: Int) {
    activity.supportFragmentManager.inTransaction {
        replace(
            frameId,
            fragment,
            fragment.javaClass.simpleName
        )
    }
}

fun View.hideKeyboard(context: Context) {
    val im = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    im.hideSoftInputFromWindow(windowToken, 0)
}


