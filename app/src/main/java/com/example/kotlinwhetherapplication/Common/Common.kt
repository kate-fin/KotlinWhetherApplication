package com.example.kotlinwhetherapplication.Common

import com.example.kotlinwhetherapplication.Interface.RetrofitServieces
import com.example.kotlinwhetherapplication.Retrofit.RetrofitClient

object Common {
    private val BASE_URL = "https://www.simplifiedcoding.net/demos/"
    val retrofitServieces: RetrofitServieces
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServieces::class.java)

    private val MY_URL = "http://api.openweathermap.org/data/2.5/"
    val weatherRetrofitServieces: RetrofitServieces
        get() = RetrofitClient.getWeatherClient(MY_URL).create(RetrofitServieces::class.java)

}