package com.fiserv.dps.mobile.sdk.handlers

import android.app.Activity
import androidx.fragment.app.Fragment

class Handlers(
        private val activity: Activity,
        private val fragment: Fragment,
        private val evaluateJS: (String)->Unit
):
        PermissionsHandler by PermissionsHandlerImpl(activity, evaluateJS),
        ContactsHandler by ContactsHandlerImpl(activity, evaluateJS),
        LocationHandler by LocationHandlerImpl(activity, evaluateJS),
        QRCodeHandler by QRCodeHandlerImpl(fragment, evaluateJS),
        PhotosHandler by PhotosHandlerImpl(fragment, evaluateJS),
        ShareHandler by ShareHandlerImpl(activity, evaluateJS),
        PopupHandler by PopupHandlerImpl(fragment)



