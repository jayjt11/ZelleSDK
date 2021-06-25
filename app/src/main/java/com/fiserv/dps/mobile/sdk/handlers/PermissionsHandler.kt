package com.fiserv.dps.mobile.sdk.handlers

import android.app.Activity
import android.os.Handler
import android.os.Looper
import android.webkit.JavascriptInterface
import android.widget.Toast

interface PermissionsHandler {
    @JavascriptInterface fun checkPermissions()
}

class PermissionsHandlerImpl(private val activity: Activity, private val evaluateJS: (String)->Unit): PermissionsHandler {

    @JavascriptInterface override fun checkPermissions() {

        Handler(Looper.getMainLooper()).postDelayed({
            evaluateJS("callbackPermissions({permission: '${"Check permissions feature is in progress, Available Soon"}'})")
        }, 1000)

    }
}