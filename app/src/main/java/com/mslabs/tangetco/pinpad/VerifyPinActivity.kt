package com.mslabs.tangetco.pinpad

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import com.mslabs.tangetco.MainActivity
import com.mslabs.tangetco.R
import com.mslabs.tangetco.common.BaseActivity
import com.mslabs.tangetco.common.PinPadView
import com.mslabs.tangetco.login.LoginActivity
import com.mslabs.tangetco.util.preference.TangedcoPreferenceManager
import kotlinx.android.synthetic.main.activity_verify_pin.*


class VerifyPinActivity : BaseActivity(), PinPadView.PinPadViewListener {


    val MAX_PIN_LENGTH = 4
    val MAX_UNLOCK_ATTEMPTS = 3
    private var pinCode: String? = null

    private var errorPinCounter = 0

    var pinEntryItems: ArrayList<ImageView>? = ArrayList()


    private var loginRequested = false

    var currentPinEntryIndex = 0

    companion object {
        fun newInstance(context: Context): Intent {
            return Intent(context, VerifyPinActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_pin)
        setupPinEntryFields()

        numpad_layout.setCallback(this)

    }

    override fun onPinPadButtonPress(pinPadCode: Int) {
        if (pinPadCode == PinPadView.LEFT_ACTION) {
            return  // We don't need a right action button
        }
        if (pinPadCode == PinPadView.RIGHT_ACTION) {
            // Delete button is the left action button
            if (currentPinEntryIndex > 0) {
                clearLastPinElement()
            }
        } else {
            // Other pin pad presses should be numbers
            setPinElement(pinPadCode)
        }
    }

    private fun resetPinFields() {
        loginRequested = false
        for (iv in pinEntryItems!!) {
            iv.tag = null
            iv.setImageDrawable(resources.getDrawable(R.drawable.pin_element_blank))
        }
        currentPinEntryIndex = 0
    }

    private fun setupPinEntryFields() {
        pin_element_1.setTag(null)
        pin_element_2.setTag(null)
        pin_element_3.setTag(null)
        pin_element_4.setTag(null)
        pinEntryItems =
            ArrayList<ImageView>(MAX_PIN_LENGTH)
        pinEntryItems!!.add(pin_element_1)
        pinEntryItems!!.add(pin_element_2)
        pinEntryItems!!.add(pin_element_3)
        pinEntryItems!!.add(pin_element_4)
    }

    private fun setPinElement(digit: Int) {
        if (currentPinEntryIndex < MAX_PIN_LENGTH) {
            val current: ImageView =
                pinEntryItems!!.get(currentPinEntryIndex)
            current.setImageDrawable(resources.getDrawable(R.drawable.pin_element_entered))
            current.tag = digit
            currentPinEntryIndex++
        }
        if (currentPinEntryIndex == MAX_PIN_LENGTH) {
            checkPin()
        }
    }

    private fun clearLastPinElement() {
        if (currentPinEntryIndex-- >= 0) {
            val current: ImageView =
                pinEntryItems!!.get(currentPinEntryIndex)
            current.setImageDrawable(resources.getDrawable(R.drawable.pin_element_blank))
            current.tag = null
        }
    }

    private fun checkPin() {
        // Do nothing until we have our 4-digit PIN ...
        if (currentPinEntryIndex == MAX_PIN_LENGTH) {
            val sb =
                StringBuilder(MAX_PIN_LENGTH)
            sb.append(pin_element_1.tag)
            sb.append(pin_element_2.tag)
            sb.append(pin_element_3.tag)
            sb.append(pin_element_4.tag)
            val currentPin: String = sb.toString()
            pinCode =TangedcoPreferenceManager.getPin()
            if (currentPin != null) {
                if (currentPin != pinCode) {
                    errorPinCounter++
                    if (errorPinCounter >= MAX_UNLOCK_ATTEMPTS) {
                        showToast(getString(R.string.app_lock_re_login_message))
                        TangedcoPreferenceManager.setPin("")
                        TangedcoPreferenceManager.setUserLoggedIn(false)
                        val intent =
                            Intent(this@VerifyPinActivity, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                        return
                    }
                    var message =
                        getString(R.string.app_lock_incorrect_pin_title) + " - "
                    if (errorPinCounter == 1) {
                        message += getString(R.string.app_lock_incorrect_pin_attempt_first)
                    } else if (errorPinCounter == 2) {
                        message += getString(R.string.app_lock_incorrect_pin_attempt_second)
                    }
                    showToast(message)
                    resetPinFields()
                } else {
                    if (!loginRequested) {
                       startActivityLeftToRight(MainActivity.newInstance(this))
                        finish()
                        loginRequested = true
                    }
                }
            }
        }
    }
}

