package com.lifesolutions.bd.kotlinCode.ui.registration

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.ui.registration.steps.RequiredStepKtFragment

class RegistrationKtActivity : AppCompatActivity() {

    private lateinit var requiredStepKtFragment: RequiredStepKtFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_kt)

        // Status Bar Color for SDK 21 & 22..
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            window.statusBarColor = ContextCompat.getColor(applicationContext, R.color.colorPrimaryDark)
        }


        requiredStepKtFragment = RequiredStepKtFragment()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, requiredStepKtFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

    }
}