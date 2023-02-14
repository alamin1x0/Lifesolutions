package com.lifesolutions.bd.kotlinCode.ui.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.utils.toast
import kotlinx.android.synthetic.main.activity_mini_app_list.*

class MiniAppListActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mini_app_list)

        // Set Actionbar
        setSupportActionBar(toolbar_mini_app)
        supportActionBar?.title = "Mini Apps"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)



        // Click Event..
        menu_chaldal.setOnClickListener(this)
        menu_baajna.setOnClickListener(this)
        menu_hungrinaki.setOnClickListener(this)
        menu_hash.setOnClickListener(this)
        menu_jhalmuri.setOnClickListener(this)
        menu_map.setOnClickListener(this)

    }

    // On Back Pressed
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onClick(v: View?) {
        when(v?.id) {
           R.id.menu_chaldal -> {
               openWebViewActivity("https://chaldal.com/", "Chaldal")
           }

            R.id.menu_hungrinaki -> {
                openWebViewActivity("https://hungrynaki.com/", "HungryNaki")
            }

            R.id.menu_baajna -> {
                openWebViewActivity("https://gaana.com/", "Gaana")
            }

            R.id.menu_map -> {
                openWebViewActivity("http://maps.dingi.tech/", "Dingi Map")
            }

            R.id.menu_jhalmuri -> {
                openWebViewActivity("https://www.jhalmuri.com/", "Jhalmuri")
            }
        R.id.menu_hash -> {
            openWebViewActivity("https://www.banglawave.com/", "Bangla Wave")
        }
        }

    }

    private fun openWebViewActivity(url: String, name: String) {
        val msg = "This feature is not supported on your device. its available soon for your device"

        if (Build.VERSION.SDK_INT > 21) {
            Intent(this, WebViewActivity::class.java).apply {
                this.putExtra("webUrl", url)
                this.putExtra("webName", name)
                startActivity(this)
            }
        } else {
            toast(msg)
        }
    }

}