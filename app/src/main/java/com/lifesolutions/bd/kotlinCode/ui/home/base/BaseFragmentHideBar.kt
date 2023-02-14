package com.lifesolutions.bd.kotlinCode.ui.home.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragmentHideBar: Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // val appBar: AppBarLayout = (requireActivity() as AppCompatActivity).findViewById(R.id.appbar_fragment_home)

        // appBar.setExpanded(false, false)
        // appBar.visibility = View.GONE

        //val layoutParam = appBar.layoutParams
        //layoutParam.height = 0
        // appBar.layoutParams = layoutParam
        // (requireActivity() as AppCompatActivity).supportActionBar?.hide()
    }
}