package com.example.kotlinwhetherapplication.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var retrofit: Retrofit? = null
    fun getClient(baseUrl: String): Retrofit{
        if(retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }

//    private val gson:Gson = GsonBuilder().setDateFormat("main").create()
    fun getWeatherClient(baseUrl: String): Retrofit{
        if(retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}