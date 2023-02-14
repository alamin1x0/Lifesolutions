package com.lifesolutions.bd.kotlinCode.services

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.lifesolutions.bd.Activities.CallScreenActivity
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.common.App.Companion.CHANNEL_ID_STAR_NOTE


class OngoingAlertService : Service() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val notificationIntent = Intent(this, CallScreenActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0, notificationIntent, 0
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) startCallForeground()
        else startForeground(1, NotificationCompat.Builder(this, CHANNEL_ID_STAR_NOTE)
            .setContentTitle("Lifesolution Call is Running")
            .setContentText("Tap to more details")
            .setSmallIcon(R.drawable.ic_baseline_phone_in_talk)
            .setAutoCancel(false)
            .setContentIntent(pendingIntent)
            .build())
        return START_STICKY
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun startCallForeground(){
        val notificationIntent = Intent(this, CallScreenActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0, notificationIntent, 0
        )

        val notification: Notification = Notification.Builder(this, CHANNEL_ID_STAR_NOTE)
            .setContentTitle("Lifesolution Call is Running")
            .setContentText("Tap to more details")
            .setColor(ContextCompat.getColor(applicationContext, R.color.colorMain))
            .setColorized(true)
            .setSmallIcon(R.drawable.ic_baseline_phone_in_talk)
            .setContentIntent(pendingIntent)
            .build()
        startForeground(1, notification)
        //do heavy work on a background thread
        //stopSelf();

    }


    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}
