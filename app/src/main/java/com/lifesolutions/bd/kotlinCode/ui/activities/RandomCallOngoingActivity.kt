package com.lifesolutions.bd.kotlinCode.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.sinch.android.rtc.SinchError
import com.lifesolutions.bd.Activities.BaseActivity.getSinchServiceInterface
import com.lifesolutions.bd.Activities.CallScreenActivity
import com.lifesolutions.bd.Helpers.InternetCheck
import com.lifesolutions.bd.Helpers.RandomCallCheck
import com.lifesolutions.bd.Helpers.RingtonePlayer
import com.lifesolutions.bd.Services.SinchService
import com.lifesolutions.bd.kotlinCode.ui.base.BaseActivityWithSinch
import com.lifesolutions.bd.kotlinCode.utils.toast
import kotlinx.android.synthetic.main.activity_random_call_ongoing.*

class RandomCallOngoingActivity : BaseActivityWithSinch(), SinchService.StartFailedListener {

    private val TAG = "RandomCallActivity"

    private lateinit var ringtonePlayer: RingtonePlayer
    private lateinit var db: FirebaseDatabase
    private lateinit var randomDbRef: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private var uID: String? = null
    private lateinit var randomCallBannerContainer:LinearLayout;

    private var isCallStarted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.lifesolutions.bd.R.layout.activity_random_call_ongoing)


        //=======================Facebook Ads========================
        randomCallBannerContainer =  findViewById(com.lifesolutions.bd.R.id.random_call_banner_container);


        //=======================Facebook Ads========================



        // Initialize with Ringtone Class
        ringtonePlayer = RingtonePlayer(applicationContext)
        // Initialize Firebase
        auth = FirebaseAuth.getInstance()
        db = FirebaseDatabase.getInstance()
        randomDbRef = db.getReference("RandomCall")
        uID = auth.currentUser?.uid

        onProgressRandomCall()

        decline_random_call_kt.setOnClickListener {
            ringtonePlayer.stopProgressTone()
            RandomCallCheck.setCheck(false)
            isCallStarted = false
            randomDbRef.child(uID!!).removeValue()
            finish()
        }
    }

    /**
     * Lifecycle
     */
    override fun onStart() {
        super.onStart()
        // ringtonePlayer.stopProgressTone()
        isCallStarted = false
    }

    override fun onDestroy() {
        super.onDestroy()
        RandomCallCheck.setCheck(false)
    }

    override fun onPause() {
        super.onPause()
        //RandomCallCheck.setCheck(false)
        ringtonePlayer.stopProgressTone()
        randomDbRef.child(uID!!).removeValue()
        finish()
    }


    override fun onStop() {
        super.onStop()
        RandomCallCheck.setCheck(false)
        randomDbRef.child(uID!!).removeValue()
    }

    // Disable Back pressed
    override fun onBackPressed() {
        return
    }

    /**
     * On Start Progress Random Call
     */

    private fun onProgressRandomCall() {
        if (InternetCheck.checkConnection(this)) {
            if (getSinchServiceInterface().isStarted) {

                RandomCallCheck.setCheck(true)
                ringtonePlayer.playProgressTone()
                // Random User ID Array List
                val userItems = ArrayList<String>()

                randomDbRef.child(uID!!).setValue(true).addOnSuccessListener {
                    randomDbRef.addValueEventListener(object : ValueEventListener {

                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            userItems.clear()
                            for (data in dataSnapshot.children) {
                                userItems.add(data.key!!)
                            }

                            if (userItems.size != 0) {
                                if (!isCallStarted) {
                                    connectWithRandomPerson(userItems)
                                }
                            }
                        }

                        override fun onCancelled(err: DatabaseError) {
                            Log.d(TAG, err.message)
                        }


                    })
                }
            } else {
                toast("Just a moment we are preparing")
            }
        } else {
            toast("No Internet Connection")
        }
    }

    /**
     * On Connect Logic with Random Person
     */

    private fun connectWithRandomPerson(userItems: List<String>) {
        var receiverID: String? = null
        if (userItems.size < 3) {
            if (userItems.size % 2 == 0) {
                val index = userItems.indexOf(uID)

                if (index % 2 == 1) {
                    receiverID = userItems[index - 1]

                    if (receiverID != uID) {
                        isCallStarted = true
                        RandomCallCheck.setCallRunning(true)
                        ringtonePlayer.stopProgressTone()

                        val call = getSinchServiceInterface().callUserAudio(receiverID)
                        val callId = call?.callId

                        // Intent on Call Screen
                        Intent(applicationContext, CallScreenActivity::class.java).apply {
                            this.putExtra(SinchService.CALL_ID, callId)
                            startActivity(this)
                        }
                    }
                }
            }
        } else {
            val index = userItems.indexOf(uID)
            if (index % 2 == 1) {
                receiverID = userItems[index - 1]

                if (receiverID != uID) {
                    isCallStarted = true
                    RandomCallCheck.setCallRunning(true)
                    ringtonePlayer.stopProgressTone()

                    val call = getSinchServiceInterface().callUserAudio(receiverID)
                    val callId = call?.callId

                    // Intent on Call Screen
                    Intent(applicationContext, CallScreenActivity::class.java).apply {
                        this.putExtra(SinchService.CALL_ID, callId)
                        startActivity(this)
                    }
                }
            }
        }
    }

    /**
     * Sinch Override Methods
     */

    override fun onServiceConnected() {
        getSinchServiceInterface()?.setStartListener(this)

        //Register user
        if (getSinchServiceInterface() != null && !getSinchServiceInterface().isStarted) {
            getSinchServiceInterface()?.startClient(uID)
        }
    }

    override fun onStarted() {
        Log.d(TAG, "Sinch Started")
    }

    override fun onStartFailed(error: SinchError?) {
        Log.d(TAG, error?.message.toString())
    }

    fun onBackBtnClick(view: View) {}
    fun onSwitchSpeakerphoneClicked(view: View) {}
    fun onLocalAudioMuteClicked(view: View) {}

}