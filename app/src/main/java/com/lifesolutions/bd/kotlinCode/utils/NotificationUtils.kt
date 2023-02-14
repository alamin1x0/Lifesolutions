package com.lifesolutions.bd.kotlinCode.utils

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.data.model.CloudToken
import com.lifesolutions.bd.kotlinCode.data.model.NotificationData
import com.lifesolutions.bd.kotlinCode.data.model.PushNotification

object NotificationUtils {

    @JvmStatic
    fun sendRequestAcceptNotification(
        senderId: String,
        receiverID: String,
        firstName: String,
        message: String
    ) {
        val tokenRef = FirebaseDatabase.getInstance().getReference("CloudTokens")
        tokenRef.child(receiverID).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val (token) = dataSnapshot.getValue(CloudToken::class.java)!!
                    val notificationData = NotificationData(
                        senderId,
                        "$firstName $message",
                        "Friend Request Accepted",
                        receiverID,
                        "request",
                        senderId,
                        R.drawable.app_icon
                    )
                    val pushNotification =
                        PushNotification(notificationData, token!!)
                    Notify.sendMgsNotification(pushNotification)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                //TODO....
            }
        })
    }
}