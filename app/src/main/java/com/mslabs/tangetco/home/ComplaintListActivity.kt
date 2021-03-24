package com.mslabs.tangetco.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.mslabs.tangetco.R
import com.mslabs.tangetco.common.BaseActivity
import com.mslabs.tangetco.home.SectionForm.Child


class ComplaintListActivity : BaseActivity() {


    var complaint_code:Int=0
    var Status_code:Int=0
    var ComplaintName=""

    companion object {

        private val ARG_PARAM1 = "complaint_code"
        private val ARG_PARAM2 = "Status_code"
        private val ARG_PARAM3 = "ComplaintName"


        fun newInstance(context: Context,complaint_code: Int,Status_code: Int,ComplaintName: String): Intent {
            val intent= Intent(context, ComplaintListActivity::class.java)
            intent.putExtra(ARG_PARAM1, complaint_code)
            intent.putExtra(ARG_PARAM2, Status_code)
            intent.putExtra(ARG_PARAM3, ComplaintName)
            return intent
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complaint_list)
        setActionBar()
        setHomeUpEnable()

        if(intent!=null)
        {
            complaint_code= intent.getIntExtra(ARG_PARAM1,0)
            Status_code= intent.getIntExtra(ARG_PARAM2,0)
            ComplaintName= intent.getStringExtra(ARG_PARAM3)
        }

        setToolbarTitle(ComplaintName)


        val complaintListFragment = ComplaintListFragment.newInstance(complaint_code,Status_code,ComplaintName)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.frame_container, complaintListFragment)
        fragmentTransaction.commit()

    }


}