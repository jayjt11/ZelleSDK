package com.fiserv.dps.mobile.sdk.bridge.controller

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.fiserv.dps.mobile.sdk.R
import com.fiserv.dps.mobile.sdk.bridge.model.BridgeConfig
import com.fiserv.dps.mobile.sdk.handlers.Handlers
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.fragment_bridge_view.*

@SuppressLint("SetJavaScriptEnabled")
open class BridgeFragment(
    private val activity: Activity,
    private val config: BridgeConfig
): DialogFragment() {

    private val RequestPermissionCode = 1
    private val evaluateJS = { js: String -> webView.evaluateJavascript(js, null) }
    var sharedPreferences: SharedPreferences? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bridge_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = activity.getSharedPreferences("zelle", Context.MODE_PRIVATE)
        webView.settings.javaScriptEnabled = true

        webView.addJavascriptInterface(Handlers(activity, this, evaluateJS), "FTAndroid")
        webView.setWebChromeClient(object : WebChromeClient() {
            override fun onJsAlert(
                view: WebView?,
                url: String?,
                message: String?,
                result: JsResult?
            ): Boolean {
                return super.onJsAlert(view, url, message, result)
            }
        })
        webView.loadUrl(config.url)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents != null) {
                evaluateJS("callbackQRCode({code: '${result.contents}'})")
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.d("Permission", "Called")
        Toast.makeText(activity, "Permission Denied", Toast.LENGTH_SHORT).show()
    }
}