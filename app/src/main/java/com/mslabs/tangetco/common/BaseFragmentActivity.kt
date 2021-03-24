package com.mslabs.tangetco.common

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

abstract class BaseFragmentActivity : BaseActivity() {

    private var fragmentContainerId: Int = 0

    protected val isAtRootFragment: Boolean
        get() = supportFragmentManager.backStackEntryCount <= 0

    protected val currentFragment: Fragment?
        get() = supportFragmentManager.findFragmentById(fragmentContainerId)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fragmentContainerId = getFragmentContainerId()

        if (fragmentContainerId == 0) {
            throw IllegalStateException("Fragment Container Id is Null.!!!!")
        }
    }

    override fun onBackPressed() {
        val fragment = currentFragment
        if (fragment is HasMarker) {
            val marker = (fragment as HasMarker).marker
            if (marker != null) {
                supportFragmentManager.popBackStack(marker, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            }
        } else {
            super.onBackPressed()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(!isAtRootFragment)
    }

    fun finishFragment() {
        onBackPressed()
    }

    open fun startFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
            .replace(fragmentContainerId, fragment, fragment.javaClass.name)

        val fragmentById = currentFragment
        val backStackTag = if (fragmentById == null) "ROOT" else fragmentById.javaClass.name
        transaction.addToBackStack(backStackTag)
        transaction.commitAllowingStateLoss()
    }


    open fun startFragmentFromRoot(fragment: Fragment) {
        popAllBackStack()

        supportFragmentManager.beginTransaction()
            ?.replace(fragmentContainerId, fragment)
            ?.disallowAddToBackStack()
            ?.commitAllowingStateLoss()
    }

    protected fun popAllBackStack() {
        val backStackEntryCount = supportFragmentManager.backStackEntryCount
        for (i in 0 until backStackEntryCount) {
            supportFragmentManager.popBackStack()
        }
    }

    protected abstract fun getFragmentContainerId(): Int

    interface HasMarker {
        val marker: String?
    }
}