package com.lifesolutions.bd.kotlinCode.utils

import android.util.Log
import com.lifesolutions.bd.kotlinCode.constant.RetrofitNotificationInstance
import com.lifesolutions.bd.kotlinCode.data.model.PushNotification
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object Notify {
    private val TAG = "NotifyFB"
    @JvmStatic
    fun sendMgsNotification(notification: PushNotification) = CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = RetrofitNotificationInstance.api.postNotification(notification)
            if (response.isSuccessful) {
                Log.d(TAG, "Response: ${com.google.gson.Gson().toJson(response)}")
            } else {
                Log.d(TAG, "Error: ${response.errorBody()}")
            }
        } catch (e: Exception) {
            Log.d(TAG, "sendNotification: ${e.message}")
        }
    }

}