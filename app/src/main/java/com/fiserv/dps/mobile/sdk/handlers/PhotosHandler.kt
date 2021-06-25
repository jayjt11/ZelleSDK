package com.fiserv.dps.mobile.sdk.handlers

import android.os.Handler
import android.os.Looper
import android.webkit.JavascriptInterface
import android.widget.Toast
import androidx.fragment.app.Fragment


interface PhotosHandler {
    @JavascriptInterface fun takePhoto()
    @JavascriptInterface fun selectFromPhotos()
}

class PhotosHandlerImpl(private val fragment: Fragment, private val evaluateJS: (String)->Unit): PhotosHandler {
    @JavascriptInterface override fun takePhoto() {

        Handler(Looper.getMainLooper()).postDelayed({
            evaluateJS("callbackPhoto({photo: '${"Photo feature is in progress, Available Soon"}'})")
        }, 1000)
    }
    @JavascriptInterface override fun selectFromPhotos() {

        Handler(Looper.getMainLooper()).postDelayed({
            evaluateJS("callbackPhoto({photo: '${"Photo feature is in progress, Available Soon"}'})")
        }, 1000)
    }
}