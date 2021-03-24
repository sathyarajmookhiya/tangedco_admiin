package com.mslabs.tangetco

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mslabs.tangetco.common.BaseFragmentActivity
import com.mslabs.tangetco.databinding.ActivityMainBinding
import com.mslabs.tangetco.home.HomeFragment
import com.mslabs.tangetco.login.LoginActivity
import com.mslabs.tangetco.profile.ProfileFragment
import com.mslabs.tangetco.util.preference.TangedcoPreferenceManager

class MainActivity : BaseFragmentActivity() {

    private var back_pressed: Long = 0

    companion object {
        fun newInstance(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    lateinit var mBinding: ActivityMainBinding
    override fun getFragmentContainerId(): Int {
        return R.id.content_frame
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setSupportActionBar(mBinding.toolbar)

        //  myFragmentManager = supportFragmentManager
        startFragmentFromRoot(HomeFragment())
        mBinding.containMainFragment.navView.setOnNavigationItemSelectedListener(
            mOnNavigationItemSelectedListener
        )
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            val id = item.itemId

            var fragment: Fragment? = null

            when (id) {
                R.id.navigation_home -> fragment = HomeFragment()
                R.id.navigation_profile -> startFragment(ProfileFragment())
                R.id.navigation_logout -> {
                    TangedcoPreferenceManager.setUserLoggedIn(false)
                    TangedcoPreferenceManager.setPin("")
                    startActivity(LoginActivity.newInstance(this))
                    finish()
                }
            }
            if (fragment != null) {
                startFragmentFromRoot(fragment)
            }
            true
        }




}