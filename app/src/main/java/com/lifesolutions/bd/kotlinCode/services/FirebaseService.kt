package com.lifesolutions.bd.kotlinCode.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.sinch.android.rtc.SinchHelpers
import com.lifesolutions.bd.Activities.FullPostActivity
import com.lifesolutions.bd.Notifications.Token
import com.lifesolutions.bd.R
import com.lifesolutions.bd.Services.SinchService
import com.lifesolutions.bd.Services.SinchService.SinchServiceInterface
import com.lifesolutions.bd.kotlinCode.ui.activities.GroupMessageActivity
import com.lifesolutions.bd.kotlinCode.ui.activities.PersonalMessageActivity
import com.lifesolutions.bd.kotlinCode.ui.activities.UserProfileActivityKt
import com.lifesolutions.bd.kotlinCode.ui.home.MainActivity
import kotlin.random.Random

const val CHANNEL_ID = "my_channel"


class FirebaseService: FirebaseMessagingService() {

    private val TAG = "FirebaseMService"
    private var intent: Intent? = null

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        Log.w(TAG, "onMessageReceived() ")

        val mAuth = FirebaseAuth.getInstance()
        val uID = mAuth.uid
        //val context = this

        val data: Map<String, String> = message.data

        //val dataHashMap = if (data is HashMap) data else HashMap(data)


        if (SinchHelpers.isSinchPushPayload(data)) {
            object : ServiceConnection {
                private var payload: Map<*, *>? = null
                override fun onServiceConnected(name: ComponentName, service: IBinder) {
                    if (payload != null) {
                        val sinchService = service as SinchServiceInterface
                        val service = SinchService()
                        if (sinchService != null) {
                            val result =
                                sinchService.relayRemotePushNotificationPayload(payload)
                            // handle result, e.g. show a notification or similar
                            val callResult = result.callResult
                            if (result.isValid && result.isCall) {
                                if (!callResult.isCallCanceled || !callResult.isTimedOut) {
//                                    if (callResult.isVideoOffered) {
//                                        val intent = Intent(
//                                            this@FirebaseService,
//                                            IncomingVideoCallScreenActivity::class.java
//                                        )
//                                        intent.putExtra(
//                                            SinchService.CALL_ID,
//                                            callResult.remoteUserId
//                                        )
//                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NO_HISTORY)
//                                        startActivity(intent)
//                                    } else {
//                                        val intent = Intent(
//                                            this@FirebaseService,
//                                            IncomingCallScreenActivity::class.java
//                                        )
//                                        intent.putExtra(
//                                            SinchService.CALL_ID,
//                                            callResult.remoteUserId
//                                        )
//                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NO_HISTORY)
//                                        startActivity(intent)
//                                    }
                                }
                            }
                        }
                    }
                    payload = null
                }

                override fun onServiceDisconnected(name: ComponentName) {}
                fun relayMessageData(data: Map<String, String>) {
                    payload = data
                    applicationContext.bindService(
                        Intent(
                            applicationContext,
                            SinchService::class.java
                        ), this, Context.BIND_AUTO_CREATE
                    )
                }
            }.relayMessageData(data)
        } else {

            Log.w(TAG, "onMessageReceived ->>>>>> ${message.data}")
            val user: String = message.data["user"]!!
            //val icon: String = message.data["icon"]!!
            val title: String = message.data["title"]!!
            val body: String = message.data["body"]!!
            val type: String = message.data["type"]!!
            val extra: String = message.data["extra"]!!


            //Uri dfSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            val dfSoundUri = Uri.parse("android.resource://" + this.packageName + "/" + R.raw.notification)

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val notificationId = Random.nextInt()

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                createNotificationChannel(notificationManager)
            }

            // Adjust Intent
            when(type) {
                "message" -> {
                    intent = Intent(this, PersonalMessageActivity::class.java)
                    val bundle = Bundle()
                    bundle.putString("receiverID", user)
                    intent?.putExtras(bundle)
                    intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                }
                "groupMessage" ->{
                    if (extra == uID) return
                    intent = Intent(this, GroupMessageActivity::class.java)
                    val bundle = Bundle()
                    bundle.putString("groupId", user)
                    intent?.putExtras(bundle)
                    intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                }
                "comment" -> {
                    intent = Intent(this, FullPostActivity::class.java)
                    val bundle = Bundle()
                    bundle.putString("Id", extra)
                    intent?.putExtras(bundle)
                    intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                }
                "request" -> {
                    intent = Intent(this, UserProfileActivityKt::class.java)
                    val bundle = Bundle()
                    bundle.putString("uID", extra)
                    intent!!.putExtras(bundle)
                    intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                }
                "push" -> {
                    intent = Intent(this, MainActivity::class.java)
                    intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                }
                "postReaction" ->{
                    intent = Intent(this, FullPostActivity::class.java)
                    val bundle = Bundle()
                    bundle.putString("Id", extra)
                    intent?.putExtras(bundle)
                    intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                }
                "missedCall" ->{
                    intent = Intent(this, PersonalMessageActivity::class.java)
                    val bundle = Bundle()
                    bundle.putString("receiverID", user)
                    intent?.putExtras(bundle)
                    intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                }

            }



            val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

            val notification = NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.drawable.app_icon)
                .setAutoCancel(true)
                .setSound(dfSoundUri)
                .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
                .setContentIntent(pendingIntent)
                .build()
            notificationManager.notify(notificationId, notification)
        }


    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(notificationManager: NotificationManager) {
        val channelName = "channelName"
        val channel = NotificationChannel(CHANNEL_ID, channelName, IMPORTANCE_HIGH).apply {
            description = "My channel description"
            enableLights(true)
            lightColor = Color.GREEN
            enableVibration(true)
        }
        notificationManager.createNotificationChannel(channel)
    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            updateToken(p0)
        }
    }


    private fun updateToken(tokenRefresh: String) {
        val user = FirebaseAuth.getInstance().currentUser
        val reference = FirebaseDatabase.getInstance().getReference("CloudTokens")
        val token = Token(tokenRefresh)
        reference.child(user!!.uid).setValue(token)
    }

}