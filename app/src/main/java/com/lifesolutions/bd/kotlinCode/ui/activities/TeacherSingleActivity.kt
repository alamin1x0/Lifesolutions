package com.lifesolutions.bd.kotlinCode.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lifesolutions.bd.R
import kotlinx.android.synthetic.main.activity_teacher_single.*

class TeacherSingleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_single)

        // Set Actionbar
        setSupportActionBar(toolbar_teacher_single)
        supportActionBar?.title = "Teacher View"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // On Back Pressed
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}