package com.lifesolutions.bd.kotlinCode.ui.activities

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.lifesolutions.bd.R


class AboutDeveloperActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_developer)
    }

    fun onClickFacebookPage(view: View) {
        val facebookIntent = Intent(Intent.ACTION_VIEW)
        val facebookUrl: String = getFacebookPageURL()
        facebookIntent.data = Uri.parse(facebookUrl)
        startActivity(facebookIntent)
    }

    fun onClickWebpage(view: View) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://softlabit.com/")
            )
        )
    }

    fun onBackBtnClick(view: View) {
        finish()
    }


    private fun getFacebookPageURL(): String {
        val fbURL = "https://www.facebook.com/softlabit"
        val fbPageId= "softlabit"
        val packageManager: PackageManager = this.packageManager
        return try {
            val versionCode =
                packageManager.getPackageInfo("com.facebook.katana", 0).versionCode
            if (versionCode >= 3002850) { //newer versions of fb app
                "fb://facewebmodal/f?href=$fbURL"
            } else { //older versions of fb app
                "fb://page/$fbPageId"
            }
        } catch (e: PackageManager.NameNotFoundException) {
            fbURL
        }
    }


}