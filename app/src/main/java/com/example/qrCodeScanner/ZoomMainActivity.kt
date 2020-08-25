package com.example.qrCodeScanner

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.roomwrapperimplement.R
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView

class ZoomMainActivity : AppCompatActivity(),ZXingScannerView.ResultHandler{


    val TAG = "test"
    lateinit var rl_dashboard: RelativeLayout
    lateinit var rl_scan:RelativeLayout
    lateinit var ll_dash_boardexpand: LinearLayout
    var ll_main:LinearLayout? = null
    var animZoomOut: Animation? = null

    var mScannerView: ZXingScannerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.zoom_activity_main)

        ll_main = findViewById(R.id.ll_main)
        animZoomOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_out)



        rl_scan = findViewById<RelativeLayout>(R.id.rl_scan)
        rl_scan.setOnClickListener(View.OnClickListener { QrScanner() })
    }

    fun QrScanner() {
        mScannerView = ZXingScannerView(this) // Programmatically initialize the scanner view<br />
        setContentView(mScannerView)
        mScannerView!!.setResultHandler(this) // Register ourselves as a handler for scan results.<br />
        mScannerView!!.startCamera()
    }



    override fun handleResult(result: Result) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Scan Result")
        builder.setMessage(result.text)
        val alert1 = builder.create()
        alert1.show()
        mScannerView!!.removeAllViews() //<- here remove all the views, it will make an Activity having no View
        /*mScannerView.stopCamera();*/ //<- then stop the camera
        setContentView(R.layout.activity_main) //<- and set the View again.
        val vString = result.text
        runOnUiThread(Runnable { Toast.makeText(this, vString, Toast.LENGTH_LONG).show() })
    }
}
