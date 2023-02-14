package com.lifesolutions.bd.kotlinCode.utils

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.lifesolutions.bd.R


class ViewProgressDialog(private val context: Context) {

    // private lateinit var dialog: Dialog
    private lateinit var alertDialog: AlertDialog



    fun showLoadingBar(message: String? = null) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        val dialogView = View.inflate(context, R.layout.s_custom_progress_dialog, null)
        builder.setCancelable(false)
        builder.setView(dialogView)

        val progressText = dialogView.findViewById<TextView>(R.id.progress_bar_text_view)

        if (message != null) {
            progressText.text = message
        }

        alertDialog = builder.create()

        alertDialog.show()
    }

    fun dismissLoadingBar() {
        alertDialog.dismiss()
    }


//    fun hideImageLoading() {
//        dialog.dismiss()
//    }

    //    fun showImageLoading() {
//
//        dialog = Dialog(context)
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.setCancelable(false)
//        dialog.setContentView(R.layout.custom_loading_layout)
//
//        val gifImageView = dialog.findViewById<ImageView>(R.id.custom_loading_imageView)
//
//        Glide.with(context)
//            .load(R.drawable.loading)
//            .placeholder(R.drawable.loading)
//            .centerCrop()
//            .into(gifImageView)
//
//        dialog.show()
//    }
}