package com.example.fingerPrint

import android.Manifest
import android.annotation.TargetApi
import android.app.AlertDialog
import android.app.KeyguardManager
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.hardware.biometrics.BiometricPrompt
import android.hardware.fingerprint.FingerprintManager
import android.os.Build
import android.os.Bundle
import android.os.CancellationSignal
import android.os.Handler
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.roomwrapperimplement.R
import java.io.IOException
import java.security.*
import java.security.cert.CertificateException
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.NoSuchPaddingException
import javax.crypto.SecretKey

class FingerPrintActivity :AppCompatActivity(),FingerprintResult{



    private var mHeadingLabel: TextView? = null
    private var mFingerprintImage: ImageView? = null
    private var mParaLabel: TextView? = null
    private var fingerprintManager: FingerprintManager? = null
    private var keyguardManager: KeyguardManager? = null
    private var keyStore: KeyStore? = null
    private var cipher: Cipher? = null
    private val KEY_NAME = "AndroidKey"
    private var mdialog: AlertDialog? = null
   private  lateinit var ivFingerPrint : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.finger_print)

        ivFingerPrint = findViewById(R.id.fingerprint)
        ivFingerPrint.setOnClickListener {

            FingerPrintAuth()
        }

    }

    fun LoginAPI(view: View?) {}

    fun FingerPrintAuth() {
        FingerPrintDialog()
    }

    private fun FingerPrintDialog() {
        val factory = LayoutInflater.from(this)
        val fingerPrintDialogView: View = factory.inflate(R.layout.finger_print_dialog, null)
        mdialog = AlertDialog.Builder(this).setCancelable(true).create()
        mdialog?.setView(fingerPrintDialogView)
        mFingerprintImage = fingerPrintDialogView.findViewById(R.id.fingerprintImage)
        mParaLabel = fingerPrintDialogView.findViewById(R.id.paraLabel)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            fingerprintManager = getSystemService(FINGERPRINT_SERVICE) as FingerprintManager
            keyguardManager = getSystemService(KEYGUARD_SERVICE) as KeyguardManager
            if (!fingerprintManager!!.isHardwareDetected) {
                mParaLabel?.setText("Fingerprint Scanner not detected in Device")
            } else if (ContextCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) !== PackageManager.PERMISSION_GRANTED) {
                mParaLabel?.setText("Permission not granted to use Fingerprint Scanner")
            } else if (!keyguardManager!!.isKeyguardSecure) {
                mParaLabel?.setText("Add Lock to your Phone in Settings")
            } else if (!fingerprintManager!!.hasEnrolledFingerprints()) {
                mParaLabel?.setText("You should add atleast 1 Fingerprint to use this Feature")
            } else {
                mParaLabel?.setText("Place your Finger on Scanner to Access the App.")
                generateKey()
                if (cipherInit()) {
                    val cryptoObject = FingerprintManager.CryptoObject(cipher!!)
                    val fingerprintHandler = FingerprintHandler(this)
                    fingerprintHandler.startAuth(fingerprintManager!!, cryptoObject)
                }
            }
        }
        mdialog?.show()
    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun generateKey() {
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore")
            val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore")
            keyStore?.load(null)
            keyGenerator.init(KeyGenParameterSpec.Builder(KEY_NAME, KeyProperties.PURPOSE_ENCRYPT or
                    KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7).build())
            keyGenerator.generateKey()
        } catch (e: KeyStoreException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: CertificateException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: InvalidAlgorithmParameterException) {
            e.printStackTrace()
        } catch (e: NoSuchProviderException) {
            e.printStackTrace()
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun cipherInit(): Boolean {
        cipher = try {
            Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/" + KeyProperties.BLOCK_MODE_CBC + "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7)
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException("Failed to get Cipher", e)
        } catch (e: NoSuchPaddingException) {
            throw RuntimeException("Failed to get Cipher", e)
        }
        return try {
            keyStore!!.load(null)
            val key = keyStore!!.getKey(KEY_NAME,
                    null) as SecretKey
            cipher?.init(Cipher.ENCRYPT_MODE, key)
            true
        } catch (e: KeyStoreException) {
            throw RuntimeException("Failed to init Cipher", e)
        } catch (e: CertificateException) {
            throw RuntimeException("Failed to init Cipher", e)
        } catch (e: UnrecoverableKeyException) {
            throw RuntimeException("Failed to init Cipher", e)
        } catch (e: IOException) {
            throw RuntimeException("Failed to init Cipher", e)
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException("Failed to init Cipher", e)
        } catch (e: InvalidKeyException) {
            throw RuntimeException("Failed to init Cipher", e)
        }
    }

    override fun update(s: String?, b: Boolean) {
        Toast.makeText(applicationContext,""+s,Toast.LENGTH_LONG).show()
        mParaLabel!!.text = s
        if (!b) {
            mParaLabel?.setTextColor(ContextCompat.getColor(this, R.color.colorAccent))
        } else {
            mParaLabel?.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary))
            //    mFingerprintImage!!.setImageResource(R.mipmap.action_done)
            Handler().postDelayed({ mdialog!!.dismiss() }, 1000)
        }
    }

}