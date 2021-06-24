package com.fiserv.dps.mobile.sdk.util

import android.app.Activity
import android.database.Cursor
import android.os.Handler
import android.os.Looper
import android.provider.ContactsContract
import android.util.Log
import com.fiserv.dps.mobile.sdk.bridge.model.Contact

class Util() {

    private fun getContact(activity : Activity, evaluateJS: (String)->Unit, key : String) {

        var contact : Contact? = null
        var cursor: Cursor? = null
        var name: String? = null
        var phonenumber: String? = null

        cursor = activity.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        while (cursor!!.moveToNext()) {
            name =
                cursor!!.getString(cursor!!.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME ))
            phonenumber =
                cursor!!.getString(cursor!!.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))


            if (name.toString().contains(key) || phonenumber.toString().contains(key)) {
                contact = Contact()
                contact.name = name
                contact.number = phonenumber
            }
        }
        cursor!!.close()

        Log.d("Contact Name", contact!!.name.toString())

        Handler(Looper.getMainLooper()).postDelayed({
            evaluateJS("callbackOneContact({contact: '${contact!!.name}'})")
        }, 3000)

    }

}