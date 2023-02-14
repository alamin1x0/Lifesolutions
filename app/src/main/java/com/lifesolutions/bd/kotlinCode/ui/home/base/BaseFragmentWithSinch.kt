package com.lifesolutions.bd.kotlinCode.ui.home.base

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.View
import androidx.fragment.app.Fragment
import com.facebook.FacebookSdk.getApplicationContext
import com.lifesolutions.bd.Services.SinchService
import com.lifesolutions.bd.Services.SinchService.SinchServiceInterface

abstract class BaseFragmentWithSinch: Fragment(), ServiceConnection {


    private var mSinchServiceInterface: SinchServiceInterface? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // val appBar: AppBarLayout = (requireActivity() as AppCompatActivity).findViewById(R.id.appbar_fragment_home)

        // appBar.setExpanded(false, false)
        // appBar.visibility = View.GONE

        getApplicationContext().bindService(
            Intent(requireContext(), SinchService::class.java), this,
            Context.BIND_AUTO_CREATE
        )
    }


    override fun onServiceConnected(name: ComponentName, iBinder: IBinder?) {
        if (SinchService::class.java.name == name.className) {
            mSinchServiceInterface = iBinder as SinchServiceInterface?
            onServiceConnected()
        }
    }

    override fun onServiceDisconnected(componentName: ComponentName) {
        if (SinchService::class.java.name == componentName.className) {
            mSinchServiceInterface = null
            onServiceDisconnected()
        }
    }

    protected open fun onServiceConnected() {
        // for subclasses
    }

    protected open fun onServiceDisconnected() {
        // for subclasses
    }

    open fun getSinchServiceInterface(): SinchServiceInterface? {
        return mSinchServiceInterface
    }

}