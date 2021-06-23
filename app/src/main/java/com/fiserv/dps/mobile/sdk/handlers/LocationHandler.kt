package com.fiserv.dps.mobile.sdk.handlers

import android.app.Activity
import android.webkit.JavascriptInterface
import android.widget.Toast

interface LocationHandler {
    @JavascriptInterface fun getLocation()
}

class LocationHandlerImpl(private val activity: Activity, private val evaluateJS: (String)->Unit): LocationHandler {
    @JavascriptInterface override fun getLocation() {

        Toast.makeText(activity, "getLocation called", Toast.LENGTH_LONG).show()
    }
}