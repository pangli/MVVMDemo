package com.zorro.mvvm.ui.main.model

/**
 * Created by pangli on 2019/9/27.
 */
data class Weather(
    var HeWeather: List<HeWeather>
)

data class HeWeather(
    var aqi: Aqi,
    var basic: Basic,
    var daily_forecast: List<DailyForecast>,
    var msg: String,
    var now: Now,
    var status: String,
    var suggestion: Suggestion,
    var update: UpdateX
)

data class Aqi(
    var city: City
)

data class City(
    var aqi: String,
    var pm25: String,
    var qlty: String
)

data class Basic(
    var admin_area: String,
    var cid: String,
    var city: String,
    var cnty: String,
    var id: String,
    var lat: String,
    var location: String,
    var lon: String,
    var parent_city: String,
    var tz: String,
    var update: Update
)

data class Update(
    var loc: String,
    var utc: String
)

data class DailyForecast(
    var cond: Cond,
    var date: String,
    var tmp: Tmp
)

data class Cond(
    var txt_d: String
)

data class Tmp(
    var max: String,
    var min: String
)

data class Now(
    var cloud: String,
    var cond: CondX,
    var cond_code: String,
    var cond_txt: String,
    var fl: String,
    var hum: String,
    var pcpn: String,
    var pres: String,
    var tmp: String,
    var vis: String,
    var wind_deg: String,
    var wind_dir: String,
    var wind_sc: String,
    var wind_spd: String
)

data class CondX(
    var code: String,
    var txt: String
)

data class Suggestion(
    var comf: Comf,
    var cw: Cw,
    var sport: Sport
)

data class Comf(
    var brf: String,
    var txt: String,
    var type: String
)

data class Cw(
    var brf: String,
    var txt: String,
    var type: String
)

data class Sport(
    var brf: String,
    var txt: String,
    var type: String
)

data class UpdateX(
    var loc: String,
    var utc: String
)