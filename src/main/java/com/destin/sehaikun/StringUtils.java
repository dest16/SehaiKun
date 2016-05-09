package com.destin.sehaikun;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;

/**
 * Created by dest1 on 2015/9/25.
 */
public class StringUtils {
    private final static String CHAR_SET = "utf-8";
    private final static String NULL = "null";
    private final static String STARS = "****";
    public final static String[] EMPTY_ARRAY = new String[0];

    //    public static void firstLowercase(String str) {
//
//        Pattern pattern = Pattern.compile("[a-z]+\\s*");
//        Matcher matcher = pattern.matcher(str);
//        while (matcher.find()) {
//            String tmp = matcher.group();
//
//            str.replace(matcher.start(), matcher.start() + 1, tmp.substring(0, 1).toUpperCase());
//        }
//        System.out.println(a.toString());
//    }
    public static String firstLowerCase(@NonNull String str) {
        char[] chars = str.toCharArray();
        chars[0] = Character.toLowerCase(chars[0]);
        return String.valueOf(chars);
    }

    public static boolean isEmpty(String string) {
        return TextUtils.isEmpty(string) || string.trim().length() == 0;
    }

    public static String hideInfo(String raw) {
        if (TextUtils.isEmpty(raw) || raw.length() < 1)
            return raw;
        else if (raw.length() < 9)
            return STARS + raw.substring(raw.length() - 1, raw.length());
        else
            return raw.substring(0, 4) + STARS + raw.substring(raw.length() - 4, raw.length());
    }

    public static String money(double money) {
        String str;
        DecimalFormat df = new DecimalFormat("##.##");
        if (money < 10000) {
            str = String.format("%s元", df.format(money));
        } else if (money < 100000000) {
            str = String.format("%s万元", df.format(money / 10000.0));
        } else {
            str = String.format("%s亿元", df.format(money / 100000000.0));
        }
        return str;
    }

    public static String encode(String input) {
        String output = null;
        try {
            output = URLEncoder.encode(input, CHAR_SET);
        } catch (UnsupportedEncodingException e) {
            //do nothing
        }
        return output;
    }

    public static String[] split(String string, String regularExpression) {
        if (isEmpty(string))
            return EMPTY_ARRAY;
        return string.split(regularExpression);
    }
}
