package com.lifesolutions.bd.kotlinCode.utils

import android.app.Dialog
import android.content.Context
import android.view.Window
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.lifesolutions.bd.R

class AnimatedLoading(private val context: Context) {

    private lateinit var dialog: Dialog
    private lateinit var alertDialog: AlertDialog

    fun showAnimatedLoading() {

        dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.animated_loading_layout)

        val gifImageView = dialog.findViewById<ImageView>(R.id.custom_loading_imageView)

        Glide.with(context)
            .load(R.drawable.avd_loading_three)
            .placeholder(R.drawable.loading_icon)
            .centerCrop()
            .into(gifImageView)

        dialog.show()
    }


    fun hideAnimatedLoading() {
        dialog.dismiss()
    }
}