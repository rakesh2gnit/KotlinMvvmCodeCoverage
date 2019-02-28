package com.myapp.kotlinmvvmcodecoverage

import com.myapp.kotlinmvvmcodecoverage.Validator.isEmailValid
import com.myapp.kotlinmvvmcodecoverage.Validator.isPasswordValid
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

class ValidatorTest {

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun emailError_invalidEmail_showInvalidEmail() {
        assertEquals(false, isEmailValid("agility"))
    }

    @Test
    fun emailError_emptyEmail_showInvalidEmail() {
        //viewModel?.afterEmailTextChanged("")
        assertEquals(false, isEmailValid(""))
    }

    @Test
    fun emailError_validEmail_notShowError() {
        assertEquals(true, isEmailValid("kkukkadapu@agility.com"))
    }

    @Test
    fun passwordError_shortPassword_showError() {
        assertEquals(false, isPasswordValid("1234"))
    }

    @Test
    fun confirmError_validPassword_notShowError() {
        assertEquals(true, isPasswordValid("12345678"))
    }
}