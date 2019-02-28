package com.myapp.kotlinmvvmcodecoverage

object Validator {

    fun isEmailValid(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return email.matches(emailPattern.toRegex())
    }

    fun isPasswordValid(password: String): Boolean {
        return password.length > 4
    }
}
