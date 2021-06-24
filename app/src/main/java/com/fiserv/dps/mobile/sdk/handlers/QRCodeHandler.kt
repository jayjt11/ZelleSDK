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

//        Toast.makeText(fragment.context, "scanQRCode Called", Toast.LENGTH_LONG).show()
//        val i = IntentIntegrator.forSupportFragment(fragment)
//        i.setBeepEnabled(false)
//        i.initiateScan(listOf(IntentIntegrator.QR_CODE))

        Handler(Looper.getMainLooper()).postDelayed({
            evaluateJS("callbackQRCode({code: '${"scanQRCode not implemented"}'})")
        }, 3000)
    }

    @JavascriptInterface override fun selectQRCodeFromPhotos() {

        Handler(Looper.getMainLooper()).postDelayed({
            evaluateJS("callbackQRCode({code: '${"selectQRCodeFromPhotos not implemented"}'})")
        }, 3000)

      //  Toast.makeText(fragment.context, "selectQRCodeFromPhotos Called", Toast.LENGTH_LONG).show()
    }
}