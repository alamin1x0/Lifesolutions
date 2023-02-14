package com.lifesolutions.bd.kotlinCode.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.ui.auth.LoginKtActivity
import com.lifesolutions.bd.kotlinCode.ui.home.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    private var mUser: FirebaseUser? = null

    private lateinit var topAnim: Animation
    private lateinit var rotateAnim: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_to_bottom)
        rotateAnim = AnimationUtils.loadAnimation(this, R.anim.rotate)

        tv_company_name.animation = topAnim
        iv_starnote_logo.animation = rotateAnim

        mAuth = FirebaseAuth.getInstance()
        mUser = mAuth?.currentUser

        Handler().postDelayed({
            if (mUser != null) {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            } else {
                startActivity(Intent(this@SplashActivity, LoginKtActivity::class.java))
            }
            finish()
        }, 1200)

    }
}