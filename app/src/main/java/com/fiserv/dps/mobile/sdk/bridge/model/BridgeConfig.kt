package com.fiserv.dps.mobile.sdk.bridge.model

interface BridgeConfig {
    var url: String
    var preCacheContacts: Boolean
}

class BaseConfig(override var url: String): BridgeConfig {
    override var preCacheContacts = false
}