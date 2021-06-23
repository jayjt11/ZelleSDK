package com.fiserv.dps.mobile.sdk.handlers

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

        Toast.makeText(fragment.context, "scanQRCode Called", Toast.LENGTH_LONG).show()
//        val i = IntentIntegrator.forSupportFragment(fragment)
//        i.setBeepEnabled(false)
//        i.initiateScan(listOf(IntentIntegrator.QR_CODE))
    }

    @JavascriptInterface override fun selectQRCodeFromPhotos() {

        Toast.makeText(fragment.context, "selectQRCodeFromPhotos Called", Toast.LENGTH_LONG).show()
    }
}