package com.fiserv.dps.mobile.sdk.bridge.view

import android.app.Activity
import com.fiserv.dps.mobile.sdk.bridge.controller.BridgeFragment
import com.fiserv.dps.mobile.sdk.bridge.model.BridgeConfig

class BridgeView(
    activity: Activity,
    config: BridgeConfig
): BridgeFragment(activity, config) {}