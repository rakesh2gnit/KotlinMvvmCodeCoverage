package com.myapp.kotlinmvvmcodecoverage.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.view.View
import android.widget.Toast
import com.myapp.kotlinmvvmcodecoverage.R
import com.myapp.kotlinmvvmcodecoverage.Validator
import com.myapp.kotlinmvvmcodecoverage.model.LoginRequest
import com.myapp.kotlinmvvmcodecoverage.model.LoginResponse
import com.myapp.kotlinmvvmcodecoverage.network.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RegisterViewModel(application: Application) : AndroidViewModel(application) {

    val etEmail: MutableLiveData<String> = MutableLiveData()
    val etPassword: MutableLiveData<String> = MutableLiveData()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val pbar: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { getRegister(etEmail.value.toString(), etPassword.value.toString()) }

    private lateinit var subscription: Disposable

    init {
        //pbar.value = View.GONE
        //getRegister(etEmail.value.toString(), etPassword.value.toString())
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    fun getRegister(email: String, password: String) {
        val apiService = ApiClient.create()
        subscription = apiService.getLoinDetails(LoginRequest(email, password))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> onRetrieveLoginResponseSuccess(result) },
                { onRetrieveLoginResponseError() }
            )
    }

    private fun onRetrieveLoginResponseSuccess(loginResponse: LoginResponse) {
        errorMessage.value = loginResponse.StatusCode
    }

    private fun onRetrieveLoginResponseError() {
        errorMessage.value = R.string.login_req_error
    }

    fun onLoginClicked() {
        pbar.value = View.VISIBLE
        if (Validator.isEmailValid(etEmail.value.toString()) && Validator.isPasswordValid(etPassword.value.toString()))
            getRegister(etEmail.value.toString(), etPassword.value.toString())
        else
            Toast.makeText(getApplication(), R.string.login_error, Toast.LENGTH_SHORT).show()
    }
}
