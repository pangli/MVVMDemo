package com.zorro.mvvm.ui.main

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zorro.mvvm.ui.main.base.BaseAndroidViewModel
import com.zorro.mvvm.ui.main.network.ServiceCreator
import com.zorro.mvvm.ui.main.network.WeatherService
import kotlinx.coroutines.*
import kotlin.random.Random

class MainViewModel(application: Application) : BaseAndroidViewModel(application) {
    var liveData: MutableLiveData<String>? = null
    var userData: MutableLiveData<User>? = null
    var random: Random? = null
    var userRepository = UserRepository()
    private val weatherService = ServiceCreator.create(WeatherService::class.java)
    lateinit var job1: Job
    lateinit var job2: Job
    lateinit var job3: Job

    init {
        random = Random
        liveData = MutableLiveData()
        userData = MutableLiveData()
        //liveData?.value = "点击${random?.nextInt(100)}"
        liveData?.value = "CN101020100"
    }

    fun buttonClick() {
        job1 = GlobalScope.launch(Dispatchers.Main) {
            delay(4000)
            liveData?.value = "CN10102010011"
            Log.e("Zorro", liveData?.value)
            Toast.makeText(getApplication(), liveData?.value, Toast.LENGTH_SHORT).show()
        }
        requestUserData()
//        runBlocking(Dispatchers.IO) {
        job2 = run({
            //delay(500)
            val response =
                userRepository.requestWeather(weatherService, liveData?.value ?: "CN101020100")
            //Log.e("Zorro", response.body()?.string())
            Log.e("Zorro", response.HeWeather.get(0).msg)
            Toast.makeText(getApplication(), response.HeWeather.get(0).msg, Toast.LENGTH_SHORT)
                .show()

        }, {})
//        runBlocking {
//            launch {
//                delay(1000)
//                job2.cancel()
//            }
//        }
//        }

    }


    fun getUser(): MutableLiveData<User> {
        return userData!!
    }

    private fun requestUserData() {
        job3 = viewModelScope.launch {
            userData?.value = userRepository.requestUser()
//            Toast.makeText(
//                getApplication(), String.format(
//                    getApplication().getString(R.string.name_and_age),
//                    it.name,
//                    it.age
//                ), Toast.LENGTH_SHORT
//            ).show()
        }
    }

    override fun onCleared() {
        super.onCleared()
        job1.cancel()
//        job2.cancel()
//        job3.cancel()
        Log.e(
            "Zorro",
            job1.isCancelled.toString() + "--" + job2.isCancelled + "--" + job3.isCancelled
        )

    }
}
