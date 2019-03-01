package com.myapp.kotlinmvvmcodecoverage

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.databinding.DataBindingUtil
import com.myapp.kotlinmvvmcodecoverage.model.User

class OneWayBindingActivity : AppCompatActivity() {

    private var binding: com.myapp.kotlinmvvmcodecoverage.databinding.ActivityOnewayBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the `activity_main` layout
        binding = DataBindingUtil.setContentView(this, R.layout.activity_oneway)
        // Store the field now if you'd like without any need for casting
        //val tvLabel = binding.tvLabel
        //tvLabel.setAllCaps(true)
        // Or use the binding to update views directly on the binding
        //binding.tvLabel.setText("Foo")
        // Create or access the data to bind
        val user = User("Sarah", "Gibbons")
        // Attach the user to the binding
        binding?.setUser(user)
    }
}