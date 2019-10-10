package com.zorro.mvvm.ui.main

import com.zorro.mvvm.ui.main.network.WeatherService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.random.Random

/**
 * Created by bona.xiao on 2019/9/19.
 * 备注：
 */
class UserRepository {

    suspend fun requestUser(): User {
        return withContext(Dispatchers.IO) {
            delay(3000)
            val user = User()
            user.name = "你爸爸我"
            user.age = Random.nextInt(100)
            user
        }
    }

    suspend fun requestWeather(weatherService: WeatherService, weatherId: String) =
        withContext(Dispatchers.IO) {
            delay(1000)
            weatherService.getWeather(weatherId)
        }
}