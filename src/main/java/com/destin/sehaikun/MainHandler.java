package com.destin.sehaikun;

import android.os.Handler;
import android.os.Looper;


public class MainHandler {
    private static Handler sHandler;

    private MainHandler() {
    }

    public static Handler getInstance() {
        if (sHandler == null) {
            sHandler = new Handler(Looper.getMainLooper());
        }
        return sHandler;
    }
}
