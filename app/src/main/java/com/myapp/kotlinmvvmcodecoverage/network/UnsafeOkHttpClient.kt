package com.myapp.kotlinmvvmcodecoverage.network

import com.myapp.kotlinmvvmcodecoverage.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

/**
 * Created by Naresh Ravva on 1/17/2019.
 */

object UnsafeOkHttpClient {

    // Create a trust manager that does not validate certificate chains
    // Define the interceptor, add authentication headers
    // Install the all-trusting trust manager
    // Create an ssl socket factory with our all-trusting manager
    val unsafeOkHttpClient: OkHttpClient
        get() {
            try {
                val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                    override fun checkClientTrusted(
                        chain: Array<java.security.cert.X509Certificate>,
                        authType: String
                    ) {
                    }

                    override fun checkServerTrusted(
                        chain: Array<java.security.cert.X509Certificate>,
                        authType: String
                    ) {
                    }

                    override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                        return arrayOf()
                    }
                })
                val interceptor = Interceptor { chain ->
                    val newRequest = chain.request().newBuilder().addHeader("Content-Type", "application/json").build()
                    chain.proceed(newRequest)
                }

                val interceptorLog = HttpLoggingInterceptor()
                if (BuildConfig.DEBUG) {
                    interceptorLog.level = HttpLoggingInterceptor.Level.BODY
                } else {
                    interceptorLog.level = HttpLoggingInterceptor.Level.NONE
                }
                val sslContext = SSLContext.getInstance("SSL")
                sslContext.init(null, trustAllCerts, java.security.SecureRandom())
                val sslSocketFactory = sslContext.socketFactory

                val builder = OkHttpClient.Builder()
                builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
                builder.hostnameVerifier { hostname, session -> true }
                builder.addInterceptor(interceptorLog)

                builder.addInterceptor(interceptor)
                builder.readTimeout((4 * 60 * 1000).toLong(), TimeUnit.MILLISECONDS)
                builder.connectTimeout((4 * 60 * 1000).toLong(), TimeUnit.MILLISECONDS)

                return builder.build()
            } catch (e: Exception) {
                e.printStackTrace()
                throw RuntimeException(e)
            }

        }
}