package com.fiserv.dps.mobile.sdk.handlers

import android.webkit.JavascriptInterface
import androidx.fragment.app.Fragment
import com.fiserv.dps.mobile.sdk.bridge.view.BridgePopup
import com.fiserv.dps.mobile.sdk.bridge.view.BridgeView

interface PopupHandler {
    @JavascriptInterface fun dismissPopup()
}

class PopupHandlerImpl(private val fragment: Fragment): PopupHandler {
    @JavascriptInterface
    override fun dismissPopup() {
        if (fragment !is BridgeView) {
            (fragment as? BridgePopup)?.dismiss()
        }
    }
}