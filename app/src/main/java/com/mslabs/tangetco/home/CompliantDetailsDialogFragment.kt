package com.mslabs.tangetco.home

import android.app.Activity
import android.app.Dialog
import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatSpinner
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.util.ViewInfo
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.gson.Gson
import com.mslabs.tangetco.R
import com.mslabs.tangetco.api.request.*
import com.mslabs.tangetco.api.response.*
import com.mslabs.tangetco.home.adapter.*
import com.mslabs.tangetco.util.Log
import com.mslabs.tangetco.util.preference.TangedcoPreferenceManager
import kotlinx.android.synthetic.main.fragment_complaint_detiles_dialog.view.*


class CompliantDetailsDialogFragment : DialogFragment() {

    lateinit var circleListAC: AppCompatSpinner
    lateinit var divisionListAC: AppCompatSpinner
    lateinit var subDivisionListAC: AppCompatSpinner
    lateinit var sectionListAC: AppCompatSpinner
    lateinit var reasonListAC: AppCompatSpinner
    lateinit var masterLayout: ConstraintLayout

    lateinit var reasonAdapter: ReasonAdapter
    lateinit var regionAdapter: RegionAdapter
    lateinit var circleAdapter: CircleAdapter
    lateinit var divisionAdapter: DivisionAdapter
    lateinit var subdivisionAdapter: SubDivisionAdapter
    lateinit var sectionAdapter: SectionAdapter

    var regionId = 0
    var circleId = 0
    var divisionId = 0
    var subDivisionId = 0
    var sectionId = 0
    var complaint_code = 0
    var complaintType = 0
    var reasonId = 0

    lateinit var dialogLoader: ProgressDialog

    companion object {

        const val TAG = "SimpleDialog"

        private lateinit var viewModel: MasterViewModel

        private const val KEY_TITLE = "KEY_TITLE"
        private const val KEY_SUBTITLE = "KEY_SUBTITLE"
        private const val KEY_CODE = "KEY_CODE"
        private const val KEY_COMPLIANT_TYPE = "COMPLAINT_TYPE"


        fun newInstance(
            title: String,
            subTitle: String,
            complaint_code: Int,
            complaintType: Int
        ): CompliantDetailsDialogFragment {
            val args = Bundle()
            args.putString(KEY_TITLE, title)
            args.putString(KEY_SUBTITLE, subTitle)
            args.putInt(KEY_CODE, complaint_code)
            args.putInt(KEY_COMPLIANT_TYPE, complaintType)
            val fragment = CompliantDetailsDialogFragment()
            fragment.arguments = args
            return fragment
        }

    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        viewModel = ViewModelProvider(this).get(MasterViewModel::class.java)
        dialogLoader = ProgressDialog(activity, theme)

        val view =
            LayoutInflater.from(requireContext())
                .inflate(R.layout.fragment_complaint_detiles_dialog, null)
        view.complaintTitle.text = arguments?.getString(KEY_TITLE)
        view.complaintNumber.text = arguments?.getString(KEY_SUBTITLE)
        complaint_code = arguments?.getInt(KEY_CODE)!!
        complaintType = arguments?.getInt(KEY_COMPLIANT_TYPE)!!
        Log.d("Complaint Code : " + complaint_code + " Complaint Type : " + complaintType)

        val regionListAC = view.regionListText
        circleListAC = view.circleListText
        divisionListAC = view.divisionListText
        subDivisionListAC = view.subDivisionListText
        sectionListAC = view.sectionListText
        reasonListAC = view.reasonListText
        masterLayout = view.masterLayout

        isCancelable = false


        view.button_Submit.setOnClickListener {

            if (complaintType == 2 || complaintType == 3) {
                val imm =
                    requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
                val loginResponse = TangedcoPreferenceManager.getProfile()

                val takeActionRequestApi = TakeActionRequestApi(
                    arguments?.getString(KEY_SUBTITLE)!!.toInt(),
                    loginResponse!!.regionId,
                    loginResponse.circleId,
                    loginResponse!!.divisionId,
                    loginResponse.subDivisionId,
                    loginResponse.sectionId,
                    1,
                    view.input_remarks.text.toString(),0
                )

                sendTakeAction(takeActionRequestApi)
            }
            if(complaintType==4)
            {
                val imm =
                    requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
                val loginResponse = TangedcoPreferenceManager.getProfile()

                val takeActionRequestApi = TakeActionRequestApi(
                    arguments?.getString(KEY_SUBTITLE)!!.toInt(),
                    loginResponse!!.regionId,
                    loginResponse.circleId,
                    loginResponse!!.divisionId,
                    loginResponse.subDivisionId,
                    loginResponse.sectionId,
                    2,
                    view.input_remarks.text.toString(),reasonId
                )

                sendTakeAction(takeActionRequestApi)
            }

            if (complaintType == 1) {

                if (regionId == 0) {
                    Toast.makeText(requireContext(), "Please Select Region", Toast.LENGTH_SHORT)
                        .show()

                } else if (circleId == 0) {
                    Toast.makeText(requireContext(), "Please Select Circle", Toast.LENGTH_SHORT)
                        .show()

                } else if (divisionId == 0) {
                    Toast.makeText(requireContext(), "Please Select Division", Toast.LENGTH_SHORT)
                        .show()

                } else if (subDivisionId == 0) {
                    Toast.makeText(
                        requireContext(),
                        "Please Select Sub Division",
                        Toast.LENGTH_SHORT
                    ).show()

                } else if (sectionId == 0) {
                    Toast.makeText(requireContext(), "Please Select Section", Toast.LENGTH_SHORT)
                        .show()

                } else {

                    val takeActionRequestApi = TakeActionRequestApi(
                        arguments?.getString(KEY_SUBTITLE)!!.toInt(),
                        regionId,
                        circleId,
                        divisionId,
                        subDivisionId,
                        sectionId,
                        1,
                        view.input_remarks.text.toString(),0
                    )
                    val gson = Gson()
                    val dataString = gson.toJson(takeActionRequestApi)

                    Log.d("Data String : " + dataString)
                    showLoader()
                    val mutableLiveData = viewModel.transferAction(takeActionRequestApi)
                    mutableLiveData!!.observe(this, Observer<BaseResponse> { responseData ->
                        hideLoader()
                        if (responseData.Iserror != 1) {

                            Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                            dismiss()

                        } else {
                            Toast.makeText(requireContext(), "Failure", Toast.LENGTH_SHORT).show()
                        }
                    })

                }
            }
        }

        view.imageView_close.setOnClickListener {
            dismiss()
        }

        if (complaintType == 1) {
            masterLayout.visibility = View.VISIBLE
            reasonListAC.visibility = View.GONE
            showLoader()
            val mutableLiveData = viewModel.getRegion()
            mutableLiveData!!.observe(this, Observer<RegionList> { responseData ->

                hideLoader()
                if (responseData.iserror != 1) {
                    val regionist: MutableList<ListDataResponse> = mutableListOf()
                    regionist.clear()
                    regionist.add(ListDataResponse("Select Region", 0))
                    regionist.addAll(responseData.regionList as MutableList<ListDataResponse>)
                    regionAdapter = RegionAdapter(regionist, requireContext())
                    regionListAC.adapter = regionAdapter

                } else {
                    Toast.makeText(requireContext(), responseData.messaage, Toast.LENGTH_SHORT)
                        .show()
                }
            })
        }

        if (complaintType == 2 || complaintType == 3) {
            masterLayout.visibility = View.GONE
            reasonListAC.visibility = View.GONE
        }

        if (complaintType == 4) {
            masterLayout.visibility = View.GONE
            reasonListAC.visibility = View.VISIBLE
            showLoader()
            val fetchReasonsInput = FetchReasonsInput(complaint_code)
            val mutableLiveDataReasons = viewModel.getReasons(fetchReasonsInput)
            mutableLiveDataReasons!!.observe(this, Observer<ReasonList> { responseData ->
                hideLoader()
                if (responseData.iserror != 1) {
                    val reasonList: MutableList<ListDataResponse> = mutableListOf()
                    reasonList.clear()
                    reasonList.add(ListDataResponse("Select Reason", 0))
                    reasonList.addAll(responseData.reasonList as MutableList<ListDataResponse>)
                    reasonAdapter = ReasonAdapter(reasonList, requireContext())
                    reasonListAC.adapter = reasonAdapter

                } else {
                    Toast.makeText(requireContext(), responseData.messaage, Toast.LENGTH_SHORT)
                        .show()
                }
            })
        }

        regionListAC.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {

                regionId = regionAdapter.localData.get(position).id!!

                if (regionId != 0) {
                    circleData(regionId)
                }

            }
        }

        return MaterialAlertDialogBuilder(
            requireContext(),
            R.style.ThemeOverlay_App_MaterialAlertDialog
        ).setView(view).setCancelable(false).create()
    }


    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

    }


    fun circleData(region: Int) {
        val fetchCircleInput = FetchCircleInput(region)
        showLoader()
        val mutableLiveData = viewModel.getCircle(fetchCircleInput)
        mutableLiveData!!.observe(this, Observer<CircleList> { responseData ->
            hideLoader()
            if (responseData.iserror != 1) {
                val circlelist: MutableList<ListDataResponse> = mutableListOf()
                circlelist.clear()
                circlelist.add(ListDataResponse("Select Circle", 0))
                circlelist.addAll(responseData.circleList!!)
                circleAdapter = CircleAdapter(circlelist, requireContext())
                circleListAC.adapter = circleAdapter

            } else {
                Toast.makeText(requireContext(), responseData.messaage, Toast.LENGTH_SHORT).show()
            }
        })

        circleListAC.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {

                circleId = circleAdapter.localData.get(position).id!!

                if (circleId != 0) {
                    divisionData(circleId)
                }

            }
        }
    }

    fun divisionData(circle: Int) {

        val fetchDivisionInput = FetchDivisionInput(circle)
        showLoader()
        val mutableLiveData = viewModel.getDivision(fetchDivisionInput)
        mutableLiveData!!.observe(this, Observer<DivisionList> { responseData ->
            hideLoader()
            if (responseData.iserror != 1) {
                val divisionList: MutableList<ListDataResponse> = mutableListOf()
                divisionList.clear()
                divisionList.add(ListDataResponse("Select Division", 0))
                divisionList.addAll(responseData.divisionList as MutableList<ListDataResponse>)
                divisionAdapter = DivisionAdapter(divisionList, requireContext())
                divisionListAC.adapter = divisionAdapter

            } else {
                Toast.makeText(requireContext(), responseData.messaage, Toast.LENGTH_SHORT).show()
            }
        })
        divisionListAC.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {

                divisionId = divisionAdapter.localData.get(position).id!!

                if (divisionId != 0) {
                    subdivisionData(divisionId)
                }

            }
        }
    }

    fun subdivisionData(division: Int) {
        val fetchSubDivisionInput = FetchSubDivisionInput(division)

        showLoader()
        val mutableLiveData = viewModel.getSubDivision(fetchSubDivisionInput)
        mutableLiveData!!.observe(this, Observer<SubDivisionList> { responseData ->
            hideLoader()
            if (responseData.iserror != 1) {
                val subdivisionlist: MutableList<ListDataResponse> = mutableListOf()
                subdivisionlist.clear()
                subdivisionlist.add(ListDataResponse("Select SubDivision", 0))
                if (responseData.subDivisionList != null) {
                    subdivisionlist.addAll(responseData.subDivisionList as MutableList<ListDataResponse>)
                    subdivisionAdapter = SubDivisionAdapter(subdivisionlist, requireContext())
                    subDivisionListAC.adapter = subdivisionAdapter
                } else {
                    Toast.makeText(requireContext(), "Sub Division List Empty", Toast.LENGTH_SHORT)
                        .show()

                }
            } else {
                Toast.makeText(requireContext(), responseData.messaage, Toast.LENGTH_SHORT).show()
            }
        })
        subDivisionListAC.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {

                subDivisionId = subdivisionAdapter.localData.get(position).id!!

                if (subDivisionId != 0) {
                    sectionData(subDivisionId)
                }

            }
        }
    }

    fun sectionData(subDivision: Int) {
        val fetchSectionInput = FetchSectionInput(subDivision)
        showLoader()
        val mutableLiveData = viewModel.getSection(fetchSectionInput)
        mutableLiveData!!.observe(this, Observer<SectionsList> { responseData ->
            hideLoader()
            if (responseData.iserror != 1) {
                val sectionList: MutableList<ListDataResponse> = mutableListOf()
                sectionList.clear()
                sectionList.add(ListDataResponse("Select Section", 0))
                sectionList.addAll(responseData.sectionList as MutableList<ListDataResponse>)
                sectionAdapter = SectionAdapter(sectionList, requireContext())
                sectionListAC.adapter = sectionAdapter

            } else {
                Toast.makeText(requireContext(), responseData.messaage, Toast.LENGTH_SHORT).show()
            }
        })
        sectionListAC.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {

                sectionId = sectionAdapter.localData.get(position).id!!

            }
        }
    }

    fun reasonData(complaintId: Int) {
        val fetchReasonsInput = FetchReasonsInput(complaintId)
        showLoader()
        val mutableLiveData = viewModel.getReasons(fetchReasonsInput)
        mutableLiveData!!.observe(this, Observer<ReasonList> { responseData ->
            hideLoader()
            if (responseData.iserror != 1) {
                val reasonList: MutableList<ListDataResponse> = mutableListOf()
                reasonList.clear()
                reasonList.add(ListDataResponse("Select Reason", 0))
                reasonList.addAll(responseData.reasonList as MutableList<ListDataResponse>)
                reasonAdapter = ReasonAdapter(reasonList, requireContext())
                reasonListAC.adapter = reasonAdapter

            } else {
                Toast.makeText(requireContext(), responseData.messaage, Toast.LENGTH_SHORT).show()
            }
        })
        reasonListAC.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {

                reasonId = reasonAdapter.localData.get(position).id!!

            }
        }
    }


    fun showLoader() {

        dialogLoader.setMessage("Loading...");
        dialogLoader.setCancelable(false);
        dialogLoader.setIndeterminate(true);
        dialogLoader.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialogLoader.show()

    }

    fun hideLoader() {
        dialogLoader.dismiss()

    }

    fun sendTakeAction(takeActionRequestApi: TakeActionRequestApi) {

        showLoader()
        val mutableLiveData = viewModel.takeAction(takeActionRequestApi)
        mutableLiveData.observe(this, Observer<BaseResponse> { responseData ->
            hideLoader()
            if (responseData.Iserror != 1) {
                // showToast("successfully saved")

                Toast.makeText(requireContext(), "Successfully saved", Toast.LENGTH_SHORT).show()
                dismiss()
            } else {
                Toast.makeText(requireContext(), responseData.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
