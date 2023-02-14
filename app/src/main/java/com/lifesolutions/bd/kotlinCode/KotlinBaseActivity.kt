package com.lifesolutions.bd.kotlinCode

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.services.OngoingAlertService
import com.lifesolutions.bd.kotlinCode.utils.toast
import kotlinx.android.synthetic.main.activity_kotlin_base.*

class KotlinBaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_base)

        val intent = intent.extras?.getString("USER_CALL_ID", "null")

        toast(intent.toString())

        btn_start_service.setOnClickListener {
            val input: String = edt_text_test_service.text.toString()
            val serviceIntent = Intent(this, OngoingAlertService::class.java)
            serviceIntent.putExtra("inputExtra", input)

            // startService(serviceIntent)

            ContextCompat.startForegroundService(this, serviceIntent)
        }

        btn_stop_service.setOnClickListener {
            val serviceIntent = Intent(this, OngoingAlertService::class.java)
            stopService(serviceIntent)
        }


    }

}