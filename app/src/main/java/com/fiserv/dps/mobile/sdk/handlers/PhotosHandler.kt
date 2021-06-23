package com.fiserv.dps.mobile.sdk.handlers

import android.webkit.JavascriptInterface
import android.widget.Toast
import androidx.fragment.app.Fragment


interface PhotosHandler {
    @JavascriptInterface fun takePhoto()
    @JavascriptInterface fun selectFromPhotos()
}

class PhotosHandlerImpl(private val fragment: Fragment, private val evaluateJS: (String)->Unit): PhotosHandler {
    @JavascriptInterface override fun takePhoto() {

        Toast.makeText(fragment.context, "takePhoto called", Toast.LENGTH_LONG).show()
    }
    @JavascriptInterface override fun selectFromPhotos() {

        Toast.makeText(fragment.context, "selectFromPhotos called", Toast.LENGTH_LONG).show()
    }
}