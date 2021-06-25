package com.fiserv.dps.mobile.sdk.handlers

import android.app.Activity
import android.os.Handler
import android.os.Looper
import android.webkit.JavascriptInterface
import android.widget.Toast

interface LocationHandler {
    @JavascriptInterface fun getLocation()
}

class LocationHandlerImpl(private val activity: Activity, private val evaluateJS: (String)->Unit): LocationHandler {
    @JavascriptInterface override fun getLocation() {

        Handler(Looper.getMainLooper()).postDelayed({
            evaluateJS("callbackLocation({location: '${"Location feature is in progress, Available Soon"}'})")
        }, 1000)
    }
}