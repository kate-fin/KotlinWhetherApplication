package com.example.kotlinwhetherapplication.Model

//class Forecast {
//    lateinit var coord:coord
//    lateinit var weather:weather
//    var base: String? = null
//    lateinit var main:main
//    var visibility:Int? = null
//    lateinit var wind: wind
//    lateinit var clouds: clouds
//    var dt:Int? = null
//    lateinit var sys: sys
//    var timezone:Int? = null
//    var id:Int? = null
//    var name:String? = null
//    var cod:Int? = null
//}

data class Forecast (
    var coord:coord,
    var weather:weather,
    var base: String? = null,
    var main:main,
    var visibility:Int? = null,
    var wind: wind,
    var clouds: clouds,
    var dt:Int? = null,
    var sys: sys,
    var timezone:Int? = null,
    var id:Int? = null,
    var name:String? = null,
    var cod:Int? = null
)

data class coord(
    var lon: Double? = null,
    var lat: Double? = null
)

class weather()

data class main(
    var temp: Double = 0.0,
    var feels_like: Double? = null,
    var temp_min: Double = 0.0,
    var temp_max: Double = 0.0,
    var pressure: Int? = null,
    var humidity: Int? = null
)

data class wind(
    var speed:Double? = null,
    var deg:Int? = null,
    var gust:Double? = null
)

data class clouds(
    var all: Int? = null
)

data class sys(
    var type: Int? = null,
    var id: Int? = null,
    var country: String? = null,
    var sunrise: Int? = null,
    var sunset: Int? = null
)