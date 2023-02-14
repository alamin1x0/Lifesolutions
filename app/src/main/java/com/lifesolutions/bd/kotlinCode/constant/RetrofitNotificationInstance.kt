package com.lifesolutions.bd.kotlinCode.constant

import com.lifesolutions.bd.kotlinCode.`interface`.NotificationAPI
import com.lifesolutions.bd.kotlinCode.constant.FcmConstant.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitNotificationInstance {

    companion object {
        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val api: NotificationAPI by lazy {
            retrofit.create(NotificationAPI::class.java)
        }
    }

}