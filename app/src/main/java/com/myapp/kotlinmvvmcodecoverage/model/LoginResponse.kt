package com.myapp.kotlinmvvmcodecoverage.model

data class LoginResponse(
        val StatusCode: Int,
        val Message: String
)

data class LoginResponseResult(val total_count: Int, val incomplete_results: Boolean, val items: List<LoginResponse>)
