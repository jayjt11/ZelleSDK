package com.fiserv.dps.mobile.sdk.handlers

import android.app.Activity
import android.webkit.JavascriptInterface
import android.widget.Toast

interface PermissionsHandler {
    @JavascriptInterface fun checkPermissions()
}

class PermissionsHandlerImpl(private val activity: Activity, private val evaluateJS: (String)->Unit): PermissionsHandler {

    @JavascriptInterface override fun checkPermissions() {

        Toast.makeText(activity, "getLocation called", Toast.LENGTH_LONG).show()

    }
}