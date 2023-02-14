package com.lifesolutions.bd.kotlinCode.ui.base

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import com.lifesolutions.bd.Services.SinchService
import com.lifesolutions.bd.Services.SinchService.SinchServiceInterface

abstract class BaseActivityWithSinch : AppCompatActivity(), ServiceConnection {

    companion object {
        var sinchServiceInterface: SinchServiceInterface? = null
            private set
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applicationContext.bindService(
            Intent(this, SinchService::class.java), this,
            Context.BIND_AUTO_CREATE
        )
    }

    override fun onServiceConnected(name: ComponentName, iBinder: IBinder) {
        if (SinchService::class.java.name == name.className) {
            sinchServiceInterface = iBinder as SinchServiceInterface
            onServiceConnected()
        }
    }

    override fun onServiceDisconnected(componentName: ComponentName) {
        if (SinchService::class.java.name == componentName.className) {
            sinchServiceInterface = null
            onServiceDisconnected()
        }
    }

    protected open fun onServiceConnected() {
        // for subclasses
    }

    private fun onServiceDisconnected() {
        // for subclasses
    }

}