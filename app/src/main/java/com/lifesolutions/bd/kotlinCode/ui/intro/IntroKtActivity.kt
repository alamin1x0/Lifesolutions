package com.lifesolutions.bd.kotlinCode.ui.intro

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayout
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.data.model.ScreenItem
import com.lifesolutions.bd.kotlinCode.ui.home.MainActivity
import kotlinx.android.synthetic.main.activity_intro_kt.*


class IntroKtActivity : AppCompatActivity() {

    private var introViewPagerAdapter: IntroViewPagerAdapter? = null
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_kt)

        // Status Bar Color for SDK 21 & 22..
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            window.statusBarColor = ContextCompat.getColor(applicationContext, R.color.colorPrimaryDark)
        }

        if (isIntroViewed()) {
            Intent(this, MainActivity::class.java).apply {
                startActivity(this)
                finish()
            }
        }


        // toast(isIntroViewed().toString())

        // View Pager Data..
        val mList: MutableList<ScreenItem> = ArrayList()
        mList.add(
            ScreenItem(
                "One App All Essential Features",
                "Enjoy StarNotes's English School Feed with contents posted by your friends & other users",
                R.drawable.intro_page_one_new
            )
        )
        mList.add(
            ScreenItem(
                "Best Video, Audio & Random Call",
                "The App offers HD audio,  video calling experience with files, images, videos & more.",
                R.drawable.intro_video_two
            )
        )
        mList.add(
            ScreenItem(
                "Double Click to Share Your Love",
                "Easy to find friends and Double click to post and give star your friend and other",
                R.drawable.intro_three
            )
        )


        // setup viewpager
        introViewPagerAdapter = IntroViewPagerAdapter(this, mList)
        screen_viewpager.adapter = introViewPagerAdapter

        // setup tab layout with viewpager
        tab_indicator.setupWithViewPager(screen_viewpager)


        btn_next.setOnClickListener {
            position = screen_viewpager.currentItem

            if (position < mList.size) {
                position++

                screen_viewpager.currentItem = position
            }

            if (position == mList.size - 1) {
                loadLastScreen()
            }
        }

        // Tab Layout add change listener
        tab_indicator.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab?.position == mList.size - 1) {
                    loadLastScreen()
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}

        })

        tv_skip.setOnClickListener {
            loadLastScreen()
        }

        btn_get_started.setOnClickListener {
            Intent(this, MainActivity::class.java).apply {
                startActivity(this)
            }

            savePrefsIntroData()
            finish()
        }

    }


    // Load Last Screen
    private fun loadLastScreen() {
        val btnAnim = AnimationUtils.loadAnimation(applicationContext, R.anim.button_animation)

        btn_next.visibility = View.INVISIBLE
        btn_get_started.visibility = View.VISIBLE
        tv_skip.visibility = View.INVISIBLE
        tab_indicator.visibility = View.INVISIBLE

        // setup animation
        btn_get_started.animation = btnAnim
    }

    /**
     * Save Intro View Data on Shared Preference
     */
    private fun savePrefsIntroData() {
        val pref = applicationContext.getSharedPreferences("startnoteIntro", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putBoolean("isIntroOpened", true)
        editor.apply()
    }

    private fun isIntroViewed(): Boolean {
        val pref = applicationContext.getSharedPreferences(
            "startnoteIntro",
            Context.MODE_PRIVATE
        )
        return pref.getBoolean("isIntroOpened", false)
    }


}