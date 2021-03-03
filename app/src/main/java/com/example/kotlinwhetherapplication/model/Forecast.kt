package com.example.kotlinwhetherapplication.model

import com.google.gson.annotations.SerializedName

data class Forecast (
    var coord: Coord,
    var weather: List<Weather>,
    var base: String? = null,
    var main:Main,
    var visibility:Int? = null,
    var wind: Wind,
    var clouds: Clouds,
    var dt:Int? = null,
    var sys: Sys,
    var timezone:Int? = null,
    var id:Int? = null,
    var name:String? = null,
    var cod:Int? = null
)
class Coord

class Weather

data class Main(
    var temp: Double = 0.0,
    @SerializedName("feels_like")
    var feelsLike: Double? = null,
    @SerializedName("temp_min")
    var tempMin: Double = 0.0,
    @SerializedName("temp_max")
    var tempMax: Double = 0.0,
    var pressure: Int? = null,
    var humidity: Int? = null
)

class Wind

class Clouds

class Sys