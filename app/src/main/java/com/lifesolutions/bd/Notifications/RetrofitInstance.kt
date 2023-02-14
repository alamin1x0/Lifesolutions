package com.lifesolutions.bd.Notifications

import com.lifesolutions.bd.kotlinCode.constant.FcmConstant.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object{
        private val retrofit by lazy{
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }

        val notificationApi: NotificationApi by lazy {
            retrofit.create(NotificationApi::class.java)
        }

    }
}