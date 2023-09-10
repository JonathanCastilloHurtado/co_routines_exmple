package com.afore_xxi.co_rutines_exmple

import retrofit2.Call
import retrofit2.http.GET

interface APIUrl {
    @GET("apis/get_book.php")
    fun apiPrueba(): Call<ApiResult?>?

}