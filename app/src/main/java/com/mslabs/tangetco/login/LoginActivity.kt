package com.mslabs.tangetco.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.AppCompatEditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.mslabs.tangetco.BuildConfig
import com.mslabs.tangetco.R
import com.mslabs.tangetco.api.response.LoginResponse
import com.mslabs.tangetco.common.BaseActivity
import com.mslabs.tangetco.databinding.ActivityLoginBinding
import com.mslabs.tangetco.pinpad.SetPinActivity
import com.mslabs.tangetco.util.Log
import com.mslabs.tangetco.util.preference.TangedcoPreferenceManager

class LoginActivity : BaseActivity() {

    companion object {
        fun newInstance(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }


    lateinit var dataBinding: ActivityLoginBinding

    val loginViewModel: LoginViewModel by lazy {
        ViewModelProvider(this)
            .get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        dataBinding.viewModel = loginViewModel
        dataBinding.lifecycleOwner = this
        dataBinding.inputMethodManager = getSystemService(
            Context.INPUT_METHOD_SERVICE
        ) as InputMethodManager


        if (BuildConfig.DEBUG) {
            /*   loginViewModel.loginUserName.value = "cbn120ae"
               loginViewModel.loginPassword.value = "cbn120ae"
    */
             /* loginViewModel.loginUserName.value = "chc221ae"
             loginViewModel.loginPassword.value = "chc221ae"
*/
           /* loginViewModel.loginUserName.value = "sekk"
            loginViewModel.loginPassword.value = "sekk"*/

            /*   loginViewModel.loginUserName.value = "ers405ae"
            loginViewModel.loginPassword.value = "ers405ae"*/

        }

        dataBinding.buttonLogin.setOnClickListener {
            handleUserLogin()
        }
    }


    private fun handleUserLogin() {
        if (validateData()) {
            showLoader()
            val mutableLiveData = loginViewModel.loginUser(
                loginViewModel.loginUserName.value,
                loginViewModel.loginPassword.value
            )
            mutableLiveData.observe(this, Observer<LoginResponse> { responseData ->
                dismissLoadingDialog()
                if (responseData.iserror == null) {
                    showSnackbar(
                        window.decorView,
                        responseData.message,
                        Snackbar.LENGTH_LONG
                    )
                } else {
                    if (responseData.iserror != 1) {

                        Log.d("Success Login !!!")

                        TangedcoPreferenceManager.saveProfile(responseData)
                        TangedcoPreferenceManager.setUserLoggedIn(true)
                        startActivity(SetPinActivity.newInstance(this))
                        finish()

                    } else {
                        showSnackbar(
                            window.decorView,
                            responseData.message,
                            Snackbar.LENGTH_LONG
                        )
                    }
                }
            })

        }
    }


    private fun validateData(): Boolean {
        val isValid = true
        val userNameField: AppCompatEditText = dataBinding.inputUserName
        if (TextUtils.isEmpty(userNameField.text)) {
            userNameField.requestFocus()
            userNameField.error =
                getErrorMessage(resources.getString(R.string.alert_please_enter_valid_username))
            return false
        } else {
            userNameField.error = null

        }

        val passwordField = dataBinding.inputPassword
        if (TextUtils.isEmpty(passwordField.text)) {
            passwordField.requestFocus()
            passwordField.error =
                getErrorMessage(resources.getString(R.string.alert_please_enter_password))
            return false
        } else {
            passwordField.error = null
        }
        return isValid
    }

}