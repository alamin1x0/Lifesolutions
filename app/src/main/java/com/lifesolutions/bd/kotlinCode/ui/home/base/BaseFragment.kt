package com.lifesolutions.bd.kotlinCode.ui.home.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.AppBarLayout
import com.lifesolutions.bd.R

abstract class BaseFragment: Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val appBar: AppBarLayout = (requireActivity() as AppCompatActivity).findViewById(R.id.appbar_fragment_home)

        appBar.setExpanded(true, true)
        appBar.visibility = View.VISIBLE

        //val layoutParam = appBar.layoutParams
        //layoutParam.height = 0
        // appBar.layoutParams = layoutParam
        // (requireActivity() as AppCompatActivity).supportActionBar?.show()
    }
}