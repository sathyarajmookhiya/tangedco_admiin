package com.mslabs.tangetco.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mslabs.tangetco.MainActivity
import com.mslabs.tangetco.R
import com.mslabs.tangetco.databinding.FragmentHomeBinding
import com.mslabs.tangetco.databinding.FragmentProfileBinding
import com.mslabs.tangetco.login.LoginViewModel
import com.mslabs.tangetco.util.preference.TangedcoPreferenceManager
import kotlinx.android.synthetic.main.content_main_fragment.*


class ProfileFragment : Fragment() {


    lateinit var dataBinding: FragmentProfileBinding

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        (activity as MainActivity).nav_view.menu.getItem(1).isChecked = true
        (activity as MainActivity).supportActionBar!!.title = "Profile"
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        dataBinding.viewModel = TangedcoPreferenceManager.getProfile()
        dataBinding.lifecycleOwner = this

        return dataBinding.root
    }
}