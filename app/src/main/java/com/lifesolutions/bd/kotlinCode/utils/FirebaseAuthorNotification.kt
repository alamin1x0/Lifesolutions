package com.lifesolutions.bd.kotlinCode.utils

import com.google.firebase.database.FirebaseDatabase
import com.lifesolutions.bd.Models.NotificationInApp

object FirebaseAuthorNotification {


    /**
     * Send Push Notification
     */
    fun sendNotificationToAuthor(
        postId: String?, // Where Go
        authorId: String?, // Who Get Notification
        userId: String?, // Sender
        type: String,
        message: String
    ) {
        val notificationReference =
            FirebaseDatabase.getInstance().getReference("Notifications")
        val pushRef = notificationReference.child(authorId!!).push()
        pushRef.push()
        val notificationId = pushRef.key
        val time = System.currentTimeMillis()
        val notification = NotificationInApp(postId, type, userId, authorId, message, notificationId, time, false)
        pushRef.setValue(notification)
    }

}