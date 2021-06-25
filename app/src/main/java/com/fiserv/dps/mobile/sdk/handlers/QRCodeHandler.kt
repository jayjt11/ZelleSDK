package com.fiserv.dps.mobile.sdk.handlers

import android.os.Handler
import android.os.Looper
import android.webkit.JavascriptInterface
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.zxing.integration.android.IntentIntegrator


interface QRCodeHandler {
    @JavascriptInterface fun scanQRCode()
    @JavascriptInterface fun selectQRCodeFromPhotos()
}

class QRCodeHandlerImpl(private val fragment: Fragment, private val evaluateJS: (String)->Unit): QRCodeHandler {
    @JavascriptInterface
    override fun scanQRCode() {

        Handler(Looper.getMainLooper()).postDelayed({
            evaluateJS("callbackQRCode({code: '${"QR code feature is in progress, Available Soon"}'})")
        }, 1000)
    }

    @JavascriptInterface override fun selectQRCodeFromPhotos() {

        Handler(Looper.getMainLooper()).postDelayed({
            evaluateJS("callbackQRCode({code: '${"QR code feature is in progress, Available Soon"}'})")
        }, 1000)
    }
}