package com.myapp.kotlinmvvmcodecoverage

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.myapp.kotlinmvvmcodecoverage.databinding.ActivityRegisterBinding
import com.myapp.kotlinmvvmcodecoverage.viewmodel.RegisterViewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)

        viewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })

        binding.registerViewModel = viewModel
    }

    private fun showError(@StringRes errorMessage: Int) {
        Log.e("RegAct", errorMessage.toString())
        //dialog.show()
    }

    private fun hideError() {
        //dialog.dismiss()
    }
}