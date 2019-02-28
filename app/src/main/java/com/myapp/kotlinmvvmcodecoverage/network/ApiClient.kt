package com.myapp.kotlinmvvmcodecoverage.network

//import com.agility.shipafreightpoc.firstapproach.network.UnsafeOkHttpClient
import com.myapp.kotlinmvvmcodecoverage.model.LoginRequest
import com.myapp.kotlinmvvmcodecoverage.model.LoginResponse
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by Naresh Ravva on 1/17/2019.
 */
interface ApiClient {

    /**
     * Companion object to create the ApiClient
     */
    companion object Factory {
        var okHttpClient = UnsafeOkHttpClient.unsafeOkHttpClient
        fun create(): ApiClient {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl("https://agile.shipaconnect.com/api/")
                .build()

            return retrofit.create(ApiClient::class.java);
        }
    }

    @POST("UserManagement/Login")
    fun getLoinDetails(@Body credentials: LoginRequest): Observable<LoginResponse>
}