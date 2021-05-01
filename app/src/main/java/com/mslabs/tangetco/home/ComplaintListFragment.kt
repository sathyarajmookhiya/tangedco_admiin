package com.mslabs.tangetco.home

import android.app.Activity
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.mslabs.tangetco.R
import com.mslabs.tangetco.api.request.ComplaintRequestApi
import com.mslabs.tangetco.api.request.TakeActionRequestApi
import com.mslabs.tangetco.api.response.BaseResponse
import com.mslabs.tangetco.api.response.ComplaintList
import com.mslabs.tangetco.api.response.ComplaintListResponse
import com.mslabs.tangetco.common.BaseFragment
import com.mslabs.tangetco.databinding.FragmentComplaintListBinding
import com.mslabs.tangetco.util.Log
import com.mslabs.tangetco.util.preference.TangedcoPreferenceManager


class ComplaintListFragment : BaseFragment(),ComplaintListAdapter.ComplaintListClickListener,ComplaintListAdapter.ComplaintTakeActionClickListener
,ComplaintListAdapter.ComplaintCompletedClickListener,ComplaintListAdapter.ComplaintTransferClickListener,ComplaintListAdapter.ComplaintUpdateClickListener{

    lateinit var complaintListViewModel: ComplaintListViewModel

    lateinit var recyclerView: RecyclerView
    lateinit var errorLayout: LinearLayout
    lateinit var errorTitle: TextView
    lateinit var mBinding: FragmentComplaintListBinding

    private val ARG_PARAM1 = "complaint_code"
    private val ARG_PARAM2 = "Status_code"
    private val ARG_PARAM3 = "Complaint_name"

   var complaintName=""
   var cName=""
    var complaint_code=0
    var status_code=0

    lateinit var complaintListAdapter: ComplaintListAdapter

    companion object {
        @JvmStatic
        fun newInstance(complaintcode: Int,statuscode: Int,complaintName:String) =
            ComplaintListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, complaintcode)
                    putInt(ARG_PARAM2,statuscode)
                    putString(ARG_PARAM3, complaintName)

                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        arguments?.let {
            status_code = (it.getInt(ARG_PARAM2))!!
            }
        arguments?.let {
            complaint_code = (it.getInt(ARG_PARAM1))!!
            }
        arguments?.let {
            complaintName = (it.getString(ARG_PARAM3))!!
            }
           /* arguments?.let {
                complaint_code = (it.getInt(ARG_PARAM2))

                Log.d("Head Id : $complaint_code")
*/

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_complaint_list, container, false)
              complaintListViewModel = ViewModelProvider(this).get(ComplaintListViewModel::class.java)
        mBinding.lifecycleOwner=this
        recyclerView = mBinding.recyclerView



        val layoutManager = LinearLayoutManager(
           requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        layoutManager.stackFromEnd = false
        recyclerView.layoutManager=layoutManager
        errorLayout = mBinding.errorLayout
        errorTitle = mBinding.errorTitle


        complaintListViewModel.complaintName.observe(
            viewLifecycleOwner,
            Observer<String?> { complaintName ->
                Log.d("Complaint head name : $complaintName")
                cName= complaintName!!
            })

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TangedcoPreferenceManager.getProfile()!!.officeId
        complaintList("sectionId", TangedcoPreferenceManager.getProfile()!!.officeId, status_code,  complaint_code)

    }

    fun complaintList(
        fieldName: String,
        officeid: Int,
        status_id: Int,
        complaint_code: Int
    ) {

        val complaintRequestApi = ComplaintRequestApi(fieldName, officeid, status_id, complaint_code)

        showLoader()
        val mutableLiveData = complaintListViewModel.complaintList(complaintRequestApi)
        mutableLiveData.observe(
            viewLifecycleOwner,
            Observer<ComplaintListResponse> { responseData ->
                dismissLoadingDialog()

                if (responseData.Iserror != 1) {
                    if (responseData != null) {

                        val complaintListResponse = TangedcoPreferenceManager.getComplaintList()
                        complaintListResponse?.let { homeComplaintList(it) }
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
                    errorTitle.text = responseData.message ?: ""
                    recyclerView.visibility = View.GONE
                }
            })
    }

    fun homeComplaintList(complaintListResponse: ComplaintListResponse) {
        complaintListAdapter= ComplaintListAdapter(requireContext(),
            complaintListResponse.complaintList as MutableList<ComplaintList>,this,this,this,this,this)

        recyclerView.adapter=complaintListAdapter
    }

    override fun onComplaintListClicked(complaintDataList: ComplaintList) {

        Log.d("Complaint No : "+complaintDataList.complaintNo)
        startActivity(ComplaintDetilesAct.getInstance(requireActivity(),complaintDataList))
    }

    override fun onComplaintTakeActionClicked(complaintDataList: ComplaintList) {

        CompliantDetailsDialogFragment.newInstance(complaintName, complaintDataList.complaintNo.toString(),complaint_code,2).show(
            requireFragmentManager(), CompliantDetailsDialogFragment.TAG)

    }

    override fun onComplaintCompletedClicked(complaintDataList: ComplaintList) {
       // updateRemarks(complaintDataList,2)
        CompliantDetailsDialogFragment.newInstance(complaintName, complaintDataList.complaintNo.toString(),complaint_code,4).show(
            requireFragmentManager(), CompliantDetailsDialogFragment.TAG)
    }

    private fun updateRemarks(complaintDataList: ComplaintList,statusId:Int) {

        val alert = AlertDialog.Builder(requireContext())
        val edittext = EditText(requireContext())
        alert.setMessage("Enter Remark Name")
        alert.setTitle(complaintDataList.complaintNo.toString())

        alert.setView(edittext)

        alert.setPositiveButton("Yes",
            DialogInterface.OnClickListener { dialog, whichButton -> //What ever you want to do with the value

                val playlistName = edittext.text.toString()
                Log.d("Name Edit : $playlistName")
              /*  val imm =
                    requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
                val loginResponse= TangedcoPreferenceManager.getProfile()

                  val takeActionRequestApi=TakeActionRequestApi(complaintDataList.complaintNo,loginResponse!!.regionId,
                      loginResponse.circleId,loginResponse!!.divisionId,loginResponse.subDivisionId,loginResponse.sectionId,statusId,playlistName)

                sendTakeAction(takeActionRequestApi)*/
            })

        alert.setNegativeButton("No",
            DialogInterface.OnClickListener { dialog, whichButton ->
                // what ever you want to do with No option.
                dialog.dismiss()
            })

        alert.show()

    }

   fun sendTakeAction(takeActionRequestApi: TakeActionRequestApi)
   {
       val mutableLiveData = complaintListViewModel.takeAction( takeActionRequestApi)
       mutableLiveData.observe(this, Observer<BaseResponse> { responseData ->
           dismissLoadingDialog()
           if (responseData.Iserror!=1) {
            showToast("successfully saved")

           } else {
               showToast(responseData.message)

           }
       })


   }

    override fun onComplaintTransferClicked(complaintDataList: ComplaintList) {
        CompliantDetailsDialogFragment.newInstance(complaintName, complaintDataList.complaintNo.toString(),complaint_code,1).show(
            requireFragmentManager(), CompliantDetailsDialogFragment.TAG)
    }

    override fun onComplaintUpdateClicked(complaintDataList: ComplaintList) {
        CompliantDetailsDialogFragment.newInstance(complaintName, complaintDataList.complaintNo.toString(),complaint_code,3).show(
            requireFragmentManager(), CompliantDetailsDialogFragment.TAG)
    }
}
