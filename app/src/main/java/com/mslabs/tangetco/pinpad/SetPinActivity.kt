package com.mslabs.tangetco.pinpad

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import com.mslabs.tangetco.R
import com.mslabs.tangetco.common.BaseActivity
import com.mslabs.tangetco.util.Log
import com.mslabs.tangetco.util.preference.TangedcoPreferenceManager
import kotlinx.android.synthetic.main.activity_set_pin.*


class SetPinActivity : BaseActivity() {
    companion object {
        fun newInstance(context: Context): Intent {
            return Intent(context, SetPinActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_pin)

        button_set_pin.setOnClickListener {
            if (validPins(input_enter_pin.text.toString(), input_confirm_pin.text.toString())) {
                Log.d("Success Pin")
                TangedcoPreferenceManager.setPin(input_enter_pin.text.toString())
                startActivity(VerifyPinActivity.newInstance(this))
                finish()
            } else {
                Log.d("Failure Pin")

            }
        }

    }


    private fun validPins(
        pinEntered: String,
        confirmPin: String
    ): Boolean {
        if (TextUtils.isEmpty(pinEntered)) {
            showToast(getString(R.string.app_lock_enter_pin_text))
            return false
        }
        if (TextUtils.isEmpty(confirmPin)) {
            showToast(getString(R.string.app_lock_confirm_pin_text))
            return false
        }
        if (confirmPin.length != 4 || pinEntered.length != 4) {
            showToast(getString(R.string.app_lock_pin_length_fails))
            return false
        }
        if (pinEntered != confirmPin) {
            showToast(
                getString(R.string.app_lock_confirm_pin_not_matches)
            )
            return false
        }
        return true
    }

}