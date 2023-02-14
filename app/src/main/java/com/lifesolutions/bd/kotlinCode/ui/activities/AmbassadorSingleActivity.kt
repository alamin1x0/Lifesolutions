package com.lifesolutions.bd.kotlinCode.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lifesolutions.bd.R
import kotlinx.android.synthetic.main.activity_ambassador_single.*

class AmbassadorSingleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ambassador_single)
        // Set Actionbar
        setSupportActionBar(toolbar_ambassador_single)
        supportActionBar?.title = "Ambassador View"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // On Back Pressed
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}