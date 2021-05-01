package com.mslabs.tangetco.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.mslabs.tangetco.MainActivity
import com.mslabs.tangetco.R
import com.mslabs.tangetco.api.response.DashboardList
import com.mslabs.tangetco.api.response.DashboardResponse
import com.mslabs.tangetco.common.BaseFragment
import com.mslabs.tangetco.databinding.FragmentHomeBinding
import com.mslabs.tangetco.home.adapter.QuickActionAdapter
import com.mslabs.tangetco.login.LoginViewModel
import com.mslabs.tangetco.util.Log
import com.mslabs.tangetco.util.preference.TangedcoPreferenceManager
import kotlinx.android.synthetic.main.content_main_fragment.*


class HomeFragment : BaseFragment(), QuickActionAdapter.QuickActionClickListener {

    lateinit var loginViewModel: LoginViewModel

    lateinit var binding: FragmentHomeBinding


    lateinit var recyclerView: RecyclerView
    lateinit var errorLayout: LinearLayout
    lateinit var errorTitle: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        (activity as MainActivity).nav_view.menu.getItem(0).isChecked = true
        recyclerView = binding.recyclerView
        errorLayout = binding.errorLayout
        errorTitle = binding.errorTitle
        (activity as MainActivity).supportActionBar!!.title = "Home"
        val glm = GridLayoutManager(activity, 2)
        recyclerView.layoutManager = glm
        recyclerView.isNestedScrollingEnabled = true
        recyclerView.setHasFixedSize(true)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dashboardList("sectionId", TangedcoPreferenceManager.getProfile()!!.officeId)
        // dashboardList("SECTION_ID", 2428)

    }

    override fun onResume() {
        super.onResume()
        dashboardList("sectionId", TangedcoPreferenceManager.getProfile()!!.officeId)
        //dashboardList("SECTION_ID", 2428)

    }

    fun homeDashboardList(dashboardList: MutableList<DashboardList>) {

        val adapter = QuickActionAdapter(
            requireContext(),
            dashboardList, this
        )
        recyclerView.adapter = adapter

        // Log.d("Name Of Response : $dashboardResponse")

    }

    fun dashboardList(fieldName: String, officeid: Int) {
        showLoader()
        val mutableLiveData = loginViewModel.dashboardList(fieldName, officeid)
        mutableLiveData.observe(viewLifecycleOwner, Observer<DashboardResponse> { responseData ->
            dismissLoadingDialog()
            if (responseData.Iserror != 1) {
                if (responseData != null) {
                    Log.d("Success Dashboard !!!")
                    val dashboardResponse = TangedcoPreferenceManager.getDashboardList()
                    if (responseData.dashboardList.isNullOrEmpty()) {
                        Log.d("Empty")
                    } else {
                        homeDashboardList(responseData.dashboardList as MutableList<DashboardList>)
                    }
                    errorLayout.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                } else {
                    errorLayout.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
            } else {
                showSnackbar(
                    responseData.message,
                    Snackbar.LENGTH_LONG
                )
                errorLayout.visibility = View.VISIBLE
                errorTitle.text = responseData.message
                recyclerView.visibility = View.GONE
            }
        })

    }


    override fun onQuickActionClicked(quickAction: DashboardList, position: Int, status: Int) {

        startActivity(
            ComplaintListActivity.newInstance(
                requireContext(),
                position,
                status,
                quickAction.Complaintname
            )
        )
        Log.d("Click Status : " + position + " :  " + status)
    }

}