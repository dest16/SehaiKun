package com.destin.sehaikun;

import android.util.SparseArray;

/**
 * Created by dest1 on 2015/11/13.
 */
public class Sparses {
    public static String[] values(SparseArray<String> array) {
        if (array.size() == 0)
            return StringUtils.EMPTY_ARRAY;
        String[] values = new String[array.size()];
        for (int i = 0; i < array.size(); i++) {
            values[i] = array.valueAt(i);
        }
        return values;
    }
}
