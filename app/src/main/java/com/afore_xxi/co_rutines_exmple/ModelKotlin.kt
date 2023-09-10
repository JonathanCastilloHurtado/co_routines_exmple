package com.afore_xxi.co_rutines_exmple

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
 import java.util.concurrent.TimeUnit


class ModelKotlin {
    private fun getRetrofit(urlBase:String) : Retrofit {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    val okHttpClient: OkHttpClient =
        OkHttpClient.Builder() //se agrega esto para el sochetexception
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(httpLoggingInterceptor).build()
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(urlBase) //Change server URL
        .client(okHttpClient)
        .build()
}
    fun getFinalURL(urlBase:String): APIUrl?{
        return getRetrofit(urlBase).create(APIUrl::class.java)
    }

}

