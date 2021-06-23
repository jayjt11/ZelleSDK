package com.fiserv.dps.mobile.sdk.bridge.controller

import android.app.Activity
import com.fiserv.dps.mobile.sdk.bridge.model.BridgeConfig
import com.fiserv.dps.mobile.sdk.bridge.view.BridgePopup
import com.fiserv.dps.mobile.sdk.bridge.view.BridgeView

class Bridge(
        private val activity: Activity,
        private var config: BridgeConfig
) {
    fun view() = BridgeView(activity, config)
    fun popup() = BridgePopup(activity, config)
}