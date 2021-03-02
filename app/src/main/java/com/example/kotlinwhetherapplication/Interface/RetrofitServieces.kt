package com.example.kotlinwhetherapplication.Interface

import com.example.kotlinwhetherapplication.Model.Forecast
import com.example.kotlinwhetherapplication.Model.Movie
import retrofit2.http.*
import retrofit2.Call

interface RetrofitServieces {
    @GET("marvel")
    fun getMovieList(): Call<MutableList<Movie>>

    @GET("weather?q=Saratov&appid=c49f1b4058370ccbda6ce765e22bcc75")
    fun getWeatherList(): Call<Forecast>
}