package com.lifesolutions.bd.kotlinCode.services

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.lifesolutions.bd.kotlinCode.constant.AppConstant
import com.lifesolutions.bd.kotlinCode.ui.home.MainActivity

class NotificationReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.action

        val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (AppConstant.YES_ACTION == action) {
            context.startActivity(Intent(context, MainActivity::class.java))
        } else if (AppConstant.STOP_ACTION == action) {
            Toast.makeText(context, "End Call", Toast.LENGTH_SHORT).show()
            notificationManager.cancel(AppConstant.INC_CALL_NOTIFY_ID)
        }

    }
}