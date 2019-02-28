package com.myapp.kotlinmvvmcodecoverage.viewmodel

import android.content.Context
import android.content.Intent
import android.databinding.BaseObservable

import android.widget.Toast
import com.myapp.kotlinmvvmcodecoverage.ListActivity
import com.myapp.kotlinmvvmcodecoverage.R
import com.myapp.kotlinmvvmcodecoverage.Validator
import com.myapp.kotlinmvvmcodecoverage.model.LoginRequest
import com.myapp.kotlinmvvmcodecoverage.model.LoginResponse
import com.myapp.kotlinmvvmcodecoverage.network.ApiClient

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginViewModel(internal var mContext: Context) : BaseObservable() {

    private var Email = ""
    private var Password = ""
    private var mCompositeDisposable: CompositeDisposable? = CompositeDisposable()

    fun afterEmailTextChanged(s: CharSequence) {
        Email = s.toString()
    }

    fun afterPasswordTextChanged(s: CharSequence) {
        Password = s.toString()
    }

    fun onLoginClicked() {
        if (Validator.isEmailValid(Email) && Validator.isPasswordValid(
                Password
            )
        )
            getLogin(Email, Password)
        else
            Toast.makeText(mContext, R.string.login_error, Toast.LENGTH_SHORT).show()
    }

    fun getLogin(email: String, password: String): Boolean? {
        val apiService = ApiClient.create()
        return mCompositeDisposable?.add(
                apiService.getLoinDetails(LoginRequest(email, password))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        //.subscribe({ result -> setToastMessage1(result.message) }, { error -> error.printStackTrace() })
                        .subscribe(this::handleResponse, this::handleError)
        )
    }

    fun handleResponse(loginResponse: LoginResponse) {
        //Log.e("Tests:::","Code"+loginResponse.StatusCode);
        if (loginResponse.StatusCode == 200) {
            Toast.makeText(mContext, loginResponse.Message, Toast.LENGTH_SHORT).show()
            val login = Intent(mContext, ListActivity::class.java)
            mContext.startActivity(login)
        } else {
            Toast.makeText(mContext, loginResponse.Message, Toast.LENGTH_SHORT).show()
        }
    }

    fun handleResponse1(statusCode: Int): String {
        //Log.e("Tests:::","Code"+loginResponse.StatusCode);
        if (statusCode == 200) {
            return "Logged In Successfully!."
        } else {
            return "Invalid Email Id or Password"
        }
    }

    private fun handleError(error: Throwable) {
        error.printStackTrace()
    }
}
