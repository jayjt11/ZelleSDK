package com.fiserv.dps.mobile.sdk.handlers

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Handler
import android.os.Looper
import android.provider.ContactsContract
import android.util.Log
import android.webkit.JavascriptInterface
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
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

    @JavascriptInterface override fun getContacts() {

        Handler(Looper.getMainLooper()).postDelayed({
            evaluateJS("callbackContacts({contacts: '${"All contacts feature is in progress, Available Soon"}'})")
        }, 1000)

    }
    @JavascriptInterface override fun getOneContact(key : String) {

        sharedPreferences = activity.getSharedPreferences("zelle", Context.MODE_PRIVATE)
        var allowed = sharedPreferences!!.getBoolean("allowed", false)
        enableRuntimePermission(key)
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

            if (name.toString().toLowerCase().trim().contains(key.toLowerCase().trim()) || phonenumber.toString().contains(key)) {
                contact = Contact()
                contact.name = name
                contact.number = phonenumber
            }
        }
        cursor!!.close()

        if(contact == null) {
            Handler(Looper.getMainLooper()).postDelayed({
                evaluateJS("callbackOneContact({contact: '${"Contact Not Found"}'})")
            }, 1000)
        } else {

            Log.d("Contact Name", contact!!.name.toString())
            Handler(Looper.getMainLooper()).postDelayed({
                evaluateJS("callbackOneContact({contact: '${contact!!.name + "," + contact!!.number}'})")
            }, 1000)
        }

    }

    private fun enableRuntimePermission(key : String) {

        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(activity, arrayOf(Manifest.permission.READ_CONTACTS), RequestPermissionCode)
        } else {
            getContact(key)
        }
    }
}