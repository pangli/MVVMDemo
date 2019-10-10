package com.zorro.mvvm.ui.main.network


import com.zorro.mvvm.ui.main.model.Weather
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by pangli on 2019/9/27.
 */
interface WeatherService {

    @GET("api/weather")
    //fun getWeather(@Query("cityid") weatherId: String): Deferred<Response<ResponseBody>>
    suspend fun getWeather(@Query("cityid") weatherId: String): Weather


}