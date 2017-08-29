The Situation

For my Android app, I needed simple function that would output the number of days between any 2 dates (same as DateDiff in VB.net). This function was being used within some other calculations as a “key” to unlock certain parts of my Android app. However as part of the requirements, the number of days must match Microsoft Excel.

Java.util.date

Simply put, the java.util.date library DOES NOT match up with Microsoft excel. In order to calculate the number of days difference using the java.util.date method, both dates are converted to milliseconds and the difference is divided by the number of milliseconds within a day.

I created a simple spreadsheet where one date is subtracted from another date to determine the number of days difference. This is quite simply performed in MS Excel. However, java.util.date does NOT match up with Microsoft Excel. I believe it has something to do with leap year, but the numbers are intermittently off by one day. As you can see in this spreadsheet, on dates before March 13, the numbers are correctly, however they are off by one day after that date on leap years. On non-leap years, the dates are correct before March 12, and off by 1 day after that date.

Java.util.calendar

My second attempt, I tried to use the java.util.calendar function in conjunction with the simple date format. However, this is once again converted to milliseconds and then divided by the number of milliseconds in a day.

This results in the exact same results as java.util.date. Apparently, the calendar object uses the same library as a base.

Joda-Time Library

Third time is a charm in this case. I used the joda-time library to correctly process the number of days difference.  I did have to import the joda-time library into my project which added some size to the APK (about 300kb).  To import the library, just add the following to the gradle: compile ‘joda-time:joda-time:+’

There are some downsides to Joda-time:

It’s not safe to convert between joda-time and util.date
Joda-Time increases the apk by about 300Kb due to a timezone database which is contained within it.
There is an IANA timezone database that you’ll need to monitor.
If the timezones change, the database needs to be updated.
Java 8 has an improved date-time library that might fix all this??
