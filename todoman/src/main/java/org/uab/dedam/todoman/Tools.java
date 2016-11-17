package org.uab.dedam.todoman;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by balazs.ujlaki on 15/11/2016.
 */

public class Tools {
    public static String convertToUTC(String textDateTime){
        DateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        formatter.setTimeZone(Calendar.getInstance().getTimeZone());
        Date date;

        try {
            date = formatter.parse(textDateTime);
        } catch (ParseException e) {
            e.printStackTrace();
            return "1900.01.01 00:00";
        }

        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        return formatter.format(date);
    }

    public static String convertFromUTC(String textDateTime){
        DateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date;

        try {
            date = formatter.parse(textDateTime);
        } catch (ParseException e) {
            e.printStackTrace();
            return "1900.01.01 00:00";
        }

        formatter.setTimeZone(Calendar.getInstance().getTimeZone());
        return formatter.format(date);
    }

    public static Long convertFromUTCToMilliseconds(String textDateTime){
        DateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date;

        try {
            date = formatter.parse(textDateTime);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0L;
        }

        return date.getTime();
    }
}
