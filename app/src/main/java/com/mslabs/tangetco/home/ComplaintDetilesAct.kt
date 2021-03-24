package com.mslabs.tangetco.home

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.daimajia.slider.library.Animations.DescriptionAnimation
import com.daimajia.slider.library.SliderLayout
import com.daimajia.slider.library.SliderTypes.BaseSliderView
import com.daimajia.slider.library.SliderTypes.DefaultSliderView
import com.mslabs.tangetco.R
import com.mslabs.tangetco.api.response.ComplaintList
import com.mslabs.tangetco.common.BaseActivity
import kotlinx.android.synthetic.main.activity_complaintdetiles.*
import java.util.*


class ComplaintDetilesAct : BaseActivity(), BaseSliderView.OnSliderClickListener {


    override fun onSliderClick(slider: BaseSliderView?) {

        imageslider = slider!!.getBundle().get("extra").toString()
        if (imageslider.equals("Slider 1")) {
            openInGallery(imageurlone)
        } else {
            openInGallery(imageurltwo)
        }


    }

    companion object {

        private val BUNDLE_INPUT = "_input"
        private var Complaint_Number = ""

        lateinit var dateform: String

        fun getInstance(fromActivity: Activity, complaintList: ComplaintList): Intent {
            val bundle = Bundle()
            val intent = Intent(fromActivity, ComplaintDetilesAct::class.java)
            bundle.putParcelable(BUNDLE_INPUT, complaintList)
            intent.putExtras(bundle)
            return intent
        }
    }

    lateinit var complaintList: ComplaintList

    var imageurlone: String = ""
    var imageurltwo: String = ""
    var imageslider: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complaintdetiles)
        setActionBar()
        setHomeUpEnable()

        if (Build.VERSION.SDK_INT >= 21) {

            window.statusBarColor =
                ContextCompat.getColor(
                    this,
                    R.color.colorPrimary
                ) //status bar or the time bar at the top
        }



        if (intent != null) {
            complaintList = intent.getParcelableExtra(ComplaintDetilesAct.BUNDLE_INPUT)
            ComplaintListData(complaintList)
            initSlider(complaintList)
            supportActionBar!!.title = complaintList.complaintNo.toString()

            // Log.d("Image Url 1: " + complaintList.imageOne)
            //Log.d("Image Url 2: " + complaintList.imageTwo)
            //supportActionBar!!.title = complaintList.complaint_no
            /*  app_bar_layout.setOnClickListener {
                  // Log.d("Slider Postition : "+slider_layout.currentPosition)
                  showToast("Click Data")
              }*/


        } else {
            showToast("Image Empty")
        }
        track_complaint.setOnClickListener {
            // showToast("Click History")
            /* val intent = ComplaintHistoryActivity.getInstance(this, complaintList)
             val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this)
             startActivity(intent, options.toBundle())
 */
            showToast("Yet to be implemented")

        }
    }

    fun ComplaintListData(complaintList: ComplaintList) {


        setToolbarTitle(complaintList.complaintNo.toString())

        if (!TextUtils.isEmpty(complaintList.complaintNo.toString())) {
            addProfileItem(complaintList.complaintNo.toString()!!, "Complaint No")
            addDivider()
        }
        if (!TextUtils.isEmpty(complaintList.statusName)) {
            addProfileItem(complaintList.statusName!!, "Status")
            addDivider()
        }
        if (!TextUtils.isEmpty(complaintList.district)) {
            addProfileItem(complaintList.district!!, "District")
            addDivider()
        }
        if (!TextUtils.isEmpty(complaintList.section)) {
            addProfileItem(complaintList.section!!.trim(), "Section")
            addDivider()
        }
        if (!TextUtils.isEmpty(complaintList.landmark)) {
            addProfileItem(complaintList.landmark!!, "Landmark")
            addDivider()
        }
        if (!TextUtils.isEmpty(complaintList.circle)) {
            addProfileItem(complaintList.circle!!, "Circle")
            addDivider()
        }

    }

    private fun addProfileItem(primaryText: String, secondaryText: String) {
        val inflater = layoutInflater
        val profileItem = inflater.inflate(R.layout.card_complaint_detiles, null)

        val primaryTextView = profileItem.findViewById<TextView>(R.id.profile_item_primary_text)
        val secondaryTextView = profileItem.findViewById<TextView>(R.id.profile_item_secondary_text)


        primaryTextView.text = primaryText
        secondaryTextView.text = secondaryText

        profile_details_layout!!.addView(profileItem)
    }

    private fun addDivider() {
        val divider = ImageView(this)
        val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1)
        lp.setMargins(80, 10, 80, 10)
        divider.layoutParams = lp
        profile_details_layout!!.addView(divider)
    }


    fun openInGallery(imageId: String) {
        val it = Intent(Intent.ACTION_VIEW)
        it.setDataAndType(Uri.parse(imageId), "image/*")
        startActivity(it)

    }

    fun initSlider(complaintList: ComplaintList) {
        val file_maps = HashMap<String, String>()

        file_maps.put("Slider 1", complaintList.image_1.toString())
        file_maps.put("Slider 2", complaintList.image_1.toString())

        imageurlone = complaintList.image_1.toString()
        imageurltwo = complaintList.image_1.toString()
        for (name in file_maps.keys) {
            val textSliderView = DefaultSliderView(this)
            // initialize a SliderLayout
            textSliderView
                .image(file_maps[name]).scaleType = BaseSliderView.ScaleType.Fit
            textSliderView.bundle(Bundle())
            textSliderView.bundle
                .putString("extra", name)
            textSliderView.setOnSliderClickListener(this)
            slider_layout.addSlider(textSliderView)
        }

        slider_layout.setPresetTransformer(SliderLayout.Transformer.Accordion)
        slider_layout.setCustomIndicator(pager_indicator)
        slider_layout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom)
        slider_layout.setCustomIndicator(pager_indicator)
        slider_layout.setCustomAnimation(DescriptionAnimation())
        //slider_layout.setDuration(4000)

    }


}