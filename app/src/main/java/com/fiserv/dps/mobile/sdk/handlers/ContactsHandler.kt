package com.fiserv.dps.mobile.sdk.handlers

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.database.Cursor
import android.provider.ContactsContract
import android.util.Log
import android.webkit.JavascriptInterface
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.fiserv.dps.mobile.sdk.bridge.model.Contact

interface ContactsHandler {
    @JavascriptInterface fun getContacts()
    @JavascriptInterface fun getOneContact(key : String)
}

class ContactsHandlerImpl(private val activity: Activity, private val evaluateJS: (String)->Unit): ContactsHandler {

    var cursor: Cursor? = null
    private val RequestPermissionCode = 1
    var name: String? = null
    var phonenumber: String? = null
    var sharedPreferences : SharedPreferences? = null
    var mycontact : Contact? = null

    @JavascriptInterface override fun getContacts() {

        Toast.makeText(activity, "getContacts called", Toast.LENGTH_LONG).show()
        evaluateJS("callbackContacts({contact: '${"callbackContacts called"}'})")

    }
    @JavascriptInterface override fun getOneContact(key : String) {

        Toast.makeText(activity, "getOneContact called", Toast.LENGTH_LONG).show()

        evaluateJS("callbackOneContact({contact: '${"getOneContact called"}'})")

//        sharedPreferences = activity.getSharedPreferences("zelle", Context.MODE_PRIVATE)
//        var allowed = sharedPreferences!!.getBoolean("allowed", false)
//        if (allowed) {
//            getContact(key)
//
//        } else {
//            enableRuntimePermission()
//        }
//
//        enableRuntimePermission()

    }

    private fun getContact(key : String) {

        var contact : Contact? = null

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

        evaluateJS("callbackOneContact({contact: '${contact!!.name}'})")

    }



    private fun enableRuntimePermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(
                activity,
                Manifest.permission.READ_CONTACTS
            )
        ) {
            Toast.makeText(
                activity,
                "CONTACTS permission allows us to Access CONTACTS app",
                Toast.LENGTH_LONG
            ).show()
        } else {
            ActivityCompat.requestPermissions(
                activity, arrayOf(
                    Manifest.permission.READ_CONTACTS
                ), RequestPermissionCode
            )
        }
    }
}