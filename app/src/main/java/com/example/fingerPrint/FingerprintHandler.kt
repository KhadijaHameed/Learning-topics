package com.example.fingerPrint

import android.annotation.TargetApi
import android.hardware.fingerprint.FingerprintManager
import android.os.Build
import android.os.CancellationSignal


@TargetApi(Build.VERSION_CODES.M)
class FingerprintHandler internal constructor(resultHandler: FingerprintResult) : FingerprintManager.AuthenticationCallback () {

    private val resultHandler: FingerprintResult = resultHandler

    fun startAuth(fingerprintManager: FingerprintManager, cryptoObject: FingerprintManager.CryptoObject?) {
        val cancellationSignal = CancellationSignal()
        fingerprintManager.authenticate(cryptoObject, cancellationSignal, 0, this, null)
    }

    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
        resultHandler.update("There was an Auth Error. $errString", false)
    }

    override fun onAuthenticationFailed() {
        resultHandler.update("Auth Failed. ", false)
    }

    override fun onAuthenticationHelp(helpCode: Int, helpString: CharSequence) {
        resultHandler.update("Error: $helpString", false)
    }

    override fun onAuthenticationSucceeded(result: FingerprintManager.AuthenticationResult) {
        resultHandler.update("You can now access the app.", true)
    }

}


