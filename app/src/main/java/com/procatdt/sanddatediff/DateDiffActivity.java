package com.procatdt.sanddatediff;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateDiffActivity extends AppCompatActivity {
    final static String tag = "TagDateDiff";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_diff);
        String[] startDateArray = {"3/3/1964", "1/1/2010", "1/1/2016"};
        String[] endDateArray = {"1/1/2016", "3/1/2016", "3/13/2016", "3/14/2016", "10/23/2016", "12/31/2016",
                "1/1/2017", "3/1/2017", "3/12/2017", "3/13/2017", "10/23/2017", "12/31/2017"};
        for (int i=0; i<startDateArray.length; i++) {
            for (int j=0; j < endDateArray.length; j++) {
                Log.d(tag, "Method 1: " + startDateArray[i] + " to " + endDateArray[j] + " = " +
                        getDateDiff1(startDateArray[i], endDateArray[j]) + " days");
                Log.d(tag, "Method 2: " + startDateArray[i] + " to " + endDateArray[j] + " = " +
                        getDateDiff2(startDateArray[i], endDateArray[j]) + " days");
                Log.d(tag, "Method 3: " + startDateArray[i] + " to " + endDateArray[j] + " = " +
                        getDateDiff3(startDateArray[i], endDateArray[j]) + " days");
            }
        }
    }

    public static long getDateDiff1 (String startDate, String endDate) {
        // This method uses the java.util.date library
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        try {
            Date baseDate = sdf.parse(startDate);
            Date delDate = sdf.parse(endDate);
            return (delDate.getTime() - baseDate.getTime()) / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            Log.d(tag, "Error parsing date");
        }
        return 0;
    }

    public static long getDateDiff2(String startDate, String endDate) {
        // This method uses java.util.calendar library
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        try {
            Calendar calendar1 = Calendar.getInstance();
            Calendar calendar2 = Calendar.getInstance();
            calendar1.setTime(sdf.parse(startDate));
            calendar2.setTime(sdf.parse(endDate));
            return (calendar2.getTimeInMillis() - calendar1.getTimeInMillis()) / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            Log.d(tag, "Error parsing date");
        }
        return 0;
    }

    public static long getDateDiff3(String startDate, String endDate) {
        // This method uses the joda-time library. Add to gradle: compile 'joda-time:joda-time:+'
        try {
            DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/dd/yyyy");
            DateTime dt1 = new DateTime(dtf.parseDateTime(startDate));
            DateTime dt2 = new DateTime(dtf.parseDateTime(endDate));
            Days d = Days.daysBetween(dt1, dt2);
            return d.getDays();
        } catch (Exception e) {
            Log.d(tag, "Error parsing date");
        }
        return 0;
    }

}
