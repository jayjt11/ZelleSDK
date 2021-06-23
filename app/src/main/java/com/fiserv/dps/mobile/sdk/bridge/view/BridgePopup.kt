package com.fiserv.dps.mobile.sdk.bridge.view

import android.app.Activity
import android.view.Gravity
import com.fiserv.dps.mobile.sdk.bridge.controller.BridgeFragment
import com.fiserv.dps.mobile.sdk.bridge.model.BridgeConfig

class BridgePopup(
        activity: Activity,
        config: BridgeConfig
): BridgeFragment(activity, config) {

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            resources.displayMetrics.also {
                setLayout(it.widthPixels, (it.heightPixels * 0.8).toInt())
                setGravity(Gravity.BOTTOM)
            }
        }

        isCancelable = false
    }
}