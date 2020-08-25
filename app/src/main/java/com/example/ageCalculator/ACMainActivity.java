package com.example.ageCalculator;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.roomwrapperimplement.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ACMainActivity extends AppCompatActivity implements View.OnClickListener , TextToSpeech.OnInitListener{

    EditText et_date_of_birth;

    TextView tv_current_day,tv_current_date,tv_year,tv_month,tv_days,tv_birthdate_day,tv_calculation_total_years,tv_calculation_total_months,
    tv_calculation_total_weeks,tv_calculation_total_days,tv_calculation_total_hours,tv_calculation_total_mintues,tv_calculation_total_seconds,
            tv_star_name;

    ImageView iv_today_calender_icon,iv_bithdate_calender_icon;

    Button btn_calculate,btn_clear;

    LinearLayout ll_upcoming_birthdays,ll_star;

    private TextToSpeech tts;

    Calendar myCalendar;

    int day,month,year,age,months,days;

    String currentDateinString ,birthdateinString,currentdate,birthdate;

    long diff,diffSeconds,diffMinutes,diffHours,diffDays,diffWeeks,diffmonth,diffyear,remaingdays;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_activity_main);

        tts = new TextToSpeech(this, this);

        initViews();

        currentDateSet();

       SetListners();








        btn_calculate.setOnClickListener(new View.OnClickListener() {
          //  @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

              if (et_date_of_birth.getText().toString().trim().equals("")){
                  et_date_of_birth.setError("this field can not be empty");
                  alertDailogeForEpmtyEditTexet();
              }
              else {
                  calculationOfDateandTime();
                  layoutVISIBLE();
                  setDataFromTextviews();
                  speakOut();


             


              }


            }

        });


    }

        @Override
    protected void onDestroy() {

        if (tts != null){
            tts.stop();
            tts.shutdown();
        }

        super.onDestroy();
    }

    private void speakOut() {

        String text = tv_year.getText().toString();
        tts.speak("your age is "+text, TextToSpeech.QUEUE_FLUSH,null);


    }

    public void SetListners(){
        iv_today_calender_icon.setOnClickListener(this);
        iv_bithdate_calender_icon.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
       if (v.getId() == R.id.iv_today_calender_icon){
         datepickerMethod();
       }
       if(v.getId() == R.id.iv_bithdate_calender_icon){
           birthdatePickerMethod();
       }if (v.getId() == R.id.btn_clear){
          String tv_total =  tv_calculation_total_seconds.getText().toString();
           if (tv_total.isEmpty()){
               Toast.makeText(ACMainActivity.this,"Plese Enter Date And Calculate Them Then Click Clear ", Toast.LENGTH_SHORT).show();
           }else {
           layoutINVISIBLE();
            ClearDateFromTextviews();
            cleardataSpeakOut();
        }
       }

    }

    private void cleardataSpeakOut() {
        tts.speak("Your Data will be clear now tou ready to input new date ", TextToSpeech.QUEUE_FLUSH,null);
    }

    public void datepickerMethod(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(ACMainActivity.this , new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                tv_current_date.setText(dayOfMonth+"/"+month+"/"+year);
               currentDateinString = dayOfMonth+"/"+month+"/"+year;
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date =null;
                try {
                    date = simpleDateFormat.parse(currentDateinString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                DateFormat format = new SimpleDateFormat("EEEE");
                String finalCurrentDate = format.format(date);
                tv_current_day.setText(finalCurrentDate);
            }
        },year,month,day);
        datePickerDialog.show();
    }

    public void birthdatePickerMethod(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(ACMainActivity.this , new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                et_date_of_birth.setText(dayOfMonth+"/"+month+"/"+year);
                birthdateinString = dayOfMonth+"/"+month+"/"+year;
                SimpleDateFormat format1=new SimpleDateFormat("dd/MM/yyyy");
                Date dt1= null;
                try {
                    dt1 = format1.parse(birthdateinString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                DateFormat format2=new SimpleDateFormat("EEEE");
                String finalDay=format2.format(dt1);
                tv_birthdate_day.setText(finalDay);
            }
        },year,month,day);
        datePickerDialog.show();

    }

    public void currentDateSet(){
        myCalendar = Calendar.getInstance();
        day = myCalendar.get(Calendar.DAY_OF_MONTH);
        month = myCalendar.get(Calendar.MONTH);
        year = myCalendar.get(Calendar.YEAR);

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        String dayOfTheWeek = sdf.format(d);
        tv_current_day.setText(dayOfTheWeek);

        month = month + 1;
        tv_current_date.setText(day + "/" + month + "/" + year);

    }

    public void alertDailogeForEpmtyEditTexet(){
        final AlertDialog alertDialog = new AlertDialog.Builder(ACMainActivity.this).create();
        alertDialog.setTitle("BE ALERT");
        alertDialog.setMessage("Firstly Enter Your Birthdate Then Click To Calculate  ");
        alertDialog.setIcon(R.drawable.alert);
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    public void  layoutVISIBLE(){
        if (ll_upcoming_birthdays.getVisibility() == View.VISIBLE || ll_star.getVisibility() == View.VISIBLE){
            ll_upcoming_birthdays.setVisibility(View.VISIBLE);ll_star.setVisibility(View.VISIBLE);
        }else {
            ll_star.setVisibility(View.VISIBLE);
            ll_upcoming_birthdays.setVisibility(View.VISIBLE);
        }
    }

    public void  layoutINVISIBLE(){
        if (ll_upcoming_birthdays.getVisibility() == View.VISIBLE || ll_star.getVisibility() == View.VISIBLE){
            ll_upcoming_birthdays.setVisibility(View.GONE);ll_star.setVisibility(View.GONE);
        }else {
            ll_star.setVisibility(View.GONE);
            ll_upcoming_birthdays.setVisibility(View.GONE);
        }
    }

    public void calculationOfDateandTime() {
        /*
        this calculation in this link below
        https://kodejava.org/how-do-i-calculate-difference-in-days-between-two-dates/*/

        currentdate = tv_current_date.getText().toString();
        birthdate = et_date_of_birth.getText().toString();



        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date currentDate = null;
        Date birthdayDate = null;
        try {
            currentDate = simpleDateFormat.parse(currentdate);
            birthdayDate = simpleDateFormat.parse(birthdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        // Set the date for both of the calendar instance
        cal1.setTime(currentDate);
        cal2.setTime(birthdayDate);

        // Get the represented date in milliseconds
        long millis1 = cal1.getTimeInMillis();
        long millis2 = cal2.getTimeInMillis();

        // Calculate difference in milliseconds
        diff = millis1 - millis2;

        // Calculate difference in seconds
        diffSeconds = diff / 1000;

        // Calculate difference in minutes
        diffMinutes = diff / (60 * 1000);

        // Calculate difference in hours
        diffHours = diff / (60 * 60 * 1000);

        // Calculate difference in days
        diffDays = diff / (24 * 60 * 60 * 1000);

        //Calculate difference in week
        diffWeeks = diff / (24 * 60 * 60 * 1000 * 7);

        // Calculate difference in year
        diffyear = diff / (1000L * 60 * 60 * 24 * 365);

        // Calculate difference in month
        diffmonth = diff / (1000L * 60 * 60 * 24 * 30);

        // calculate remaing days
        remaingdays =  diff / (24 * 60 * 60 * 1000);
        //  Toast.makeText(MainActivity.this,"Remain days: " + remaingdays + " .",Toast.LENGTH_SHORT).show();

        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.setTime(birthdayDate);
        //  dob.set(1996, month, day);

         age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

         months = today.get(Calendar.MONTH)-dob.get(Calendar.MONTH);

         days = today.get(Calendar.DATE)-dob.get(Calendar.DATE);

        Toast.makeText(this,""+months+"", Toast.LENGTH_SHORT).show();

        Toast.makeText(this,""+age+"", Toast.LENGTH_SHORT).show();

        Toast.makeText(this,""+days+"", Toast.LENGTH_SHORT).show();
        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }
        if (today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)) {
            months--;
        }
        if (today.get(Calendar.DATE) < dob.get(Calendar.DATE)) {
            days--;
        }

    }

    public  void  setDataFromTextviews(){
        // Day SEt
        tv_calculation_total_days.setText(""+diffDays);
        tv_calculation_total_weeks.setText(""+diffWeeks);
        tv_calculation_total_months.setText(""+diffmonth);
        tv_calculation_total_years.setText(""+diffyear);
        tv_year.setText(""+age);
       tv_days.setText(""+days);
        tv_month.setText(""+months);
        //Time SEt
        tv_calculation_total_hours.setText(""+diffHours);
        tv_calculation_total_mintues.setText(""+diffMinutes);
        tv_calculation_total_seconds.setText(""+diffSeconds);

    }

    public void ClearDateFromTextviews(){
        et_date_of_birth.setText("");
        tv_month.setText("");
        tv_days.setText("");
        tv_year.setText("");
        tv_calculation_total_years.setText("");
        tv_calculation_total_months.setText("");
        tv_calculation_total_weeks.setText("");
        tv_calculation_total_days.setText("");
        tv_calculation_total_hours.setText("");
        tv_calculation_total_mintues.setText("");
        tv_calculation_total_seconds.setText("");
        tv_birthdate_day.setText("");

    }

    public void initViews(){
        //TextViews
        tv_star_name = findViewById(R.id.tv_star_name);
        tv_current_day = findViewById(R.id.tv_current_day);
        tv_current_date = findViewById(R.id.tv_current_date);
        tv_year = findViewById(R.id.tv_year);
        tv_month = findViewById(R.id.tv_month);
        tv_days = findViewById(R.id.tv_days);
        tv_birthdate_day = findViewById(R.id.tv_birthdate_day);
        tv_calculation_total_years = findViewById(R.id.tv_calculation_total_years);
        tv_calculation_total_months = findViewById(R.id.tv_calculation_total_months);
        tv_calculation_total_weeks = findViewById(R.id.tv_calculation_total_weeks);
        tv_calculation_total_days = findViewById(R.id.tv_calculation_total_days);
        tv_calculation_total_hours = findViewById(R.id.tv_calculation_total_hours);
        tv_calculation_total_mintues = findViewById(R.id.tv_calculation_total_mintues);
        tv_calculation_total_seconds = findViewById(R.id.tv_calculation_total_seconds);

        //ImageViews
        iv_today_calender_icon = findViewById(R.id.iv_today_calender_icon);
        iv_bithdate_calender_icon =findViewById(R.id.iv_bithdate_calender_icon);

        //EditText
        et_date_of_birth = findViewById(R.id.et_date_of_birth);

        //Buttons
        btn_calculate = findViewById(R.id.btn_calculate);
        btn_clear =findViewById(R.id.btn_clear);

        //LinearLayouts
        ll_upcoming_birthdays = findViewById(R.id.ll_upcoming_birthdays);
        ll_star = findViewById(R.id.ll_star);

    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                btn_calculate.setEnabled(true);
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }
    }

    /*private void calculateNextBirthday() {
        // TODO Auto-generated method stub

        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        final String strBDay = sdf.format(dt);
        try {

            dt = sdf.parse(strBDay);
        } catch (final java.text.ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        final Calendar c = Calendar.getInstance();

        c.setTime(dt);
        // c.add(Calendar.DATE, value);
        final Calendar today = Calendar.getInstance();
        // Take your DOB Month and compare it to current
        // month
        final int BMonth = c.get(Calendar.MONTH);
        final int CMonth = today.get(Calendar.MONTH);
        c.set(Calendar.YEAR, today.get(Calendar.YEAR));
        c.set(Calendar.DAY_OF_WEEK,
                today.get(Calendar.DAY_OF_WEEK));
        if (BMonth <= CMonth) {
            c.set(Calendar.YEAR,
                    today.get(Calendar.YEAR) + 1);
        }
        // Result in millis

        final long millis = c.getTimeInMillis()
                - today.getTimeInMillis();
        // Convert to days
        final long days = millis / 86400000; // Precalculated
        // (24 *
        // 60 *
        // 60 *
        // 1000)
        // final String dayOfTheWeek =
        // sdf.format(BDay.getTime());
        sdf = new SimpleDateFormat("EEEE");
        final String dayOfTheWeek = sdf.format(dt);
        tv.setText("" + days + " days");
        txt10.setText("" + dayOfTheWeek);
    }*/
}
