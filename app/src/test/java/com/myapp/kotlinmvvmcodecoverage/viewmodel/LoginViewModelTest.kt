package com.myapp.kotlinmvvmcodecoverage.viewmodel

import android.content.Context

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

import org.junit.Assert.assertEquals


class LoginViewModelTest {

    private var viewModel: LoginViewModel? = null

    @Mock
    private lateinit var mockContext: Context

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        viewModel = LoginViewModel(mockContext)
    }

    @Test
    fun testServerLoginSuccess() {
        val email = "kkukkadapi@agility.com"
        val password = "Agility123"

        val data = viewModel?.getLogin(email, password)

        assertEquals(true, data)
    }

    @Test
    fun handleResponseSuccess(){
        val data = viewModel?.handleResponse1(200)
        assertEquals("Logged In Successfully!.", data)
    }

    @Test
    fun handleResponseFailed(){
        val data = viewModel?.handleResponse1(417)
        assertEquals("Invalid Email Id or Password", data)
    }

    /*@Test
    fun testServerHandleSuccess() {

        val email = "kkukkadapi@agility.com"
        val password = "Agility123"

        //{"StatusCode": 200,"Message": "Logged In Successfully!."}

        val loginResponse = LoginResponse(200, "Logged In Successfully!.");

        val data = viewModel?.handleResponse(loginResponse)

        assertEquals(true, data)
    }*/
}
