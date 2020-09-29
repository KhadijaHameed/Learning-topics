package com.example.detectingphoneincomenoutgoingcalls;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.example.roomwrapperimplement.R;


public class DetectCallMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detect_call_activity_main);

   if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_PHONE_STATE))
        {
            ActivityCompat.requestPermissions (this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);

    } else {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
    }

}
    else{
        //do nothing
    }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {


        switch (requestCode) {
            case 1:{
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

              if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                  Toast.makeText(this, "PERMISSION GRANTED", Toast.LENGTH_SHORT).show();
              }

                }else{
                    Toast.makeText(this, "PERMISSION NOT GRANTED", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}

