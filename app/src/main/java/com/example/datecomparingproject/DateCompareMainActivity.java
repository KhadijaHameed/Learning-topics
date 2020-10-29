package com.example.datecomparingproject;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.roomwrapperimplement.R;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateCompareMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_compare_activity_main);

        SimpleDateFormat format = new SimpleDateFormat("dd-MM");
        try {
            Date startDate = format.parse("11-09");
            Date endDate = format.parse("11-10");
            Date testingDate = format.parse("10-09");

            if (testingDate.after(startDate) && testingDate.before(endDate)) {
                Toast.makeText(DateCompareMainActivity.this, "The date is in range", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(DateCompareMainActivity.this, "The date is not in range", Toast.LENGTH_SHORT).show();
            }
           }catch (Exception e) {
            Toast.makeText(DateCompareMainActivity.this,"Exception occurred", Toast.LENGTH_SHORT).show();
        }
    }

}
