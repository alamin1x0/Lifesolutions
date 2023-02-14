package com.lifesolutions.bd.kotlinCode.ui.activities

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.constant.AppConstant
import com.lifesolutions.bd.kotlinCode.services.NotificationReceiver


class TestNotiActivity : AppCompatActivity() {

    private val CHANNEL_ID = "my_channel_test"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_noti)


    }

    fun btnPopupNotification(view: View) {

        val intent = Intent(this, AboutDeveloperActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.putExtra("message", "Hello")
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

//        val intentAction = Intent(this, NotificationReceiver::class.java)
//        intentAction.putExtra("CALL_ACTION", notificationActionData)
//        val pendingIntentTest =
//            PendingIntent.getBroadcast(this, 12345, intentAction, PendingIntent.FLAG_UPDATE_CURRENT)

        //Yes intent
        val yesReceive = Intent(this, NotificationReceiver::class.java)
        yesReceive.action = AppConstant.YES_ACTION
        val pendingIntentYes =
            PendingIntent.getBroadcast(this, AppConstant.INCOMING_CALL_REQ_CODE, yesReceive, PendingIntent.FLAG_UPDATE_CURRENT)

        //No intent
        val noReceive = Intent(this, NotificationReceiver::class.java)
        noReceive.action = AppConstant.STOP_ACTION
        val pendingIntentNo =
            PendingIntent.getBroadcast(this,  AppConstant.INCOMING_CALL_REQ_CODE, noReceive, PendingIntent.FLAG_UPDATE_CURRENT)


        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        // val notificationId = Random.nextInt()
        val notificationId = AppConstant.INC_CALL_NOTIFY_ID

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(notificationManager)
        }

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.app_icon))
            .setColor(ContextCompat.getColor(applicationContext, R.color.colorMain))
            .setContentTitle(getString(R.string.hello))
            .setContentText("This is Notification Description")
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setCategory(NotificationCompat.CATEGORY_CALL)
            .setAutoCancel(false)
            .setOngoing(false)
            .addAction(R.drawable.ic_video_call_icon, "Yes", pendingIntentYes)
            .addAction(R.drawable.ic_video_call_icon, "Cancel", pendingIntentNo)
            .build()


        notificationManager.notify(notificationId, notification)

    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(notificationManager: NotificationManager) {
        val channelName = "channelNameHere"
        val channel = NotificationChannel(CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_HIGH).apply {
            description = "My channel description"
            enableLights(true)
            lightColor = Color.GREEN
        }
        notificationManager.createNotificationChannel(channel)
    }

}

