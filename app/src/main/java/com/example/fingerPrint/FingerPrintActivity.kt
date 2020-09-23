package com.example.fingerPrint

import android.content.Context
import android.content.DialogInterface
import android.hardware.biometrics.BiometricPrompt
import android.hardware.fingerprint.FingerprintManager
import android.os.Build
import android.os.Bundle
import android.os.CancellationSignal
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.roomwrapperimplement.R
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FingerPrintActivity :AppCompatActivity(){


    var fingerPrintData: FingerPrintData? = null
    var executor: Executor? = null
    var bmPrompt: BiometricPrompt? = null
    lateinit  var btnCheck: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.finger_print)

        btnCheck = findViewById(R.id.btn_check_fingerprint)

        fingerPrintData = FingerPrintData(this)

        btnCheck.setOnClickListener {
            Log.d("test","check click")
                     checkForFingerPrint()

        }



        if (bmPrompt != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                bmPrompt!!.authenticate(CancellationSignal(), executor!!, object : BiometricPrompt.AuthenticationCallback() {
                    override fun onAuthenticationFailed() {
                        super.onAuthenticationFailed()
                        Toast.makeText(applicationContext, " Authentication Failed",Toast.LENGTH_LONG).show()
                        runOnUiThread { }
                    }

                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                        super.onAuthenticationSucceeded(result)
                        runOnUiThread {

                         //   CallAPI(fingerPrintData!!.getUserId(),
                            //   fingerPrintData!!.getUserPass(), context)

                            Toast.makeText(applicationContext, " Authentication Succeeded",Toast.LENGTH_LONG).show()
                        }
                    }
                })
            }
        }

    }

    private fun checkForFingerPrint() {
        executor = Executors.newSingleThreadExecutor()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Log.d("test", "device  support scanning!")
            Toast.makeText(this, "device  support scanning!", Toast.LENGTH_SHORT).show()
            //Fingerprint API only available on from Android 6.0 (M)
            val fingerprintManager = getSystemService(Context.FINGERPRINT_SERVICE) as FingerprintManager
            if (fingerprintManager.isHardwareDetected) {
                Log.d("test","isHardwareDetected")
                        if (fingerprintManager.hasEnrolledFingerprints()) {
                            Log.d("test","hasEnrolledFingerprints "+fingerprintManager.hasEnrolledFingerprints())
                            Toast.makeText(this, "no         hasEnrolledFingerprints "+fingerprintManager.hasEnrolledFingerprints(), Toast.LENGTH_SHORT).show()
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        Log.d("test",">=VERSION_CODES.P")
                        Toast.makeText(this, ">=VERSION_CODES.P", Toast.LENGTH_SHORT).show()
                        bmPrompt = BiometricPrompt.Builder(applicationContext)
                                .setTitle("Login")
                                .setSubtitle("Scan to get login into " + "123")
                                .setNegativeButton("Cancel", executor as ExecutorService,
                                        DialogInterface.OnClickListener { dialogInterface, i -> }).build()
                    }else{
                        Log.d("test","no         >=VERSION_CODES.P")
                        Toast.makeText(this, "no         >=VERSION_CODES.P", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Atleast register your finger print before you use it!", Toast.LENGTH_SHORT).show()
            }
        } else {
            Log.d("test", "device doesn't support scanning!")
            Toast.makeText(this, "Sorry your device doesn't support scanning!", Toast.LENGTH_SHORT).show()
        }
    }

}