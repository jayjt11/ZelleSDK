package com.fiserv.dps.mobile.sdk.handlers

import android.app.Activity
import android.webkit.JavascriptInterface
import android.widget.Toast

interface ShareHandler {
    @JavascriptInterface fun sharePhoto()
    @JavascriptInterface fun shareText()
}

class ShareHandlerImpl(private val activity: Activity, private val evaluateJS: (String)->Unit): ShareHandler {
    @JavascriptInterface override fun sharePhoto() {
        Toast.makeText(activity, "sharePhoto Called", Toast.LENGTH_LONG).show()

    }
    @JavascriptInterface override fun shareText() {


        Toast.makeText(activity, "shareText Called", Toast.LENGTH_LONG).show()

    }
}