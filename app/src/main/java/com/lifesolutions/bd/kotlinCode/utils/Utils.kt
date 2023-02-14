package com.lifesolutions.bd.kotlinCode.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import androidx.core.app.ActivityCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.lifesolutions.bd.Helpers.TimeAgo
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.data.model.CloudToken
import com.lifesolutions.bd.kotlinCode.data.model.NotificationData
import com.lifesolutions.bd.kotlinCode.data.model.PostKt
import com.lifesolutions.bd.kotlinCode.data.model.PushNotification
import java.io.ByteArrayOutputStream
import java.net.Inet4Address
import java.net.NetworkInterface
import java.net.SocketException
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    private var database: FirebaseDatabase = FirebaseDatabase.getInstance()

    fun generateReferCode(): String? {

        /*Date myDate = new Date();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a", Locale.getDefault());
        String date = format1.format(myDate);*/
        val r = Random()
        val n = 100000 + r.nextInt(900000)
        return n.toString()
    }

    fun getLocalIpAddress(): String? {
        try {
            val en =
                NetworkInterface.getNetworkInterfaces()
            while (en.hasMoreElements()) {
                val intf = en.nextElement()
                val enumIpAddr =
                    intf.inetAddresses
                while (enumIpAddr.hasMoreElements()) {
                    val inetAddress = enumIpAddr.nextElement()
                    if (!inetAddress.isLoopbackAddress && inetAddress is Inet4Address) {
                        return inetAddress.getHostAddress()
                    }
                }
            }
        } catch (ex: SocketException) {
            ex.printStackTrace()
        }
        return null
    }


    @SuppressLint("SimpleDateFormat")
    fun getNormalDate(timeInMillies: Long): String? {
        var date: String? = null
        val formatter = SimpleDateFormat("dd - MMMM - yyyy")
        date = formatter.format(timeInMillies)
        return date
    }

    fun getDateTimeAgo(timeInMilli: Long): String? {
        val cDate = System.currentTimeMillis()
        return TimeAgo.toDuration(cDate - timeInMilli)
    }

    @JvmStatic
    fun hasPermissions(context: Context?, permissions: Array<String>): Boolean {
        if (context != null) {
            for (permission in permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false
                }
            }
        }
        return true
    }


    fun getRealPathFromURI(contentURI: Uri?, context: Activity): String? {
        val projection =
            arrayOf(MediaStore.Images.Media.DATA)
        val cursor = context.managedQuery(
            contentURI, projection, null,
            null, null
        )
            ?: return null
        val columnIndex = cursor
            .getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        return if (cursor.moveToFirst()) {
            // cursor.close();
            cursor.getString(columnIndex)
        } else null
        // cursor.close();
    }

    fun getImageUri(inContext: Context, inImage: Bitmap): Uri? {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(
            inContext.contentResolver,
            inImage,
            "Title",
            null
        )
        return Uri.parse(path)
    }

    fun addPostToDB(post: PostKt, authId: String) {
        database.reference.child("UserPosts").child(authId).child(post.postID!!)
            .setValue(post)
    }

    fun getDateTime(timeInMilli: Any): String? {
        val cDate = System.currentTimeMillis()
        return TimeAgo.toDuration(cDate - timeInMilli as Long)
    }

    fun sendNotification(receiverID: String, firstName: String, message: String,postId: String) {
        val tokenRef = FirebaseDatabase.getInstance().getReference("CloudTokens")
        tokenRef.child(receiverID).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val (token) = dataSnapshot.getValue(CloudToken::class.java)!!
                    val notificationData = NotificationData(
                        FirebaseAuth.getInstance().currentUser?.uid,
                        "$firstName $message",
                        "Missed Call",
                        receiverID,
                        "missedCall",
                        postId,
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