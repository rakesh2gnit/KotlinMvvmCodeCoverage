package com.myapp.kotlinmvvmcodecoverage

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.myapp.kotlinmvvmcodecoverage.databinding.ActivityLoginBinding
import com.myapp.kotlinmvvmcodecoverage.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val loginBinding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        loginBinding.loginViewModel = LoginViewModel(this)
        loginBinding.executePendingBindings()
    }
}
