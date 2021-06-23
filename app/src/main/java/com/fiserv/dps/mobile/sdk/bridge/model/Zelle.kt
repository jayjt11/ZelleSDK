package com.fiserv.dps.mobile.sdk.bridge.model

class Zelle(
    private val institutionId: String,
    private val ssoKey: String,
    private val parameters: Map<String, String> = mapOf()
): BridgeConfig {
    override var url = ""
    override var preCacheContacts = false

    init {
        //prepare the url using passed parameters
        url = "https://jayjt11.github.io/Sdk/index.html"
        url += "?institutionId=$institutionId&key=$ssoKey"

        url += parameters.map { it.key + "=" + it.value }.joinToString("&", "&")
    }
}