package com.lifesolutions.bd.kotlinCode.ui.activities

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.bambuser.broadcaster.BroadcastStatus
import com.bambuser.broadcaster.Broadcaster
import com.bambuser.broadcaster.CameraError
import com.bambuser.broadcaster.ConnectionError
import com.bumptech.glide.Glide
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ServerValue
import com.squareup.picasso.Picasso
import com.lifesolutions.bd.Activities.LiveVideo
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.utils.toast
import kotlinx.android.synthetic.main.activity_stream.*


class StreamActivity : AppCompatActivity() {
  //  val APPLICATION_ID = "eEIk69e5p4h7VA7Kbn4J2w"
     val APPLICATION_ID = "aCJqRXQ4xqSNUfjvPhi8Ew"

    // Shared Preferences
    private lateinit var userPreferences: SharedPreferences
    private var uID: String? = null
    var viewer :TextView? = null
    var title : EditText? = null
    var livegif : ImageView? = null
    private val activity = this
    val broadcaster by lazy {

        Broadcaster(this, APPLICATION_ID, broadcasterObserver)

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.stream_activity)


        // Set Actionbar
        setSupportActionBar(toolbar_live_stream)
        supportActionBar?.title = "Live Stream"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        userPreferences = getSharedPreferences("com.starnote.CurrentAuthUser", Context.MODE_PRIVATE)

        //Toast.makeText(applicationContext,"asdasdasda", Toast.LENGTH_LONG).show()
        val broadcasButton:Button = findViewById(R.id.BroadcastButton)
        //val watch_live :Button = findViewById(R.id.watch_live)
        // val viewer :TextView = findViewById(R.id.ViewerCount)
        viewer = findViewById(R.id.ViewerCount)
        title = findViewById(R.id.live_title)
        livegif = findViewById(R.id.livegif)
        val switchCamera :ImageView = findViewById(R.id.switch_camera)



        getUserInfo()




        switchCamera.setOnClickListener {
            broadcaster.switchCamera()
        }






        broadcasButton.setOnClickListener {

            if (broadcaster.canStartBroadcasting()) {
                broadcasButton.setText("END LIVE")
                val myRefDB = FirebaseDatabase.getInstance().getReference("LiveVideos").push()
                uID = userPreferences.getString("uID", null)
                val firstName = userPreferences.getString("firstName", "")
                val lastName = userPreferences.getString("lastName", "")
                val imageThumbnail = userPreferences.getString("imageThumbnail", null)

                val newVideo = LiveVideo(
                    uID,
                    firstName,
                    lastName,
                    imageThumbnail,
                    ServerValue.TIMESTAMP,
                    title!!.text.toString(),
                    null
                )

                myRefDB.setValue(newVideo).addOnSuccessListener { aVoid: Void? ->
                    toast("Live Video Started")
                }

                    .addOnFailureListener {
                        toast("Live Video Starting Failed")
                    }

                Glide.with(applicationContext).load(R.drawable.livegif).into(livegif!!)
                livegif!!.visibility = View.VISIBLE;


                broadcaster.startBroadcast()

            } else {
                broadcasButton.setText("Go LIVE")
                livegif!!.visibility = View.GONE;
                viewer!!.setText("")
                broadcaster.stopBroadcast()

            }
        }


    }




    private val broadcasterObserver = object : Broadcaster.Observer {
        override fun onConnectionStatusChange(broadcastStatus: BroadcastStatus) {
            Log.i("Mybroadcastingapp", "Received status change: $broadcastStatus")
            if (broadcastStatus == BroadcastStatus.STARTING) {
                activity.window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            }
            if (broadcastStatus == BroadcastStatus.IDLE) {
                activity.window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            }
            // broadcastButton.setText(if (broadcastStatus == BroadcastStatus.IDLE) "Broadcast" else "Disconnect")
        }
        override fun onStreamHealthUpdate(i: Int) {
            viewer!!.setText("Health : "+i)

        }
        override fun onConnectionError(connectionError: ConnectionError, s: String?) {
            Log.i("Mybroadcastingapp","Received connection error: $connectionError, $s")
        }
        override fun onCameraError(cameraError: CameraError) {
            Log.i("Mybroadcastingapp","Received camera error: $cameraError")
        }
        override fun onChatMessage(s: String) {
            Log.i("Mybroadcastingapp","Received chat messsage: $s")
        }
        override fun onResolutionsScanned() {}
        override fun onCameraPreviewStateChanged() {}
        override fun onBroadcastInfoAvailable(s: String, s1: String) {
            Log.i("Mybroadcastingapp","Received broadcast info: $s, $s1")
        }
        override fun onBroadcastIdAvailable(id: String) {
            Log.i("Mybroadcastingapp", "Received broadcast id: $id")
        }




    }

    override fun onPause() {
        super.onPause()
        broadcaster.onActivityPause()
    }


    // On Back Pressed
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onStart() {
        super.onStart()
        super.onResume()
    }







    override fun onResume() {
        super.onResume()

        if (!hasPermission(Manifest.permission.CAMERA) && !hasPermission(Manifest.permission.RECORD_AUDIO))
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO), 1)
        else if (!hasPermission(Manifest.permission.RECORD_AUDIO))
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), 1)
        else if (!hasPermission(Manifest.permission.CAMERA))
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 1)

        broadcaster.setCameraSurface(previewSurface)

        broadcaster.onActivityResume()
        broadcaster.switchCamera()
        broadcaster.setRotation(this.windowManager.defaultDisplay.rotation)
    }

    // ...
    override fun onDestroy() {
        super.onDestroy()
        broadcaster.onActivityDestroy()
    }

    private fun hasPermission(permission: String): Boolean {
        return ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
    }




    @SuppressLint("SetTextI18n")
    private fun getUserInfo() {
        // animatedLoading.showAnimatedLoading()
        uID = userPreferences.getString("uID", null)
        val firstName = userPreferences.getString("firstName", "")
        val lastName = userPreferences.getString("lastName", "")
        val imageThumbnail = userPreferences.getString("imageThumbnail", null)

        if (uID != null) {
            tv_profile_author_name_stream.text = "$firstName $lastName"
            if (imageThumbnail != null && imageThumbnail != "none") {
                Picasso.get().load(imageThumbnail).into(iv_profile_author_stream)
            } else {
                Picasso.get().load(R.drawable.user_low).into(iv_profile_author_stream)
            }
        }

    }



}
