package com.destin.sehaikun;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by dest1 on 2015/10/23.
 */
public class TimeUtils {
    public static final String DEFAULT_FULL_FORMAT = "yyyy-MM-dd HH:mm";

    public static String formatTime(String format, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        return sdf.format(date);
    }
}
