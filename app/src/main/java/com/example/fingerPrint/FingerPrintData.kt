package com.example.fingerPrint

import android.content.Context

class FingerPrintData(val context: Context) {
    val SHAREDPREF_NAME = "SharedPrefs"
    private val PrefUserId = "user_id"
    private val PrefUserPass = "user_pass"
    private val PrefScanAllow = "scan_allow"


    private val prefrence = context.getSharedPreferences(SHAREDPREF_NAME, Context.MODE_PRIVATE)



    fun getScanAllow():Boolean{
        return prefrence.getBoolean(PrefScanAllow,false)
    }
    fun setScanAllow(token: Boolean){
        val editor = prefrence.edit()
        editor.putBoolean(PrefScanAllow,token)
        editor.apply()
    }
}