package com.lifesolutions.bd.kotlinCode.ui.activities

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.lifesolutions.bd.R
import kotlinx.android.synthetic.main.activity_buy_coin.*

class BuyCoinActivity : AppCompatActivity() {
    private val TAG = "BuyCoinActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_coin)

        // Set Actionbar
        setSupportActionBar(toolbar_buy_coin)
        supportActionBar?.title = "Buy Coin"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // On Back Pressed
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    private fun showDialog() {
        val epicDialog = Dialog(this)
        epicDialog.setContentView(R.layout.buy_coin_card_dialog)

        // ID....
        val closeBtn = epicDialog.findViewById<Button>(R.id.close_dialog)

        epicDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        epicDialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        epicDialog.setCancelable(true)
        closeBtn.setOnClickListener {
            epicDialog.dismiss()
        }
        epicDialog.show()
    }

    fun buyPackageOne(view: View) {
        showDialog()
    }

}