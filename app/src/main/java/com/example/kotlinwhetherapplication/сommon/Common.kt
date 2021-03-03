package com.example.kotlinwhetherapplication.сommon

import com.example.kotlinwhetherapplication.interfaces.RetrofitServieces
import com.example.kotlinwhetherapplication.retrofit.RetrofitClient

object Common {
    private const val BASE_URL = "https://www.simplifiedcoding.net/demos/"
    val marvelRetrofitServieces: RetrofitServieces
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServieces::class.java)

    private const val MY_URL = "http://api.openweathermap.org/data/2.5/"
    val weatherRetrofitServieces: RetrofitServieces
        get() = RetrofitClient.getWeatherClient(MY_URL).create(RetrofitServieces::class.java)

}