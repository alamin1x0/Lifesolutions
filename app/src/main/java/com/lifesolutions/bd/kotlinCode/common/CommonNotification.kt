package com.lifesolutions.bd.kotlinCode.common

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.services.CHANNEL_ID
import com.lifesolutions.bd.kotlinCode.ui.activities.PersonalMessageActivity

import kotlin.random.Random

class CommonNotification(
    private val context: Context,
    private val type: String,
    private val callType: String,
    private val uniqueId: String,
    private val callerName: String
) {

    private var intent: Intent? = null

    fun createNotification() {
        // val dfSoundUri = Uri.parse("android.resource://" + this.packageName + "/" + R.raw.notification)

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationId = Random.nextInt()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(notificationManager)
        }

        when(type) {
            "MISSED_CALL" -> {
                intent = Intent(context, PersonalMessageActivity::class.java)
                val bundle = Bundle()
                bundle.putString("receiverID", uniqueId)
                intent?.putExtras(bundle)
                intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            }
        }

        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle("Missed $callType Call")
            .setContentText("You missed a call from $callerName")
            .setSmallIcon(R.drawable.ic_baseline_phone_missed)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()
        notificationManager.notify(notificationId, notification)

    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(notificationManager: NotificationManager) {
        val channelName = "Starnote Call Alert"
        val channel = NotificationChannel(CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_DEFAULT).apply {
            description = "A notification channel for notify calling info."
            enableLights(true)
            lightColor = Color.GREEN
        }
        notificationManager.createNotificationChannel(channel)
    }

}